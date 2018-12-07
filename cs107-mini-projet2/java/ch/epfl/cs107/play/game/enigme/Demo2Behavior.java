package ch.epfl.cs107.play.game.enigme;

import ch.epfl.cs107.play.game.areagame.AreaBehavior;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.window.Window;

public class Demo2Behavior extends AreaBehavior {

	public Demo2Behavior(Window window, String fileName) {
		super(window, fileName);
		for (int x= 0; x < getCells().length; ++x) {
			for (int y = 0; y < getCells()[x].length; ++y) {
				getCells()[x][y] = new Demo2Cell(x,y,Demo2CellType.toType(getBehaviorMap().getRGB(getHeight()-1-y, x)));
			}
		}
		// TODO Auto-generated constructor stub
	}
	
	public int getCameraScaleFactor() {
		return 10;
	}
	
	public Demo2CellType getType(int x, int y) {
		Demo2Cell cell = (Demo2Cell) getCell(x,y);
		return cell.cellType;
	} 

	public enum Demo2CellType {
		NULL(0), WALL(-16777216), // RGB code of black
		DOOR(-65536), // RGB code of red
		WATER(-16776961), // RGB code of blue
		INDOOR_WALKABLE(-1), OUTDOOR_WALKABLE(-14112955);
		final int type;

		Demo2CellType(int type) {
			this.type = type;
		}

		static Demo2CellType toType(int type) {
			switch (type) {
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

	public class Demo2Cell extends Cell {
		private Demo2CellType cellType;
		private Demo2Cell(int x, int y, Demo2CellType type) {
			super(x, y);
			cellType = type;
		}
		
		@Override
		public boolean takeCellSpace() {
			// TODO Auto-generated method stub
			return false;
		}
		@Override
		public boolean isViewInteractable() {
			// TODO Auto-generated method stub
			return false;
		}
		@Override
		public boolean isCellInteractable() {
			// TODO Auto-generated method stub
			return true;
		}
		@Override
		protected boolean canEnter(Interactable entity) {
			// TODO Auto-generated method stub
			if(this.cellType == Demo2CellType.NULL || this.cellType == Demo2CellType.WALL) {
				return false;
			}
			return true;
		}
		@Override
		protected boolean canLeave(Interactable entity) {
			// TODO Auto-generated method stub
			return true;
		}
	}
}