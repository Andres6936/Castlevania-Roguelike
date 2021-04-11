package co.castle.ui.consoleUI.cuts;

import java.util.Vector;

public class CharChat
{
	private Vector conversations = new Vector( 5 );
	private Vector names = new Vector( 5 );

	public void add( String name, String conversation )
	{
		names.add( name );
		conversations.add( conversation );
	}

	public String getConversation( int i )
	{
		return (String) conversations.elementAt( i );
	}

	public int getConversations( )
	{
		return conversations.size( );
	}

	public String getName( int i )
	{
		return (String) names.elementAt( i );
	}

}
