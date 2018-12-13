package ch.epfl.cs107.play.game.areagame;

import ch.epfl.cs107.play.game.Playable;
import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Interactor;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.io.ResourcePath;
import ch.epfl.cs107.play.game.enigme.actor.EnigmePlayer;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Keyboard;
import ch.epfl.cs107.play.window.Window;

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
    private List<Interactor> interactors;

    private Map<Interactable, List<DiscreteCoordinates>> interactablesToEnter;
    private Map<Interactable, List<DiscreteCoordinates>> interactablesToLeave;

    // Camera Parameter
    // actor on which the view is centered
    private Actor viewCandidate;
    // effective center of the view
    private Vector viewCenter;

    /// The behavior Map
    private AreaBehavior areaBehavior;

    private boolean wasVisited;


    private boolean isPaused;
    private ImageGraphics pauseGraphics;
    private float DX;
    private float DY;
    private EnigmePlayer.Inventory inventory;
    private boolean inventoryMode;


    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        this.window = window;
        this.fileSystem = fileSystem;
        this.actors = new LinkedList<>();
        this.registeredActors = new LinkedList<>();
        this.unregisteredActors = new LinkedList<>();
        this.interactors = new LinkedList<>();
        this.interactablesToEnter = new HashMap<>();
        this.interactablesToLeave = new HashMap<>();
        viewCandidate = null;
        viewCenter = Vector.ZERO;
        wasVisited = true;
        pauseGraphics = new ImageGraphics(ResourcePath.getSprite("added/pause.1"), 5.f, 5.f, null, Vector.ZERO);
        DX = getCameraScaleFactor() / 2;
        DY = getCameraScaleFactor() / 2;
        return true;
    }

    /**
     * @return (float): camera scale factor, assume it is the same in x and y
     * direction
     */
    public abstract float getCameraScaleFactor();

    public Window getWindow() {
        return window;
    }

    /**
     * @return the Window Keyboard for inputs
     */
    public final Keyboard getKeyboard() {
        return getWindow().getKeyboard();
    }

    protected FileSystem getFileSystem() {
        return fileSystem;
    }

    final protected void setBehavior(AreaBehavior ab) {
        this.areaBehavior = ab;
    }

    public AreaBehavior getAreaBehavior() {
        return areaBehavior;
    }

    /**
     * Getter for the area width
     *
     * @return (int) : the width in number of cols
     */
    public final int getWidth() {
        return areaBehavior.getWidth();
    }

    /**
     * Getter for the area height
     *
     * @return (int) : the height in number of rows
     */
    public final int getHeight() {
        return areaBehavior.getHeight();
    }

    public final boolean wasVisited() {
        return wasVisited;
    }

    public final void setViewCandidate(Actor a) {
        this.viewCandidate = a;
    }

    public final boolean leaveAreaCells(Interactable entity, List<DiscreteCoordinates> coordinates) {
        if (areaBehavior.canLeave(entity, coordinates)) {
            interactablesToLeave.put(entity, coordinates);
            return true;
        } else
            return false;
    }

    public final boolean enterAreaCells(Interactable entity, List<DiscreteCoordinates> coordinates) {
        if (areaBehavior.canEnter(entity, coordinates)) {
            interactablesToEnter.put(entity, coordinates);
            return true;
        } else
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
        boolean errorOccurred = !actors.add(a);
        if (a instanceof Interactable) {
            errorOccurred = errorOccurred || !enterAreaCells(((Interactable) a), ((Interactable) a).getCurrentCells());
        }
        if (a instanceof Interactor) {
            errorOccurred = errorOccurred || !interactors.add((Interactor) a);
        }
        if (errorOccurred && !forced) {
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
        if (a instanceof Interactor) {
            errorOccured = errorOccured || !interactors.remove((Interactor) a);
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
        return registeredActors.add(a);
    }

    /**
     * Unregister an actor : will be removed at next update
     *
     * @param a (Actor): the actor to unregister, not null
     * @return (boolean): true if the actor is correctly unregistered
     */
    public final boolean unregisterActor(Actor a) {
        unregisteredActors.add(a);
        return false;
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
        checkPause();
        updateCamera();
        purgeRegistration();
        for (Actor actor : actors) {
            if (!isPaused)
                actor.update(deltaTime);
            actor.draw(window);
        }
        if (isPaused)
            pauseDraw();
        if (!isPaused)
            for (Interactor interactor : interactors) {
                if (interactor.wantsCellInteraction()) {
                    areaBehavior.cellInteractionOf(interactor);
                }
                if (interactor.wantsViewInteraction()) {
                    areaBehavior.viewInteractionOf(interactor);
                    interactor.setWantsViewInteraction(false);
                }
            }
    }

    private void checkPause() {
        if (getKeyboard().get(Keyboard.K).isPressed())
            if (isPaused)
                unPause();
            else
                pause();
    }

    private void pauseDraw() {
        final Transform transform = Transform.I.translated(window.getPosition().add(-DX, DY - 5));
        pauseGraphics.setRelativeTransform(transform);
        pauseGraphics.draw(window);
        if (inventoryMode)
            inventory.draw(window);
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
        // add newly registered interactables to the actors list
        for (Interactable i : interactablesToEnter.keySet()) {
            areaBehavior.enter(i, interactablesToEnter.get(i));
        }
        // remove unwanted interactables from the actor list
        for (Interactable i : interactablesToLeave.keySet()) {
            areaBehavior.leave(i, interactablesToLeave.get(i));
        }

        // once updated actors, clears lists
        registeredActors.clear();
        unregisteredActors.clear();
        interactablesToEnter.clear();
        interactablesToLeave.clear();
    }

    private void updateCamera() {
        if (viewCandidate != null) {
            viewCenter = viewCandidate.getPosition();
        }
        // Compute new viewport
        Transform viewTransform = Transform.I.scaled(getCameraScaleFactor()).translated(viewCenter);
        window.setRelativeTransform(viewTransform);
    }

    /**
     * Suspend method: Can be overridden, called before resume other
     */
    public void suspend() {
        purgeRegistration();
    }

    @Override
    public void end() {
        // TODO save the AreaState somewhere
    }

    public void pause() {
        suspend();
        isPaused = true;
    }

    public void unPause() {
        resume(window, fileSystem);
        isPaused = false;
        inventoryMode = false;
    }

    public void inventory(EnigmePlayer.Inventory inventory) {
        if (inventory == null)
            return;
        this.inventory = inventory;
        this.inventoryMode = true;
        pause();
    }
}
