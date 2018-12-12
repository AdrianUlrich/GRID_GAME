package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Canvas;

public abstract class SwitchableEntity extends AreaEntity implements Logic {
	private boolean isOn;
	private Sprite onSprite;
	private Sprite offSprite;
	
	public SwitchableEntity(Area area, DiscreteCoordinates position, boolean isOn, String onSprite, String offSprite) {
		super(area, Orientation.DOWN, position);
		this.isOn = isOn;
		this.onSprite = new Sprite(onSprite, 1.f, 1.f, this);
		this.offSprite = new Sprite(offSprite, 1.f, 1.f, this);
	}
	
	@Override
	public boolean isOn() {
		return isOn;
	}
	
	public void switchState() {
		isOn = !isOn;
	}
	
	public void Off() {isOn = false;}
	public void On() {isOn = true;}
	
	@Override
	public boolean isViewInteractable() {
		return true;
	}
	
	@Override
	public void draw(Canvas canvas) {
		if (isOn) {
			onSprite.draw(canvas);
		} else {
			offSprite.draw(canvas);
		}
	}
	
}
