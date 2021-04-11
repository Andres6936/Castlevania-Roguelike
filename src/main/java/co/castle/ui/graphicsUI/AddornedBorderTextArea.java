package co.castle.ui.graphicsUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class AddornedBorderTextArea extends AddornedBorderPanel {
	private final JTextArea textArea;

	public AddornedBorderTextArea(Image UPRIGHT, Image UPLEFT, Image DOWNRIGHT,
								  Image DOWNLEFT, Color OUT_COLOR, Color IN_COLOR,
								  int borderWidth, int borderHeight) {
		super(UPRIGHT, UPLEFT, DOWNRIGHT, DOWNLEFT, OUT_COLOR, IN_COLOR, borderWidth,
				borderHeight);

		textArea = new JTextArea();
		setLayout(new GridLayout(1, 1));
		textArea.setWrapStyleWord( true );
		textArea.setLineWrap( true );
		textArea.setFocusable( false );
		textArea.setEditable( false );
		textArea.setOpaque( false );
		// textArea.setVisible(false);
		setBorder( new EmptyBorder( 32, 32, 32, 32 ) );
		setOpaque( false );
		add( textArea );
	}

	public void setBackground( Color fore )
	{
		if ( textArea != null )
			textArea.setBackground( fore );
	}

	public void setFont( Font font )
	{
		if ( textArea != null )
			textArea.setFont( font );
	}

	public void setForeground( Color fore )
	{
		if ( textArea != null )
			textArea.setForeground( fore );
	}

	public void setText( String text )
	{
		textArea.setText( text );
	}

}
