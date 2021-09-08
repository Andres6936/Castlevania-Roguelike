package sz.csi;

import java.util.Hashtable;

import sz.util.Debug;

public class KeyCode {
	public int code;

	public final static Hashtable<String, String> mirrors = new Hashtable<>(20);

	public final static int DUMMY = -1;
	public final static int UARROW = 0;
	public final static int DARROW = 1;
	public final static int LARROW = 2;
	public final static int RARROW = 3;
	public final static int LCTRL = 4;
	public final static int RCTRL = 5;
	public final static int LALT = 6;
	public final static int RALT = 7;
	public final static int LSHIFT = 8;
	public final static int RSHIFT = 9;
	public final static int ENTER = 10;
	public final static int BACKSPACE = 11;
	public final static int F1 = 12;
	public final static int F2 = 13;
	public final static int F3 = 14;
	public final static int F4 = 15;
	public final static int F5 = 16;
	public final static int F6 = 17;
	public final static int F7 = 18;
	public final static int F8 = 19;
	public final static int F9 = 20;
	public final static int F10 = 21;
	public final static int F11 = 22;
	public final static int F12 = 23;
	public final static int INSERT = 24;
	public final static int HOME = 25;
	public final static int PAGEUP = 26;
	public final static int PAGEDOWN = 27;
	public final static int DELETE = 28;
	public final static int END = 29;
	public final static int ESC = 30;
	public final static int TAB = 31;
	public final static int OPENSHARPBRACETS = 32;
	public final static int CLOSESHARPBRACETS = 33;
	public final static int SEMICOLON = 34;
	public final static int APOSTROPHE = 35;
	public final static int COMMA = 36;
	public final static int DOT = 37;
	public final static int SLASH = 38;
	public final static int BACKSLASH = 39;
	public final static int SPACE = 40;
	public final static int MINUS = 41;
	public final static int EQUALS = 42;
	public final static int BACKAPOSTROPHE = 43;
	public final static int CURLYMINUS = 44;
	public final static int EXCLAMATION = 45;
	public final static int ARROBE = 46;
	public final static int MONEY = 47;
	public final static int PERCENTAGE = 48;
	public final static int EXPONENCIATION = 49;
	public final static int AMPERSAND = 50;
	public final static int ASTERISK = 51;
	public final static int OPENPARENTHESIS = 52;
	public final static int CLOSEPARENTHESIS = 53;
	public final static int UNDERLINE = 54;
	public final static int PLUS = 55;
	public final static int OPENCURLYBRACETS = 56;
	public final static int CLOSECURLYBRACETS = 57;
	public final static int COLON = 58;
	public final static int COMILLAS = 59;
	public final static int LESSTHAN = 60;
	public final static int MORETHAN = 61;
	public final static int QUESTION = 62;
	public final static int OR = 63;
	public final static int a = 64;
	public final static int b = 65;
	public final static int c = 66;
	public final static int d = 67;
	public final static int e = 68;
	public final static int f = 69;
	public final static int g = 70;
	public final static int h = 71;
	public final static int i = 72;
	public final static int j = 73;
	public final static int k = 74;
	public final static int l = 75;
	public final static int m = 76;
	public final static int n = 77;
	public final static int o = 78;
	public final static int p = 79;
	public final static int q = 80;
	public final static int r = 81;
	public final static int s = 82;
	public final static int t = 83;
	public final static int u = 84;
	public final static int v = 85;
	public final static int w = 86;
	public final static int x = 87;
	public final static int y = 88;
	public final static int z = 89;
	public final static int A = 90;
	public final static int B = 91;
	public final static int C = 92;
	public final static int D = 93;
	public final static int E = 94;
	public final static int F = 95;
	public final static int G = 96;
	public final static int H = 97;
	public final static int I = 98;
	public final static int J = 99;
	public final static int K = 100;
	public final static int L = 101;
	public final static int M = 102;
	public final static int N = 103;
	public final static int O = 104;
	public final static int P = 105;
	public final static int Q = 106;
	public final static int R = 107;
	public final static int S = 108;
	public final static int T = 109;
	public final static int U = 110;
	public final static int V = 111;
	public final static int W = 112;
	public final static int X = 113;
	public final static int Y = 114;
	public final static int Z = 115;
	public final static int NONE = 116;
	public final static int N0 = 117;
	public final static int N1 = 118;
	public final static int N2 = 119;
	public final static int N3 = 120;
	public final static int N4 = 121;
	public final static int N5 = 122;
	public final static int N6 = 123;
	public final static int N7 = 124;
	public final static int N8 = 125;
	public final static int N9 = 126;
	public final static int CTRL = 127;

