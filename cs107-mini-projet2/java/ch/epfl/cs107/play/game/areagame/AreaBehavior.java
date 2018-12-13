package ch.epfl.cs107.play.game.areagame;

import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Interactor;
import ch.epfl.cs107.play.game.areagame.io.ResourcePath;

import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Image;
import ch.epfl.cs107.play.window.Window;

import java.util.Set;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * AreaBehavior manages a map of Cells.
 */
public abstract class AreaBehavior {
	
	/// The behavior is an Image of size height x width
	private final Image behaviorMap;
	private final int width, height;
	/// We will convert the image into an array of cells
	private final Cell[][] cells;
	
	/**
	 * Default AreaBehavior Constructor
	 *
	 * @param window   (Window): graphic context, not null
	 * @param fileName (String): name of the file containing the behavior image, not
	 *                 null
	 */
	public AreaBehavior(Window window, String fileName) {
		behaviorMap = window.getImage(ResourcePath.getBehaviors(fileName), null, false);
		width = behaviorMap.getWidth();
		height = behaviorMap.getHeight();
		cells = new Cell[width][height];
	}
	
	/**
	 * Checks wether an entity has permission to leave some cells.
	 * @param entity : The entity to be checked
	 * @param coordinates : The list of coordinates to be checked.
	 * @return (boolean) : Whether the entity is allowed to leave the cells.
	 */
	public boolean canLeave(Interactable entity, List<DiscreteCoordinates> coordinates) {
		for (DiscreteCoordinates coord : coordinates) {
			if (coord.x < 0 || coord.y < 0 || coord.x > width || coord.y > height) {
				return false;
			} else if (!cells[coord.x][coord.y].canLeave(entity)) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Checks wether an entity has permission to enter some cells.
	 * @param entity : The entity to be checked
	 * @param coordinates : The list of coordinates to be checked.
	 * @return (boolean) : Whether the entity is allowed to enter the cells.
	 */
	public boolean canEnter(Interactable entity, List<DiscreteCoordinates> coordinates) {
		for (DiscreteCoordinates coord : coordinates) {
			if (coord.x < 0 || coord.y < 0 || coord.x > width || coord.y > height) {
				return false;
			} else if (!cells[coord.x][coord.y].canEnter(entity)) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Makes an entity leave some cells.
	 * @param entity : the entity to leave some cells.
	 * @param coordinates : the list of coordinates to be left
	 */
	protected void leave(Interactable entity, List<DiscreteCoordinates> coordinates) {
		for (DiscreteCoordinates coord : coordinates) {
			cells[coord.x][coord.y].leave(entity);
		}
	}
	
	/**
	 * Makes an entity enter some cells.
	 * @param entity : the entity to enter some cells.
	 * @param coordinates : the list of coordinates to be entered.
	 */
	protected void enter(Interactable entity, List<DiscreteCoordinates> coordinates) {
		for (DiscreteCoordinates coord : coordinates) {
			cells[coord.x][coord.y].enter(entity);
		}
	}
	
	public final int getWidth() {
		return width;
	}
	
	public final int getHeight() {
		return height;
	}
	
	public Image getBehaviorMap() {
		return behaviorMap;
	}
	
	protected Cell getCell(int x, int y) {
		return cells[x][y];
	}
	
	public void setCell(int x, int y, Cell cell) {
		cells[x][y] = cell;
	}
	
	public Cell[][] getCells() {
		return cells;
	}
	

	public void cellInteractionOf(Interactor interactor) {
		for (DiscreteCoordinates coord : interactor.getCurrentCells()) {
			if (coord.x >= 0 || coord.y >= 0 || coord.x < width || coord.y < height) {
				cells[coord.x][coord.y].cellInteractionOf(interactor);
			}
		}
	}
	
	public void viewInteractionOf(Interactor interactor) {
		for (DiscreteCoordinates coord : interactor.getFieldOfViewCells()) {
			if (coord.x >= 0 || coord.y >= 0 || coord.x < width || coord.y < height) {
				cells[coord.x][coord.y].viewInteractionOf(interactor);
			}
		}
	}
	
	/**
	 * An Area behavior is actually a grid made of cells.
	 */
	public abstract class Cell implements Interactable {
		private DiscreteCoordinates pos;
		private Set<Interactable> interactables;

//	private Set<Interactable> interactableActors;
		
		public Cell(int x, int y) {
			pos = new DiscreteCoordinates(x, y);
			interactables = new HashSet<>();
//	    interactableActors = new HashSet<Interactable>();
		}
		
		private void cellInteractionOf(Interactor interactor) {
			for (Interactable interactable : interactables) {
				if (interactable.isCellInteractable()) {
					interactor.interactWith(interactable);
				}
			}
		}
		
		private void viewInteractionOf(Interactor interactor) {
			for (Interactable interactable : interactables) {
				if (interactable.isViewInteractable()) {
					interactor.interactWith(interactable);
				}
			}
			
		}
		
		public List<DiscreteCoordinates> getCurrentCells() {
			List<DiscreteCoordinates> coordinates = new LinkedList<DiscreteCoordinates>();
			coordinates.add(pos);
			return coordinates;
		}
		
		private void enter(Interactable i) {
			interactables.add(i);
		}
		
		private void leave(Interactable i) {
			interactables.remove(i);
		}
		
		public boolean isOccupied() {
			for (Interactable interactable : interactables) {
				if (interactable.takeCellSpace()) {
					return true;
				}
			}
			return false;
		}
		
		protected abstract boolean canEnter(Interactable entity);
		
		protected abstract boolean canLeave(Interactable entity);
	}
	
}
