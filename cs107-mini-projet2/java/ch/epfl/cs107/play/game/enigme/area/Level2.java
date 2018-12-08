package ch.epfl.cs107.play.game.enigme.area;

import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.enigme.actor.Apple;
import ch.epfl.cs107.play.game.enigme.actor.Door;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public class Level2 extends EnigmeArea{
	
	public Level2(){
		super("Level2");
		Door door = new Door(this, "Level Selector", new DiscreteCoordinates(2, 6), Orientation.DOWN,
				new DiscreteCoordinates(5, 0));
		registerActor(door);
		Apple apple = new Apple(this,Orientation.DOWN,new DiscreteCoordinates(5,6));
		registerActor(apple);
	}

	
	@Override
	public DiscreteCoordinates getEntrance() {
	    return new DiscreteCoordinates(5,2);
	}
	
}