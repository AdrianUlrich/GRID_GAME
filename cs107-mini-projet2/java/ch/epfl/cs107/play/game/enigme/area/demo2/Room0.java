package ch.epfl.cs107.play.game.enigme.area.demo2;

import ch.epfl.cs107.play.math.DiscreteCoordinates;

public class Room0 extends Demo2Area {

	public Room0() {
		super("LevelSelector");
	}

	@Override
	public DiscreteCoordinates getEntrance() {
	    return new DiscreteCoordinates(5, 5);
	}
	
}
