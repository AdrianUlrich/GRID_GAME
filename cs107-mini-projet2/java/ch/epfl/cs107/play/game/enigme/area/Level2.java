package ch.epfl.cs107.play.game.enigme.area;

import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.enigme.actor.Apple;
import ch.epfl.cs107.play.game.enigme.actor.Door;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;

public class Level2 extends EnigmeArea {
	
	public Level2() {
		super("Level2");
	}
	
	@Override
	public boolean begin(Window window, FileSystem fileSystem) {
		boolean supSuccess = super.begin(window, fileSystem);
		Door door = new Door(this, "LevelSelector", new DiscreteCoordinates(2, 6), Orientation.DOWN,
				new DiscreteCoordinates(5, 0));
		registerActor(door);
		Apple apple = new Apple(this, Orientation.DOWN, new DiscreteCoordinates(5, 6));
		registerActor(apple);
		return supSuccess;
	}
	
}