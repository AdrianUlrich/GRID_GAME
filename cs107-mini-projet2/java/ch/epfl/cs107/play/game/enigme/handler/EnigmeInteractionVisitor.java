package ch.epfl.cs107.play.game.enigme.handler;

import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.EnigmeBehavior;
import ch.epfl.cs107.play.game.enigme.actor.*;

public interface EnigmeInteractionVisitor extends AreaInteractionVisitor {
	/**
	 * Simulates an interaction between Interactors and Apple in enigme
	 *
	 * @param apple (Apple), not null
	 */
	default void interactWith(Collectable collectable) {
		// by default the interaction is empty
	}
	
	default void interactWith(EnigmeBehavior.EnigmeCell cell) {
		// by default the interaction is empty
	}
	
	default void interactWith(Door door) {
		// by default the interaction is empty
	}
	
	default void interactWith(EnigmePlayer player) {
		// by default the interaction is empty
	}
	
	default void interactWith(Dialog dialog) {
		// by default the interaction is empty
	}
	
	default void interactWith(LogicEntity logicEntity) {
		// by default the interaction is empty
	}
	
	default void interactWith(Switchable switchable) {
		// by default the interaction is empty
	}
	
	default void interactWith(PressurePlate pressurePlate) {
		// Any interactor triggers the pressure plate
		pressurePlate.On();
	}
	
	default void interactWith(RunningShoes runningShoes) {
		// by default the interaction is empty
	}
}
