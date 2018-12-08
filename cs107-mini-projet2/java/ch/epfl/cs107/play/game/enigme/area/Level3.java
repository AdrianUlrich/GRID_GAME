package ch.epfl.cs107.play.game.enigme.area;

import ch.epfl.cs107.play.math.DiscreteCoordinates;

public class Level3 extends EnigmeArea{
	
	public Level3(){
		super("Level3");
	}
	
	@Override
	public DiscreteCoordinates getEntrance() {
	    return new DiscreteCoordinates(5,2);
	}
	
}