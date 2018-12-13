package ch.epfl.cs107.play.game.areagame.actor;

import java.util.LinkedList;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.AreaBehavior;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;

/**
 * MovableAreaEntity are AreaEntity able to move on a grid
 */
public abstract class MovableAreaEntity extends AreaEntity {
	
	/// Indicates whether the actor is currently moving
	private boolean isMoving;
	/// Indicates whether it is the actor's first frame on its new cell
	private boolean justArrived;
	/// Indicate how many frames the current move is supposed to take
	private int framesForCurrentMove;
	/// The target cell (i.e. where the mainCell will be after the motion)
	private DiscreteCoordinates targetMainCellCoordinates;
	
	/**
	 * Default MovableAreaEntity constructor
	 *
	 * @param area        (Area): Owner area. Not null
	 * @param position    (Coordinate): Initial position of the entity. Not null
	 * @param orientation (Orientation): Initial orientation of the entity. Not null
	 */
	public MovableAreaEntity(Area area, Orientation orientation, DiscreteCoordinates position) {
		super(area, orientation, position);
		resetMotion();
	}
	
	/**
	 * Initialize or reset the current motion information
	 */
	protected void resetMotion() {
		isMoving = false;
		framesForCurrentMove = 0;
		targetMainCellCoordinates = getCurrentMainCellCoordinates();
	}
	
	/**
	 * @param frameForMove (int): number of frames used for simulating motion
	 * @return (boolean): returns true if motion can occur
	 */
	
	protected boolean move(int framesForMove) {
		List<DiscreteCoordinates> leavingCells = getCurrentCells();
		if (!isMoving || leavingCells.get(0) != targetMainCellCoordinates) {
			List<DiscreteCoordinates> enteringCells = getEnteringCells();
//			System.out.println(getEnteringCells());
			if (getOwnerArea().enterAreaCells(this, enteringCells)
					&& getOwnerArea().leaveAreaCells(this, leavingCells)) {
				framesForCurrentMove = framesForMove;
				if (framesForCurrentMove < 1) {
					framesForCurrentMove = 1;
				}
				Vector orientation = getOrientation().toVector();
				targetMainCellCoordinates = getCurrentMainCellCoordinates().jump(orientation);
				isMoving = true;
				return true;
			}
		}
		return false;
	}
	
	/// MovableAreaEntity implements Actor
	
	@Override
	public void update(float deltaTime) {
		if (isMoving) {
			if (!getCurrentCells().get(0).equals(targetMainCellCoordinates)) {
				Vector distance = getOrientation().toVector();
				distance = distance.mul(1.0f / framesForCurrentMove);
				setCurrentPosition(getPosition().add(distance));
			} else {
				justArrived = true;
				resetMotion();
			}
		} else {
			justArrived = false;
		}
	}
	
	/// Implements Positionable
	
	@Override
	public Vector getVelocity() {
		return getOrientation().toVector()/*div may not be necessary*/.div(framesForCurrentMove);
	}
	
	/**
	 * @return whether this isMoving
	 */
	public boolean isMoving() {
		return isMoving;
	}
	
	public boolean isJustArrived() {
		return justArrived;
	}
	
	protected final List<DiscreteCoordinates> getLeavingCells() {
		return getCurrentCells();
	}
	
	protected final List<DiscreteCoordinates> getEnteringCells() {
		List<DiscreteCoordinates> investedCells = new LinkedList<>();
		for (DiscreteCoordinates coordinates : getCurrentCells()) {
			DiscreteCoordinates coord = coordinates.jump(getOrientation().toVector());
			investedCells.add(coord);
		}
		return investedCells;
	}
	
	@Override
	protected void setOrientation(Orientation orientation) {
		if (!isMoving)
			super.setOrientation(orientation);
	}
	
}
