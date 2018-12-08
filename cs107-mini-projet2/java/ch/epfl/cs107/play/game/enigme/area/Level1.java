package ch.epfl.cs107.play.game.enigme.area;

import ch.epfl.cs107.play.math.DiscreteCoordinates;

public class Level1 extends EnigmeArea{
	
	public Level1(){
		super("Level1");
	}
	
	@Override
	public DiscreteCoordinates getEntrance() {
	    return new DiscreteCoordinates(5,2);
	}
	
}