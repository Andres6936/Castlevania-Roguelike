package co.castle.ui.graphicsUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Hashtable;
import java.util.Vector;

import javax.swing.JTextArea;

import co.castle.Main;
import co.castle.conf.gfx.data.Asset;
import co.castle.conf.gfx.data.GFXCuts;
import co.castle.game.Game;
import co.castle.game.MonsterRecord;
import co.castle.main.ApplicationGraphics;
import co.castle.monster.Monster;
import co.castle.npc.Hostage;
import co.castle.player.GameSessionInfo;
import co.castle.player.HiScore;
import co.castle.player.Player;
import co.castle.player.advancements.Advancement;
import co.castle.ui.Display;
import co.castle.ui.UserInterface;
import co.castle.ui.graphicsUI.components.GFXChatBox;
import sz.csi.KeyCode;
import sz.util.ImageUtils;
import sz.util.Position;
import sz.util.ScriptUtil;

public class GraphicsDisplay extends Display
{
	private AddornedBorderTextArea addornedTextArea;

	private GFXChatBox gfxChatBox;

	private Hashtable <String, Position> locationKeys;

	// Get instance of ApplicationFrame
	private static final ApplicationGraphics renderer = ApplicationGraphics.getInstance();

	// Get instance of Asset
	protected Asset assets = ApplicationGraphics.assets;

	{
		locationKeys = new Hashtable<>();
		locationKeys.put("TOWN", new Position(130, 206));
		locationKeys.put("FOREST", new Position(201, 206));
		locationKeys.put("BRIDGE", new Position(273, 206));
		locationKeys.put("ENTRANCE", new Position(316, 206));
		locationKeys.put("HALL", new Position(348, 206));
		locationKeys.put("LAB", new Position(316, 171));
		locationKeys.put("CHAPEL", new Position(316, 139));
		locationKeys.put("RUINS", new Position(383, 172));
		locationKeys.put("CAVES", new Position(383, 261));
		locationKeys.put("COURTYARD", new Position(448, 232));
		locationKeys.put( "VILLA", new Position( 448, 232 ) );
		locationKeys.put( "DUNGEON", new Position( 512, 261 ) );
		locationKeys.put( "STORAGE", new Position( 555, 206 ) );
		locationKeys.put( "CLOCKTOWER", new Position( 555, 119 ) );
		locationKeys.put( "KEEP", new Position( 462, 69 ) );
	}

	public GraphicsDisplay( ) {
		try {
			// BufferedImage BORDERS = ImageUtils.createImage(IMG_BORDERS);
			BufferedImage b1 = ImageUtils.crearImagen(assets.IMAGE_BORDERS, 34, 1, 32, 32);
			BufferedImage b2 = ImageUtils.crearImagen(assets.IMAGE_BORDERS, 1, 1, 32, 32);
			BufferedImage b3 = ImageUtils.crearImagen(assets.IMAGE_BORDERS, 100, 1, 32, 32);
			BufferedImage b4 = ImageUtils.crearImagen(assets.IMAGE_BORDERS, 67, 1, 32, 32);

			addornedTextArea = new AddornedBorderTextArea(b1, b2, b3, b4,
					new Color(187, 161, 80), new Color(92, 78, 36), 32, 32);
			addornedTextArea.setVisible( false );
			addornedTextArea.setEnabled( false );
			addornedTextArea.setForeground( Color.WHITE );
			addornedTextArea.setBackground( Color.BLACK );
			addornedTextArea.setFont( assets.FONT_TEXT );
			addornedTextArea.setOpaque( false );

			gfxChatBox = new GFXChatBox( b1, b2, b3, b4, new Color( 187, 161, 80 ),
					new Color(92, 78, 36), 32, 32);

			gfxChatBox.setBounds(50, 20, 700, 220);
			gfxChatBox.setVisible(false);
		} catch (Exception e) {
			Game.crash("Error loading UI data", e);
		}

		renderer.addComponentToPanel(addornedTextArea);
		renderer.addComponentToPanel(gfxChatBox);
	}

