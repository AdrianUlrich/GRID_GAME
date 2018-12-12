package ch.epfl.cs107.play.game.enigme;

import ch.epfl.cs107.play.game.areagame.AreaGame;
import ch.epfl.cs107.play.game.enigme.actor.EnigmePlayer;
import ch.epfl.cs107.play.game.enigme.area.Level1;
import ch.epfl.cs107.play.game.enigme.area.Level2;
import ch.epfl.cs107.play.game.enigme.area.Level3;
import ch.epfl.cs107.play.game.enigme.area.Level4;
import ch.epfl.cs107.play.game.enigme.area.LevelSelector;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;

public class Enigme extends AreaGame {
	
	private EnigmePlayer player;
//    private TextGraphics playerDebugIndicator; //DEBUG
	
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
		addArea(new LevelSelector());
		addArea(new Level1());
		addArea(new Level2());
		addArea(new Level3());
		addArea(new Level4());
		setCurrentArea("LevelSelector", false);
		player = new EnigmePlayer(getCurrentArea(), (new DiscreteCoordinates(5, 5)));
//		playerDebugIndicator = new TextGraphics("O", .3f, null, Color.GREEN, .15f, true, false, new Vector(.5f, .5f), // DEBUG
//		TextAlign.Horizontal.CENTER, TextAlign.Vertical.MIDDLE, .7f, .0f); // DEBUG
//		playerDebugIndicator.setParent(player); //DEBUG
		getCurrentArea().setViewCandidate(player);
		getCurrentArea().registerActor(player);
		return true;
	}
	
	@Override
	public void update(float deltaTime) {
		if (player.isPassingDoor()) {
			player.isPassingDoor(false);
			if (!player.isMoving() && player.passedDoor().goesTo() != null) {
//				System.out.println("passdoor*");
				player.leaveArea();
				setCurrentArea(player.passedDoor().goesTo(), false);
				player.enterArea(getCurrentArea());
				getCurrentArea().setViewCandidate(player);
			}
		}
		super.update(deltaTime);
	}
	
}
