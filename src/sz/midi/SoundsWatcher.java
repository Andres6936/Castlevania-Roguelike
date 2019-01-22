package sz.midi;

//SoundsWatcher.java
//Andrew Davison, April 2005, ad@fivedots.coe.psu.ac.th

/* 
*/

public interface SoundsWatcher
{
	public final static int REPLAYED = 1;
	// constants used by SoundsWatcher method, atSequenceEnd()
	public final static int STOPPED = 0;

	void atSequenceEnd( String filename, int status );
}
