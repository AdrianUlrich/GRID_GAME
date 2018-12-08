package ch.epfl.cs107.play.game.enigme.area;

import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.enigme.actor.Door;
import ch.epfl.cs107.play.math.DiscreteCoordinates;


public class LevelSelector extends EnigmeArea {

	public LevelSelector() {
		super("LevelSelector");
		for (int i = 2; i < 9; ++i) {
			String destination;
			if (i == 1) {
				destination = "Level1";
			}
			if (i == 2) {
				destination = "Level2";
			} else {
				destination = "";
			}
			Door door = new Door(this, destination, new DiscreteCoordinates(5, 1), Orientation.DOWN,
					new DiscreteCoordinates(i, 7));
			registerActor(door);
		}
	}

	@Override
	public DiscreteCoordinates getEntrance() {
	    return new DiscreteCoordinates(5, 5);
	}
	
}