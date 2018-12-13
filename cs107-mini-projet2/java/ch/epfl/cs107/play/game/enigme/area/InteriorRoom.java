package ch.epfl.cs107.play.game.enigme.area;


import ch.epfl.cs107.play.game.enigme.actor.Dialog;
import ch.epfl.cs107.play.game.enigme.actor.Door;
import ch.epfl.cs107.play.game.enigme.actor.Talkable;
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
		Talkable pnj = new Talkable(this, new DiscreteCoordinates(7,5), new Dialog("Congratulation for beating the game! Enjoy your retirement.","dialog.1",this), "cup.1");
		registerActor(pnj);
		registerActor(interiorDoor);
		return supSuccess;
	}
	
}