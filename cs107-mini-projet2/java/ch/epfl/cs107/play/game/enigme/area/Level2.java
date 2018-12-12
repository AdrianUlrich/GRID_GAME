package ch.epfl.cs107.play.game.enigme.area;

import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.enigme.actor.Apple;
import ch.epfl.cs107.play.game.enigme.actor.Door;
import ch.epfl.cs107.play.game.enigme.actor.ImmovableRock;
import ch.epfl.cs107.play.game.enigme.actor.RunningShoes;
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
		Door door = new Door(this, "LevelSelector", new DiscreteCoordinates(2, 6),
				new DiscreteCoordinates(5, 0));
		registerActor(door);
		Apple apple = new Apple(this, new DiscreteCoordinates(5, 6));
		RunningShoes runningShoes = new RunningShoes(this, new DiscreteCoordinates(4,6));
		ImmovableRock rock = new ImmovableRock(this, new DiscreteCoordinates(2,6));
		registerActor(runningShoes);
		registerActor(apple);
		registerActor(rock);
		return supSuccess;
	}
	
}