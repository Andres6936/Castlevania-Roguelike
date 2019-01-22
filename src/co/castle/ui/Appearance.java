package co.castle.ui;

public abstract class Appearance
/* this must not be serializable for complete decoupling */ {
	private String ID;

	// public abstract static Appearance getVoidAppearance();

	public Appearance( String ID )
	{
		this.ID = ID;
	}

	public String getID( )
	{
		return ID;
	}
}