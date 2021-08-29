package co.castle.data;

import co.castle.ai.SelectorFactory;
import co.castle.feature.SmartFeature;
import co.castle.ui.AppearanceFactory;

public class SmartFeatures {
	public static SmartFeature[] getSmartFeatures() {
		SelectorFactory sf = SelectorFactory.getSelectorFactory();
		AppearanceFactory apf = AppearanceFactory.getAppearanceFactory();
		SmartFeature[] ret = new SmartFeature[6];
		ret[0] = new SmartFeature("CROSS", "Holy cross",
				apf.getAppearance("CROSS"));
		ret[1] = new SmartFeature("BURNING_FLAME", "Burning Flame",
				apf.getAppearance("FLAME"));
		ret[2] = new SmartFeature("GARLIC", "Garlic", apf.getAppearance("GARLIC"));
		ret[3] = new SmartFeature("BIBUTI", "Bibuti Powder",
				apf.getAppearance("BIBUTI"));
		ret[4] = new SmartFeature("FLAME", "Magic Flame",
				apf.getAppearance( "FLAME" ) );
		ret[ 5 ] = new SmartFeature( "BLAST_CRYSTAL", "Blast Crystal",
				apf.getAppearance( "BLAST_CRYSTAL" ) );

		ret[ 0 ].setSelector( sf.getSelector( "CROSS_SELECTOR" ) );
		ret[ 1 ].setSelector( sf.getSelector( "FLAME_SELECTOR" ) );
		ret[ 1 ].setDamageOnStep( 1 );
		ret[ 2 ].setSelector( sf.getSelector( "COUNTDOWN" ) );
		ret[ 2 ].setEffectOnStep( "TRAP" );
		ret[ 3 ].setSelector( sf.getSelector( "COUNTDOWN" ) );
		ret[ 3 ].setDamageOnStep( 3 );
		ret[ 4 ].setSelector( sf.getSelector( "COUNTDOWN" ) );
		ret[ 4 ].setDamageOnStep( 4 );
		ret[ 5 ].setSelector( sf.getSelector( "CRYSTAL_SELECTOR" ) );
		return ret;
	}
}
