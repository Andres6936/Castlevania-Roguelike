package co.castle.data;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Hashtable;
import java.util.Vector;

import org.xml.sax.AttributeList;
import org.xml.sax.DocumentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import co.castle.ai.ActionSelector;
import co.castle.ai.monster.BasicMonsterAI;
import co.castle.ai.monster.MonsterAI;
import co.castle.ai.monster.RangedAI;
import co.castle.ai.monster.RangedAttack;
import co.castle.ai.monster.StationaryAI;
import co.castle.ai.monster.UnderwaterAI;
import co.castle.ai.monster.WanderToPlayerAI;
import co.castle.ai.monster.boss.DeathAI;
import co.castle.ai.monster.boss.DemonDraculaAI;
import co.castle.ai.monster.boss.DraculaAI;
import co.castle.ai.monster.boss.FrankAI;
import co.castle.ai.monster.boss.MedusaAI;
import co.castle.game.CRLException;
import co.castle.monster.MonsterDefinition;
import co.castle.ui.AppearanceFactory;
import sz.crypt.DESEncrypter;
import uk.co.wilson.xml.MinML;

public class MonsterLoader
{
	public static MonsterDefinition[ ] getBaseMonsters( String monsterFile ) throws CRLException
	{
		BufferedReader br = null;
		try
		{
			Vector<MonsterDefinition> vecMonsters = new Vector<>(10);
			DESEncrypter encrypter = new DESEncrypter("65csvlk3489585f9rjh");
			br = new BufferedReader(new InputStreamReader(
					encrypter.decrypt(new FileInputStream(monsterFile))));
			String line = br.readLine();
			line = br.readLine();
			while (line != null) {
				String[] data = line.split(";");
				MonsterDefinition def = new MonsterDefinition(data[0]);
				def.setAppearance(AppearanceFactory.getAppearanceFactory()
						.getAppearance( data[ 1 ] ) );
				def.setDescription( data[ 2 ] );
				def.setLongDescription( data[ 3 ] );
				def.setWavOnHit( data[ 4 ] );
				def.setBloodContent( Integer.parseInt( data[ 5 ] ) );
				def.setUndead( data[ 6 ].equals( "true" ) );
				def.setEthereal( data[ 7 ].equals( "true" ) );
				def.setCanSwim( data[ 8 ].equals( "true" ) );
				def.setCanFly( data[ 9 ].equals( "true" ) );
				def.setScore( Integer.parseInt( data[ 10 ] ) );
				def.setSightRange( Integer.parseInt( data[ 11 ] ) );
				def.setMaxHits( Integer.parseInt( data[ 12 ] ) );
				def.setAttack(Integer.parseInt(data[13]));
				def.setWalkCost(Integer.parseInt(data[14]));
				def.setAttackCost(Integer.parseInt(data[15]));
				def.setEvadeChance(Integer.parseInt(data[16]));
				def.setEvadeMessage(data[17]);
				def.setAutorespawnCount(Integer.parseInt(data[18]));

				vecMonsters.add(def);
				line = br.readLine();
			}
			return vecMonsters
					.toArray(new MonsterDefinition[vecMonsters.size()]);
		}
		catch ( IOException ioe )
		{
			throw new CRLException( "Error while loading data from monster file" );
		}
		finally
		{
			try
			{
				br.close( );
			}
			catch ( IOException ioe )
			{
				throw new CRLException( "Error while loading data from monster file" );
			}
		}
	}

	public static MonsterDefinition[ ] getMonsterDefinitions(	String monsterDefFile,
																String monsterXMLAIFile ) throws CRLException
	{
		try {
			MonsterDefinition[] monsters = getBaseMonsters(monsterDefFile);
			Hashtable<String, MonsterDefinition> hashMonsters = new Hashtable<>();
			for (MonsterDefinition monster : monsters) {
				hashMonsters.put(monster.getID(), monster);
			}

			MonsterDocumentHandler handler = new MonsterDocumentHandler(hashMonsters);
			MinML parser = new MinML();
			DESEncrypter encrypter = new DESEncrypter("65csvlk3489585f9rjh");
			// parser.setContentHandler(handler);
			parser.setDocumentHandler(handler);
			parser.parse(new InputSource(
					encrypter.decrypt(new FileInputStream(monsterXMLAIFile))));
			return monsters;

			/*
			 * Print Some data to a CSV File, yeah I am evil BufferedWriter write =
			 * FileUtil.getWriter("monsterStats.csv"); for (int i = 0; i < ret.length;
			 * i++){
			 * write.write(ret[i].getID()+","+ret[i].getAppearance().getID()+","+ret[i].
			 * getDescription()+",,"+
			 * ret[i].getWavOnHit()+","+ret[i].getBloodContent()+","+
			 * ret[i].isUndead()+","+ret[i].isEthereal()+","+
			 * ret[i].isCanSwim()+",false,"+ret[i].getScore()+","+
			 * ret[i].getSightRange()+","+ret[i].getMaxHits()+","+
			 * ret[i].getAttack()+","+ret[i].getWalkCost()+","+ret[i].getAttackCost()+","+
			 * ret[i].getEvadeChance()+","+ret[i].getEvadeMessage()+","+ret[i].
			 * getAutorespawnCount() ); write.write("\n"); } write.close();
			 */
			// End Print Some data to a CSV File, yeah I am evil

		}
		catch ( IOException ioe )
		{
			throw new CRLException( "Error while loading data from monster file" );
		}
		catch ( SAXException sax )
		{
			sax.printStackTrace( );
			throw new CRLException( "Error while loading data from monster file" );
		}
	}

}

