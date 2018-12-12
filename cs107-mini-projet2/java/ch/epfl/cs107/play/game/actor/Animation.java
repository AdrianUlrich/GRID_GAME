package ch.epfl.cs107.play.game.actor;

import java.util.LinkedList;
import java.util.List;

import ch.epfl.cs107.play.game.Updatable;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.window.Canvas;

public class Animation implements Graphics {

	private List<Sprite> animationFrames;
	private int currentFrameIndex;

	public Animation(Sprite... animationFrames) {
		this.animationFrames = new LinkedList<>();
		for (Sprite frame : animationFrames) {
			this.animationFrames.add(frame);
		}
		currentFrameIndex = 0;
	}

	@Override
	public void draw(Canvas canvas) {
		animationFrames.get(currentFrameIndex).draw(canvas);
	}

	public void incrementAnimation() {
		currentFrameIndex = (currentFrameIndex + 1) % animationFrames.size();
	}

	public void resetAnimation() {
		currentFrameIndex = 0;
	}

}
