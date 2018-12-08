package ch.epfl.cs107.play.game.areagame.actor;

import java.util.List;

import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

import java.util.HashSet;

/**
 * Models objects receptive to interaction (i.e. Interactor can interact with them)
 * @see Interactor
 * This interface makes sense only in the "AreaGame" context with Actor contained into Area Cell
 */
public interface Interactable {
    // TODO implements me #PROJECT #TUTO
	List<DiscreteCoordinates> getCurrentCells();
	
	boolean takeCellSpace();
	
	boolean isViewInteractable();
	
	boolean isCellInteractable();
	
	void acceptInteraction(AreaInteractionVisitor v) ;
}
