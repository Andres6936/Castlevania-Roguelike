package co.castle.level;

import java.util.Hashtable;

import co.castle.data.Cells;
import co.castle.game.CRLException;

public class MapCellFactory {
	private final Hashtable<String, Cell> definitions = new Hashtable<>(40);
	private static final MapCellFactory singleton = new MapCellFactory();

	/*
	 * public Cell buildMapCell (String id){ Cell x = (Cell) definitions.get(id);
	 * return x.clone(); }
	 */

	public MapCellFactory() {
		for (Cell def : Cells.getCellDefinitions()) {
			definitions.put(def.getID(), def);
		}
	}

	public static MapCellFactory getMapCellFactory( )
	{
		return singleton;
	}

	public void addDefinition( Cell definition )
	{
		definitions.put( definition.getID( ), definition );
	}

	public Cell getMapCell( String id ) throws CRLException {
		Cell ret = definitions.get(id);
		if (ret != null)
			return ret;
		throw new CRLException("MapCellID " + id + " not found");
	}
}