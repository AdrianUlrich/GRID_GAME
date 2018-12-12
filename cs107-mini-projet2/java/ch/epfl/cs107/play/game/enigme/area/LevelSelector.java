package ch.epfl.cs107.play.game.enigme.area;

import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.enigme.actor.Door;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;

public class LevelSelector extends EnigmeArea {
	
	public LevelSelector() {
		super("LevelSelector");
	}
	
	@Override
	public boolean begin(Window window, FileSystem fileSystem) {
		boolean supSuccess = super.begin(window, fileSystem);
		for (int i = 1; i <= 8; ++i) {
			String destination;
			DiscreteCoordinates destinationCoords = new DiscreteCoordinates(5, 1);
			switch (i) {
				case 1:
					destination = "Level1";
					break;
				case 2:
					destination = "Level2";
					break;
//				case 3:
//					destination += "Level3";
//					break;
				default: {
					destination = "LevelSelector";
					destinationCoords = new DiscreteCoordinates(i, 7);
				}
				break;
			}
			Door door = new Door(this, destination, destinationCoords, Orientation.DOWN, new DiscreteCoordinates(i, 7));
			registerActor(door);
		}
		return supSuccess;
	}
}