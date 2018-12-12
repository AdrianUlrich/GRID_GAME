package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.actor.Graphics;
import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Canvas;

public abstract class LogicEntity extends AreaEntity implements Logic {
	private boolean isOn;
	private Graphics onGraphic;
	private Graphics offGraphic;
	
	public LogicEntity(Area area, DiscreteCoordinates position, boolean isOn, Graphics onGraphic, Graphics offGraphic) {
		super(area, Orientation.DOWN, position);
		this.isOn = isOn;
		this.onGraphic = onGraphic;
		this.offGraphic = offGraphic;
	}
	
	public LogicEntity(Area area, DiscreteCoordinates position, boolean isOn) {
		super(area, Orientation.DOWN, position);
		this.isOn = isOn;
		this.onGraphic = null;
		this.offGraphic = null;
	}
	
	protected void setGraphics(Graphics onGraphic, Graphics offGraphic) {
		this.onGraphic = onGraphic;
		this.offGraphic = offGraphic;
	}
	
	@Override
	public boolean isOn() {
		return isOn;
	}
	
	public boolean isOn(boolean isOn) {
		return this.isOn = isOn;
	}
	
	public void Off() {
		isOn = false;
	}
	
	public void On() {
		isOn = true;
	}
	
	@Override
	public boolean isViewInteractable() {
		return true;
	}
	
	@Override
	public void draw(Canvas canvas) {
		if (isOn) {
			onGraphic.draw(canvas);
		} else {
			offGraphic.draw(canvas);
		}
	}
	
}
