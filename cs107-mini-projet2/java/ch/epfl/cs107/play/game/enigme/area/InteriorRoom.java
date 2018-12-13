package ch.epfl.cs107.play.game.enigme.area;


import ch.epfl.cs107.play.game.enigme.actor.Door;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;

public class InteriorRoom extends EnigmeArea {
	public InteriorRoom() {
		super("Enigme2");
	}
	
	@Override
	public boolean begin(Window window, FileSystem fileSystem) {
		boolean supSuccess = super.begin(window, fileSystem);
		Door interiorDoor = new Door(this, "Enigme1", new DiscreteCoordinates(6,31), new DiscreteCoordinates(7,0));
		registerActor(interiorDoor);
		return supSuccess;
	}
	
}