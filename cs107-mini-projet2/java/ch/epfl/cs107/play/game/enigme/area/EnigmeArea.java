package ch.epfl.cs107.play.game.enigme.area;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.game.areagame.actor.Foreground;
import ch.epfl.cs107.play.game.enigme.EnigmeBehavior;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.window.Window;

public abstract class EnigmeArea extends Area {

	private String title;
	
	public EnigmeArea(String title) {
		this.title = title;
	}
	
	@Override
	public String getTitle() {
		return title;
	}
	
	public boolean begin(Window window, FileSystem fileSystem) {
		boolean supSuccess = super.begin(window, fileSystem);
		setBehavior(new EnigmeBehavior(window , getTitle()));
		registerActor(new Background(this));
		registerActor(new Foreground(this));

		return supSuccess;
	}
	
	@Override
	public float getCameraScaleFactor() {
		return 25;
	}


}
