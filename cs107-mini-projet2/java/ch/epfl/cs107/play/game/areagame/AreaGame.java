package ch.epfl.cs107.play.game.areagame;

import java.util.HashMap;
import java.util.Map;

import javax.swing.text.BadLocationException;

import ch.epfl.cs107.play.game.Game;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.window.Window;

/**
 * AreaGame is a type of Game displayed in a (MxN) Grid called Area An AreaGame
 * has multiple Areas
 */
abstract public class AreaGame implements Game {

	// Context objects
	private Window window;
	private FileSystem fileSystem;

	/// A map containing all the Area of the Game
	private Map<String, Area> areas;
	/// The current area the game is in
	private Area currentArea;

	/**
	 * Add an Area to the AreaGame list
	 * 
	 * @param a (Area): The area to add, not null
	 */
	protected final void addArea(Area a) {
		// TODO implements me #PROJECT #TUTO
		areas.put(a.getTitle(), a);
	}

	/**
	 * Setter for the current area: Select an Area in the list from its key - the
	 * area is then begin or resume depending if the area is already started or not
	 * and if it is forced
	 * 
	 * @param key        (String): Key of the Area to select, not null
	 * @param forceBegin (boolean): force the key area to call begin even if it was
	 *                   already started
	 * @return (Area): after setting it, return the new current area
	 */
	protected final Area setCurrentArea(String key, boolean forceBegin) {
		// TODO implements me #PROJECT #TUTO
		Area oldArea = currentArea;
		if (currentArea != null) {
			currentArea.suspend();
		}
		currentArea = areas.get(key);

		if (currentArea != null) {
			if (forceBegin == true || currentArea.getVisited()==false) {
				currentArea.begin(window, fileSystem);
			} else {
				currentArea.resume(window, fileSystem);
			}
		} else {
			currentArea = oldArea;
			if (currentArea == null) {
				throw new NullPointerException("Fell out of the world");
			}
		}
		return null;

	}
	protected Area getArea(String room) {
		return areas.get(room);
	}
	protected Area getCurrentArea() {
		return currentArea;
	}

	/** @return (Window) : the Graphic and Audio context */
	protected final Window getWindow() {
		// TODO implements me #PROJECT #TUTO
		return null;
	}

	/** @return (FIleSystem): the linked file system */
	protected final FileSystem getFileSystem() {
		// TODO implements me #PROJECT #TUTO
		return null;
	}

	/// AreaGame implements Playable

	@Override
	public boolean begin(Window window, FileSystem fileSystem) {
		// TODO implements me #PROJECT #TUTO
		this.fileSystem = fileSystem;
		this.window = window;
		areas = new HashMap<>();
		return true;
	}

	@Override
	public void update(float deltaTime) {
		currentArea.update(deltaTime);
		// TODO implements me #PROJECT #TUTO
	}

	@Override
	public void end() {
		// TODO save the game states somewhere
	}

}
