package co.castle.npc;

import java.util.Hashtable;
import java.util.Vector;

import co.castle.data.NPCs;
import co.castle.item.ItemFactory;
import co.castle.item.Merchant;
import co.castle.player.Player;
import co.castle.ui.UserInterface;
import sz.util.Util;

public class NPCFactory {
	private final Hashtable<String, NPCDefinition> definitions;
	private final Vector<String> hostages = new Vector<>();
	private static final String[][] hostageArtifacts = new String[][]
			{
					{"HOLBEIN_DAGGER", "SHOTEL"},
					{"WEREBANE", "ALCARDE_SPEAR", "ETHANOS_BLADE"},
					{"FIREBRAND", "GURTHANG", "HADOR"},
					{"ICEBRAND", "MORMEGIL", "VORPAL_BLADE"},
					{"GRAM", "CRISSAEGRIM"},
					{"KAISER_KNUCKLE", "OSAFUNE", "MASAMUNE"}};

	private final static NPCFactory singleton = new NPCFactory();

	public NPCFactory( ) {
		definitions = new Hashtable<>(40);
		for (NPCDefinition definition : NPCs.getNPCDefinitions()) {
			addDefinition(definition);
		}
	}

	public static NPCFactory getFactory( )
	{
		return singleton;
	}

	public void addDefinition( NPCDefinition definition )
	{
		definitions.put( definition.getID( ), definition );
		if ( definition.isHostage( ) )
			hostages.add( definition.getID( ) );
	}

	public Hostage buildHostage( ) {
		Hostage ret = new Hostage(definitions
				.get((String) Util.randomElementOf(hostages)));
		if (UserInterface.getUI().getPlayer()
				.getPlayerClass() != Player.CLASS_VAMPIREKILLER) {
			int artifactCategory = ((int) (UserInterface.getUI().getPlayer()
					.getPlayerLevel() / 6.0d));
			ret.setItemReward(ItemFactory.getItemFactory().createWeapon(
					Util.randomElementOf(hostageArtifacts[artifactCategory]), ""));
		}
		return ret;
	}

	public Merchant buildMerchant( int merchandiseType ) {
		return new Merchant(definitions.get("MERCHANT"),
				merchandiseType);
	}

	public NPC buildNPC( String id ) {
		return new NPC(definitions.get(id));
	}

	public NPCDefinition getDefinition( String id ) {
		return definitions.get(id);
	}
}