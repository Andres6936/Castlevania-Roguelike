package co.castle.game;

public class CRLException extends Exception
{
	private String message;

	public CRLException( String message )
	{
		this.message = message;
	}

	public String toString( )
	{
		return message;
	}
}