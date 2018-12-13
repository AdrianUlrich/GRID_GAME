package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;

import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

public abstract class Collectable extends AreaEntity {

	private boolean isCollected;

	/**
	 * A Collectable is simply an entity which can be collected.
	 * 
	 * @param area        : The area in which the entity exists.
	 * @param orientation : The orientation of the Collectable.
	 * @param position    : The position of the Collectable in the area.
	 */
	public Collectable(Area area, Orientation orientation, DiscreteCoordinates position) {
		super(area, orientation, position);
		isCollected = false;
	}

	@Override
	public boolean takeCellSpace() {
		return true;
	}

	@Override
	public boolean isViewInteractable() {
		return true;
	}

	@Override
	public boolean isCellInteractable() {
		return false;
	}

	@Override
	public void draw(Canvas canvas) {
	}

	public void setIsCollected(boolean collected) {
		isCollected = collected;
	}

	protected boolean isCollected() {
		return isCollected;
	}
}
