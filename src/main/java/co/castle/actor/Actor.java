package co.castle.actor;

import java.util.Enumeration;
import java.util.Hashtable;

import co.castle.action.Action;
import co.castle.ai.ActionSelector;
import co.castle.ai.SelectorFactory;
import co.castle.game.SFXManager;
import co.castle.level.Level;
import co.castle.ui.Appearance;
import sz.util.Debug;
import sz.util.Position;
import sz.util.PriorityEnqueable;

public class Actor implements Cloneable, java.io.Serializable, PriorityEnqueable {
	private boolean aWannaDie;
	private final Hashtable<String, Boolean> hashFlags = new Hashtable<String, Boolean>();

	protected static final SelectorFactory selectorFactory = SelectorFactory.getSelectorFactory();

	private int hoverHeight;
	private boolean isJumping;
	private /* transient */ int nextTime = 10;
	private /* transient */ Position position = new Position(0, 0, 0);

	private int startingJumpingHeight;

	protected transient Appearance appearance;

	protected Hashtable<String, Integer> hashCounters = new Hashtable<>();

	protected Level level;

	protected ActionSelector selector;

	public void act() {
		Action x = getSelector().selectAction(this);
		execute(x);
	}

	public Object clone( )
	{
		try
		{
			Actor x = (Actor) super.clone( );
			if ( position != null )
				x.setPosition( new Position( position.x, position.y, position.z ) );
			return x;
		}
		catch ( CloneNotSupportedException cnse )
		{
			Debug.doAssert( false, "failed class cast, Feature.clone()" );
		}
		return null;
	}

	public void die( ) {
		// Request to be removed from any dispatcher or structure.
		aWannaDie = true;
	}

	public void doJump( int startingJumpingHeight )
	{
		this.isJumping = true;
		this.startingJumpingHeight = startingJumpingHeight;
	}

	public void execute( Action x )
	{
		if ( x != null )
		{
			x.setPerformer( this );
			if ( x.canPerform( this ) )
			{
				if ( x.getSFX( ) != null )
					SFXManager.play( x.getSFX( ) );
				x.execute( );
				// Debug.say("("+x.getCost()+")");
				setNextTime( x.getCost( ) );
			}
		}
		else
		{
			setNextTime( 50 );
		}
		updateStatus( );
	}

	public Appearance getAppearance( )
	{
		return appearance;
	}

	public int getCost( )
	{
		// Debug.say("Cost of "+getDescription()+" "+ nextTime);
		return nextTime;
	}

	public int getCounter( String counterID ) {
		Integer val = hashCounters.get(counterID);
		if (val == null)
			return -1;
		else
			return val;
	}

	public String getDescription( )
	{
		return "";
	}

	public boolean getFlag( String flagID ) {
		Boolean val = hashFlags.get(flagID);
		return val != null && val;
	}

	public int getHoverHeight( )
	{
		return hoverHeight;
	}

	public Level getLevel( )
	{
		return level;
	}

	public Position getPosition( )
	{
		return position;
	}

	public ActionSelector getSelector( )
	{
		return selector;
	}

	public int getStandingHeight( )
	{
		if ( isJumping )
		{
			return startingJumpingHeight + 2;
		}
		if ( level.getMapCell( getPosition( ) ) != null )
			return level.getMapCell( getPosition( ) ).getHeight( ) + getHoverHeight( );
		else
			return getHoverHeight( );
	}

	public boolean hasCounter( String counterID )
	{
		return getCounter( counterID ) > 0;
	}

	public boolean isJumping( )
	{
		return isJumping;
	}

	public void message( String mess )
	{
	}

	public void reduceCost( int value )
	{
		// Debug.say("Reducing cost of "+getDescription()+"by"+value+" (from
		// "+nextTime+")");
		nextTime = nextTime - value;
	}

	public void setAppearance( Appearance value )
	{
		appearance = value;
	}

	public void setCounter( String counterID, int turns ) {
		hashCounters.put(counterID, turns);
	}

	public void setFlag( String flagID, boolean value ) {
		hashFlags.put(flagID, value);
	}

	public void setHoverHeight( int hoverHeight )
	{
		this.hoverHeight = Math.max(hoverHeight, 0);
	}

	public void setLevel( Level what )
	{
		level = what;
	}

	public void setNextTime( int value )
	{
		// Debug.say("Next time for "+getDescription()+" "+ value);
		nextTime = value;
	}

	public void setPosition( int x, int y, int z )
	{
		position.x = x;
		position.y = y;
		position.z = z;
	}

	public void setPosition( Position p )
	{
		position = p;
	}

	public void setSelector( ActionSelector value )
	{
		selector = value;
	}

	public void stopJump( )
	{
		this.isJumping = false;
	}

	public void updateStatus( ) {
		Enumeration<String> countersKeys = hashCounters.keys();
		while (countersKeys.hasMoreElements()) {
			String key = countersKeys.nextElement();
			Integer counter = hashCounters.get(key);
			if (counter == 0) {
				hashCounters.remove(key);
			} else {
				hashCounters.put(key, counter - 1);
			}
		}
	}

	public boolean wannaDie( )
	{
		return aWannaDie;
	}

}