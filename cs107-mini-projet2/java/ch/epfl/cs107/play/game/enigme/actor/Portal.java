package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.actor.Animation;
import ch.epfl.cs107.play.game.actor.Graphics;
import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.RegionOfInterest;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

public class Portal extends Door{
	
	private final Graphics sprite;
	private final Animation animation;
	private int animationTime;


	public Portal(Area area, String destination, DiscreteCoordinates destinationCoords, DiscreteCoordinates position,
			DiscreteCoordinates... otherCells) {
		super(area, destination, destinationCoords, position, otherCells);
		 sprite = new Sprite("jewel.5", 1.f, 1.f, this);
		 
		 Vector anchor = new Vector(0.25f, 0.32f);
		Sprite[] tempSpriteArray = new Sprite[16];

			for (int j = 0; j < 4; ++j) {
				for (int i = 0; i < 4; ++i) {
					tempSpriteArray[i*4+j] = new Sprite("added/portal.1", 1.2f*0.5f, 1.2f*0.65625f, this,
							new RegionOfInterest(j*182 , i * 206, 182, 206), anchor);
				}
			}
			
			animation = new Animation(tempSpriteArray);
			animationTime = 0;
	}

	@Override
	public void draw(Canvas canvas) {
		animation.draw(canvas);
	}

	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);
		if (animationTime++%2==0)
		animation.incrementAnimation();
	}
	
	
}
