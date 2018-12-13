package ch.epfl.cs107.play.game.enigme.area;

import ch.epfl.cs107.play.game.enigme.actor.*;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.*;
import ch.epfl.cs107.play.window.Window;

public class Level3 extends EnigmeArea {
	
	public Level3() {
		super("Level3");
	}
	
	
	@Override
	public boolean begin(Window window, FileSystem fileSystem) {
		boolean supSuccess = super.begin(window, fileSystem);
		
		Key key = new Key(this, new DiscreteCoordinates(1, 3));
		registerActor(key);
		Torch torch = new Torch(this, new DiscreteCoordinates(7, 5));
		registerActor(torch);
		PressurePlate pressurePlate = new PressurePlate(this, new DiscreteCoordinates(9, 8), 0.3f);
		registerActor(pressurePlate);
		PressureButton pb1 = new PressureButton(this, new DiscreteCoordinates(4, 4));
		registerActor(pb1);
		PressureButton pb2 = new PressureButton(this, new DiscreteCoordinates(5, 4));
		registerActor(pb2);
		PressureButton pb3 = new PressureButton(this, new DiscreteCoordinates(6, 4));
		registerActor(pb3);
		PressureButton pb4 = new PressureButton(this, new DiscreteCoordinates(5, 5));
		registerActor(pb4);
		PressureButton pb5 = new PressureButton(this, new DiscreteCoordinates(4, 6));
		registerActor(pb5);
		PressureButton pb6 = new PressureButton(this, new DiscreteCoordinates(5, 6));
		registerActor(pb6);
		PressureButton pb7 = new PressureButton(this, new DiscreteCoordinates(6, 6));
		registerActor(pb7);
		Lever lever1 = new Lever(this, new DiscreteCoordinates(10, 5));
		registerActor(lever1);
		Lever lever2 = new Lever(this, new DiscreteCoordinates(9, 5));
		registerActor(lever2);
		Lever lever3 = new Lever(this, new DiscreteCoordinates(8, 5));
		registerActor(lever3);
		
		registerActor(new SignalDoor(key, this, "LevelSelector", new DiscreteCoordinates(6, 3), new DiscreteCoordinates(5, 9)));
		registerActor(new SignalRock(pressurePlate, this, new DiscreteCoordinates(6, 8)));
		registerActor(new SignalRock(new MultipleAnd(pb1, pb2, pb3, pb4, pb5, pb6, pb7), this, new DiscreteCoordinates(5, 8)));
		registerActor(new SignalRock(new Or(new LogicNumber(5, lever1, lever2, lever3), torch), this, new DiscreteCoordinates(4, 8)));
		
		return supSuccess;
	}
	
}