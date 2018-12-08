package ch.epfl.cs107.play.game.enigme.actor;

import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.MovableAreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;

public class EnigmePlayer extends MovableAreaEntity {
	
	private Door passedDoor;
	private boolean isPassingDoor;
	private final static int ANIMATION_DURATION = 8;
	private final Sprite sprite;

	public EnigmePlayer(Area area, Orientation orientation, DiscreteCoordinates position) {
		super(area, orientation, position);
		isPassingDoor = false;
		sprite = new Sprite("ghost.1", 1, 1.f, this);
	}

	public EnigmePlayer(Area area, DiscreteCoordinates position) {
		this(area, Orientation.DOWN, position);
	}

	public void enterArea(Area area, DiscreteCoordinates position) {
		setOwnerArea(area);
		getOwnerArea().registerActor(this);
		setCurrentPosition(new Vector(position.x, position.y));
//	update(ANIMATION_DURATION);
		resetMotion();
	}

	public void enterArea(Area area) {
		enterArea(area, area.getEntrance());
	}

	public void leaveArea() {
		getOwnerArea().unregisterActor(this);
	}

	public boolean isPassingDoor(boolean isPassingDoor) {
		return this.isPassingDoor = isPassingDoor;
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
		sprite.draw(getOwnerArea().getWindow());
	}

	@Override
	public void update(float deltaTime) {
		Keyboard keyboard = getOwnerArea().getWindow().getKeyboard();
		Button leftArrow = keyboard.get(Keyboard.LEFT);
		Button rightArrow = keyboard.get(Keyboard.RIGHT);
		Button downArrow = keyboard.get(Keyboard.DOWN);
		Button upArrow = keyboard.get(Keyboard.UP);

		if (leftArrow.isDown()) {
			if (getOrientation() == Orientation.LEFT) {
				move(ANIMATION_DURATION);
			} else {
				setOrientation(Orientation.LEFT);
			}
		}

		if (rightArrow.isDown()) {
			if (getOrientation() == Orientation.RIGHT) {
				move(ANIMATION_DURATION);
			} else {
				setOrientation(Orientation.RIGHT);
			}
		}

		if (downArrow.isDown()) {
			if (getOrientation() == Orientation.DOWN) {
				move(ANIMATION_DURATION);
			} else {
				setOrientation(Orientation.DOWN);
			}
		}

		if (upArrow.isDown()) {
			if (getOrientation() == Orientation.UP) {
				move(ANIMATION_DURATION);
			} else {
				setOrientation(Orientation.UP);
			}
		}
		super.update(deltaTime);
	}
}
