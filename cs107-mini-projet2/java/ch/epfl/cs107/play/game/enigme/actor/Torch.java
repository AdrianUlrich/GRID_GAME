package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

import java.util.Collections;
import java.util.List;

public class Torch extends LogicEntity implements Switchable {
	
	public Torch(Area area, DiscreteCoordinates position) {
		super(area, position, true);
		setGraphics(new Sprite("torch.ground.on.1", 1.f, 1.f, this), new Sprite("torch.ground.off", 1.f, 1.f, this));
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
