package ch.epfl.cs107.play.game.enigme.area;

import ch.epfl.cs107.play.game.enigme.actor.Bomb;
import ch.epfl.cs107.play.game.enigme.actor.Dialog;
import ch.epfl.cs107.play.game.enigme.actor.Door;
import ch.epfl.cs107.play.game.enigme.actor.ExplodableRock;
import ch.epfl.cs107.play.game.enigme.actor.ImmovableRock;
import ch.epfl.cs107.play.game.enigme.actor.Portal;
import ch.epfl.cs107.play.game.enigme.actor.PressurePlate;
import ch.epfl.cs107.play.game.enigme.actor.RunningShoes;
import ch.epfl.cs107.play.game.enigme.actor.SignalRock;
import ch.epfl.cs107.play.game.enigme.actor.Talkable;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Or;
import ch.epfl.cs107.play.window.Window;

public class Level4 extends EnigmeArea {
	
	

    public Level4() {
	super("Enigme1");
    }

    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
	boolean supSuccess = super.begin(window, fileSystem);
	Portal returnPortal = new Portal(this, "LevelSelector", new DiscreteCoordinates(4,6), new DiscreteCoordinates(22, 2));
	registerActor(returnPortal);
	Portal exitPortal = new Portal(this, "Enigme1", new DiscreteCoordinates(23,12), new DiscreteCoordinates(19, 37));
	registerActor(exitPortal);
	Portal goodPortalExit6 = new Portal(this, "Enigme1", new DiscreteCoordinates(23,30), new DiscreteCoordinates(14, 37));
	registerActor(goodPortalExit6);
	Portal goodPortalEntrance6 = new Portal(this, "Enigme1", new DiscreteCoordinates(14,37), new DiscreteCoordinates(23, 30));
	registerActor(goodPortalEntrance6);
	Portal goodPortalExit5 = new Portal(this, "Enigme1", new DiscreteCoordinates(15,22), new DiscreteCoordinates(19, 24));
	registerActor(goodPortalExit5);
	Portal VeryGoodPortalExit4Entrance5 = new Portal(this, "Enigme1", new DiscreteCoordinates(19,24), new DiscreteCoordinates(15, 22));
	registerActor(VeryGoodPortalExit4Entrance5);
	Portal goodPortalEntrance4 = new Portal(this, "Enigme1", new DiscreteCoordinates(15,22), new DiscreteCoordinates(12, 35));
	registerActor(goodPortalEntrance4);
	Portal goodPortalExit3 = new Portal(this, "Enigme1", new DiscreteCoordinates(19,22), new DiscreteCoordinates(16, 35));
	registerActor(goodPortalExit3);
	Portal goodPortalEntrance3 = new Portal(this, "Enigme1", new DiscreteCoordinates(16,35), new DiscreteCoordinates(19, 22));
	registerActor(goodPortalEntrance3);
	Portal goodPortalExit2 = new Portal(this, "Enigme1", new DiscreteCoordinates(21,18), new DiscreteCoordinates(18, 23));
	registerActor(goodPortalExit2);
	Portal goodPortalEntrance2 = new Portal(this, "Enigme1", new DiscreteCoordinates(18,23), new DiscreteCoordinates(21, 18));
	registerActor(goodPortalEntrance2);
	Portal goodPortalExit1 = new Portal(this, "Enigme1", new DiscreteCoordinates(14,12), new DiscreteCoordinates(17, 18));
	registerActor(goodPortalExit1);
	Portal goodPortalEntrance1 = new Portal(this, "Enigme1", new DiscreteCoordinates(17,18), new DiscreteCoordinates(14, 12));
	registerActor(goodPortalEntrance1);
	Portal wrongPortal1 = new Portal(this, "Enigme1", new DiscreteCoordinates(23,12), new DiscreteCoordinates(14, 7));
	registerActor(wrongPortal1);
	Portal wrongPortal2 = new Portal(this, "Enigme1", new DiscreteCoordinates(23,12), new DiscreteCoordinates(23, 14));
	registerActor(wrongPortal2);
	Portal wrongPortal3 = new Portal(this, "Enigme1", new DiscreteCoordinates(23,12), new DiscreteCoordinates(17, 16));
	registerActor(wrongPortal3);
	Portal wrongPortal4 = new Portal(this, "Enigme1", new DiscreteCoordinates(23,12), new DiscreteCoordinates(17, 22));
	registerActor(wrongPortal4);
	Portal wrongPortal5 = new Portal(this, "Enigme1", new DiscreteCoordinates(23,12), new DiscreteCoordinates(10, 25));
	registerActor(wrongPortal5);
	Portal wrongPortal6 = new Portal(this, "Enigme1", new DiscreteCoordinates(23,12), new DiscreteCoordinates(14, 26));
	registerActor(wrongPortal6);
	Portal wrongPortal7 = new Portal(this, "Enigme1", new DiscreteCoordinates(23,12), new DiscreteCoordinates(16, 27));
	registerActor(wrongPortal7);
	Portal wrongPortal8 = new Portal(this, "Enigme1", new DiscreteCoordinates(23,12), new DiscreteCoordinates(18, 28));
	registerActor(wrongPortal8);
	Portal wrongPortal9 = new Portal(this, "Enigme1", new DiscreteCoordinates(23,12), new DiscreteCoordinates(23, 35));
	registerActor(wrongPortal9);
	Portal wrongPortal10 = new Portal(this, "Enigme1", new DiscreteCoordinates(23,12), new DiscreteCoordinates(23, 27));
	registerActor(wrongPortal10);
	Portal wrongPortalBlue = new Portal(this, "Enigme1", new DiscreteCoordinates(15,20), new DiscreteCoordinates(18, 9));
	registerActor(wrongPortalBlue);
	Portal wrongPortalBlue2 = new Portal(this, "Enigme1", new DiscreteCoordinates(18,9), new DiscreteCoordinates(15, 20));
	registerActor(wrongPortalBlue2);
	Portal wrongPortalPurple = new Portal(this, "Enigme1", new DiscreteCoordinates(21,37), new DiscreteCoordinates(21, 25));
	registerActor(wrongPortalPurple);
	Portal wrongPortalPurple2 = new Portal(this, "Enigme1", new DiscreteCoordinates(21,25), new DiscreteCoordinates(21, 37));
	registerActor(wrongPortalPurple2);
	Portal wrongPortalPink = new Portal(this, "Enigme1", new DiscreteCoordinates(10,27), new DiscreteCoordinates(18, 34));
	registerActor(wrongPortalPink);
	Portal wrongPortalPink2 = new Portal(this, "Enigme1", new DiscreteCoordinates(18,34), new DiscreteCoordinates(10, 27));
	registerActor(wrongPortalPink2);
	Portal wrongPortalYellow = new Portal(this, "Enigme1", new DiscreteCoordinates(16,32), new DiscreteCoordinates(10, 30));
	registerActor(wrongPortalYellow);
	Portal wrongPortalYellow2 = new Portal(this, "Enigme1", new DiscreteCoordinates(10,30), new DiscreteCoordinates(16, 32));
	registerActor(wrongPortalYellow2);
	Portal backPortal = new Portal(this, "Enigme1", new DiscreteCoordinates(7,4), new DiscreteCoordinates(12, 11));
	registerActor(backPortal);
	ImmovableRock rock1 = new ImmovableRock(this, new DiscreteCoordinates(14,36));
	registerActor(rock1);
	ImmovableRock rock2 = new ImmovableRock(this, new DiscreteCoordinates(16,34));
	registerActor(rock2);
	ImmovableRock rock3 = new ImmovableRock(this, new DiscreteCoordinates(17,34));
	registerActor(rock3);
	ImmovableRock rock4 = new ImmovableRock(this, new DiscreteCoordinates(15,32));
	registerActor(rock4);
	ImmovableRock rock5 = new ImmovableRock(this, new DiscreteCoordinates(12,34));
	registerActor(rock5);
	ImmovableRock rock6 = new ImmovableRock(this, new DiscreteCoordinates(15,27));
	registerActor(rock6);
	ImmovableRock rock7 = new ImmovableRock(this, new DiscreteCoordinates(14,27));
	registerActor(rock7);
	ImmovableRock rock8 = new ImmovableRock(this, new DiscreteCoordinates(13,24));
	registerActor(rock8);
	ImmovableRock rock9 = new ImmovableRock(this, new DiscreteCoordinates(21,26));
	registerActor(rock9);
	ImmovableRock rock10 = new ImmovableRock(this, new DiscreteCoordinates(20,24));
	registerActor(rock10);
	ImmovableRock rock11 = new ImmovableRock(this, new DiscreteCoordinates(18,24));
	registerActor(rock11);
	ImmovableRock rock12 = new ImmovableRock(this, new DiscreteCoordinates(23,28));
	registerActor(rock12);
	ImmovableRock rock13 = new ImmovableRock(this, new DiscreteCoordinates(21,19));
	registerActor(rock13);
	ImmovableRock rock14 = new ImmovableRock(this, new DiscreteCoordinates(15,21));
	registerActor(rock14);
	ImmovableRock rock15 = new ImmovableRock(this, new DiscreteCoordinates(18,15));
	registerActor(rock15);
	ImmovableRock rock16 = new ImmovableRock(this, new DiscreteCoordinates(23,13));
	registerActor(rock16);
	RunningShoes runningShoes = new RunningShoes(this, new DiscreteCoordinates(17,37));
	registerActor(runningShoes);
	Door interiorDoor = new Door(this, "Enigme2", new DiscreteCoordinates(7,1), new DiscreteCoordinates(6,32));
	registerActor(interiorDoor);
	Bomb bomb = new Bomb(this, new DiscreteCoordinates(10,19));
	registerActor(bomb);
	Talkable pnj = new Talkable(this, new DiscreteCoordinates(12,8), new Dialog("Usain Bolt : In my youth I was a very good runner, but i'm far too slow to make it in time now...","dialog.1",this), "old.man.4");
	registerActor(pnj);
	Talkable pnj2 = new Talkable(this, new DiscreteCoordinates(12,16), new Dialog("Kid From Tenesse : Rock blows... i'm a country fan.","dialog.1",this), "boy.5");
	registerActor(pnj2);
	Talkable pnj3 = new Talkable(this, new DiscreteCoordinates(21,9), new Dialog("Geralt : There might be something useful in this maze. But I hate portals.","dialog.1",this), "old.man.5");
	registerActor(pnj3);
	Talkable pnj4 = new Talkable(this, new DiscreteCoordinates(23,5), new Dialog("Female quotas : Thought you were stuck here? The devs were nice enough to give you a portal back to the Level Selector.","dialog.1",this), "girl.6");
	registerActor(pnj4);
	Talkable pnj5 = new Talkable(this, new DiscreteCoordinates(22,37), new Dialog("Christopher Colombus : I'm completely lost.","dialog.1",this), "max.ghost.1");
	registerActor(pnj5);
	PressurePlate pressurePlate = new PressurePlate(this, new DiscreteCoordinates(7,5),1.f);
	registerActor(pressurePlate);
	PressurePlate pressurePlate2 = new PressurePlate(this, new DiscreteCoordinates(6,5),1.f);
	registerActor(pressurePlate2);
	Or pressurePlates = new Or(pressurePlate,pressurePlate2);
	SignalRock signalRock= new SignalRock(pressurePlates ,this, new DiscreteCoordinates(7,10));
	registerActor(signalRock);
	SignalRock signalRock2 = new SignalRock(pressurePlates ,this, new DiscreteCoordinates(6,10));
	registerActor(signalRock2);
	ExplodableRock explodableRock1 = new ExplodableRock(this, new DiscreteCoordinates(7,17));
	registerActor(explodableRock1);
	ExplodableRock explodableRock2 = new ExplodableRock(this, new DiscreteCoordinates(6,18));
	registerActor(explodableRock2);
	ExplodableRock explodableRock3 = new ExplodableRock(this, new DiscreteCoordinates(7,18));
	registerActor(explodableRock3);
	ExplodableRock explodableRock4 = new ExplodableRock(this, new DiscreteCoordinates(6,17));
	registerActor(explodableRock4);
	
	return supSuccess;
    }
    
}