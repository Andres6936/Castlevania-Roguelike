package sz.midi;

//MidisLoader.java
//Andrew Davison, April 2005, ad@fivedots.coe.psu.ac.th

/* MidisLoader  stores a collection of MidiInfo objects
in a HashMap whose keys are their names. 

The name and filename for a midi sequence is obtained from a sounds
information file which is loaded when MidisLoader is created.
The file is assumed to be in Sounds/.

MidisLoader allows a specified midi sequence to be played, stopped, 
resumed, looped. A SoundsWatcher can be attached to the sequencer
(not a sequence).

MidisLoader differs from ClipsLoader in containing a Sequencer
object for playing a sequence. Consequently, only a single 
sequence can be played at a time, while many clips can be 
playing together.

A reference to the sequencer is passed to each MidiInfo object,
which are responsible for playing, stopping, resuming and looping
their sequences.
*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

import javax.sound.midi.MetaEventListener;
import javax.sound.midi.MetaMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.Sequencer;
import javax.sound.midi.Synthesizer;
import javax.sound.midi.Transmitter;

public class MidisLoader implements MetaEventListener
{
	private MidiInfo currentMidi = null;
	// reference to currently playing MidiInfo object

	private HashMap midisMap;

	private Sequencer sequencer;

	private SoundsWatcher watcher = null;
	// midi meta-event constant used to signal the end of a track
	private static final int END_OF_TRACK = 47;

	private final static String SOUND_DIR = "";

	public MidisLoader( )
	{
		midisMap = new HashMap( );
		initSequencer( );
	}

	public MidisLoader( String soundsFnm )
	{
		midisMap = new HashMap( );
		initSequencer( );
		loadSoundsFile( soundsFnm );
	}

	public void close( )
	/*
	 * Close down the sequencer (and any playing sequence). This is different from
	 * close() in ClipsLoader in that it is dealing with the sequencer primarily,
	 * rather than a particular midi sequence.
	 */
	{
		stop( ); // stop the playing sequence
		if ( sequencer != null )
		{
			if ( sequencer.isRunning( ) )
				sequencer.stop( );

			sequencer.removeMetaEventListener( this );
			sequencer.close( );
			sequencer = null;
		}
	} // end of close()

	public void load( String name, String fnm )
	// create a MidiInfo object, and store it under name
	{
		if ( midisMap.containsKey( name ) )
			System.out.println( "Error: " + name + "already stored" );
		else if ( sequencer == null )
			System.out.println( "No sequencer for: " + name );
		else
		{
			midisMap.put( name, new MidiInfo( name, fnm, sequencer ) );
			System.out.println( "-- " + name + "/" + fnm );
		}
	} // end of load()

	public void meta( MetaMessage meta )
	/*
	 * Called when a meta event occurs during sequence playing. The code only deals
	 * with an end-of-track event, which can be triggered by the MidisInfo object
	 * when a sequence reaches its end _or_ is stopped. However, a sequence at its
	 * end may be looping, and so this is checked by calling tryLooping() in
	 * MidiInfo. If there is a watcher, it is notified of the status.
	 */
	{
		if ( meta.getType( ) == END_OF_TRACK )
		{
			String name = currentMidi.getName( );
			// System.out.println(" END_OF_TRACK for " + name);
			boolean hasLooped = currentMidi.tryLooping( ); // music still looping?
			if ( !hasLooped ) // no it's finished
				currentMidi = null;

			if ( watcher != null )
			{ // tell the watcher
				if ( hasLooped ) // the music is playing again
					watcher.atSequenceEnd( name, SoundsWatcher.REPLAYED );
				else // the music has finished
					watcher.atSequenceEnd( name, SoundsWatcher.STOPPED );
			}
		}
	} // end of meta()

	public void pause( )
	{
		if ( currentMidi != null )
			currentMidi.pause( );
		else
			System.out.println( "No music to pause" );
	} // end of pause()

	// ----------- manipulate a particular midi sequence --------

	public void play( String name, boolean toLoop )
	// play (perhaps loop) the sequence
	{
		MidiInfo mi = (MidiInfo) midisMap.get( name );
		if ( mi == null )
			System.out.println( "Error: " + name + "not stored" );
		else
		{
			if ( currentMidi != null )
				System.out.println(
						"Sorry, " + currentMidi.getName( ) + " already playing" );
			else
			{
				currentMidi = mi; // store a reference to playing midi
				mi.play( toLoop );
			}
		}
	} // end of play()

	public void playFile( String filename, boolean toLoop )
	// play (perhaps loop) the sequence
	{
		System.out.println( "playFile " + filename );
		MidiInfo mi = new MidiInfo( "TEMP", filename, sequencer );
		if ( currentMidi != null )
		{
			System.out.println( "Sorry, " + currentMidi.getName( ) + " already playing" );
			stop( );
		}
		currentMidi = mi; // store a reference to playing midi
		mi.play( toLoop );

	} // end of play()

	public void resume( )
	{
		if ( currentMidi != null )
			currentMidi.resume( );
		else
			System.out.println( "No music to resume" );
	} // end of resume()

	public void setWatcher( SoundsWatcher sw )
	{
		watcher = sw;
	}

	public void stop( )
	{
		if ( currentMidi != null )
			currentMidi.stop( ); // triggers an 'end-of-track' meta event
		else // which causes meta() to be called here
			System.out.println( "No music playing" );
	} // end of stop()

	private void initSequencer( )
	/*
	 * Set up the MIDI sequencer, and the sequencer's meta-event listener. No
	 * synthesizer is used here.
	 */
	{
		try
		{
			sequencer = MidiSystem.getSequencer( );
			if ( sequencer == null )
			{
				System.out.println( "Cannot get a sequencer" );
				return;
			}

			sequencer.open( );
			sequencer.addMetaEventListener( this );

			// maybe the sequencer is not the same as the synthesizer
			// so link sequencer --> synth (this is required in J2SE 1.5)
			if ( !( sequencer instanceof Synthesizer ) )
			{
				System.out.println( "Linking the MIDI sequencer and synthesizer" );
				Synthesizer synthesizer = MidiSystem.getSynthesizer( );
				Receiver synthReceiver = synthesizer.getReceiver( );
				Transmitter seqTransmitter = sequencer.getTransmitter( );
				seqTransmitter.setReceiver( synthReceiver );
			}
		}
		catch ( MidiUnavailableException e )
		{
			System.out.println( "No sequencer available" );
			sequencer = null;
		}
	} // end of initSequencer()

	// ---------------------------------------------------

	private void loadSoundsFile( String soundsFnm )
	/*
	 * The format of the input lines are: <name> <fnm> // a single sound file and
	 * blank lines and comment lines.
	 */
	{
		String sndsFNm = SOUND_DIR + soundsFnm;
		System.out.println( "Reading file: " + sndsFNm );
		try
		{
			InputStream in = this.getClass( ).getResourceAsStream( sndsFNm );
			BufferedReader br = new BufferedReader( new InputStreamReader( in ) );
			// BufferedReader br = new BufferedReader( new FileReader(sndsFNm));
			StringTokenizer tokens;
			String line, name, fnm;
			while ( ( line = br.readLine( ) ) != null )
			{
				if ( line.length( ) == 0 ) // blank line
					continue;
				if ( line.startsWith( "//" ) ) // comment
					continue;

				tokens = new StringTokenizer( line );
				if ( tokens.countTokens( ) != 2 )
					System.out.println( "Wrong no. of arguments for " + line );
				else
				{
					name = tokens.nextToken( );
					fnm = tokens.nextToken( );
					load( name, fnm );
				}
			}
			br.close( );
		}
		catch ( IOException e )
		{
			System.out.println( "Error reading file: " + sndsFNm );
			System.exit( 1 );
		}
	} // end of loadSoundsFile()

} // end of MidisLoader class