	static {
		mirrors.put("" + OPENSHARPBRACETS, "[");
		mirrors.put("" + CLOSESHARPBRACETS, "]");
		mirrors.put("" + SEMICOLON, ";");
		mirrors.put("" + APOSTROPHE, "'");
		mirrors.put("" + COMMA, ",");
		mirrors.put("" + DOT, ".");
		mirrors.put("" + SLASH, "/");
		mirrors.put("" + BACKSLASH, "\\");
		mirrors.put("" + SPACE, " ");
		mirrors.put("" + MINUS, "-");
		mirrors.put( "" + EQUALS, "=" );
		mirrors.put( "" + BACKAPOSTROPHE, "`" );
		mirrors.put( "" + CURLYMINUS, "~" );
		mirrors.put( "" + EXCLAMATION, "!" );
		mirrors.put( "" + ARROBE, "@" );
		mirrors.put( "" + MONEY, "$" );
		mirrors.put( "" + PERCENTAGE, "%" );
		mirrors.put( "" + EXPONENCIATION, "^" );
		mirrors.put( "" + AMPERSAND, "&" );
		mirrors.put( "" + ASTERISK, "*" );
		mirrors.put( "" + OPENPARENTHESIS, "(" );
		mirrors.put( "" + CLOSEPARENTHESIS, ")" );
		mirrors.put( "" + UNDERLINE, "_" );
		mirrors.put( "" + PLUS, "+" );
		mirrors.put( "" + OPENCURLYBRACETS, "{" );
		mirrors.put( "" + CLOSECURLYBRACETS, "}" );
		mirrors.put( "" + COLON, ":" );
		mirrors.put( "" + COMILLAS, "\"" );
		mirrors.put( "" + LESSTHAN, "<" );
		mirrors.put( "" + MORETHAN, ">" );
		mirrors.put( "" + QUESTION, "?" );
		mirrors.put( "" + OR, "|" );
		mirrors.put( "" + CTRL, "Ctrl" );

	}

	public KeyCode() {

	}

	public KeyCode(int code) {
		this.code = code;
	}

	public static String getString( int code )
	{
		if ( code >= 90 && code <= 115 )
			return "" + ( (char) ( code - 25 ) );
		if ( code >= 64 && code <= 89 )
			return "" + ( (char) ( code + 33 ) );
		if ( code >= 117 && code <= 126 )
			return "" + ( (char) ( code - 69 ) );
		String ret = mirrors.get("" + code);
		if ( ret != null )
			return ret;
		else
			return "(" + code + ")";
	}

	public static String getString( String code )
	{
		return getString( Integer.parseInt( code ) );
	}

	public boolean isAlphaNumeric( )
	{
		if ( code >= 90 && code <= 115 )
			return true;
		if ( code >= 64 && code <= 89 )
			return true;
		if ( code >= 117 && code <= 126 )
			return true;
		return false;
	}

	public boolean isArrow( )
	{
		Debug.enterMethod( this, "isArrow" );
		boolean ret = ( isRightArrow( ) || isUpArrow( ) || isLeftArrow( )
				|| isDownArrow( ) || isDownLeftArrow( ) || isDownRightArrow( )
				|| isUpLeftArrow( ) || isUpRightArrow( ) || isSelfArrow( ) );
		Debug.exitMethod( ret + "" );
		return ret;
	}

	public boolean isDownArrow( )
	{
		return code == KeyCode.DARROW || code == KeyCode.N2;
	}

	public boolean isDownLeftArrow( )
	{
		return code == KeyCode.N1;
	}

	public boolean isDownRightArrow( )
	{
		return code == KeyCode.N3;
	}

	public boolean isLeftArrow( )
	{
		return code == KeyCode.LARROW || code == KeyCode.N4;
	}

	public boolean isMetaKey( )
	{
		return code == CTRL || code == RALT || code == RCTRL || code == RSHIFT
				|| code == LALT || code == LCTRL || code == LSHIFT;
	}

	public boolean isRightArrow( )
	{
		return code == KeyCode.RARROW || code == KeyCode.N6;
	}

	public boolean isSelfArrow( )
	{
		return code == KeyCode.N5;
	}

	public boolean isUpArrow( )
	{
		return code == KeyCode.UARROW || code == KeyCode.N8;
	}

	public boolean isUpLeftArrow( )
	{
		return code == KeyCode.N7;
	}

	public boolean isUpRightArrow( )
	{
		return code == KeyCode.N9;
	}

	public String toString( )
	{
		return getString( code );
	}
}