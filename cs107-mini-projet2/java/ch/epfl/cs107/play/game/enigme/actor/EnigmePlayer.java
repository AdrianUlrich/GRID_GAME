package ch.epfl.cs107.play.game.enigme.actor;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import ch.epfl.cs107.play.game.actor.Animation;
import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Interactor;
import ch.epfl.cs107.play.game.areagame.actor.MovableAreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.RegionOfInterest;
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
	private int animationTime;
	private final Map<Orientation, Animation> animations;
	private boolean wantsViewInteraction;
	private final EnigmePlayerHandler handler;
	private boolean canRun;

	public EnigmePlayer(Area area, Orientation orientation, DiscreteCoordinates position) {
		super(area, orientation, position);
		isPassingDoor = false;
		animationTime = 0;
		animations = new HashMap<>();
		Vector anchor = new Vector(0.25f, 0.32f);

		Animation[] tempAnimationArray = new Animation[4];

		for (int j = 0; j < 4; ++j) {
			Sprite[] tempSpriteArray = new Sprite[4];
			for (int i = 0; i < 4; ++i) {
				tempSpriteArray[i] = new Sprite("max.new.1", 1.2f*0.5f, 1.2f*0.65625f, this,
						new RegionOfInterest(j*16, i * 21, 16, 21), anchor);
			}
			Animation tempAnimation = new Animation(tempSpriteArray);
			tempAnimationArray[j] = tempAnimation;
		}
		animations.put(Orientation.DOWN, tempAnimationArray[0]);
		animations.put(Orientation.LEFT, tempAnimationArray[1]);
		animations.put(Orientation.UP, tempAnimationArray[2]);
		animations.put(Orientation.RIGHT, tempAnimationArray[3]);
		wantsViewInteraction = false;
		handler = new EnigmePlayerHandler();
		canRun = false;
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
		animations.get(getOrientation()).draw(canvas);
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
		Button leftArrow = keyboard.get(Keyboard.A);
		Button rightArrow = keyboard.get(Keyboard.D);
		Button downArrow = keyboard.get(Keyboard.S);
		Button upArrow = keyboard.get(Keyboard.W);
		Button lKey = keyboard.get(Keyboard.L);

		//allows to move double speed if the character is running.
		int factor = (keyboard.get(Keyboard.SPACE).isDown() && canRun ? 2 : 1);

		if (leftArrow.isDown()) {
			if (getOrientation() == Orientation.LEFT) {
				move(ANIMATION_DURATION / factor);
			} else {
				setOrientation(Orientation.LEFT);
			}
		}

		if (rightArrow.isDown()) {
			if (getOrientation() == Orientation.RIGHT) {
				move(ANIMATION_DURATION / factor);
			} else {
				setOrientation(Orientation.RIGHT);
			}
		}

		if (downArrow.isDown()) {
			if (getOrientation() == Orientation.DOWN) {
				move(ANIMATION_DURATION / factor);
			} else {
				setOrientation(Orientation.DOWN);
			}
		}

		if (upArrow.isDown()) {
			if (getOrientation() == Orientation.UP) {
				move(ANIMATION_DURATION / factor);
			} else {
				setOrientation(Orientation.UP);
			}
		}

		if(isMoving()) {
			if (animationTime%3 == 0) animations.get(getOrientation()).incrementAnimation();
			animationTime++;
		} else {
			animations.get(getOrientation()).resetAnimation();
			animationTime = 0;
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

		@Override
		public void interactWith(RunningShoes runningShoes) {
			getOwnerArea().unregisterActor(runningShoes);
			runningShoes.setIsCollected(true);
			canRun = true;
		}
	}

}
