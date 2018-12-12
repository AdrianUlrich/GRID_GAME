package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.actor.Graphics;
import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

public class Portal extends Door{
	
	private final Graphics sprite;

	public Portal(Area area, String destination, DiscreteCoordinates destinationCoords, DiscreteCoordinates position,
			DiscreteCoordinates... otherCells) {
		super(area, destination, destinationCoords, position, otherCells);
		 sprite = new Sprite("jewel.5", 1.f, 1.f, this);
	}

	@Override
	public void draw(Canvas canvas) {
		sprite.draw(canvas);
	}
	
}
