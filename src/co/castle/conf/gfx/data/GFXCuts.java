package co.castle.conf.gfx.data;

import java.awt.image.BufferedImage;
import java.util.Hashtable;

import co.castle.game.Game;
import co.castle.ui.graphicsUI.GFXChat;
import sz.util.ImageUtils;

// With keyword final, we be prevent the inheritance of this class
// This class is type Singleton
public final class GFXCuts
{
	public BufferedImage PRT_M1;
	public BufferedImage PRT_M2;
	public BufferedImage PRT_M3;
	public BufferedImage PRT_M4;
	public BufferedImage PRT_M5;
	public BufferedImage PRT_M6;
	public BufferedImage PRT_F1;
	public BufferedImage PRT_F2;
	public BufferedImage PRT_F3;
	public BufferedImage PRT_F4;
	public BufferedImage PRT_F5;
	public BufferedImage PRT_F6;

	private final Hashtable<String, GFXChat> hashCuts = new Hashtable<>();

	private BufferedImage PRT_DRACULA;
	private BufferedImage PRT_CHRIS;
	private BufferedImage PRT_SOLIEYU;
	private BufferedImage PRT_SOLIEYU_D;
	private BufferedImage PRT_MELDUCK;
	private BufferedImage PRT_CLARA;
	private BufferedImage PRT_MAIDEN;
	private BufferedImage PRT_DEATH;
	private BufferedImage PRT_VINDELITH;
	private BufferedImage PRT_CLAW;

	private static GFXCuts instance;

	// We make the constructor private to prevent the use of 'new'
	private GFXCuts( )
	{
		try
		{
			BufferedImage sheetPortraits = ImageUtils.createImage( "gfx/crl_portraits2x.gif" );

			PRT_DRACULA = ImageUtils.crearImagen( sheetPortraits, 8, 205, 84, 86 );
			PRT_DEATH = ImageUtils.crearImagen( sheetPortraits, 98, 205, 84, 86 );
			PRT_SOLIEYU = ImageUtils.crearImagen( sheetPortraits, 188, 205, 84, 86 );
			PRT_SOLIEYU_D = ImageUtils.crearImagen( sheetPortraits, 278, 204, 84, 86 );
			PRT_CHRIS = ImageUtils.crearImagen( sheetPortraits, 368, 205, 84, 86 );
			PRT_VINDELITH = ImageUtils.crearImagen( sheetPortraits, 458, 204, 84, 86 );

			PRT_CLAW = ImageUtils.crearImagen( sheetPortraits, 8, 303, 84, 86 );
			PRT_CLARA = ImageUtils.crearImagen( sheetPortraits, 98, 303, 84, 86 );
			PRT_MELDUCK = ImageUtils.crearImagen( sheetPortraits, 188, 303, 84, 86 );
			PRT_MAIDEN = ImageUtils.crearImagen( sheetPortraits, 278, 303, 84, 86 );

			PRT_M1 = ImageUtils.crearImagen( sheetPortraits, 8, 10, 84, 86 );
			PRT_M2 = ImageUtils.crearImagen( sheetPortraits, 98, 10, 84, 86 );
			PRT_M3 = ImageUtils.crearImagen( sheetPortraits, 188, 10, 84, 86 );
			PRT_M4 = ImageUtils.crearImagen( sheetPortraits, 278, 10, 84, 86 );
			PRT_M5 = ImageUtils.crearImagen( sheetPortraits, 368, 10, 84, 86 );
			PRT_M6 = ImageUtils.crearImagen( sheetPortraits, 458, 10, 84, 86 );
			PRT_F1 = ImageUtils.crearImagen( sheetPortraits, 8, 107, 84, 86 );
			PRT_F2 = ImageUtils.crearImagen( sheetPortraits, 98, 107, 84, 86 );
			PRT_F3 = ImageUtils.crearImagen( sheetPortraits, 188, 107, 84, 86 );
			PRT_F4 = ImageUtils.crearImagen( sheetPortraits, 278, 107, 84, 86 );
			PRT_F5 = ImageUtils.crearImagen( sheetPortraits, 368, 107, 84, 86 );
			PRT_F6 = ImageUtils.crearImagen( sheetPortraits, 458, 107, 84, 86 );
		}
		catch ( Exception e )
		{
			System.out.println( "Error loading portraits" );
		}
	}

