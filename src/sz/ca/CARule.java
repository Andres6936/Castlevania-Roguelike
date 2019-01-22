package sz.ca;

import sz.util.Debug;

public class CARule
{
	/**
	 * Stores a rule of the form: if {baseCell} has {condType} {cellQuant}
	 * {cellParam} around, turn into {destinationCell} Example when 1 has morethan 4
	 * 2 around turn into 0
	 */
	private int baseCell, condType, cellQuant, cellParam, destinationCell;

	public final static int HAS = 0, MORE_THAN = 1, LESS_THAN = 2;

	public CARule(	int baseCell, int condType, int cellQuant, int cellParam,
					int destinationCell )
	{
		validateType( condType, cellQuant );
		this.baseCell = baseCell;
		Debug.doAssert( condType >= 0 && condType <= 2, " Valid conditions on rule set" );
		this.condType = condType;
		this.cellQuant = cellQuant;
		this.cellParam = cellParam;
		this.destinationCell = destinationCell;
		Debug.exitMethod( );
	}

	public void apply( int x, int y, Matrix m, boolean wrap )
	{
		// Debug.enterSoftMethod(this, "apply "+this+" to "+x+","+y);
		if ( m.get( x, y ) == baseCell )
		{
			int surroundingCount = 0;
			if ( wrap )
				surroundingCount = m.getSurroundingCount( x, y, cellParam );
			else
				surroundingCount = m.getSurroundingCountNoWrap( x, y, cellParam );
			switch ( condType )
			{
			case HAS:
				if ( surroundingCount == cellQuant )
				{
					m.setFuture( destinationCell, x, y );
				}
				break;
			case MORE_THAN:
				if ( surroundingCount > cellQuant )
				{
					m.setFuture( destinationCell, x, y );
				}
				break;
			case LESS_THAN:
				if ( surroundingCount < cellQuant )
				{
					m.setFuture( destinationCell, x, y );
				}
				break;
			default:
				Debug.doAssert( false, "Condition Type" );
			}

		}

		// Debug.exitSoftMethod();
	}

	public String toString( )
	{
		String comparation = " INVALIDCOMPARATION";
		switch ( condType )
		{
		case 0:
			comparation = " has ";
			break;
		case 1:
			comparation = " there are more than ";
			break;
		case 2:
			comparation = " there are less than ";
			break;
		}
		return "When the cell status is " + baseCell + " and " + comparation + ""
				+ cellQuant + " cells of status " + cellParam + " around, turn it into "
				+ destinationCell;
	}

	private void validateType( int condType, int cellQuant )
	{
		Debug.doAssert( cellQuant >= 0 && cellQuant <= 8,
				"Invalid cell quantity parameter on rule: " + cellQuant );
		Debug.doAssert( condType >= 0 && condType <= 2,
				"Invalid condition type : " + condType );
	}

}