package co.castle.game;

public class CRLException extends Exception {
	private final String message;

	public CRLException(String message) {
		this.message = message;
	}

	public String toString() {
		return message;
	}
}