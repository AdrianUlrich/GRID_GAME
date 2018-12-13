package ch.epfl.cs107.play.game.enigme.area;


import ch.epfl.cs107.play.game.enigme.actor.SignalDoor;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
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
				case 3:
					destination = "Level3";
					break;
				case 4:
					destination = "Enigme1";
					destinationCoords = new DiscreteCoordinates(16,0);
					break;
				default : 
					destination = null;
					destinationCoords = null;
					break;
				}
			SignalDoor lockedDoor = new SignalDoor((destination!=null?Logic.TRUE:Logic.FALSE), this, destination, destinationCoords, new DiscreteCoordinates(i,7), new DiscreteCoordinates[0]);
			registerActor(lockedDoor);

		}
		return supSuccess;
	}
}