	public static JTextArea createTempArea( int xpos, int ypos, int w, int h ) {
		JTextArea ret = new JTextArea();
		ret.setOpaque(false);
		ret.setForeground(Color.WHITE);
		ret.setVisible(true);
		ret.setEditable(false);
		ret.setFocusable(false);
		ret.setBounds(xpos, ypos, w, h);
		ret.setLineWrap(true);
		ret.setWrapStyleWord(true);
		ret.setFont(renderer.getFont());
		return ret;
	}

	public void clearTextBox( )
	{
		addornedTextArea.setVisible( false );
	}

	public void showChat( String chatID, Game game ) {
		renderer.saveBuffer();
		GFXChat chat = GFXCuts.getInstance().get(chatID);
		String[] marks = new String[]
				{"%NAME", "%%INTRO_1", "%%CLARA1"};
		String[] replacements = new String[]
				{game.getPlayer().getName(), game.getPlayer().getCustomMessage("INTRO_1"),
						game.getPlayer().getCustomMessage("CLARA1")};
		Image image = null;
		for (int i = 0; i < chat.getConversations(); i++) {
			renderer.restore();
			// si.setColor(TRANSPARENT_BLUE);
			// si.getGraphics2D().fillRect(26,26,665,185);
			if (chat.getPortrait(i) != null)
				image = chat.getPortrait(i);
			else
				image = getPortraitForPlayer(game.getPlayer());
			gfxChatBox.set(image,
					ScriptUtil.replace(marks, replacements, chat.getName(i)),
					ScriptUtil.replace(marks, replacements,
							chat.getConversation(i)));
			gfxChatBox.setVisible(true);
			renderer.waitKey(KeyCode.SPACE);
		}
		gfxChatBox.setVisible(false);
		renderer.restore();
	}

	public void showDraculaSequence( )
	{
		showTextBox(
				"Ahhh... a human... the first one to get this far. HAHAHAHA! Now it is time to die!",
				3, 4, 40, 10 );

	}

	public void showEndgame( Player player ) {
		((GFXUserInterface) UserInterface.getUI()).messageBox.setVisible(false);
		renderer.drawImage(assets.IMAGE_ENDGAME);
		renderer.setFontToPanel(assets.FONT_TITLE);
		renderer.printAtPixel(426, 95, "Epilogue", Color.RED);
		String heshe = (player.getSex() == Player.MALE ? "he" : "she");
		// String hisher = (player.getSex() == Player.MALE ? "his" : "her");
		JTextArea t1 = createTempArea(20, 125, 700, 400);
		t1.setForeground(Color.WHITE);
		t1.setText(player.getName()
				+ " made many sacrifices, but now the long fight is over. Dracula is dead "
				+ "and all other spirits are asleep. In the shadows, a person watches the castle fall. "
				+ player.getName() + " must go for now but " + heshe + " hopes someday "
				+ heshe + " will get the " + "respect that " + heshe
				+ " deserves.    After this fight the new Belmont name shall be honored "
				+ "by all people. \n\n\n"
				+ "You played the greatest role in this history... \n\n"
				+ "Thank you for playing.\n\n\n" + "CastlevaniaRL: v" + Game.getVersion()
				+ "\n\nSantiago Zapata 2005-2010");
		renderer.addComponentToPanel(t1);
		renderer.setFontToPanel(assets.FONT_TEXT);
		renderer.print(2, 20, "[Press Space]", Color.WHITE);
		renderer.refresh();
		renderer.waitKey(KeyCode.SPACE);
		renderer.remove(t1);

	}

	public void showHelp( )
	{
		// Delegated to GFXUserInterface.HelpBox
	}

