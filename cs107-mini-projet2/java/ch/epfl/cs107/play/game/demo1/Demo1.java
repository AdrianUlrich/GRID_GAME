package ch.epfl.cs107.play.game.demo1;

import java.awt.Color;

import ch.epfl.cs107.play.game.Game;
import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.actor.GraphicsEntity;
import ch.epfl.cs107.play.game.actor.ShapeGraphics;
import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.game.demo1.actor.MovingRock;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.Circle;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Keyboard;
import ch.epfl.cs107.play.window.Window;

public class Demo1 implements Game {
	private Actor actor1;
	private Actor actor2;
	private TextGraphics txtBOUM;
	private Window window;
	private FileSystem fileSystem;

	@Override
	public boolean begin(Window window, FileSystem fileSystem) {
		// TODO Auto-generated method stub
		this.fileSystem = fileSystem;
		this.window = window;
		Vector vector = new Vector(0.25f, 0.25f);
		float radius = 0.2f;
		actor1 = new GraphicsEntity(Vector.ZERO, new ShapeGraphics(new Circle(radius), null, Color.RED, 0.005f));
		actor2 = new MovingRock(vector, "Hello, I'm a moving rock !");
		txtBOUM = new TextGraphics("BOUM!",0.1f,Color.RED);
		txtBOUM.setBold(true);
		txtBOUM.setParent(actor1);
		return true;
	}

	@Override
	public void end() {
		// TODO Auto-generated method stub

	}

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return "Demo 1";
	}

	@Override
	public void update(float deltaTime) {
		// TODO Auto-generated method stub
		// ici donner un peu de vie au premier acteur si nécessaire
		actor1.draw(window);
		actor2.draw(window);
		Keyboard keyboard = window.getKeyboard();
		Button downArrow = keyboard.get(Keyboard.DOWN);
		if (downArrow.isDown()) {
			actor2.update(0.5f);
		}
		if (actor2.getPosition().sub(actor1.getPosition()).getLength()<=0.2f) {
		    txtBOUM.draw(window);		
                }
	}

	@Override
	public int getFrameRate() {
		// TODO Auto-generated method stub
		return 24;
	}
}
