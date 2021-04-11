package co.castle.level;

import java.util.Hashtable;

import co.castle.game.CRLException;

public class MapCellFactory {
	private final Hashtable<String, Cell> definitions;
	private static final MapCellFactory singleton = new MapCellFactory();

	/*
	 * public Cell buildMapCell (String id){ Cell x = (Cell) definitions.get(id);
	 * return x.clone(); }
	 */

	public MapCellFactory() {
		definitions = new Hashtable<>(40);
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

	public void init( Cell[ ] defs ) {
		for (Cell def : defs) definitions.put(def.getID(), def);
	}

}