	public void showHiscores( HiScore[ ] scores )
	{
		int leftMargin;
		int widthAdjustment;

		if (assets.SCREEN_WIDTH == 1024) {
			leftMargin = 9;
			widthAdjustment = 112;
		} else {
			leftMargin = 0;
			widthAdjustment = 0;
		}
		renderer.drawImage(assets.IMAGE_HISCORES);
		addornedTextArea.setBounds(8, 110, 782, 395);
		addornedTextArea.paintAt(renderer.getGraphics2D(), 8 + widthAdjustment, 110);
		renderer.setFontToPanel(assets.FONT_TITLE);
		renderer.printAtPixelCentered(assets.SCREEN_WIDTH / 2, 160,
				"The most brave of Belmonts", Color.WHITE);
		renderer.setFontToPanel(assets.FONT_TEXT);
		renderer.print(18 + leftMargin, 8, "SCORE", assets.COLOR_BOLD);
		renderer.print(25 + leftMargin, 8, "DATE", assets.COLOR_BOLD);
		renderer.print(36 + leftMargin, 8, "TURNS", assets.COLOR_BOLD);
		renderer.print(43 + leftMargin, 8, "DEATH", assets.COLOR_BOLD);

		for (int i = 0; i < scores.length; i++) {
			renderer.print(7 + leftMargin, (9 + i),
					scores[i].getName() + " (" + scores[i].getPlayerClass() + ")",
					Color.WHITE);
			renderer.print(18 + leftMargin, (9 + i), "" + scores[i].getScore(),
					Color.GRAY);
			renderer.print(25 + leftMargin, (9 + i), "" + scores[i].getDate(),
					Color.GRAY);
			renderer.print(36 + leftMargin, (9 + i), "" + scores[i].getTurns(),
					Color.GRAY);
			renderer.print(43 + leftMargin, (9 + i), "" + scores[i].getDeathString()
					+ " on level " + scores[i].getDeathLevel(), Color.GRAY);

		}
		renderer.print(7 + leftMargin, 20, "[space] to continue", assets.COLOR_BOLD);
		renderer.refresh();
		renderer.waitKey(KeyCode.SPACE);
	}

	public void showHostageRescue( Hostage h )
	{
		String text = "Thanks for rescuing me! I will give you " + h.getReward( )
				+ " gold, it is all I have!";
		if ( h.getItemReward( ) != null )
			text += "\n Take this, the " + h.getItemReward( ).getDescription( )
					+ ", I found it inside the castle ";
		showTextBox( text, 30, 40, 300, 300 );
	}

	public void showIntro( Player player ) {
		renderer.drawImage(assets.IMAGE_PROLOGUE);
		renderer.setFontToPanel(assets.FONT_TITLE);
		renderer.printAtPixel(156, 136, "prologue", Color.WHITE);
		// si.drawImage(311,64, IMG_GBAT);
		renderer.setFontToPanel(assets.FONT_TEXT);
		renderer.setColor(Color.GRAY);
		JTextArea t1 = createTempArea(150, 170, 510, 300);
		t1.setForeground(Color.LIGHT_GRAY);
		t1.setText(
				"In the year of 1691, a dark castle emerges from the cursed soils of the plains of Transylvannia. "
						+ "Chaos and death spread along the land, as the evil count Dracula unleases his powers, "
						+ "turning its forests and lakes into a pool of blood. \n\n"
						+ "The trip to the castle was long and harsh, after enduring many challenges through all Transylvannia, "
						+ "you are close to the castle of chaos. You are almost at Castlevania, and you are here on business: "
						+ "To destroy forever the Curse of the Evil Count.\n\n" +

						player.getPlot() + ", " + player.getDescription()
						+ " stands on a forest near the town of Petra and the cursed castle; "
						+ player.getPlot2()
						+ " and the fate running through his veins being the sole hope for mankind.");

		renderer.addComponentToPanel(t1);
		renderer.printAtPixel(156, 490, "[Space] to continue", assets.COLOR_BOLD);
		renderer.refresh();
		renderer.waitKey(KeyCode.SPACE);
		renderer.remove(t1);
	}