class MonsterDocumentHandler implements DocumentHandler {
	private MonsterDefinition currentMD;

	private Vector<RangedAttack> currentRangedAttacks;

	private ActionSelector currentSelector;
	private final Hashtable<String, MonsterDefinition> hashMonsters;

	MonsterDocumentHandler(Hashtable<String, MonsterDefinition> hashMonsters) {
		this.hashMonsters = hashMonsters;
	}

	public void characters(char[] values, int param,
						   int param2) throws org.xml.sax.SAXException {
	}

	public void endDocument() throws org.xml.sax.SAXException
	{
	}

	public void endElement( String localName ) throws org.xml.sax.SAXException
	{
		if ( localName.equals( "rangedAttacks" ) )
		{
			( (MonsterAI) currentSelector ).setRangedAttacks( currentRangedAttacks );
		}
		else if ( localName.equals( "selector" ) )
		{

			currentMD.setDefaultSelector( currentSelector );
		}

	}

	public void ignorableWhitespace(	char[ ] values, int param,
										int param2 ) throws org.xml.sax.SAXException
	{
	}

	public void processingInstruction(	String str,
										String str1 ) throws org.xml.sax.SAXException
	{
	}

	public void setDocumentLocator( org.xml.sax.Locator locator )
	{
	}

	public void startDocument( ) throws org.xml.sax.SAXException
	{
	}

	public void startElement(	String localName,
								AttributeList at ) throws org.xml.sax.SAXException
	{
		if ( localName.equals( "monster" ) ) {
			currentMD = hashMonsters.get(at.getValue("id"));
		}
		else if ( localName.equals( "sel_wander" ) )
		{
			currentSelector = new WanderToPlayerAI( );
		}
		else if ( localName.equals( "sel_underwater" ) )
		{
			currentSelector = new UnderwaterAI( );
		}
		else if ( localName.equals( "sel_sickle" ) )
		{
			currentSelector = new co.castle.action.monster.boss.SickleAI( );
		}
		else if ( localName.equals( "sel_death" ) )
		{
			currentSelector = new DeathAI( );
		}
		else if ( localName.equals( "sel_dracula" ) )
		{
			currentSelector = new DraculaAI( );
		}
		else if ( localName.equals( "sel_demondracula" ) )
		{
			currentSelector = new DemonDraculaAI( );
		}
		else if ( localName.equals( "sel_medusa" ) )
		{
			currentSelector = new MedusaAI( );
		}
		else if ( localName.equals( "sel_frank" ) )
		{
			currentSelector = new FrankAI( );
		}
		else if ( localName.equals( "sel_stationary" ) )
		{
			currentSelector = new StationaryAI( );
		}
		else if ( localName.equals( "sel_basic" ) )
		{
			currentSelector = new BasicMonsterAI( );
			if ( at.getValue( "stationary" ) != null )
				( (BasicMonsterAI) currentSelector )
						.setStationary( at.getValue( "stationary" ).equals( "true" ) );
			if ( at.getValue( "approachLimit" ) != null )
				( (BasicMonsterAI) currentSelector )
						.setApproachLimit( inte( at.getValue( "approachLimit" ) ) );
			if ( at.getValue( "waitPlayerRange" ) != null )
				( (BasicMonsterAI) currentSelector )
						.setWaitPlayerRange( inte( at.getValue( "waitPlayerRange" ) ) );
			if ( at.getValue( "patrolRange" ) != null )
				( (BasicMonsterAI) currentSelector )
						.setPatrolRange( inte( at.getValue( "patrolRange" ) ) );
		}
		else if ( localName.equals( "sel_ranged" ) )
		{
			currentSelector = new RangedAI( );
			( (RangedAI) currentSelector )
					.setApproachLimit( inte( at.getValue( "approachLimit" ) ) );
		}
		else if ( localName.equals( "rangedAttacks" ) ) {
			currentRangedAttacks = new Vector<>(10);
		}
		else if ( localName.equals( "rangedAttack" ) )
		{
			int damage = 0;
			try {
				damage = Integer.parseInt(at.getValue("damage"));
			} catch (NumberFormatException ignored) {

			}

			RangedAttack ra = new RangedAttack( at.getValue( "id" ),
					at.getValue( "type" ), at.getValue( "status_effect" ),
					Integer.parseInt( at.getValue( "range" ) ),
					Integer.parseInt( at.getValue( "frequency" ) ),
					at.getValue( "message" ), at.getValue( "effectType" ),
					at.getValue( "effectID" ), damage

			// color
			);
			if ( at.getValue( "effectWav" ) != null )
				ra.setEffectWav( at.getValue( "effectWav" ) );
			if ( at.getValue( "summonMonsterId" ) != null )
				ra.setSummonMonsterId( at.getValue( "summonMonsterId" ) );
			if ( at.getValue( "charge" ) != null )
				ra.setChargeCounter( inte( at.getValue( "charge" ) ) );

			currentRangedAttacks.add( ra );
		}
	}

	private int inte( String s )
	{
		return Integer.parseInt( s );
	}
}
