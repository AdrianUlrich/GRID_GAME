package ch.epfl.cs107.play.game.enigme.area;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.math.DiscreteCoordinates;


public class LevelSelector extends EnigmeArea {

	public LevelSelector() {
		super("LevelSelector");
	}

	@Override
	public DiscreteCoordinates getEntrance() {
	    return new DiscreteCoordinates(5, 5);
	}
	
}