	public Advancement showLevelUp( Vector <Advancement> advancements ) {
		((GFXUserInterface) UserInterface.getUI()).messageBox.setVisible(false);

		renderer.saveBuffer();
		renderer.drawImage(assets.IMAGE_LEVEL_UP);
		renderer.print(4, 3, "You have gained a chance to pick an advancement!",
				assets.COLOR_BOLD);
		for (int i = 0; i < advancements.size(); i++) {
			renderer.print(3, 4 + i * 2,
					((char) ('a' + i)) + ". "
							+ ((Advancement) advancements.elementAt(i)).getName(),
					assets.COLOR_BOLD);
			renderer.print(3, 5 + i * 2, "   "
							+ ((Advancement) advancements.elementAt(i)).getDescription(),
					Color.WHITE);
		}
		renderer.refresh();
		int choice = readAlphaToNumber(advancements.size());
		renderer.restore();
		renderer.refresh();
		((GFXUserInterface) UserInterface.getUI()).messageBox.setVisible(true);
		return (Advancement) advancements.elementAt(choice);
	}

	public void showMap( String locationKey, String locationDescription ) {
		renderer.saveBuffer();
		renderer.drawImage(50, 50, assets.IMAGE_MAP);
		renderer.print(180, 200, locationDescription, Color.BLACK);
		if (locationKey != null) {
			Position location = (Position) locationKeys.get(locationKey);
			if (location != null)
				renderer.drawImage(location.x + 53, location.y + 53, assets.IMAGE_MAPMARKER);
		}
		renderer.refresh();
		renderer.waitKey(KeyCode.SPACE);
		renderer.restore();
		renderer.refresh();
	}

	public void showMonsterScreen( Monster who, Player player ) {
		((GFXUserInterface) UserInterface.getUI()).messageBox.setVisible(false);
		GFXAppearance app = (GFXAppearance) who.getAppearance();
		// si.saveBuffer();
		renderer.drawImage(assets.IMAGE_LEVEL_UP);
		renderer.print(6, 3, who.getDescription(), assets.COLOR_BOLD);
		renderer.drawImage(15, 40, app.getImage());
		JTextArea t1 = createTempArea(20, 125, 700, 400);
		t1.setForeground(Color.WHITE);
		t1.setText(who.getLongDescription());
		renderer.addComponentToPanel(t1);
		renderer.setFontToPanel(assets.FONT_TEXT);
//		MonsterRecord record = Main.getMonsterRecordFor(who.getID());
		long baseKilled = 0;
		long baseKillers = 0;
//		if (record != null) {
//			baseKilled = record.getKilled();
//			baseKillers = record.getKillers();
//		}
		renderer.print(2, 17, "You have killed "
				+ (baseKilled + player.getGameSessionInfo().getDeathCountFor(who))
				+ " " + who.getDescription() + "s", Color.WHITE);
		if (baseKillers == 0) {
			renderer.print(2, 18, "No " + who.getDescription() + "s have killed you",
					Color.WHITE);
		} else {
			renderer.print(2, 18, "You have been killed by " + baseKillers + " "
					+ who.getDescription() + "s", Color.WHITE);
		}
		renderer.print(2, 20, "[Press Space]", Color.WHITE);
		renderer.refresh();
		renderer.waitKey(KeyCode.SPACE);
		((GFXUserInterface) UserInterface.getUI()).messageBox.setVisible(true);
		t1.setVisible(false);
		renderer.remove(t1);
		renderer.restore();
		renderer.refresh();
	}

