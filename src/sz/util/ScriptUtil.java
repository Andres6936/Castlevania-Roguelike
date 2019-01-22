package sz.util;

public class ScriptUtil
{
	public static String replace( String[ ] marks, String[ ] replacements, String source )
	{
		String ret = source;
		for ( int i = 0; i < marks.length; i++ )
		{
			ret = ret.replaceAll( marks[ i ], replacements[ i ] );
		}
		return ret;
	}
}
