package ch.epfl.cs107.play.game.areagame;

import ch.epfl.cs107.play.game.Playable;
import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Keyboard;
import ch.epfl.cs107.play.window.Window;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Area is a "Part" of the AreaGame. It is characterized by its AreaBehavior and
 * a List of Actors. Area implements Playable
 */
public abstract class Area implements Playable {

	// Context objects
	private Window window;
	private FileSystem fileSystem;

	/// List of Actors inside the area
	private List<Actor> actors;
	private List<Actor> registeredActors;
	private List<Actor> unregisteredActors;

	private Map<Interactable, List<DiscreteCoordinates>> interactablesToEnter;
	private Map<Interactable, List<DiscreteCoordinates>> interactablesToLeave;

	// Camera Parameter
	// actor on which the view is centered
	private Actor viewCandidate;
	// effective center of the view
	private Vector viewCenter;

	/// The behavior Map
	private AreaBehavior areaBehavior;

	private boolean wasVisited = false;

	// TODO implements me #PROJECT #TUTO
	
	@Override
	public boolean begin(Window window, FileSystem fileSystem) {
		// TODO implements me #PROJECT #TUTO
		this.actors = new LinkedList<>();
		this.registeredActors = new LinkedList<>();
		this.unregisteredActors = new LinkedList<>();
		this.interactablesToEnter = new HashMap<>();
		this.interactablesToLeave = new HashMap<>();
		this.fileSystem = fileSystem;
		this.window = window;
		wasVisited = true;
		viewCenter = Vector.ZERO;
		viewCandidate = null;
		return true;
	}
	
	/**
	 * @return (float): camera scale factor, assume it is the same in x and y
	 *         direction
	 */
	public abstract float getCameraScaleFactor();

	final protected void setBehavior(AreaBehavior ab) {
		this.areaBehavior = ab;
	}

	public AreaBehavior getAreaBehavior() {
		return areaBehavior;
	}

	public final boolean leaveAreaCells(Interactable entity, List<DiscreteCoordinates> coordinates) {
		if (areaBehavior.canLeave(entity, coordinates)) {
			interactablesToLeave.put(entity, coordinates);
			return true;
		}
		return false;
	}

	public final boolean enterAreaCells(Interactable entity, List<DiscreteCoordinates> coordinates) {
		if (areaBehavior.canEnter(entity, coordinates)) {
			interactablesToEnter.put(entity, coordinates);
			return true;
		}
		return false;
	}

	/**
	 * Add an actor to the actors list
	 * 
	 * @param a      (Actor): the actor to add, not null
	 * @param forced (Boolean): if true, the method ends
	 */
	private void addActor(Actor a, boolean forced) {
		// Here decisions at the area level to decide if an actor
		// must be added or not
		boolean errorOccured = !actors.add(a);
		if (a instanceof Interactable) {
			errorOccured = errorOccured || !enterAreaCells(((Interactable) a), ((Interactable) a).getCurrentCells());
		}
		if (errorOccured && !forced) {
			System.out.println("Actor " + a + " cannot be completely added , so removed it from where it was");
			removeActor(a, true);
		}

	}

	/**
	 * Remove an actor form the actor list
	 * 
	 * @param a      (Actor): the actor to remove, not null
	 * @param forced (Boolean): if true, the method ends
	 */
	private void removeActor(Actor a, boolean forced) {
		boolean errorOccured = !actors.remove(a);
		if (a instanceof Interactable) {
			errorOccured = errorOccured || !leaveAreaCells(((Interactable) a), ((Interactable) a).getCurrentCells());
		}
		if (errorOccured && !forced) {
			System.out.println("Actor " + a + " cannot be completely removed , so added it where it was");
			addActor(a, true);
		}
	}

	/**
	 * Register an actor : will be added at next update
	 * 
	 * @param a (Actor): the actor to register, not null
	 * @return (boolean): true if the actor is correctly registered
	 */
	public final boolean registerActor(Actor a) {
		// TODO implements me #PROJECT #TUTO
		registeredActors.add(a);
		return false;
	}

	/**
	 * Unregister an actor : will be removed at next update
	 * 
	 * @param a (Actor): the actor to unregister, not null
	 * @return (boolean): true if the actor is correctly unregistered
	 */
	public final boolean unregisterActor(Actor a) {
		// TODO implements me #PROJECT #TUTO
		unregisteredActors.add(a);
		return false;
	}

	/**
	 * Getter for the area width
	 * 
	 * @return (int) : the width in number of cols
	 */
	public final int getWidth() {
		// TODO implements me #PROJECT #TUTO
		return areaBehavior.getWidth();
	}

	/**
	 * Getter for the area height
	 * 
	 * @return (int) : the height in number of rows
	 */
	public final int getHeight() {
		// TODO implements me #PROJECT #TUTO
		return areaBehavior.getHeight();
	}

	/** @return the Window Keyboard for inputs */
	public final Keyboard getKeyboard() {
		// TODO implements me #PROJECT #TUTO
		return null;
	}

	public final boolean getVisited() {
		return wasVisited;
	}

	/**
	 * Resume method: Can be overridden
	 * 
	 * @param window     (Window): display context, not null
	 * @param fileSystem (FileSystem): given file system, not null
	 * @return (boolean) : if the resume succeed, true by default
	 */
	public boolean resume(Window window, FileSystem fileSystem) {
		return true;
	}

	@Override
	public void update(float deltaTime) {
		// TODO implements me #PROJECT #TUTO
		updateCamera();
		purgeRegistration();
		for (Actor actor : actors) {
			actor.update(deltaTime);
			actor.draw(window);
		}
	}

	private final void purgeRegistration() {
		// add newly registered actors to the actors list
		for (Actor actor : registeredActors) {
			addActor(actor, false);
		}
		// remove unwanted actors from the actor list
		for (Actor actor : unregisteredActors) {
			removeActor(actor, false);
		}

		for (Interactable i : interactablesToEnter.keySet()) {
			areaBehavior.enter(i, interactablesToEnter.get(i));
			;
		}

		for (Interactable i : interactablesToLeave.keySet()) {
			areaBehavior.enter(i, interactablesToLeave.get(i));
			;
		}
		// once updated actors, clears lists
		registeredActors.clear();
		unregisteredActors.clear();
		interactablesToEnter.clear();
		interactablesToLeave.clear();
	}

	private void updateCamera() {
		// TODO implements me #PROJECT #TUTO
		if (viewCandidate != null) {
			viewCenter = viewCandidate.getPosition();
		}
		// Compute new viewport
		// TODO find the right scale factor
		Transform viewTransform = Transform.I.scaled(getCameraScaleFactor()).translated(viewCenter);
		window.setRelativeTransform(viewTransform);
	}

	public final void setViewCandidate(Actor a) {
		this.viewCandidate = a;
	}

	/**
	 * Suspend method: Can be overridden, called before resume other
	 */
	public void suspend() {
		// Do nothing by default
		purgeRegistration();
	}

	@Override
	public void end() {
		// TODO save the AreaState somewhere
	}

	public Window getWindow() {
		return window;
	}
}
