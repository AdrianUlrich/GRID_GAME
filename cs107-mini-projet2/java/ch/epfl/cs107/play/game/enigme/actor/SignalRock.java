package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Canvas;

public class SignalRock extends ImmovableRock {
	private Logic logic;
	private Sprite sprite;
	
	
	public SignalRock(Logic logic, Area area, DiscreteCoordinates position) {
		super(area, position);
		this.logic = logic;
		sprite = new Sprite("rock.3", 1.f, 1.f, this);
	}
	
	@Override
	public boolean takeCellSpace() {
		return !logic.isOn();
	}
	
	@Override
	public void draw(Canvas canvas) {
		if (!logic.isOn()) {
			sprite.draw(canvas);
		}
	}
	
}
