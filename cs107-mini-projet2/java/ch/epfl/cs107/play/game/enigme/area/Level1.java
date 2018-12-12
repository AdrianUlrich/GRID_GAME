package ch.epfl.cs107.play.game.enigme.area;

import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.enigme.actor.Door;
import ch.epfl.cs107.play.game.enigme.actor.Key;
import ch.epfl.cs107.play.game.enigme.actor.Portal;
import ch.epfl.cs107.play.game.enigme.actor.Torch;
import ch.epfl.cs107.play.game.enigme.actor.demo2.Demo2Player;
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
		Door door = new Door(this, "LevelSelector", new DiscreteCoordinates(1, 6),
				new DiscreteCoordinates(5, 0));
		Portal portal1 = new Portal(this, "Level1", new DiscreteCoordinates(5,3), new DiscreteCoordinates(8,8));
		registerActor(new Key(this,new DiscreteCoordinates(5,5)));
		registerActor(new Torch(this,new DiscreteCoordinates(5,6)));
		registerActor(door);
		registerActor(portal1);
		return supSuccess;
	}
	
}