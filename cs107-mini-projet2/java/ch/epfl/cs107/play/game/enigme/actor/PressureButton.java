package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

import java.util.Collections;
import java.util.List;

public class PressureButton extends LogicEntity implements Switchable {
	
	public PressureButton(Area area, DiscreteCoordinates position) {
		super(area, position, false);
		setGraphics(new Sprite("GroundLightOn", 1.f, 1.f, this), new Sprite("GroundLightOff", 1.f, 1.f, this));
	}
	
	@Override
	public boolean takeCellSpace() {
		return false;
	}
	
	@Override
	public boolean isCellInteractable() {
		return true;
	}
	
	@Override
	public boolean isViewInteractable() {
		return false;
	}
	
	@Override
	public List<DiscreteCoordinates> getCurrentCells() {
		return Collections.singletonList(getCurrentMainCellCoordinates());
	}
	
	@Override
	public void acceptInteraction(AreaInteractionVisitor v) {
		((EnigmeInteractionVisitor) v).interactWith(this);
	}
	
	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);
	}
	
	@Override
	public boolean switchState() {
		return isOn(!isOn());
	}
	
}
