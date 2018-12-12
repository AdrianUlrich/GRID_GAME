package ch.epfl.cs107.play.game.enigme.actor;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Interactor;
import ch.epfl.cs107.play.game.areagame.actor.MovableAreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.handler.EnigmeInteractionVisitor;

public class EnigmePlayer extends MovableAreaEntity implements Interactor {
	
	private Door passedDoor;
	private boolean isPassingDoor;
	private final static int ANIMATION_DURATION = 8;
	private final Sprite sprite;
	private boolean wantsViewInteraction;
	private final EnigmePlayerHandler handler;
	
	public EnigmePlayer(Area area, Orientation orientation, DiscreteCoordinates position) {
		super(area, orientation, position);
		isPassingDoor = false;
		sprite = new Sprite("ghost.1", 1, 1.f, this);
		wantsViewInteraction = false;
		handler = new EnigmePlayerHandler();
	}
	
	public EnigmePlayer(Area area, DiscreteCoordinates position) {
		this(area, Orientation.DOWN, position);
	}
	
	public void enterArea(Area area, DiscreteCoordinates position) {
		setOwnerArea(area);
		getOwnerArea().registerActor(this);
		setCurrentPosition(new Vector(position.x, position.y));
//		update(ANIMATION_DURATION);
		resetMotion();
	}
	
	public void enterArea(Area area) {
		enterArea(area, passedDoor.goesToCoord());
	}
	
	public void leaveArea() {
		getOwnerArea().unregisterActor(this);
	}
	
	public void isPassingDoor(boolean isPassingDoor) {
		this.isPassingDoor = isPassingDoor;
	}
	
	void setIsPassingDoor(Door door) {
		isPassingDoor = true;
		passedDoor = door;
	}
	
	public boolean isPassingDoor() {
		return isPassingDoor;
	}
	
	public Door passedDoor() {
		return passedDoor;
	}
	
	public Vector getOrientationVector() {
		return getOrientation().toVector();
	}
	
	@Override
	public List<DiscreteCoordinates> getCurrentCells() {
		return Collections.singletonList(getCurrentMainCellCoordinates());
	}
	
	@Override
	public boolean takeCellSpace() {
		// Does take space
		return true;
	}
	
	@Override
	public boolean isViewInteractable() {
		// Can be interacted with at distance
		return true;
	}
	
	@Override
	public boolean isCellInteractable() {
		// Can be interacted with by contact
		return true;
	}
	
	@Override
	public void draw(Canvas canvas) {
		sprite.draw(canvas);
	}
	
	@Override
	public List<DiscreteCoordinates> getFieldOfViewCells() {
		LinkedList<DiscreteCoordinates> fieldOfView = new LinkedList<>();
		Vector orientation = getOrientation().toVector();
		for (DiscreteCoordinates coords : getCurrentCells()) {
			Vector viewPart = coords.toVector().add(orientation);
			fieldOfView.add(new DiscreteCoordinates((int) viewPart.x, (int) viewPart.y));
		}
//	if (getOrientation() == Orientation.DOWN) {
//	    for (DiscreteCoordinates coords : getCurrentCells()) {
//		fieldOfView.add(new DiscreteCoordinates(coords.x, coords.y - 1));
//	    }
//	}
//	if (getOrientation() == Orientation.UP) {
//	    for (DiscreteCoordinates coords : getCurrentCells()) {
//		fieldOfView.add(new DiscreteCoordinates(coords.x, coords.y + 1));
//	    }
//	}
//	if (getOrientation() == Orientation.LEFT) {
//	    for (DiscreteCoordinates coords : getCurrentCells()) {
//		fieldOfView.add(new DiscreteCoordinates(coords.x - 1, coords.y));
//	    }
//	}
//	if (getOrientation() == Orientation.RIGHT) {
//	    for (DiscreteCoordinates coords : getCurrentCells()) {
//		fieldOfView.add(new DiscreteCoordinates(coords.x + 1, coords.y));
//	    }
//	}
		return fieldOfView;
	}
	
	@Override
	public boolean wantsCellInteraction() {
		return true;
	}
	
	@Override
	public boolean wantsViewInteraction() {
		return wantsViewInteraction;
	}
	
	@Override
	public void setWantsViewInteraction(boolean b) {
		wantsViewInteraction = b;
	}
	
	@Override
	public void interactWith(Interactable other) {
		other.acceptInteraction(handler);
	}
	
	@Override
	public void acceptInteraction(AreaInteractionVisitor v) {
		((EnigmeInteractionVisitor) v).interactWith(this);
	}
	
	@Override
	public void update(float deltaTime) {
		Keyboard keyboard = getOwnerArea().getKeyboard();
		Button leftArrow = keyboard.get(Keyboard.LEFT);
		Button rightArrow = keyboard.get(Keyboard.RIGHT);
		Button downArrow = keyboard.get(Keyboard.DOWN);
		Button upArrow = keyboard.get(Keyboard.UP);
		Button lKey = keyboard.get(Keyboard.L);
		
		int factor = (keyboard.get(Keyboard.SPACE).isDown()?2:1);
		
		if (leftArrow.isDown()) {
			if (getOrientation() == Orientation.LEFT) {
				move(ANIMATION_DURATION/factor);
			} else {
				setOrientation(Orientation.LEFT);
			}
		}
		
		if (rightArrow.isDown()) {
			if (getOrientation() == Orientation.RIGHT) {
				move(ANIMATION_DURATION/factor);
			} else {
				setOrientation(Orientation.RIGHT);
			}
		}
		
		if (downArrow.isDown()) {
			if (getOrientation() == Orientation.DOWN) {
				move(ANIMATION_DURATION/factor);
			} else {
				setOrientation(Orientation.DOWN);
			}
		}
		
		if (upArrow.isDown()) {
			if (getOrientation() == Orientation.UP) {
				move(ANIMATION_DURATION/factor);
			} else {
				setOrientation(Orientation.UP);
			}
		}
		
		wantsViewInteraction = lKey.isDown();
		
		super.update(deltaTime);
	}
	
	class EnigmePlayerHandler implements EnigmeInteractionVisitor {
		@Override
		public void interactWith(Door door) {
			setIsPassingDoor(door);
//			System.out.println(door+" "+isMoving());
		}
		
		@Override
		public void interactWith(Collectable collectable) {
			getOwnerArea().unregisterActor(collectable);
			collectable.setIsCollected(true);
		}
		
		@Override
		public void interactWith(SwitchableEntity switchableEntity) {
			switchableEntity.switchState();
		}
	}
	
}
