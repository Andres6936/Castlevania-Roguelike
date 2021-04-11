package co.castle.ui.graphicsUI;

import java.awt.Image;
import java.util.Vector;

public class GFXChat {
	private final Vector<String> conversations = new Vector<>(10);
	private final Vector<String> names = new Vector<>(10);
	private final Vector<Image> portraits = new Vector<>(10);

	public void add(String name, String conversation) {
		names.add(name);
		conversations.add(conversation);
		portraits.add(null);
	}

	public void add(String name, String conversation, Image portrait) {
		names.add( name );
		conversations.add( conversation );
		portraits.add( portrait );
	}

	public String getConversation( int i ) {
		return conversations.elementAt(i);
	}

	public int getConversations( )
	{
		return conversations.size( );
	}

	public String getName( int i ) {
		return names.elementAt(i);
	}

	public Image getPortrait( int i ) {
		return portraits.elementAt(i);
	}
}
