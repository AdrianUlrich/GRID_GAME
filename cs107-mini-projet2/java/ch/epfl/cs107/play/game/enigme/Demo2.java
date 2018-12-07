package ch.epfl.cs107.play.game.enigme;

import java.awt.Color;

import ch.epfl.cs107.play.game.Game;
import ch.epfl.cs107.play.game.actor.GraphicsEntity;
import ch.epfl.cs107.play.game.actor.ShapeGraphics;
import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.AreaGame;
import ch.epfl.cs107.play.game.demo1.actor.MovingRock;
import ch.epfl.cs107.play.game.enigme.area.demo2.Room1;
import ch.epfl.cs107.play.game.enigme.actor.demo2.Demo2Player;
import ch.epfl.cs107.play.game.enigme.area.demo2.Demo2Area;
import ch.epfl.cs107.play.game.enigme.area.demo2.Room0;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.Circle;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Window;

public class Demo2 extends AreaGame {

	Demo2Player player = new Demo2Player(getCurrentArea(), (new DiscreteCoordinates(5, 5)));

	@Override
	public int getFrameRate() {
		// TODO Auto-generated method stub
		return 24;
	}

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return "Demo2";
	}

	@Override
	public boolean begin(Window window, FileSystem fileSystem) {
		// TODO Auto-generated method stubs
		super.begin(window, fileSystem);
		addArea(new Room0());
		addArea(new Room1());
		setCurrentArea("LevelSelector", false);
		getCurrentArea().setViewCandidate(player);
		getCurrentArea().registerActor(player);
		return true;
	}

	@Override
	public void update(float deltaTime) {
		if (player.isPassingDoor) {
			if (getCurrentArea() == new Demo2Area("Room1")) {
				player.leaveArea();
				player.enterArea(getArea("Room0"), new DiscreteCoordinates(5, 5));
			} else {
				player.leaveArea();
				player.enterArea(getArea("Room1"), new DiscreteCoordinates(5, 2));
			}
		}
		super.update(deltaTime);
		// TODO implements me #PROJECT #TUTO
	}

}
