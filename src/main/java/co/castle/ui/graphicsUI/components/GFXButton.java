package co.castle.ui.graphicsUI.components;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.JPanel;

public class GFXButton extends JPanel implements MouseListener {
	private boolean habilitado = true;
	private boolean hovering;
	private final Image imgActivado;
	private final Image imgDesactivado;
	private final Image imgHover;

	private final Vector<ActionListener> objListeners = new Vector<>();

	public GFXButton(Image iimgActivado) {
		this(iimgActivado, iimgActivado);
	}

	public GFXButton(Image iimgActivado, Image iimgDesactivado) {
		super();
		imgActivado = iimgActivado;
		imgHover = iimgActivado;
		imgDesactivado = iimgDesactivado;
		Dimension preferredSize = new Dimension( imgActivado.getWidth( this ),
				imgActivado.getHeight( this ) );
		// Dimension preferredSize = new Dimension(200, 200);
		setSize( preferredSize );
		setPreferredSize( preferredSize );
		addMouseListener( this );
		this.setOpaque( false );
		// this.setBackground(java.awt.Color.RED);
	}

	public void addActionListener( ActionListener iobjListener )
	{
		objListeners.add( iobjListener );
	}

	public void mouseClicked( MouseEvent e )
	{
		if ( habilitado )
		{
			hovering = false;
			fireActionEvent( );
		}
	}

	public void mouseEntered( MouseEvent e )
	{
		if ( habilitado )
		{
			setCursor( Cursor.getPredefinedCursor( Cursor.HAND_CURSOR ) );
			hovering = true;
			repaint( );
		}
	}

	public void mouseExited( MouseEvent e )
	{
		if ( habilitado )
		{
			setCursor( Cursor.getPredefinedCursor( Cursor.DEFAULT_CURSOR ) );
			hovering = false;
			repaint( );
		}
	}

	public void mousePressed( MouseEvent e )
	{
	}

	public void mouseReleased( MouseEvent e )
	{
	}

	public void paintComponent( Graphics g )
	{
		// super.paintComponent(g);
		if ( hovering )
		{
			g.drawImage( imgHover, 0, 0, this );
		}
		else
		{
			if ( habilitado )
			{
				g.drawImage( imgActivado, 0, 0, this );
			}
			else
				g.drawImage( imgDesactivado, 0, 0, this );
		}
	}

	public void setEnabled( boolean value )
	{
		super.setEnabled( value );
		habilitado = value;
	}

	private void fireActionEvent( ) {
		for (ActionListener objListener : objListeners) {
			objListener.actionPerformed(new ActionEvent(this, 1, ""));
		}
	}

}