	public boolean showResumeScreen( Player player ) {
		((GFXUserInterface) UserInterface.getUI()).messageBox.setVisible(false);
		renderer.drawImage(assets.IMAGE_RESUME);

		GameSessionInfo gsi = player.getGameSessionInfo();
		String heshe = (player.getSex() == Player.MALE ? "He" : "She");

		renderer.setFontToPanel(assets.FONT_TITLE);
		renderer.print(2, 3, "The chronicles of " + player.getName(), assets.COLOR_BOLD);
		JTextArea t1 = createTempArea(20, 125, 700, 120);
		t1.setForeground(Color.WHITE);
		t1.setText("  ...And so it was that " + player.getDescription() + ", "
				+ gsi.getDeathString() + " on the "
				+ player.getLevel().getDescription() + "...\n\n" + heshe + " scored "
				+ player.getScore() + " points and earned " + player.getGold()
				+ " gold \n\n" + heshe + " survived for " + gsi.getTurns()
				+ " turns \n\n" + heshe + " took " + gsi.getTotalDeathCount()
				+ " monsters to the other world");
		renderer.addComponentToPanel(t1);
		/*
		 * int i = 0; Enumeration monsters = gsi.getDeathCount().elements(); while
		 * (monsters.hasMoreElements()){ MonsterDeath mons = (MonsterDeath)
		 * monsters.nextElement(); si.print(5,11+i, mons.getTimes()
		 * +" "+mons.getMonsterDescription(), ConsoleSystemInterface.RED); i++; }
		 */
		renderer.setFontToPanel(assets.FONT_TEXT);
		renderer.print(2, 14, "Do you want to save your character memorial? [Y/N]",
				Color.WHITE);
		renderer.refresh();
		boolean ret = UserInterface.getUI().prompt();
		renderer.remove(t1);
		return ret;
	}

	public int showSavedGames( File[ ] saveFiles ) {
		renderer.drawImage(assets.IMAGE_SAVED);
		if (saveFiles == null || saveFiles.length == 0) {
			renderer.print(3, 6, "No adventurers available", Color.WHITE);
			renderer.print(4, 8, "[Space to Cancel]", Color.WHITE);
			renderer.refresh();
			renderer.waitKey(KeyCode.SPACE);
			return -1;
		}

		renderer.print(3, 6, "Pick an adventurer", Color.WHITE);
		for (int i = 0; i < saveFiles.length; i++) {
			String saveFileName = saveFiles[i].getName();
			renderer.print(5, 7 + i,
					(char) (KeyCode.a + i + 1) + " - "
							+ saveFileName.substring(0, saveFileName.indexOf(".sav")),
					assets.COLOR_BOLD);
		}
		renderer.print(3, 9 + saveFiles.length, "[Space to Cancel]", Color.WHITE);
		renderer.refresh();
		KeyCode x = renderer.getKeyPressed();
		while ((x.code < KeyCode.a || x.code > KeyCode.a + saveFiles.length - 1)
				&& x.code != KeyCode.SPACE) {
			x = renderer.getKeyPressed();
		}
		if (x.code == KeyCode.SPACE)
			return -1;
		else
			return x.code - KeyCode.a;
	}

	public void showScreen( Object pScreen ) {
		renderer.saveBuffer();
		String screenText = (String) pScreen;
		showTextBox(screenText, 430, 70, 340, 375);
		// si.waitKey(CharKey.SPACE);
		renderer.restore();
	}

	public void showTextBox(	String text, int consoleX, int consoleY, int consoleW,
								int consoleH ) {
		addornedTextArea.setBounds(consoleX, consoleY, consoleW, consoleH);
		addornedTextArea.setText(text);
		addornedTextArea.setVisible(true);
		renderer.waitKey(KeyCode.SPACE);
		addornedTextArea.setVisible(false);
	}

	public void showTextBox(	String text, int consoleX, int consoleY, int consoleW,
								int consoleH, Font f ) {
		addornedTextArea.setBounds(consoleX, consoleY, consoleW, consoleH);
		addornedTextArea.setText(text);
		addornedTextArea.setFont(f);
		addornedTextArea.setVisible(true);
		renderer.waitKey(KeyCode.SPACE);
		addornedTextArea.setVisible(false);
	}

