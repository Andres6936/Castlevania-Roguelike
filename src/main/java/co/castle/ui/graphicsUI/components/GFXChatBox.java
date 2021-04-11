package co.castle.ui.graphicsUI.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import co.castle.conf.gfx.data.Asset;
import co.castle.main.ApplicationGraphics;
import co.castle.ui.graphicsUI.AddornedBorderPanel;

public class GFXChatBox extends AddornedBorderPanel {
	private final JLabel lblImageIcon;
	private final JLabel lblName;
	private final JTextArea txtText;

	public GFXChatBox(Image UPRIGHT, Image UPLEFT, Image DOWNRIGHT, Image DOWNLEFT,
					  Color OUT_COLOR, Color IN_COLOR, int borderWidth,
					  int borderHeight) {
		super(UPRIGHT, UPLEFT, DOWNRIGHT, DOWNLEFT, OUT_COLOR, IN_COLOR, borderWidth,
				borderHeight);
		lblImageIcon = new JLabel();
		lblName = new JLabel();
		txtText = new JTextArea();

		// Get instance of Asset
		Asset asset = ApplicationGraphics.assets;
		lblName.setFont(asset.FONT_TITLE);
		lblName.setForeground(Color.WHITE);

		txtText.setWrapStyleWord(true);
		txtText.setLineWrap(true);
		txtText.setFont(asset.FONT_TEXT);
		txtText.setForeground(Color.WHITE);
		txtText.setEditable(false);
		txtText.setFocusable(false);

		txtText.setOpaque( false );
		lblName.setOpaque( false );
		lblImageIcon.setOpaque( false );
		setOpaque( false );

		lblImageIcon.setVerticalAlignment( SwingConstants.TOP );

		setBorder(
				new EmptyBorder( borderWidth, borderWidth, borderWidth, borderWidth ) );
		setLayout( new BorderLayout( ) );
		( (BorderLayout) getLayout( ) ).setHgap( 10 );
		( (BorderLayout) getLayout( ) ).setVgap( 10 );
		add( lblImageIcon, BorderLayout.WEST );
		add( lblName, BorderLayout.NORTH );
		add( txtText, BorderLayout.CENTER );
		// setBackground(TRANSPARENT_BLUE);
	}

	public void set( Image pimg, String pname, String ptext )
	{
		lblImageIcon.setIcon( new ImageIcon( pimg ) );
		lblName.setText( pname );
		txtText.setText( ptext );
	}
}
