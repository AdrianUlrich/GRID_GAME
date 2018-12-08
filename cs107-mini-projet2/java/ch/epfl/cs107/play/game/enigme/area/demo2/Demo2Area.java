package ch.epfl.cs107.play.game.enigme.area.demo2;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.game.enigme.Demo2Behavior;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;

public abstract class Demo2Area extends Area {

	private String title;
	
	public Demo2Area(String title) {
		this.title = title;
	}
	
	@Override
	public String getTitle() {
		return title;
	}
	
	public boolean begin(Window window, FileSystem fileSystem) {
		super.begin(window, fileSystem);
		setBehavior(new Demo2Behavior(window , getTitle()));
		registerActor(new Background(this));

		return true;
	}
	
	@Override
	public float getCameraScaleFactor() {
		// TODO Auto-generated method stub
		return 25;
	}


}
