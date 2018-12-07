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

	/// Indicate if the actor is currently moving
	private boolean isMoving;
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
	 * 
	 * @param frameForMove (int): number of frames used for simulating motion
	 * @return (boolean): returns true if motion can occur
	 */

	protected boolean move(int framesForMove) {
		if (isMoving == false || getCurrentCells().get(0) != targetMainCellCoordinates) {
			if (getOwnerArea().enterAreaCells(this, getEnteringCells())
					&& getOwnerArea().leaveAreaCells(this, getLeavingCells())) {
//				boolean canLeaveCell = true;
//				boolean canEnterCell = true;
//				if (canLeaveCell == false || canEnterCell == false) {
//					return false;
//				} else {
					framesForCurrentMove = framesForMove;
					if (framesForCurrentMove < 1) {
						framesForCurrentMove = 1;
					}
					Vector orientation = getOrientation().toVector();
					targetMainCellCoordinates = getCurrentMainCellCoordinates().jump(orientation);
					isMoving = true;
					return true;
					// TODO add area conditions here
//				}
			}
		}
		return false;
	}

	/// MovableAreaEntity implements Actor

	@Override
	public void update(float deltaTime) {
		if (isMoving == true && getCurrentCells().get(0) != targetMainCellCoordinates) {
			Vector distance = getOrientation().toVector();
			distance = distance.mul(1.0f / framesForCurrentMove);
			setCurrentPosition(getPosition().add(distance));
		} else {
			resetMotion();
		}

	}

	/// Implements Positionable

	@Override
	public Vector getVelocity() {
		// TODO implements me #PROJECT #TUTO
		// the velocity must be computed as the orientation vector
		// (getOrientation().toVector() mutiplied by
		// framesForCurrentMove
		return null;
	}

	protected final List<DiscreteCoordinates> getLeavingCells() {
		List<DiscreteCoordinates> leavingCells = new LinkedList<DiscreteCoordinates>();
		leavingCells = getCurrentCells();
		return leavingCells;
	}

	protected final List<DiscreteCoordinates> getEnteringCells() {
		List<DiscreteCoordinates> investedCells = new LinkedList<DiscreteCoordinates>();
		List<DiscreteCoordinates> currentCells = new LinkedList<DiscreteCoordinates>();
		currentCells = getCurrentCells();
		for (DiscreteCoordinates coordinates : currentCells) {
			DiscreteCoordinates coordToCheck = coordinates.jump(getOrientation().toVector());
			if (coordToCheck.x > 0 && coordToCheck.y > 0 && coordToCheck.x < getOwnerArea().getWidth()
					&& coordToCheck.y < getOwnerArea().getHeight()) {
				investedCells.add(coordinates);
			}
		}
		return investedCells;
	}

	@Override
	protected void setOrientation(Orientation orientation) {
	    if (!isMoving)
	    super.setOrientation(orientation);
	}
	
	
}
