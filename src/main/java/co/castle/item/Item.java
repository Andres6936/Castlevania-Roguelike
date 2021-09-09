package co.castle.item;

import co.castle.player.Consts;
import co.castle.player.Player;
import co.castle.ui.Appearance;
import co.castle.ui.consoleUI.CharAppearance;
import co.castle.ui.graphicsUI.GFXAppearance;
import sz.csi.textcomponents.MenuItem;
import sz.gadgets.GFXMenuItem;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

public class Item implements Serializable, MenuItem, GFXMenuItem {
	private final String defID;
	private transient ItemDefinition definition;
	private final ArrayList<Modifier> postmodifiers = new ArrayList<>(10);
	private final ArrayList<Modifier> premodifiers = new ArrayList<>(10);
	// Status
	private int remainingTurnsToReload;
	protected Hashtable<String, Integer> hashCounters = new Hashtable<>();

	public static boolean shopMode = false;

	public Item(ItemDefinition itemDef) {
		definition = itemDef;
		defID = definition.getID();
		reload();
	}

	public void addPostModifier( Modifier post )
	{
		postmodifiers.add( post );
	}

	public void addPreModifier( Modifier pre )
	{
		premodifiers.add( pre );
	}

	public Appearance getAppearance( )
	{
		return getDefinition( ).getAppearance( );
	}

	/*
	 * public ListItem getSightListItem() {
	 * ((BasicListItem)getDefinition().getSightListItem()).setRow(getDescription());
	 * return getDefinition().getSightListItem(); }
	 */

	public int getAttack( )
	{
		int ret = getDefinition( ).getAttack( ) + getModifiersAttackBonus( );
		if ( getDefinition( ).getAttack( ) > 0 && ret <= 0 )
			return 1;
		else
			return ret;
	}

	public int getAttackCost( )
	{
		return getDefinition( ).getAttackCost( ) + getModifiersAttackCost( );
	}

	public String getAttackSound( )
	{
		return getDefinition( ).getAttackSound( );
	}

	public String getAttributesDescription( )
	{
		String base = getDescription( );
		if ( getRemainingTurnsToReload( ) > 0 )
		{
			base += " {" + getRemainingTurnsToReload( ) + "}";
		}
		if ( getAttack( ) > 0 || getDefense( ) > 0 || getRange( ) > 1
				|| getVerticalRange( ) > 0 )
			base += " (";
		if ( getAttack( ) > 0 )
		{
			if ( hasCounter( Consts.C_WEAPON_ENCHANTMENT ) )
			{
				base += "ATK:" + getAttack( ) + "+2 ";
			}
			else
			{
				base += "ATK:" + getAttack( ) + " ";
			}
		}

		if ( getDefense( ) > 0 )
			base += "DEF:" + getDefense( ) + " ";
		if ( getCoverage( ) > 0 )
			base += "COV:" + getCoverage( ) + "% ";
		if ( getRange( ) > 1 || getVerticalRange( ) > 0 )
			if ( getVerticalRange( ) > 0 )
				base += "RNG:" + getRange( ) + "," + getVerticalRange( );
			else
				base += "RNG:" + getRange( );
		if ( definition.getReloadCostGold( ) > 0 )
		{
			base += " RLD:" + definition.getReloadCostGold( ) + "$";
		}
		if ( getAttack( ) > 0 || getDefense( ) > 0 || getRange( ) > 1
				|| getVerticalRange( ) > 0 )
			base += ")";
		if ( isTwoHanded( ) )
		{
			base += "(2H)";
		}
		return base;
	}

	public int getCounter( String counterID )
	{
		Integer val = hashCounters.get(counterID);
		if (val == null)
			return -1;
		else
			return val;
	}

	public int getCoverage( )
	{
		return getDefinition( ).getCoverage( );
	}

	public int getDefense( )
	{
		return getDefinition( ).getDefense( ) + getModifiersDefenseBonus( );
	}

	public ItemDefinition getDefinition( )
	{
		if ( definition == null )
		{
			definition = ItemFactory.getItemFactory( ).getDefinition( defID );
		}
		return definition;
	}

	public String getDescription( ) {
		String description = "";
		for (Modifier premodifier : premodifiers) {
			description += premodifier.getDescription();
		}
		description += getDefinition().getDescription();
		for (Modifier postmodifier : postmodifiers) {
			description += postmodifier.getDescription();
		}
		return description;

	}

	public String getEffectOnAcquire( )
	{
		return getDefinition( ).getEffectOnAcquire( );
	}

	public String getEffectOnUse( )
	{
		return getDefinition( ).getEffectOnUse( );
	}

	public int getFeatureTurns( )
	{
		return getDefinition( ).getFeatureTurns( );
	}

	public String getFullID( ) {
		String toAddID = getDefinition().getID();
		for (Modifier premodifier : premodifiers) {
			toAddID += premodifier.getID();
		}
		for (Modifier postmodifier : postmodifiers) {
			toAddID += postmodifier.getID();
		}
		return toAddID;
	}

	public int getGoldPrice( )
	{
		double modifiersGold = 1 + getModifiersGoldMod( );
		if ( modifiersGold == 1 )
			return getDefinition( ).getGoldPrice( );
		else
			return (int) ( Math
					.round( getDefinition( ).getGoldPrice( ) * getModifiersGoldMod( ) ) );
	}

	/* Unsafe, Coupled */
	public char getMenuChar( )
	{
		return ( (CharAppearance) getDefinition( ).getAppearance( ) ).getChar( );
	}

