package ch.epfl.cs107.play.game.enigme.area;

import ch.epfl.cs107.play.game.enigme.actor.*;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;

public class Level4 extends EnigmeArea {
	
	
	public Level4() {
		super("Enigme1");
	}
	
	@Override
	public boolean begin(Window window, FileSystem fileSystem) {
		boolean supSuccess = super.begin(window, fileSystem);
		Portal returnPortal = new Portal(this, "LevelSelector", new DiscreteCoordinates(4, 6), new DiscreteCoordinates(22, 2));
		registerActor(returnPortal);
		Portal exitPortal = new Portal(this, "Enigme1", new DiscreteCoordinates(23, 12), new DiscreteCoordinates(19, 37));
		registerActor(exitPortal);
		Portal goodPortalExit6 = new Portal(this, "Enigme1", new DiscreteCoordinates(23, 30), new DiscreteCoordinates(14, 37));
		registerActor(goodPortalExit6);
		Portal goodPortalEntrance6 = new Portal(this, "Enigme1", new DiscreteCoordinates(14, 37), new DiscreteCoordinates(23, 30));
		registerActor(goodPortalEntrance6);
		Portal goodPortalExit5 = new Portal(this, "Enigme1", new DiscreteCoordinates(15, 22), new DiscreteCoordinates(19, 24));
		registerActor(goodPortalExit5);
		Portal VeryGoodPortalExit4Entrance5 = new Portal(this, "Enigme1", new DiscreteCoordinates(19, 24), new DiscreteCoordinates(15, 22));
		registerActor(VeryGoodPortalExit4Entrance5);
		Portal goodPortalEntrance4 = new Portal(this, "Enigme1", new DiscreteCoordinates(15, 22), new DiscreteCoordinates(12, 35));
		registerActor(goodPortalEntrance4);
		Portal goodPortalExit3 = new Portal(this, "Enigme1", new DiscreteCoordinates(19, 22), new DiscreteCoordinates(16, 35));
		registerActor(goodPortalExit3);
		Portal goodPortalEntrance3 = new Portal(this, "Enigme1", new DiscreteCoordinates(16, 35), new DiscreteCoordinates(19, 22));
		registerActor(goodPortalEntrance3);
		Portal goodPortalExit2 = new Portal(this, "Enigme1", new DiscreteCoordinates(21, 18), new DiscreteCoordinates(18, 23));
		registerActor(goodPortalExit2);
		Portal goodPortalEntrance2 = new Portal(this, "Enigme1", new DiscreteCoordinates(18, 23), new DiscreteCoordinates(21, 18));
		registerActor(goodPortalEntrance2);
		Portal goodPortalExit1 = new Portal(this, "Enigme1", new DiscreteCoordinates(14, 12), new DiscreteCoordinates(17, 18));
		registerActor(goodPortalExit1);
		Portal goodPortalEntrance1 = new Portal(this, "Enigme1", new DiscreteCoordinates(17, 18), new DiscreteCoordinates(14, 12));
		registerActor(goodPortalEntrance1);
		Portal wrongPortal1 = new Portal(this, "Enigme1", new DiscreteCoordinates(23, 12), new DiscreteCoordinates(14, 7));
		registerActor(wrongPortal1);
		Portal wrongPortal2 = new Portal(this, "Enigme1", new DiscreteCoordinates(23, 12), new DiscreteCoordinates(23, 14));
		registerActor(wrongPortal2);
		Portal wrongPortal3 = new Portal(this, "Enigme1", new DiscreteCoordinates(23, 12), new DiscreteCoordinates(17, 16));
		registerActor(wrongPortal3);
		Portal wrongPortal4 = new Portal(this, "Enigme1", new DiscreteCoordinates(23, 12), new DiscreteCoordinates(17, 22));
		registerActor(wrongPortal4);
		Portal wrongPortal5 = new Portal(this, "Enigme1", new DiscreteCoordinates(23, 12), new DiscreteCoordinates(10, 25));
		registerActor(wrongPortal5);
		Portal wrongPortal6 = new Portal(this, "Enigme1", new DiscreteCoordinates(23, 12), new DiscreteCoordinates(14, 26));
		registerActor(wrongPortal6);
		Portal wrongPortal7 = new Portal(this, "Enigme1", new DiscreteCoordinates(23, 12), new DiscreteCoordinates(16, 27));
		registerActor(wrongPortal7);
		Portal wrongPortal8 = new Portal(this, "Enigme1", new DiscreteCoordinates(23, 12), new DiscreteCoordinates(18, 28));
		registerActor(wrongPortal8);
		Portal wrongPortal9 = new Portal(this, "Enigme1", new DiscreteCoordinates(23, 12), new DiscreteCoordinates(23, 35));
		registerActor(wrongPortal9);
		Portal wrongPortal10 = new Portal(this, "Enigme1", new DiscreteCoordinates(23, 12), new DiscreteCoordinates(23, 27));
		registerActor(wrongPortal10);
		Portal wrongPortalBlue = new Portal(this, "Enigme1", new DiscreteCoordinates(14, 20), new DiscreteCoordinates(18, 9));
		registerActor(wrongPortalBlue);
		Portal wrongPortalBlue2 = new Portal(this, "Enigme1", new DiscreteCoordinates(18, 9), new DiscreteCoordinates(14, 20));
		registerActor(wrongPortalBlue2);
		Portal wrongPortalPurple = new Portal(this, "Enigme1", new DiscreteCoordinates(21, 37), new DiscreteCoordinates(21, 25));
		registerActor(wrongPortalPurple);
		Portal wrongPortalPurple2 = new Portal(this, "Enigme1", new DiscreteCoordinates(21, 25), new DiscreteCoordinates(21, 37));
		registerActor(wrongPortalPurple2);
		Portal wrongPortalPink = new Portal(this, "Enigme1", new DiscreteCoordinates(10, 27), new DiscreteCoordinates(18, 34));
		registerActor(wrongPortalPink);
		Portal wrongPortalPink2 = new Portal(this, "Enigme1", new DiscreteCoordinates(18, 34), new DiscreteCoordinates(10, 27));
		registerActor(wrongPortalPink2);
		Portal wrongPortalYellow = new Portal(this, "Enigme1", new DiscreteCoordinates(16, 32), new DiscreteCoordinates(10, 30));
		registerActor(wrongPortalYellow);
		Portal wrongPortalYellow2 = new Portal(this, "Enigme1", new DiscreteCoordinates(10, 30), new DiscreteCoordinates(16, 32));
		registerActor(wrongPortalYellow2);
		ImmovableRock rock1 = new ImmovableRock(this, new DiscreteCoordinates(14, 36));
		registerActor(rock1);
		ImmovableRock rock2 = new ImmovableRock(this, new DiscreteCoordinates(16, 34));
		registerActor(rock2);
		ImmovableRock rock3 = new ImmovableRock(this, new DiscreteCoordinates(17, 34));
		registerActor(rock3);
		ImmovableRock rock4 = new ImmovableRock(this, new DiscreteCoordinates(15, 32));
		registerActor(rock4);
		ImmovableRock rock5 = new ImmovableRock(this, new DiscreteCoordinates(12, 34));
		registerActor(rock5);
		ImmovableRock rock6 = new ImmovableRock(this, new DiscreteCoordinates(15, 27));
		registerActor(rock6);
		ImmovableRock rock7 = new ImmovableRock(this, new DiscreteCoordinates(14, 27));
		registerActor(rock7);
		ImmovableRock rock8 = new ImmovableRock(this, new DiscreteCoordinates(13, 24));
		registerActor(rock8);
		ImmovableRock rock9 = new ImmovableRock(this, new DiscreteCoordinates(21, 26));
		registerActor(rock9);
		ImmovableRock rock10 = new ImmovableRock(this, new DiscreteCoordinates(20, 24));
		registerActor(rock10);
		ImmovableRock rock11 = new ImmovableRock(this, new DiscreteCoordinates(18, 24));
		registerActor(rock11);
		ImmovableRock rock12 = new ImmovableRock(this, new DiscreteCoordinates(23, 28));
		registerActor(rock12);
		ImmovableRock rock13 = new ImmovableRock(this, new DiscreteCoordinates(21, 19));
		registerActor(rock13);
		ImmovableRock rock14 = new ImmovableRock(this, new DiscreteCoordinates(15, 21));
		registerActor(rock14);
		ImmovableRock rock15 = new ImmovableRock(this, new DiscreteCoordinates(18, 15));
		registerActor(rock15);
		ImmovableRock rock16 = new ImmovableRock(this, new DiscreteCoordinates(23, 13));
		registerActor(rock16);
		RunningShoes runningShoes = new RunningShoes(this, new DiscreteCoordinates(17, 37));
		registerActor(runningShoes);
		Door interiorDoor = new Door(this, "Enigme2", new DiscreteCoordinates(7, 1), new DiscreteCoordinates(6, 32));
		registerActor(interiorDoor);
		Talkable pnj = new Talkable(this, new DiscreteCoordinates(12, 8), new Dialog("In my youth I was a very good runner, but i'm far too slow to make it in time now...", "dialog.1", this), "old.man.4");
		registerActor(pnj);
		registerActor(new ExplodableRock(this, new DiscreteCoordinates(6, 17)));
		registerActor(new ExplodableRock(this, new DiscreteCoordinates(7, 17)));
		return supSuccess;
	}
	
}