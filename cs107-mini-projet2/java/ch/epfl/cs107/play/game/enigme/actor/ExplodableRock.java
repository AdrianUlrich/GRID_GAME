package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.actor.Animation;
import ch.epfl.cs107.play.game.actor.Graphics;
import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.*;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.RegionOfInterest;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Audio;
import ch.epfl.cs107.play.window.Canvas;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ExplodableRock extends AreaEntity implements Interactor {
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
	private EnigmeExplosionRockHandler handler;
	private boolean chaining;
	
	public ExplodableRock(Area area, DiscreteCoordinates position) {
		super(area, Orientation.DOWN, position);
		rock = new Sprite("rock.2", 1.f, 1.f, this);
		isBombed = false;
		bomb = new Sprite("added/bomb.1", 1.f, 1.f, this);
		isExploding = false;
		explosionMaxIndex = 80;
		int factor = 100;
		Sprite[] sprites = new Sprite[81];
		for (int i = 0; i < 80; i++) {
			int x = i % 9;
			int y = i / 9;
			sprites[i] = new Sprite("added/explosion.1", 3.f, 3.f, this, new RegionOfInterest(x * factor, y * factor, factor, factor), new Vector(-1f, -01f));
		}
		handler = new EnigmeExplosionRockHandler();
		explosion = new Animation(sprites);
	}
	
	@Override
	public List<DiscreteCoordinates> getFieldOfViewCells() {
		List<DiscreteCoordinates> chainReaction = new LinkedList<>();
		DiscreteCoordinates cell = getCurrentMainCellCoordinates();
		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				chainReaction.add(new DiscreteCoordinates(cell.x + i, cell.y + j));
			}
		}
		return chainReaction;
	}
	
	@Override
	public boolean wantsCellInteraction() {
		return false;
	}
	
	@Override
	public boolean wantsViewInteraction() {
		return isExploding && chaining;
	}
	
	@Override
	public void setWantsViewInteraction(boolean b) {
		chaining = b;
	}
	
	@Override
	public void interactWith(Interactable interactable) {
		interactable.acceptInteraction(handler);
	}
	
	public void setBomb(float fuseLength) {
		isBombed = true;
		this.fuseLength = fuseLength;
		fuseUsed = 0.f;
	}
	
	private void explode() {
		if (!isExploded) {
			if (isBombed) {
				if (!isExploding) {
					isExploding = true;
					chaining = true;
					explosionIndex = 0;
				}
			} else {
				isExploded = true;
				explosionIndex = 80;
			}
		}
	}
	
	@Override
	public void draw(Canvas canvas) {
		if (!isExploded) {
			if (explosionIndex < 40) {
				rock.draw(canvas);
			}
			if (isExploding) {
				explosion.draw(canvas);
			} else if (isBombed) {
				bomb.draw(canvas);
			}
		}
	}
	
	@Override
	public void update(float deltaTime) {
		if (!isExploded) {
			if (isExploding) {
				if (explosionIndex < explosionMaxIndex) {
					explosion.incrementAnimation();
					++explosionIndex;
				} else {
					isExploded = true;
				}
			} else if (isBombed) {
				fuseUsed += deltaTime;
				if (fuseUsed >= fuseLength) {
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
		return !isExploding && !isExploded;
	}
	
	@Override
	public boolean isCellInteractable() {
		return false;
	}
	
	@Override
	public void acceptInteraction(AreaInteractionVisitor v) {
		((EnigmeInteractionVisitor) v).interactWith(this);
	}
	
	
	class EnigmeExplosionRockHandler implements EnigmeInteractionVisitor {
		
		@Override
		public void interactWith(ExplodableRock explodableRock) {
			explodableRock.explode();
		}
	}
}
