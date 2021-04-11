package co.castle.feature;

import java.util.Hashtable;

import sz.util.Debug;

public class FeatureFactory {
	private final Hashtable<String, Feature> definitions;

	private static final FeatureFactory singleton = new FeatureFactory();

	public FeatureFactory() {
		definitions = new Hashtable<>(40);
	}

	public static FeatureFactory getFactory() {
		return singleton;
	}

	public Feature buildFeature( String id ) {
		Feature x = definitions.get(id);
		if (x != null)
			return (Feature) x.clone();
		Debug.byebye("Feature " + id + " not found");
		return null;
	}

	public String getDescriptionForID( String id ) {
		Feature x = definitions.get(id);
		if (x != null)
			return x.getDescription();
		else
			return "?";
	}

	public void init( Feature[ ] defs ) {
		for (Feature def : defs) definitions.put(def.getID(), def);
	}
}