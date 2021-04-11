package co.castle.ui.graphicsUI.components;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JPanel;

public class GFXButton extends JPanel implements MouseListener
{
	private boolean habilitado = true;
	private boolean hovering;
	private Image imgActivado;
	private Image imgDesactivado;
	private Image imgHover;

	private Vector objListeners = new Vector( );

	public GFXButton( GFXButton base )
	{
		imgActivado = base.imgActivado;
		imgDesactivado = base.imgDesactivado;
		imgHover = base.imgHover;
		setLocation( base.getLocation( ) );
		Dimension preferredSize = new Dimension( imgActivado.getWidth( this ),
				imgActivado.getHeight( this ) );
		setSize( preferredSize );
		setPreferredSize( preferredSize );
		addMouseListener( this );
	}

	public GFXButton( Image iimgActivado )
	{
		this( iimgActivado, iimgActivado );
	}

	public GFXButton( Image iimgActivado, Image iimgDesactivado )
	{
		super( );
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

	public GFXButton( Image iimgActivado, Image iimgDesactivado, Image iimgHover )
	{
		this( iimgActivado, iimgDesactivado );
		imgHover = iimgHover;
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

	private void fireActionEvent( )
	{
		for ( Iterator iter = objListeners.iterator( ); iter.hasNext( ); )
		{
			ActionListener element = (ActionListener) iter.next( );
			element.actionPerformed( new ActionEvent( this, 1, "" ) );
		}
	}

}
