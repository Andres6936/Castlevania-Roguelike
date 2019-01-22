package co.castle.npc;

import co.castle.ai.ActionSelector;
import co.castle.ai.SelectorFactory;
import co.castle.ui.Appearance;
import co.castle.ui.AppearanceFactory;
import sz.csi.textcomponents.ListItem;

public class NPCDefinition
{
	private String angryMessage;
	private Appearance appearance;
	private int attack;
	private String dangerMessage;
	private ActionSelector defaultSelector;
	private String description;
	private int hits;
	private String ID;
	private boolean isHostage;
	private boolean isPriest;
	private ListItem sightListItem;
	private String talkMessage;

	public NPCDefinition(	String pID, String pDescription, String pAppearance,
							String pDefaultSelector, String pTalkMessage, int pAttack,
							int pHits, String pAngryMessage, String pDangerMessage,
							boolean pHostage, boolean pPriest )
	{
		ID = pID;
		description = pDescription;
		appearance = AppearanceFactory.getAppearanceFactory( )
				.getAppearance( pAppearance );
		defaultSelector = SelectorFactory.getSelectorFactory( )
				.createSelector( pDefaultSelector );
		// sightListItem = new BasicListItem(appearance.getChar(),
		// appearance.getColor(), description);
		talkMessage = pTalkMessage;

		attack = pAttack;
		hits = pHits;
		angryMessage = pAngryMessage;
		dangerMessage = pDangerMessage;
		isHostage = pHostage;
		isPriest = pPriest;
	}

	public String getAngryMessage( )
	{
		return angryMessage;
	}

	public Appearance getAppearance( )
	{
		return appearance;
	}

	public int getAttack( )
	{
		return attack;
	}

	public String getDangerMessage( )
	{
		return dangerMessage;
	}

	public ActionSelector getDefaultSelector( )
	{
		return defaultSelector;
	}

	public String getDescription( )
	{
		return description;
	}

	public int getHits( )
	{
		return hits;
	}

	public String getID( )
	{
		return ID;
	}

	public ListItem getSightListItem( )
	{
		return sightListItem;
	}

	public String getTalkMessage( )
	{
		return talkMessage;
	}

	public boolean isHostage( )
	{
		return isHostage;
	}

	public boolean isPriest( )
	{
		return isPriest;
	}

	public void setHostage( boolean isHostage )
	{
		this.isHostage = isHostage;
	}

	public void setPriest( boolean isPriest )
	{
		this.isPriest = isPriest;
	}

}
