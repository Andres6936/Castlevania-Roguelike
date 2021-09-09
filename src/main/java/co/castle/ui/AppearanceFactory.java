package co.castle.ui;

import sz.util.Debug;

import java.util.Hashtable;

public class AppearanceFactory {
	private final Hashtable<String, Appearance> definitions;
	private static final AppearanceFactory singleton = new AppearanceFactory();

	public AppearanceFactory() {
		definitions = new Hashtable<>(40);
	}

	public static AppearanceFactory getAppearanceFactory() {
		return singleton;
	}

	public void addDefinition( Appearance definition )
	{
		definitions.put(definition.getSerial(), definition);
	}

	public Appearance getAppearance( String id ) {
		Appearance ret = definitions.get(id);
		Debug.doAssert(ret != null, "Couldnt find the appearance " + id);
		return ret;
	}

}