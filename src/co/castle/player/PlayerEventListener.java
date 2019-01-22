package co.castle.player;

public interface PlayerEventListener
{
	public void informEvent( int code );

	public void informEvent( int code, Object param );
}