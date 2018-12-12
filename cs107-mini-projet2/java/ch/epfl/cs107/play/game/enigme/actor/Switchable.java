package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.signal.logic.Logic;

/**
 * Basic interface for a Logic class that can switch its state
 * @see Logic
 * */
public interface Switchable extends Logic {
	
	/**
	 * Switches the state of the Logic class
	 *
	 * @return (boolean)    The new state
	 * */
	boolean switchState();
}
