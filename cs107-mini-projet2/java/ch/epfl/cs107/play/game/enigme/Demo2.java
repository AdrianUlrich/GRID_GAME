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
import ch.epfl.cs107.play.math.TextAlign;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Window;
import javafx.scene.text.TextAlignment;

public class Demo2 extends AreaGame {

    private Demo2Player player;
    private TextGraphics playerDebugIndicator; //DEBUG

    @Override
    public int getFrameRate() {
	return 24;
    }

    @Override
    public String getTitle() {
	return "Demo2";
    }

    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
	super.begin(window, fileSystem);
	addArea(new Room0());
	addArea(new Room1());
	setCurrentArea("LevelSelector", false);
	player = new Demo2Player(getCurrentArea(), (new DiscreteCoordinates(5, 5)));
	playerDebugIndicator = new TextGraphics("O", .3f, null, Color.GREEN, .15f, true, false, new Vector(.5f, .5f), // DEBUG
		TextAlign.Horizontal.CENTER, TextAlign.Vertical.MIDDLE, .7f, .0f); // DEBUG
	playerDebugIndicator.setParent(player); //DEBUG
	getCurrentArea().setViewCandidate(player);
	getCurrentArea().registerActor(player);
	return true;
    }

    @Override
    public void update(float deltaTime) {
	playerDebugIndicator.setOutlineColor(player.isPassingDoor()?Color.CYAN:Color.GREEN); // DEBUG
	playerDebugIndicator.setAnchor((new Vector(.5f, .5f)) // DEBUG
		.add(player.isMoving()?player.getOrientationVector():Vector.ZERO)); // DEBUG
	playerDebugIndicator.draw(getWindow()); // DEBUG
	if (player.isPassingDoor() && !player.isMoving()) {
	    if (getCurrentArea().getTitle() == "Level1") {
		setCurrentArea("LevelSelector", false);
	    } else {
		setCurrentArea("Level1", false);
	    }
	    player.leaveArea();
	    player.enterArea(getCurrentArea());
	    player.isPassingDoor(false);
	}
	super.update(deltaTime);
    }

}
