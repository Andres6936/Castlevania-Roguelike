package co.castle.feature;

import java.util.Hashtable;

import sz.util.Debug;

public class SmartFeatureFactory {
	private final Hashtable<String, SmartFeature> definitions;
	private static final SmartFeatureFactory singleton = new SmartFeatureFactory();

	public SmartFeatureFactory() {
		definitions = new Hashtable<>(40);
	}

	public static SmartFeatureFactory getFactory() {
		return singleton;
	}

	public SmartFeature buildFeature( String id ) {
		SmartFeature x = definitions.get(id);
		if (x != null)
			return (SmartFeature) x.clone();
		Debug.byebye("SmartFeature " + id + " not found");
		return null;
	}

	public void init( SmartFeature[ ] defs ) {
		for (SmartFeature def : defs) definitions.put(def.getID(), def);
	}
}