	/* Unsafe, Coupled */
	public int getMenuColor( )
	{
		return ( (CharAppearance) getDefinition( ).getAppearance( ) ).getColor( );
	}

	public String getMenuDescription( )
	{
		if ( shopMode )
			return getAttributesDescription( ) + " ["
					+ getDefinition( ).getMenuDescription( ) + "] ($" + getGoldPrice( )
					+ ")";
		else
			return getAttributesDescription( );
	}

	/*
	 * public String getEffectOnStep() { return getDefinition().getEffectOnStep(); }
	 */

	public String getMenuDetail( )
	{
		return null;
	}

	public Image getMenuImage( )
	{
		return ( (GFXAppearance) getAppearance( ) ).getImage( );

	}

	public String getPlacedSmartFeature( )
	{
		return getDefinition( ).getPlacedSmartFeature( );
	}

	public int getRange( )
	{
		return getDefinition( ).getRange( ) + getModifiersRangeBonus( );
	}

	public int getReloadTurns( )
	{
		return getDefinition( ).getReloadTurns( );
	}

	public int getRemainingTurnsToReload( )
	{
		return remainingTurnsToReload;
	}

	public String getShopDescription( )
	{
		return getDefinition( ).getShopDescription( );
	}

	/*
	 * public String getThrowMessage() { return getDefinition().getThrowMessage(); }
	 */

	public int getThrowRange( )
	{
		return getDefinition( ).getThrowRange( );
	}

	public String getUseMessage( )
	{
		return getDefinition( ).getUseMessage( );
	}

	public int getVerticalRange( )
	{
		return getDefinition( ).getVerticalRange( );
	}

	public String getWeaponCategory( )
	{
		return getDefinition( ).getWeaponCategory( );
	}

	public boolean hasCounter( String counterID )
	{
		return getCounter( counterID ) > 0;
	}

	public boolean hasCounters( )
	{
		return hashCounters.size( ) > 0;
	}

	public boolean isHarmsUndead( )
	{
		return getDefinition( ).isHarmsUndead( ) || modifiersHarmUndead( );
	}

	public boolean isSlicesThrough( )
	{
		return getDefinition( ).isSlicesThrough( ) || modifiersSliceThru( );
	}

	public boolean isTwoHanded( )
	{
		return getDefinition( ).isTwoHanded( );
	}

	public boolean isVisible( )
	{
		return !getDefinition().getAppearance().getSerial().equals("VOID");
	}

	public void reduceCounters( Player p ) {
		Enumeration<String> countersKeys = hashCounters.keys();
		while (countersKeys.hasMoreElements()) {
			String key = countersKeys.nextElement();
			Integer counter = hashCounters.get(key);
			if (counter == 0) {
				if (key.equals(Consts.C_WEAPON_ENCHANTMENT)) {
					p.getLevel().addMessage(
							"Your " + getDescription() + " stops glowing.");
				}
				hashCounters.remove(key);
			} else {
				hashCounters.put(key, counter - 1);
			}
		}
	}

	public void reload( )
	{
		setRemainingTurnsToReload( getDefinition( ).getReloadTurns( ) );
	}

	public void setCounter( String counterID, int turns ) {
		hashCounters.put(counterID, turns);
	}

	public void setRemainingTurnsToReload( int value )
	{
		remainingTurnsToReload = value;
	}

	private int getModifiersAttackBonus( ) {
		int ret = 0;
		for (Modifier premodifier : premodifiers) {
			ret += premodifier.getAtkBonus();
		}
		for (Modifier postmodifier : postmodifiers) {
			ret += postmodifier.getAtkBonus();
		}
		return ret;
	}

	private int getModifiersAttackCost( ) {
		int ret = 0;
		for (Modifier premodifier : premodifiers) {
			ret += premodifier.getAtkCostBonus();
		}
		for (Modifier postmodifier : postmodifiers) {
			ret += postmodifier.getAtkCostBonus();
		}
		return ret;
	}

	private int getModifiersDefenseBonus( ) {
		int ret = 0;
		for (Modifier premodifier : premodifiers) {
			ret += premodifier.getDefenseBonus();
		}
		for (Modifier postmodifier : postmodifiers) {
			ret += postmodifier.getDefenseBonus();
		}
		return ret;
	}

	private double getModifiersGoldMod( ) {
		double ret = 0;
		for (Modifier premodifier : premodifiers) {
			ret += premodifier.getPriceModifier() / 100.0;
		}
		for (Modifier postmodifier : postmodifiers) {
			ret += postmodifier.getPriceModifier() / 100.0;
		}
		return ret;
	}

	private int getModifiersRangeBonus( ) {
		int ret = 0;
		for (Modifier premodifier : premodifiers) {
			ret += premodifier.getRangeBonus();
		}
		for (Modifier postmodifier : postmodifiers) {
			ret += postmodifier.getRangeBonus();
		}
		return ret;
	}

	private boolean modifiersHarmUndead( ) {
		for (Modifier premodifier : premodifiers) {
			if (premodifier.isHarmsUndead())
				return true;
		}
		for (Modifier postmodifier : postmodifiers) {
			if (postmodifier.isHarmsUndead())
				return true;
		}
		return false;
	}

	private boolean modifiersSliceThru( ) {
		for (Modifier premodifier : premodifiers) {
			if (premodifier.isSlicesThru())
				return true;
		}
		for (Modifier postmodifier : postmodifiers) {
			if (postmodifier.isSlicesThru())
				return true;
		}
		return false;
	}

}