	// private Color TRANSPARENT_BLUE = new Color(100,100,100,200);

	public void showTextBox(	String title, String text, int consoleX, int consoleY,
								int consoleW, int consoleH )
	{
		showTextBox( title + " " + text, consoleX, consoleY, consoleW, consoleH );
	}

	public void showTextBoxNoWait(	String text, int consoleX, int consoleY, int consoleW,
									int consoleH )
	{
		addornedTextArea.setBounds( consoleX, consoleY, consoleW, consoleH );
		addornedTextArea.setText( text );
		addornedTextArea.setVisible( true );
	}

	public boolean showTextBoxPrompt(	String text, int consoleX, int consoleY,
										int consoleW, int consoleH ) {
		addornedTextArea.setBounds(consoleX, consoleY, consoleW, consoleH);
		addornedTextArea.setText(text);
		addornedTextArea.setVisible(true);
		KeyCode x = new KeyCode(KeyCode.NONE);
		while (x.code != KeyCode.Y && x.code != KeyCode.y && x.code != KeyCode.N
				&& x.code != KeyCode.n)
			x = renderer.getKeyPressed();
		boolean ret = (x.code == KeyCode.Y || x.code == KeyCode.y);
		addornedTextArea.setVisible(false);
		return ret;
	}
	public void showTimeChange(	boolean day, boolean fog, boolean rain,
								boolean thunderstorm, boolean sunnyDay )
	{
		String baseMessage = getTimeChangeMessage( day, fog, rain, thunderstorm,
				sunnyDay );
		showTextBox( baseMessage, 40, 60, 300, 200 );
	}

	public int showTitleScreen( ) {
		int middlePoint = Asset.SCREEN_WIDTH / 2;
		int pickerXCoordinate = (Asset.SCREEN_WIDTH / 2)
				- (assets.IMAGE_PICKER.getWidth() / 2);

		((GFXUserInterface) UserInterface.getUI()).messageBox.setVisible(false);
		((GFXUserInterface) UserInterface.getUI()).persistantMessageBox
				.setVisible(false);

		renderer.setFontToPanel(assets.FONT_TEXT);
		renderer.drawImage(assets.IMAGE_TITLE);

		renderer.printAtPixelCentered(middlePoint, (int) (530 * Asset.SCREEN_SCALE),
				"'CastleVania' is a trademark of Konami Corporation.",
				Asset.COLOR_BOLD);
		renderer.printAtPixelCentered(middlePoint, (int) (555 * Asset.SCREEN_SCALE), "CastlevaniaRL v"
						+ Game.getVersion() + ", Developed by Santiago Zapata 2005-2010",
				Color.WHITE);
		renderer.printAtPixelCentered(middlePoint, (int) (570 * Asset.SCREEN_SCALE),
				"Artwork by Christopher Barrett, 2006-2007", Color.WHITE);
		renderer.printAtPixelCentered(middlePoint, (int) (585 * Asset.SCREEN_SCALE),
				"Midi Tracks by Jorge E. Fuentes, JiLost, Nicholas and Tom Kim",
				Color.WHITE);

		KeyCode x = new KeyCode(KeyCode.NONE);
		int choice = 0;
		renderer.saveBuffer();

		while (true) {
			renderer.restore();

			renderer.drawImage(pickerXCoordinate, (int) ((356 + choice * 20) * Asset.SCREEN_SCALE),
					assets.IMAGE_PICKER);
			renderer.printAtPixelCentered(middlePoint, (int) (368 * Asset.SCREEN_SCALE), "a. New Game",
					Color.WHITE);
			renderer.printAtPixelCentered(middlePoint, (int) (388 * Asset.SCREEN_SCALE),
					"b. Load Character", Color.WHITE);
			renderer.printAtPixelCentered(middlePoint, (int) (408 * Asset.SCREEN_SCALE),
					"c. View Prologue", Color.WHITE);
			renderer.printAtPixelCentered(middlePoint, (int) (428 * Asset.SCREEN_SCALE), "d. Training",
					Color.WHITE);
			renderer.printAtPixelCentered(middlePoint, (int) (448 * Asset.SCREEN_SCALE),
					"e. Prelude Arena", Color.WHITE);
			renderer.printAtPixelCentered(middlePoint, (int) (468 * Asset.SCREEN_SCALE),
					"f. Show HiScores", Color.WHITE);
			renderer.printAtPixelCentered(middlePoint, (int) (488 * Asset.SCREEN_SCALE), "g. Quit",
					Color.WHITE);
			renderer.refresh();
			while (x.code != KeyCode.A && x.code != KeyCode.a && x.code != KeyCode.B
					&& x.code != KeyCode.b && x.code != KeyCode.C && x.code != KeyCode.c
					&& x.code != KeyCode.D && x.code != KeyCode.d && x.code != KeyCode.E
					&& x.code != KeyCode.e && x.code != KeyCode.G && x.code != KeyCode.g
					&& x.code != KeyCode.F && x.code != KeyCode.f
					&& x.code != KeyCode.UARROW && x.code != KeyCode.DARROW
					&& x.code != KeyCode.SPACE && x.code != KeyCode.ENTER)
				x = renderer.getKeyPressed();
			switch (x.code) {
				case KeyCode.A:
				case KeyCode.a:
					return 0;
				case KeyCode.B:
				case KeyCode.b:
					return 1;
				case KeyCode.C:
				case KeyCode.c:
					return 2;
				case KeyCode.D:
				case KeyCode.d:
					return 3;
				case KeyCode.E:
				case KeyCode.e:
					return 4;
				case KeyCode.F:
				case KeyCode.f:
					return 5;
				case KeyCode.G:
				case KeyCode.g:
					return 6;
				case KeyCode.UARROW:
					if (choice > 0)
						choice--;
					break;
				case KeyCode.DARROW:
					if (choice < 6)
						choice++;
					break;
				case KeyCode.SPACE:
				case KeyCode.ENTER:
					return choice;
			}
			x.code = KeyCode.NONE;
		}
	}

