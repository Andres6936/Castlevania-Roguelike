package co.castle.npc;

import co.castle.item.Item;
import sz.util.Util;

public class Hostage extends NPC
{
	private String hostrinchLevel;
	private Item itemReward;
	private boolean rescued;
	private String rescuedMessage;
	private int reward;

	private static String[ ] HOSTAGE_TIPS = new String[ ]
	{	"If you dont kill the Aluras quickly, you are dead",
		"The Nova Skeleton has a deadly secret, dont let him let you know about it",
		"It is better to run from Iron Golems!", "All caves have two keys to find!",
		"You must not face Dracula unless you have enough shields",
		"Dracula turns into a Demon uppon death",
		"I met a Knight which could use all the ultimate weapon skills" };

	public Hostage( NPCDefinition def )
	{
		super( def );
	}

	public String getHostrinchLevel( )
	{
		return hostrinchLevel;
	}

	public Item getItemReward( )
	{
		return itemReward;
	}

	public int getReward( )
	{
		return reward;
	}

	public String getTalkMessage( )
	{
		if ( !rescued )
			return super.getTalkMessage( );
		else
			return rescuedMessage;
	}

	public boolean isRescued( )
	{
		return rescued;
	}

	public void setHostrinchLevel( String hostrinchLevel )
	{
		this.hostrinchLevel = hostrinchLevel;
	}

	public void setItemReward( Item itemReward )
	{
		this.itemReward = itemReward;
	}

	public void setRescued( boolean rescued )
	{
		this.rescued = rescued;
		rescuedMessage = Util.randomElementOf( HOSTAGE_TIPS );
	}

	public void setReward( int l )
	{
		reward = l;
	}

}