	{
		GFXChat temp;
		temp = new GFXChat();
		temp.add("Count Dracula", "We meet again Vampire Killer. You are old now.", PRT_DRACULA);
		temp.add("Christopher Belmont",
				"I came here to fulfill my fate as a Belmont; age bears no relevance.", PRT_CHRIS);
		temp.add("Count Dracula",
				"Look at your own self! And look at me, just reborn from warm innocent blood, you stand no chance against my power!",
				PRT_DRACULA);
		temp.add("Christopher Belmont",
				"It is for that one same blood that my whip shall seek revenge against thee, dark lord.",
				PRT_CHRIS);
		temp.add( "Count Dracula",
				"HAHAHAHA! Don't make me laugh, pitiful excuse for a warrior, you shall regret your words!",
				PRT_DRACULA );
		hashCuts.put( "PRELUDE_DRACULA1", temp );
		temp = new GFXChat( );
		temp.add( "Soleiyu Belmont",
				"Father! I finally understand, I am here to confront my destiny, the destiny marked by my legacy!",
				PRT_SOLIEYU );
		temp.add( "Soleiyu Belmont", "... Father? FATHER!", PRT_SOLIEYU );
		temp.add( "Count Dracula", "Your father belongs to me now, you are late, son of a Belmont",
				PRT_DRACULA );
		temp.add( "Soleiyu Belmont", "No! NO! this.. this cannot be! You miserable monster! Die!",
				PRT_SOLIEYU );
		temp.add( "Count Dracula", "HAHAHAHA!", PRT_DRACULA );
		hashCuts.put( "PRELUDE_DRACULA2", temp );
		temp = new GFXChat( );
		temp.add( "Count Dracula", "Don't be such a fool! Your soul has always been my possession! ",
				PRT_DRACULA );
		temp.add( "Soleiyu Belmont", "What! What is this! What happens to my body! ARGH!", PRT_SOLIEYU );
		hashCuts.put( "PRELUDE_DRACULA3", temp );
		temp = new GFXChat( );
		temp.add( "Soleiyu Belmont", "WARGH!!! WARRRGH!", PRT_SOLIEYU_D );
		temp.add( "Count Dracula",
				"HAHAHAHA! Now, get out of my sight shameful creature! I have important things to do",
				PRT_DRACULA );
		hashCuts.put( "PRELUDE_DRACULA4", temp );
		temp = new GFXChat( );
		temp.add( "Melduck",
				"This looks bad... with the charriot like this we cannot make it to Petra, and this creepy forest...",
				PRT_MELDUCK );
		temp.add( "Melduck", ". . . wait . . . Did you hear that?", PRT_MELDUCK );
		temp.add( "%NAME", "%%INTRO_1" );
		temp.add( "Melduck",
				"Petra is to the northwest, and the castle of Dracula is just east of there... you better proceed on foot... but quickly, I'm afraid I can't get past the forest with my feet like this...",
				PRT_MELDUCK );
		temp.add( "%NAME", "You will be safe inside this ruined building. I will come back with help" );
		hashCuts.put( "INTRO_1", temp );
		temp = new GFXChat( );
		temp.add( "???", "What is this... another fool going into the castle... Who are you?", PRT_CLAW );
		temp.add( "%NAME", "My name is %NAME" );
		temp.add( "???",
				"Leave this castle now if you want to live... this is not a place for commoners like you",
				PRT_CLAW );
		temp.add( "%NAME", "%%CLARA1" );
		temp.add( "???",
				"Oh really? You don't have a clue about anything... this castle is a creature of chaos, and it will engulf you mercilessly",
				PRT_CLAW );
		temp.add( "???", "Goodbye fool, maybe we will meet inside... if you survive!", PRT_CLAW );
		hashCuts.put( "CLARA1", temp );
		temp = new GFXChat( );
		temp.add( "%NAME", "What is this, a garden inside this foul castle?" );
		temp.add( "%NAME", "And here we have yet another evil forger of darkness whom soul must be freed!" );
		temp.add( "???", ". . . Stop where you are, son of a Belmont!", PRT_MAIDEN );
		temp.add( "%NAME", "What? How do you..." );
		temp.add( "???", "You have come pretty far on the castle; this place is safe for you, for now.",
				PRT_MAIDEN );
		temp.add( "%NAME", "But... who are ---" );
		temp.add( "???",
				"I am known as Heliann, the blacksmith maiden. I inhabit the villa of Castlevania, I am here to help the Belmonts to find their path.",
				PRT_MAIDEN );
		temp.add( "Heliann",
				"We are running out of time though... the count is using the souls from the grand Belmonts to perform a ritual that will be catastrophic for the world, to open the portal to hell!",
				PRT_MAIDEN );
		temp.add( "%NAME", "If they couldn't stop him, I doubt I will be able to!" );
		temp.add( "Heliann",
				"Only way to know if you are ready is to confront your fate; death will be the price to pay if you are not the chosen one to carry on with the Belmont legacy!",
				PRT_MAIDEN );
		temp.add( "Heliann",
				"Take this key, it opens the castle dungeon, from there you can reach the clock tower, and finally, the castle keep. That is the only way to go. Be careful %NAME Belmont.",
				PRT_MAIDEN );
		hashCuts.put( "MAIDEN1", temp );
		temp = new GFXChat( );
		temp.add( "%NAME", "Your reign of blood ends here, Count Dracula!" );
		temp.add( "Count Dracula", "The color of your soul... Amusing... A Belmont!", PRT_DRACULA );
		temp.add( "%NAME", "And I am here to vanquish you for good. Prepare to fight!" );
		temp.add( "Count Dracula",
				"HAHAHA! Humans! Mankind! Carrying hope as their standard, is it true that you cannot see everything is doomed?",
				PRT_DRACULA );
		temp.add( "Count Dracula",
				"Can you not see that you are not the heir of the night hunters? They are mine, already!",
				PRT_DRACULA );
		temp.add( "%NAME",
				"No! our blood will never be yours, our fate will never dissapear, as long as you are a threat to men!" );
		temp.add( "Count Dracula", "You already rennounced everything when you ran away from your destiny!",
				PRT_DRACULA );
		temp.add( "Count Dracula",
				"Yes, the son of the Belmont, the one true hunter, with his demise he condemned this world!",
				PRT_DRACULA );
		temp.add( "%NAME", "Stop talking! It's time to fight!" );
		temp.add( "Count Dracula", "HAHAHA!", PRT_DRACULA );
		hashCuts.put( "DRACULA1", temp );
		temp = new GFXChat( );
		temp.add( "Count Dracula", "Argh! You are strong Belmont! But not enough... HAHAHA!", PRT_DRACULA );
		temp.add( "%NAME", "What?" );
		temp.add( "Count Dracula", "TASTE MY TRUE POWER!", PRT_DRACULA );
		hashCuts.put( "DRACULA2", temp );
		temp = new GFXChat( );
		temp.add( "Count Dracula",
				"HAHAHA! It is worthless! As long as light exists on this world, I shall return as darkness made flesh!",
				PRT_DRACULA );
		temp.add( "%NAME", "And my heir, the new Belmonts, we will be there to vanquish you again. DIE!" );
		temp.add( "Count Dracula", "NOOOOOOOOOOOOOOOOOOOO!!!!!!", PRT_DRACULA );
		hashCuts.put( "DRACULA3", temp );
		temp = new GFXChat( );
		temp.add( "Death", "The color of your soul, a Belmont?", PRT_DEATH );
		temp.add( "%NAME", "Indeed, and I am here to vanquish your lord for good." );
		temp.add( "Death",
				"I admire your attitude, but I am afraid THAT won't be enough... how dare you challenge the Castle of Chaos mortal!",
				PRT_DEATH );
		temp.add( "Death",
				"I would stop you here if I wanted to, but that would be much of a swift death for one that dares to challenge this castle! You will die as your brothers, and your soul will haunt this place for eternity!",
				PRT_DEATH );
		hashCuts.put( "DEATH_HALL", temp );
		temp = new GFXChat( );
		temp.add( "Soleiyu Belmont", "Begone... Your quest ends here", PRT_SOLIEYU_D );
		temp.add( "%NAME", "It is not time yet... I won't give up... YOU can't give up!" );
		temp.add( "Soleiyu Belmont",
				"It is all worthless, all our efforts. A world of light is a dream built up by dreamers, for dreamers.",
				PRT_SOLIEYU_D );
		temp.add( "Soleiyu Belmont",
				"Your fight is a pitiful struggle against the natural evolution of universe. A blindfolded battle against something you don�t event know about. The time has come for a new ordeal",
				PRT_SOLIEYU_D );
		temp.add( "%NAME",
				"Our fight is something far more important than ourselves. Our fate is to protect all that is good in the world. You must never forget that� everything is important!" );
		temp.add( "Soleiyu Belmont", "Shut up! And DIE!", PRT_SOLIEYU_D );
		hashCuts.put( "BADSOLIEYU1", temp );
		temp = new GFXChat( );
		temp.add( "Soleiyu Belmont", "I am done for... I deserve this death...", PRT_SOLIEYU_D );
		temp.add( "%NAME", "Solieyu! don't give up!" );
		temp.add( "Soleiyu Belmont",
				"It is all over... my father, the only vampire killer... he is dead, because of me! I carry the burden of the destruction of this world... I DO!",
				PRT_SOLIEYU_D );
		temp.add( "%NAME", "What?" );
		temp.add( "Soleiyu Belmont", "Shut up! Let me go... ARGH!!", PRT_SOLIEYU_D );
		hashCuts.put( "BADSOLIEYU2", temp );
		temp = new GFXChat( );
		temp.add( "Soleiyu Belmont", "What is... that sound!", PRT_SOLIEYU_D );
		temp.add( "%NAME", "This jukebox... is it yours?" );
		temp.add( "Soleiyu Belmont",
				"That melody... father! I promised to never let you down, but I was not born to be a Vampire Killer!",
				PRT_SOLIEYU_D );
		temp.add( "Soleiyu Belmont",
				"No! that's wrong... empty promises, all of them worthless, I must destroy you at all costs!",
				PRT_SOLIEYU_D );
		temp.add( "%NAME", "I don't want to fight you!" );
		temp.add( "Soleiyu Belmont", "My head! it hurts... it hurts! LET ME GO!", PRT_SOLIEYU_D );
		hashCuts.put( "SAVESOLIEYU", temp );
		temp = new GFXChat( );
		temp.add( "Mysterious Girl", "ARGH!!", PRT_CLAW );
		temp.add( "Sorceress?",
				"Give up now foolish girl! There is no place for your games in this place. Leave now, we do not need you!",
				PRT_VINDELITH );
		temp.add( "Mysterious Girl", "I will not give up, NEVER!", PRT_CLAW );
		temp.add( "Sorceress?", "Then we have a problem, and I only see one way to fix it!", PRT_VINDELITH );
		temp.add( "Sorceress?",
				"Forgive me for what I am about to do, but you must understand... I am sure they will understand",
				PRT_VINDELITH );
		temp.add( "Mysterious Girl", "I am tired of your words... now fall to my claws!", PRT_CLAW );
		hashCuts.put( "VINDELITH1", temp );
		temp = new GFXChat( );
		temp.add( "Sorceress?", "Girl you are strong... stronger than I thought...", PRT_VINDELITH );
		temp.add( "Mysterious Girl", "Now don't blame me Vindelith... you asked for this", PRT_CLAW );
		temp.add( "Vindelith",
				"I hope you realize what you are doing now, the time is running out and you are just slowing us down!",
				PRT_VINDELITH );
		temp.add( "Mysterious Girl", "I don't have to listen to you... farewell", PRT_CLAW );
		temp.add( "Vindelith", "Wait!", PRT_VINDELITH );
		hashCuts.put( "VINDELITH2", temp );
		temp = new GFXChat( );
		temp.add( "Death", "HAHAHAHAHAH! You have arrived... I am impressed!", PRT_DEATH );
		hashCuts.put( "DEATH1", temp );
		temp = new GFXChat( );
		temp.add( "Short haired girl",
				"What a nasty surprise... another annoying bug comes to stop our advancement", PRT_CLARA );
		temp.add( "%NAME", "I am not sure I know what you mean" );
		temp.add( "Vindelith", "I saw this person back when Claw beat me up, perhaps they are a team",
				PRT_VINDELITH );
		temp.add( "Short haired girl",
				"Claw... I am tired of hearing that name! Your friend has been a nuisance for us for too long!",
				PRT_CLARA );
		temp.add( "%NAME", "I do not know who you are talking about!" );
		temp.add( "Vindelith", "So, who are you, and why are you here", PRT_VINDELITH );
		temp.add( "%NAME", "I am %NAME Belmont, I came here to kill count Dracula." );
		temp.add( "Short haired girl",
				"Belmont? So, you too are claiming to belong to the Belmont Heritage? That means we have a problem here!",
				PRT_CLARA );
		temp.add( "Vindelith",
				"Clara! We can't afford to lose any more time fiddling with scumbags like this... let's push ahead!",
				PRT_VINDELITH );
		temp.add( "Clara",
				"You are right... I have seen enough fake Belmonts lately... Just a word of advice \"Belmont\": get the hell out of here while you can!",
				PRT_CLARA );
		hashCuts.put( "VINDELITH3", temp );

	}

	public static GFXCuts getInstance( )
	{
		// Lazy initialization
		if ( instance == null )
		{
			instance = new GFXCuts( );
		}

		return instance;
	}

	public GFXChat get( String ID )
	{
		GFXChat ret = (GFXChat) hashCuts.get( ID );
		if ( ret == null )
			Game.crash( "Couldnt find GFXChat " + ID, new Exception( ) );
		return ret;
	}

}
