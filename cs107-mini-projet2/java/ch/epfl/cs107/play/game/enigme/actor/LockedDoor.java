package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.Signal;

public class LockedDoor extends Door {
	Signal signal;
	
	public LockedDoor(Signal signal, Area area, String destination, DiscreteCoordinates destinationCoords, Orientation orientation, DiscreteCoordinates position, DiscreteCoordinates... otherCells) {
		super(area, destination, destinationCoords, position, otherCells);
		this.signal = signal;
	}
	
	@Override
	public boolean isCellInteractable() {
		// TODO fix time value
		return signal.getIntensity(0) == 1.f;
	}
}
