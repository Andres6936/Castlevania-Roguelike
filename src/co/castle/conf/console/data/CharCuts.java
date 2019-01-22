package co.castle.conf.console.data;

import java.util.Hashtable;

import co.castle.game.Game;
import co.castle.ui.consoleUI.cuts.CharChat;

public class CharCuts
{
	private Hashtable hashCuts = new Hashtable( );

	public static CharCuts thus;

	{
		CharChat temp = null;
		temp = new CharChat( );
		// Prologue Sequence
		temp.add( "Count Dracula", "We meet again Vampire Killer. You are old now." );
		temp.add( "Christopher Belmont",
				"I came here to fulfill my fate as a Belmont; age bears no relevance." );
		temp.add( "Count Dracula",
				"Look at your own self! And look at me, just reborn from warm innocent blood, you stand no chance against my power!" );
		temp.add( "Christopher Belmont",
				"It is for that one same blood that my whip shall seek revenge against thee, dark lord." );
		temp.add( "Count Dracula",
				"HAHAHAHA! Don't make me laugh, pitiful excuse for a warrior, you shall regret your words!" );
		hashCuts.put( "PRELUDE_DRACULA1", temp );
		temp = new CharChat( );
		temp.add( "Soleiyu Belmont",
				"Father! I finally understand, I am here to confront my destiny, the destiny marked by my legacy!" );
		temp.add( "Soleiyu Belmont", "... Father? FATHER!" );
		temp.add( "Count Dracula",
				"Your father belongs to me now, you are late, son of a Belmont" );
		temp.add( "Soleiyu Belmont",
				"No! NO! this.. this cannot be! You miserable monster! Die!" );
		temp.add( "Count Dracula", "HAHAHAHA!" );
		hashCuts.put( "PRELUDE_DRACULA2", temp );
		temp = new CharChat( );
		temp.add( "Count Dracula",
				"Don't be such a fool! Your soul has always been my possession! " );
		temp.add( "Soleiyu Belmont",
				"What! What is this! What happens to my body! ARGH!" );
		hashCuts.put( "PRELUDE_DRACULA3", temp );
		temp = new CharChat( );
		temp.add( "Soleiyu Belmont", "WARGH!!! WARRRGH!" );
		temp.add( "Count Dracula",
				"HAHAHAHA! Now.... get out of my sight shameful creature! I have important things to do." );
		hashCuts.put( "PRELUDE_DRACULA4", temp );

		// Intro Sequence
		temp = new CharChat( );
		temp.add( "Melduck",
				"No luck, with the charriot like this we cannot make it to Petra, and this creepy forest..." );
		temp.add( "Melduck", ". . . wait . . . Did you hear that?" );
		temp.add( "%NAME", "%%INTRO_1" );
		/*
		 * This can be any of the following: {Player_VK}: This forest suffers as
		 * darkness corrupts its roots, it is my fate to get to Castlevania and fight my
		 * way through. {Player_VA}: The time is almost over, the trip over here will be
		 * wasted if I dont reach the castle soon. {Player_RE}: I can almost breathe the
		 * chaos that invades every tree and animal in this forest. It is time to return
		 * to the castle. {Player_IN}: I can sense all kind of dreaded souls lurking in
		 * here, I am afraid my master was right, I really need to reach the castle.
		 * {Player_MB}: I can hear, I can smell, I can sense the damned creatures that
		 * almost anihilated my brothers... tell me, where is the castle? {Player_KN}:
		 * Indeed, those seem to be Wargs: demonic wolf-like creatures summoned by the
		 * Count of Darkness to protect his castle. We better get to the castle quickly.
		 */
		temp.add( "Melduck",
				"Petra is to the northwest, and the castle of Dracula is just east of there... you better proceed on foot... but quickly, I'm afraid I can't get past the forest with my feet like this..." );
		temp.add( "%NAME",
				"You will be safe inside this ruined building. I will come back with help" );

		// Castle Entrance Sequence
		hashCuts.put( "INTRO_1", temp );
		temp = new CharChat( );
		temp.add( "Mysterious Girl",
				"What is this... another fool going into the castle... Who are you?" );
		temp.add( "%NAME", "My name is %NAME" );
		temp.add( "Mysterious Girl",
				"Leave this castle now if you want to live... this is not a place for commoners like you!" );
		temp.add( "%NAME", "%%CLARA1" );
		/*
		 * This can be any of the following: {Player_VK}: I came here to fulfill my fate
		 * as a Vampire Killer, and destroy the dark count Dracula {Player_VA}: Get out
		 * of my way, I have issues to attend inside the castle {Player_RE}: Commonner?
		 * . . . Ignorant human... get out of my way. {Player_IN}: Something bigger than
		 * myself has got me here, you would never understand it... the castle calls me.
		 * {Player_MB}: Don't let appearances fool you... the blood of my brothers and
		 * sisters fills my veins, anger will get me through this damned place and to
		 * the heart of the count {Player_KN}: Can you not see the mark of heavens? I am
		 * a Knight of the Sacred Order, and I came to cleanse this place from darkness.
		 */
		temp.add( "Mysterious Girl",
				"Oh really? You don't have a clue about anything... this castle is a creature of chaos, and it will engulf you mercilessly." );
		temp.add( "Mysterious Girl",
				"Goodbye fool, maybe we will meet inside... if you survive!" );
		hashCuts.put( "CLARA1", temp );

		// First Death Sequence
		temp = new CharChat( );
		temp.add( "Death", "The color of your soul, a Belmont?" );
		temp.add( "%NAME", "Indeed, and I am here to vanquish your lord for good." );
		temp.add( "Death",
				"I admire your attitude, but I am afraid THAT won't be enough... how dare you challenge the Castle of Chaos mortal!" );
		temp.add( "Death",
				"I would stop you here if I wanted to, but that would be much of a swift death for one that dares to challenge this castle! You will die as your brothers, and your soul will haunt this place for eternity!" );
		hashCuts.put( "DEATH_HALL", temp );

		// Castle Garden Sequence
		temp = new CharChat( );
		temp.add( "%NAME", "What is this, a garden inside this foul castle?" );
		temp.add( "%NAME",
				"And here we have yet another evil forger of darkness whom soul must be freed!" );
		temp.add( "Mysterious Woman", ". . . Stop where you are, son of a Belmont!" );
		temp.add( "%NAME", "What? How do you..." );
		temp.add( "Mysterious Woman",
				"You have come pretty far on the castle; this place is safe for you, for now." );
		temp.add( "%NAME", "But... who are ---" );
		temp.add( "Mysterious Woman",
				"I am known as Heliann, the blacksmith maiden. I inhabit the villa of Castlevania, I am here to help the Belmonts to find their path." );
		temp.add( "Heliann",
				"We are running out of time though... the count is using the souls from the grand Belmonts to perform a ritual that will be catastrophic for the world, to open the portal to hell!" );
		temp.add( "%NAME", "If they couldn't stop him, I doubt I will be able to!" );
		temp.add( "Heliann",
				"Only way to know if you are ready is to confront your fate; death will be the price to pay if you are not the chosen one to carry on with the Belmont legacy!" );
		temp.add( "Heliann",
				"Take this key, it opens the castle dungeon, from there you can reach the clock tower, and finally, the castle keep. That is the only way to go. Be careful %NAME Belmont." );
		hashCuts.put( "MAIDEN1", temp );

		// Dracula Sequence
		temp = new CharChat( );
		temp.add( "%NAME", "Your reign of blood ends here, Count Dracula!" );
		temp.add( "Count Dracula", "Amusing... A Belmont!" );
		temp.add( "%NAME", "And I am here to vanquish you for good. Prepare to fight!" );
		temp.add( "Count Dracula",
				"HAHAHA! Humans! Mankind! Carrying hope as their standard, is it true that you cannot see everything is doomed?" );
		temp.add( "Count Dracula",
				"Can you not see that you are not the heir of the night hunters? They are mine, already!" );
		temp.add( "%NAME",
				"No! our blood will never be yours, our fate will never dissapear, as long as you are a threat to men!" );
		temp.add( "Count Dracula",
				"You already rennounced everything when you ran away from your destiny!" );
		temp.add( "Count Dracula",
				"Yes, the son of the Belmont, the one true hunter, with his demise he condemned this world!" );
		temp.add( "%NAME", "Stop talking! It's time to fight!" );
		temp.add( "Count Dracula", "HAHAHA!" );
		hashCuts.put( "DRACULA1", temp );
		temp = new CharChat( );
		temp.add( "Count Dracula", "Argh! You are strong Belmont! But not enough!!" );
		temp.add( "%NAME", "What?" );
		temp.add( "Count Dracula", "TASTE MY TRUE POWER!" );
		hashCuts.put( "DRACULA2", temp );
		temp = new CharChat( );
		temp.add( "Count Dracula",
				"It is worthless! As long as light exists on this world, I shall return as darkness made flesh!" );
		temp.add( "%NAME",
				"And we the new Belmonts, we will be there to vanquish you again.... DIE!" );
		temp.add( "Count Dracula", "This cannot be! NOOOOOOOOOOOOOOOOOOOO!!!!!!" );
		hashCuts.put( "DRACULA3", temp );

		// Solieyu Belmont Fight Sequence
		temp = new CharChat( );
		temp.add( "Soleiyu Belmont", "Begone... Your quest ends here" );
		temp.add( "%NAME",
				"It is not time yet... I won't give up... YOU can't give up!" );
		temp.add( "Soleiyu Belmont",
				"It is all worthless, all our efforts... A world of light is a dream built up by dreamers, for dreamers." );
		temp.add( "Soleiyu Belmont",
				"Your fight is a pitiful struggle against the natural evolution of universe. A blindfolded battle against something you don't event know about. The time has come for a new ordeal." );
		temp.add( "%NAME",
				"Our fight is something far more important than ourselves. Our fate is to protect all that is good in the world. You must never forget that... everything is important!" );
		temp.add( "Soleiyu Belmont", "Shut up! And DIE!" );
		hashCuts.put( "BADSOLIEYU1", temp );

		// Solieyu Belmont Death Sequence
		temp = new CharChat( );
		temp.add( "Soleiyu Belmont", "I am done for... I deserve this death..." );
		temp.add( "%NAME", "Solieyu! don't give up!" );
		temp.add( "Soleiyu Belmont",
				"It is all over... my father, the only vampire killer... he is dead, because of me! I carry the burden of the destruction of this world... I DO!" );
		temp.add( "%NAME", "What?" );
		temp.add( "Soleiyu Belmont", "Shut up! Let me go... ARGH!!" );
		hashCuts.put( "BADSOLIEYU2", temp );
		temp = new CharChat( );

		// Solieyu Belmont Saved Sequence
		temp.add( "Soleiyu Belmont", "What is... that sound!" );
		temp.add( "%NAME", "This jukebox... is it yours?" );
		temp.add( "Soleiyu Belmont",
				"That melody... father! I promised to never let you down, but I was not born to be a Vampire Killer!" );
		temp.add( "Soleiyu Belmont",
				"No! that's wrong... empty promises, all of them worthless, I must destroy you at all costs!" );
		temp.add( "%NAME", "I don't want to fight you!" );
		temp.add( "Soleiyu Belmont", "My head! it hurts... it hurts! LET ME GO!" );
		hashCuts.put( "SAVESOLIEYU", temp );

		// Girls Sequence 1
		temp = new CharChat( );
		temp.add( "Mysterious Girl", "ARGH!!" );
		temp.add( "Sorceress?",
				"Give up now foolish girl! There is no place for your games in this place. Leave now, we do not need you!" );
		temp.add( "Mysterious Girl", "I will not give up, NEVER!" );
		temp.add( "Sorceress?",
				"Then we have a problem, and I only see one way to fix it!" );
		temp.add( "Sorceress?",
				"Forgive me for what I am about to do, but you must understand... I am sure they will understand" );
		temp.add( "Mysterious Girl",
				"I am tired of your words... now fall to my claws!" );
		hashCuts.put( "VINDELITH1", temp );
		temp = new CharChat( );
		temp.add( "Sorceress?",
				"Girl you are strong... stronger than I thought... you have forced me to stay here for now." );
		temp.add( "Mysterious Girl",
				"Now don't blame me Vindelith... you asked for this" );
		temp.add( "Vindelith",
				"I hope you realize what you are doing now, the time is running out and you are just slowing us down!" );
		temp.add( "Mysterious Girl", "I don't have to listen to you... farewell" );
		temp.add( "Vindelith", "Wait!" );
		hashCuts.put( "VINDELITH2", temp );

		// Fight with Death
		temp = new CharChat( );
		temp.add( "Death", "HAHAHAHAHAH! You have arrived... I am impressed!" );
		hashCuts.put( "DEATH1", temp );

		// Girls Sequence 2
		temp = new CharChat( );
		temp.add( "Short haired girl",
				"What a surprise... another annoying bug comes to stop our advancement" );
		temp.add( "%NAME", "I am not sure I know what you mean" );
		temp.add( "Vindelith",
				"I saw this person back when Claw beat me up, perhaps they are a team" );
		temp.add( "Short haired girl",
				"Claw... I am tired of hearing that name! Your friend has been a nuisance for us for too long!" );
		temp.add( "%NAME", "I do not know who you are talking about!" );
		temp.add( "Vindelith", "So, who are you, and why are you here" );
		temp.add( "%NAME", "I am %NAME Belmont, I came here to kill count Dracula." );
		temp.add( "Short haired girl",
				"Belmont? So, you too are claiming to belong to the Belmont Heritage? That means we have a problem here!" );
		temp.add( "Vindelith",
				"Clara! We can't afford to lose any more time fiddling with scumbags like this... let's push ahead!" );
		temp.add( "Clara",
				"You are right... I have seen enough fake Belmonts lately... Just a word of advice \"Belmont\": get the hell out of here while you can!" );
		hashCuts.put( "VINDELITH3", temp );
	}
	public static void initializeSingleton( )
	{
		thus = new CharCuts( );
	}

	public CharChat get( String ID )
	{
		CharChat ret = (CharChat) hashCuts.get( ID );
		if ( ret == null )
			Game.crash( "Couldnt find CharChat " + ID, new Exception( ) );
		return ret;
	}

}
