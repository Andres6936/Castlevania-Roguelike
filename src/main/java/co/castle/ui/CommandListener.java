package co.castle.ui;

public interface CommandListener
{
	public final static int QUIT = 0, SAVE = 1, NONE = 2, HELP = 3, LOOK = 4, RESTART = 5,
			SHOWINVEN = 6, SHOWHISCORES = 7, SHOWSKILLS = 8, SHOWSTATS = 9,
			PROMPTQUIT = 10, PROMPTSAVE = 11, SHOWUNEQUIP = 12, SHOWMESSAGEHISTORY = 13,
			SHOWMAP = 14, SWITCHMUSIC = 15, EXAMINELEVELMAP = 16, CHARDUMP = 17;

	public void commandSelected( int pCommand );
}