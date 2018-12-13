package ch.epfl.cs107.play.game.enigme.area;

import ch.epfl.cs107.play.game.enigme.actor.*;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;

public class Level1 extends EnigmeArea {
	public Level1() {
		super("Level1");
	}

	@Override
	public boolean begin(Window window, FileSystem fileSystem) {
		boolean supSuccess = super.begin(window, fileSystem);
		Door door = new Door(this, "LevelSelector", new DiscreteCoordinates(1, 6), new DiscreteCoordinates(5, 0));
		registerActor(door);
		return supSuccess;
	}

}