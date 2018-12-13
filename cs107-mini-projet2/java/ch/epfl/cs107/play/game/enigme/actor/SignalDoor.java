package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.actor.Graphics;
import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Canvas;

public class SignalDoor extends Door {
	private Logic logic;
	private Sprite[] openDoor;
	private Sprite[] closedDoor;
	
	public SignalDoor(Logic logic, Area area, String destination, DiscreteCoordinates destinationCoords, Orientation orientation, DiscreteCoordinates position, DiscreteCoordinates... otherCells) {
		super(area, destination, destinationCoords, position, otherCells);
		this.logic = logic;
		openDoor = new Sprite[1 + otherCells.length];
		closedDoor = new Sprite[1 + otherCells.length];
		openDoor[0] = new Sprite("door.close.2", 1.f, 1.f, this);
		closedDoor[0] = new Sprite("door.open.2", 1.f, 1.f, this);
		for (int i = 0; i < otherCells.length; i++) {
			DiscreteCoordinates otherCell = otherCells[i];
			Vector anchor = new Vector(otherCell.x, otherCell.y);
			openDoor[i + 1] = new Sprite("door.close.2", 1.f, 1.f, this, null, anchor);
			closedDoor[i + 1] = new Sprite("door.open.2", 1.f, 1.f, this, null, anchor);
		}
	}
	
	@Override
	public boolean isCellInteractable() {
		return logic.isOn();
	}
	
	@Override
	public boolean takeCellSpace() {
		return !logic.isOn();
	}
	
	@Override
	public void draw(Canvas canvas) {
		if (logic.isOn()) {
			for (Sprite sprite : openDoor) {
				sprite.draw(canvas);
			}
		} else {
			for (Sprite sprite : closedDoor) {
				sprite.draw(canvas);
			}
		}
	}
}
