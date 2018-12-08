package ch.epfl.cs107.play.game.enigme.area;

import ch.epfl.cs107.play.math.DiscreteCoordinates;

public class Level2 extends EnigmeArea{
	
	public Level2(){
		super("Level2");
	}
	
	@Override
	public DiscreteCoordinates getEntrance() {
	    return new DiscreteCoordinates(5,2);
	}
	
}