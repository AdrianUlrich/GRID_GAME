package ch.epfl.cs107.play.game.enigme.handler;

import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.EnigmeBehavior;
import ch.epfl.cs107.play.game.enigme.actor.Collectable;
import ch.epfl.cs107.play.game.enigme.actor.Dialog;
import ch.epfl.cs107.play.game.enigme.actor.Door;
import ch.epfl.cs107.play.game.enigme.actor.EnigmePlayer;

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
}
