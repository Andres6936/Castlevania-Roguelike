package co.castle.ui;

public abstract class Appearance
/* this must not be serializable for complete decoupling */ {
	private final String serial;

	// public abstract static Appearance getVoidAppearance();

	public Appearance(String serial) {
		this.serial = serial;
	}

	public String getSerial() {
		return serial;
	}
}