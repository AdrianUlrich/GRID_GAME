package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.actor.Animation;
import ch.epfl.cs107.play.game.actor.Graphics;
import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.RegionOfInterest;
import ch.epfl.cs107.play.window.Audio;
import ch.epfl.cs107.play.window.Canvas;

import java.util.Collections;
import java.util.List;

public class ExplodableRock extends AreaEntity {
	private final int explosionMaxIndex;
	private Graphics rock;
	private boolean isBombed;
	private Graphics bomb;
	private float fuseLength;
	private float fuseUsed;
	private boolean isExploding;
	private Animation explosion;
	private int explosionIndex;
	private boolean isExploded;
	
	public ExplodableRock(Area area, DiscreteCoordinates position) {
		super(area, Orientation.DOWN, position);
		rock = new Sprite("rock.2", 1.f, 1.f, this);
		isBombed = false;
		bomb = new Sprite("added/bomb.1", 1.f, 1.f, this);
		isExploding = false;
		explosionMaxIndex = 80;
		Sprite[] sprites = new Sprite[80];
		for (int i = 0; i < 80; i++) {
			int x = i % 9;
			int y = i / 9;
			sprites[i] = new Sprite("added/explosion.1",1.f,1.f,this,new RegionOfInterest(x*10,y*10,10,10));
		}
		explosion = new Animation(sprites);
	}
	
	public void setBomb(float fuseLength) {
		isBombed = true;
		this.fuseLength = fuseLength;
		fuseUsed = 0.f;
	}
	
	private void explode() {
		isExploding = true;
		explosionIndex = 0;
	}
	
	@Override
	public void draw(Canvas canvas) {
		if (!isExploded) {
			if (explosionIndex < 40)
				rock.draw(canvas);
			if (isBombed) {
				if (isExploding) {
					explosion.draw(canvas);
				} else {
					bomb.draw(canvas);
				}
			}
		}
	}
	
	@Override
	public void update(float deltaTime) {
		if (!isExploded) {
			if (isBombed) {
				if (isExploding) {
					if (explosionIndex < explosionMaxIndex) {
						explosion.incrementAnimation();
						++explosionIndex;
					} else {
						isExploded = true;
					}
				} else {
					fuseUsed += deltaTime;
					if (fuseUsed >= fuseLength)
						explode();
				}
			}
		}
	}
	
	@Override
	public List<DiscreteCoordinates> getCurrentCells() {
		return Collections.singletonList(getCurrentMainCellCoordinates());
	}
	
	@Override
	public boolean takeCellSpace() {
		return !isExploded;
	}
	
	@Override
	public boolean isViewInteractable() {
		return !isBombed;
	}
	
	@Override
	public boolean isCellInteractable() {
		return false;
	}
	
	@Override
	public void acceptInteraction(AreaInteractionVisitor v) {
		((EnigmeInteractionVisitor) v).interactWith(this);
	}
	
}
