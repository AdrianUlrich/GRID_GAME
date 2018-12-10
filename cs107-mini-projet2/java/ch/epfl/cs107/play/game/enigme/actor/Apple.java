package ch.epfl.cs107.play.game.enigme.actor;

import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.actor.EnigmePlayer.EnigmePlayerHandler;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

public class Apple extends Collectable {

    private final Sprite sprite;

    public Apple(Area area, Orientation orientation, DiscreteCoordinates position) {
	super(area, orientation, position);
	sprite = new Sprite("apple.1", 1, 1.f, this);
    }

    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
	return Collections.singletonList(getCurrentMainCellCoordinates());
    }

    public void acceptInteraction(EnigmePlayerHandler v) {
	v.interactWith(this);
    }

    @Override
    public void draw(Canvas canvas) {
	sprite.draw(canvas);
    }
}
