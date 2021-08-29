package co.castle.feature;

import java.util.Hashtable;

import co.castle.data.Features;
import sz.util.Debug;

public class FeatureFactory {
	private final Hashtable<String, Feature> definitions;

	private static final FeatureFactory singleton = new FeatureFactory();

	public FeatureFactory() {
		definitions = new Hashtable<>(40);
		for (Feature def : Features.getFeatureDefinitions()) definitions.put(def.getID(), def);
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

}