package ch.epfl.cs107.play.game.enigme;

import java.awt.Color;

import ch.epfl.cs107.play.game.Game;
import ch.epfl.cs107.play.game.actor.GraphicsEntity;
import ch.epfl.cs107.play.game.actor.ShapeGraphics;
import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.AreaGame;
import ch.epfl.cs107.play.game.demo1.actor.MovingRock;
import ch.epfl.cs107.play.game.enigme.area.demo2.Level1;
import ch.epfl.cs107.play.game.enigme.area.demo2.LevelSelector;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.Circle;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Window;

public class Demo2 extends AreaGame{
	
	@Override
	public int getFrameRate() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public boolean begin(Window window, FileSystem fileSystem) {
		// TODO Auto-generated method stubs
		super.begin(window, fileSystem);
		addArea(new LevelSelector());
		addArea(new Level1());
		setCurrentArea("LevelSelector",false);
		return true;
	}

}