	private Image getPortraitForPlayer( Player p )
	{
		if ( p.getSex( ) == Player.MALE )
		{
			GFXCuts assets = GFXCuts.getInstance( );

			switch ( p.getPlayerClass( ) )
			{
			case Player.CLASS_VAMPIREKILLER:
				return assets.PRT_M1;
			case Player.CLASS_RENEGADE:
				return assets.PRT_M2;
			case Player.CLASS_VANQUISHER:
				return assets.PRT_M3;
			case Player.CLASS_INVOKER:
				return assets.PRT_M4;
			case Player.CLASS_MANBEAST:
				return assets.PRT_M5;
			case Player.CLASS_KNIGHT:
				return assets.PRT_M6;
			}
		}

		else
		{
			GFXCuts assets = GFXCuts.getInstance( );

			switch ( p.getPlayerClass( ) )
			{
			case Player.CLASS_VAMPIREKILLER:
				return assets.PRT_F1;
			case Player.CLASS_RENEGADE:
				return assets.PRT_F2;
			case Player.CLASS_VANQUISHER:
				return assets.PRT_F3;
			case Player.CLASS_INVOKER:
				return assets.PRT_F4;
			case Player.CLASS_MANBEAST:
				return assets.PRT_F5;
			case Player.CLASS_KNIGHT:
				return assets.PRT_F6;
			}
		}

		return null;
	}

	private int readAlphaToNumber( int numbers )
	{
		while ( true ) {
			KeyCode key = renderer.getKeyPressed();
			if (key.code >= KeyCode.A && key.code <= KeyCode.A + numbers - 1) {
				return key.code - KeyCode.A;
			}
			if (key.code >= KeyCode.a && key.code <= KeyCode.a + numbers - 1) {
				return key.code - KeyCode.a;
			}
		}
	}

}
