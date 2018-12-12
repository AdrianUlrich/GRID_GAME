package ch.epfl.cs107.play.game.enigme.actor;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

public class Door extends AreaEntity {
	
	private String goesTo;
	private DiscreteCoordinates goesToCoord;
	private List<DiscreteCoordinates> otherCells;
	
	public Door(Area area, String destination, DiscreteCoordinates destinationCoords,
	            DiscreteCoordinates position, DiscreteCoordinates... otherCells) {
		super(area, Orientation.DOWN, position);
		goesTo = destination;
		goesToCoord = destinationCoords;
		this.otherCells = new LinkedList<DiscreteCoordinates>();
		for (DiscreteCoordinates cell : otherCells) {
			this.otherCells.add(cell);
		}
	}
	
	public String goesTo() {
		return goesTo;
	}
	
	public DiscreteCoordinates goesToCoord() {
		return goesToCoord;
	}
	
	@Override
	public List<DiscreteCoordinates> getCurrentCells() {
		List<DiscreteCoordinates> coordinates = new LinkedList<>();
		coordinates.add(getCurrentMainCellCoordinates());
		for (DiscreteCoordinates cell : otherCells) {
			coordinates.add(cell);
		}
		return coordinates;
	}
	
	@Override
	public boolean takeCellSpace() {
		// You can walk into a door... you even should
		return false;
	}
	
	@Override
	public boolean isViewInteractable() {
		// You enter a door by walking into it
		return false;
	}
	
	@Override
	public boolean isCellInteractable() {
		// You enter a door by walking into it
		return true;
	}
	
	@Override
	public void draw(Canvas canvas) {
		// The entity is invisible and its graphic
		// representation is done by the background
	}
	
	@Override
	public void acceptInteraction(AreaInteractionVisitor v) {
		((EnigmeInteractionVisitor) v).interactWith(this);
	}
	
	/**
	 * Debug logging method
	 *
	 * @return string   (String) A representation of the door
	 * */
	@Override
	public String toString() {
		return "Door{" +
				"goesTo='" + goesTo + '\'' +
				", Cells=" + getCurrentCells() +
				'}';
	}
}
