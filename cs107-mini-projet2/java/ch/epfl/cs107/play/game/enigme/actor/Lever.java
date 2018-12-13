package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

import java.util.Collections;
import java.util.List;

public class Lever extends LogicEntity implements Switchable {
	
	public Lever(Area area, DiscreteCoordinates position, boolean isOn) {
		super(area, position, isOn);
		setGraphics(new Sprite("lever.big.left", 1.f, 1.f, this), new Sprite("lever.big.right", 1.f, 1.f, this));
	}
	
	public Lever(Area area, DiscreteCoordinates position) {
		this(area, position, false);
	}
	
	
	@Override
	public boolean takeCellSpace() {
		return true;
	}
	
	@Override
	public boolean isCellInteractable() {
		return false;
	}
	
	@Override
	public List<DiscreteCoordinates> getCurrentCells() {
		return Collections.singletonList(getCurrentMainCellCoordinates());
	}
	
	@Override
	public void acceptInteraction(AreaInteractionVisitor v) {
		((EnigmeInteractionVisitor) v).interactWith((Switchable) this);
	}
	
	@Override
	public boolean switchState() {
		return isOn(!isOn());
	}
}
