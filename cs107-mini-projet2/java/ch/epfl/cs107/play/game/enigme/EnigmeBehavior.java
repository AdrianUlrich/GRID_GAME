package ch.epfl.cs107.play.game.enigme;

import ch.epfl.cs107.play.game.areagame.AreaBehavior;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.window.Window;

public class EnigmeBehavior extends AreaBehavior {

    public EnigmeBehavior(Window window, String fileName) {
	super(window, fileName);
	for (int x = 0; x < getCells().length; ++x) {
	    for (int y = 0; y < getCells()[x].length; ++y) {
		getCells()[x][y] = new EnigmeCell(x, y,
			EnigmeCellType.toType(getBehaviorMap().getRGB(getHeight() - 1 - y, x)));
	    }
	}
    }

    public int getCameraScaleFactor() {
	return 10;
    }

    public EnigmeCellType getType(int x, int y) {
	EnigmeCell cell = (EnigmeCell) getCell(x, y);
	return cell.cellType;
    }

    public enum EnigmeCellType {
	NULL(0), WALL(-16777216), // RGB code of black
	DOOR(-65536), // RGB code of red
	WATER(-16776961), // RGB code of blue
	INDOOR_WALKABLE(-1), OUTDOOR_WALKABLE(-14112955);
	final int type;

	EnigmeCellType(int type) {
	    this.type = type;
	}

	static EnigmeCellType toType(int rgb) {
	    switch (rgb) {
	    case -16777216:
		return WALL;

	    case -65536:
		return DOOR;

	    case -16776961:
		return WATER;

	    case -1:
		return INDOOR_WALKABLE;

	    case -14112955:
		return OUTDOOR_WALKABLE;

	    default:
		return NULL;
	    }
	}
    }

    public class EnigmeCell extends Cell {
	private EnigmeCellType cellType;

	private EnigmeCell(int x, int y, EnigmeCellType type) {
	    super(x, y);
	    cellType = type;
	}

	@Override
	public boolean takeCellSpace() {
	    // Cells already have a way to occupy a cell canEnter(Interactable)
	    return false;
	}

	@Override
	public boolean isViewInteractable() {
	    // Cells refuse view interactions (ranged)
	    return false;
	}

	@Override
	public boolean isCellInteractable() {
	    // Cells accept contact interaction (same-cell)
	    return true;
	}

	@Override
	protected boolean canEnter(Interactable entity) {
	    // You can't enter a wall or fall out of the world
	    if (this.cellType == EnigmeCellType.NULL || this.cellType == EnigmeCellType.WALL) {
		return false;
	    }
	    return !isOccupied();
	}

	@Override
	protected boolean canLeave(Interactable entity) {
	    // You can always leave a cell for the moment
	    return true;
	}

	@Override
	public void acceptInteraction(AreaInteractionVisitor v) {
	    // TODO Auto-generated method stub

	}
    }
}