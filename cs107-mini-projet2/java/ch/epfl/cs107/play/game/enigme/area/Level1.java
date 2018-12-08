package ch.epfl.cs107.play.game.enigme.area;

import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.enigme.actor.Door;
import ch.epfl.cs107.play.game.enigme.actor.demo2.Demo2Player;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public class Level1 extends EnigmeArea {

	public Level1() {
		super("Level1");
		Door door = new Door(this, "Level Selector", new DiscreteCoordinates(1, 6), Orientation.DOWN,
				new DiscreteCoordinates(5, 0));
		registerActor(door);
	}


	@Override
	public DiscreteCoordinates getEntrance() {
		return new DiscreteCoordinates(5, 2);
	}

}