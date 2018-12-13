package ch.epfl.cs107.play.game.enigme.actor;

import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

public class Talkable extends AreaEntity{

	private Sprite sprite;
	private Dialog dialog;
	private boolean showText;
	
	public Talkable(Area area, DiscreteCoordinates position, Dialog dialog, String spriteName) {
		super(area, Orientation.DOWN, position);
		this.dialog = dialog;
		this.sprite = new Sprite(spriteName, 1.f, 1.f, this);
		showText = false;
	}

	@Override
	public List<DiscreteCoordinates> getCurrentCells() {
		return Collections.singletonList(getCurrentMainCellCoordinates());
	}

	@Override
	public boolean takeCellSpace() {
		return true;
	}

	@Override
	public boolean isViewInteractable() {
		return true;
	}

	@Override
	public boolean isCellInteractable() {
		return false;
	}
	
	public void showText() {
		showText = true;
	}
	
	public void hideText() {
		showText = false;
	}
	
	@Override
	public void acceptInteraction(AreaInteractionVisitor v) {
		((EnigmeInteractionVisitor) v).interactWith(this);
	}

	@Override
	public void draw(Canvas canvas) {
		sprite.draw(canvas);
		if(showText) {
			dialog.draw(getOwnerArea().getWindow());
		}

	}

}