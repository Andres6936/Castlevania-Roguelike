package co.castle.level;

import co.castle.levelgen.LevelMetaData;
import sz.util.Util;

import java.util.Hashtable;
import java.util.Vector;

public class RepositoryLevelMetadata extends Hashtable<String, LevelMetaData> {
    public RepositoryLevelMetadata() {
        String[][] order = new String[][]
                {
                        {"TOWN", "ONE"},
                        {"FOREST", "ONE"},
                        {"CASTLE_BRIDGE", "ONE"},
                        {"GARDEN", "ONE"},
                        {"MAIN_HALLX", "ONE"},
                        {"QUARTERS_FORK", "ONE,NONUMBER"}, // Quarters Branch /*5+*/ /*unnumbered*/
                        {"MAIN_HALL", ""},
                        {"MOAT", "ONE"},
                        {"BAT_HALL", "ONE"},
                        {"DEATH_HALL", "ONE,NONUMBER"}, /* unnumbered */
                        {"TELEPAD1", "ONE,NONUMBER"}, /* unnumbered */
                        {"LABX", "ONE"},
                        {"VINDELITH_MEETING", "ONE,NONUMBER"},
                        {"LAB", ""},
                        {"MEDUSA_LAIR", "ONE, NONUMBER"}, /* unnumbered */
                        {"CHAPEL", "ONE"},
                        {"TELEPAD2", "ONE, NONUMBER"}, /* unnumbered */
                        {"RUINSX", "ONE"},
                        {"RUINSY", "ONE"},
                        {"CLARA_MEETING", "ONE, NONUMBER"}, /* unnumbered */
                        {"RUINS", ""},
                        {"MUMMIES_LAIR", "ONE, NONUMBER"}, /* unnumbered */
                        {"CAVESX", "ONE"},
                        {"CAVE_FORK", "ONE, NONUMBER"}, // Warehouse branch /*24+*/ /*unnumbered*/
                        {"CAVES", ""},
                        {"DRAGON_KING_LAIR", "ONE, NONUMBER"}, /* unnumbered */
                        {"TELEPAD3", "ONE, NONUMBER"}, /* unnumbered */
                        {"COURTYARD", "ONE"},
                        {"DUNGEONX", "ONE"},
                        {"DUNGEONY", "ONE"},
                        {"BADBELMONT", "ONE, NONUMBER"}, /* unnumbered */
                        {"DUNGEON", ""},
                        {"FRANK_LAIR", "ONE, NONUMBER"}, /* unnumbered */
                        {"TELEPAD4", "ONE, NONUMBER"}, /* unnumbered */
                        /* {"FINAL_BRIDGE", "*"}, */
                        {"CLOCK_BASE", ""},
                        {"TOWER", "ONE"},
                        {"TOWER_TOP", "ONE"}, /* unnumbered */
                        {"KEEP", "ONE"},
                        {"VOID", "ONE"}};
        processLevelData(order, 0);

        // Warehouse Branch
        order = new String[][]
                {
                        {"WAREHOUSEX", "ONE, NONUMBER"}, /* Starts in 6 */
                        {"DEEP_FORK", "ONE, NONUMBER"}, /* 6+ */
                        {"WAREHOUSE", ""},
                        {"TELEPADX1", "ONE, NONUMBER"},
                        {"CATACOMBS", ""},
                        {"LEGION_LAIR", "ONE, NONUMBER"},
                        {"TELEPADX2", "ONE, NONUMBER"},
                        {"PRIZE_CATACOMBS", "ONE, NONUMBER"},};
        processLevelData(order, 8);

        // Underground reservoir branch
        order = new String[][]
                {
                        {"RESERVOIR", ""}, /* Start in 7 */
                        {"RESERVOIR", ""},
                        {"WATER_DRAGON_LAIR", "ONE, NONUMBER"},
                        {"SPECIAL_RESERVOIR_TELEPAD", "ONE, NONUMBER"},
                        {"PRIZE_RESERVOIR", "ONE, NONUMBER"},};

        processLevelData(order, 10);

        // Quarters Branch
        order = new String[][]
                {
                        {"INNER_QUARTERS", ""},
                        {"INNER_QUARTERS", ""},
                        {"INNER_QUARTERS", ""},
                        {"TELEPADZ1", "ONE, NONUMBER"},
                        {"QUARTERS_PRIZE", "ONE, NONUMBER"},};

        processLevelData(order, 7);

        // Sewers Branch
        order = new String[][]
                {
                        {"SPECIAL_SEWERS_ENTRANCE", "ONE, NONUMBER"},
                        {"SEWERS", "NONUMBER"},
                        {"SEWERSY", "NONUMBER"},
                        {"SEWERSZ", "NONUMBER"},
                        {"DEEP_SEWERS", "ONE, NONUMBER"},
                        {"PRIZE_SEWERS", "ONE, NONUMBER"},};
        processLevelData(order, 1);

        // End of Branches
        LevelMetaData md = new LevelMetaData();
        md.setLevelID("CHARRIOT_W");
        md.addExits("FOREST0", "_NEXT");
        put("CHARRIOT_W", md);

        md = new LevelMetaData();
        md.setLevelID("DINING_HALL");
        put("DINING_HALL", md);

        md = new LevelMetaData();
        md.setLevelID("TRAINING");
        put("TRAINING", md);

        md = new LevelMetaData();
        md.setLevelID("PROLOGUE_KEEP");
        put("PROLOGUE_KEEP", md);

        md = new LevelMetaData();
        md.setLevelID("PRELUDE_ARENA");
        put("PRELUDE_ARENA", md);

        md = new LevelMetaData();
        md.setLevelID("VILLA");
        put("VILLA", md);
    }

    private void processLevelData(String[][] order, int startLevelNumber) {
        Vector<String> levels = new Vector<>(5);
        Vector<String> numbered = new Vector<>(5);
        int levelCount = startLevelNumber;
        for (String[] strings : order) {
            int n = Util.rand(3, 6);
            if (strings[1].contains("ONE"))
                n = 1;
            for (int j = 0; j < n; j++) {
                levels.add(strings[0] + j);
                if (!strings[1].contains("NONUMBER"))
                    numbered.add("yes");
                else
                    numbered.add("no");
            }
        }

        for (int i = 0; i < levels.size(); i++) {
            LevelMetaData md = new LevelMetaData();
            md.setLevelID(levels.get(i));
            if (i > 0) {
                md.addExits(levels.get(i - 1), "_BACK");
            }
            if (i < levels.size() - 1) {
                md.addExits(levels.get(i + 1), "_NEXT");
            }
            if (numbered.get(i).equals("yes")) {
                md.setLevelNumber(levelCount);
                levelCount++;
            }

            put(md.getLevelID(), md);
        }
    }
}
