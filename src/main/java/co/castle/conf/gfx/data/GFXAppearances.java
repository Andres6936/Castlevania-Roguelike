package co.castle.conf.gfx.data;

import co.castle.ui.Appearance;
import co.castle.ui.graphicsUI.GFXAppearance;
import sz.util.ImageUtils;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;

public class GFXAppearances extends ArrayList<Appearance> {
    private final int CELL_HEIGHT;
    private final int WIDTH_BIG;
    private final int WIDTH_HALF;

    private final int WIDTH_NORMAL;

    // Get instance of Asset
    protected Asset configuration;

    public GFXAppearances(Asset configuration) {
        this.configuration = configuration;
        WIDTH_BIG = Asset.BIG_TILE_WIDTH;
        WIDTH_HALF = Asset.HALF_TILE_WIDTH;
        CELL_HEIGHT = Asset.CELL_HEIGHT;
        WIDTH_NORMAL = Asset.NORMAL_TILE_WIDTH;

        SetAppearances();
    }

    private GFXAppearance createAppearance(String ID, BufferedImage bigImage, int xpos, int ypos) {
        xpos--;
        ypos--;

        try {
            BufferedImage img = ImageUtils.createImage(bigImage, xpos * WIDTH_NORMAL,
                    ypos * WIDTH_NORMAL, WIDTH_NORMAL, WIDTH_NORMAL);
            return new GFXAppearance(ID, img, 0, 0);
        } catch (Exception e) {
            System.err.println("Error loading image ");
            e.printStackTrace();
        }
        return null;
    }

    private GFXAppearance createAppearance(String ID, BufferedImage bigImage, int xpos,
                                           int ypos, int width, int height, int superw,
                                           int superh) {
        xpos--;
        ypos--;
        try {
            BufferedImage img = ImageUtils.createImage(bigImage, xpos, ypos, width,
                    height);
            return new GFXAppearance(ID, img, superw, superh);
        } catch (Exception e) {
            System.err.println("Error loading image ");
        }
        return null;
    }

    private GFXAppearance createBigAppearance(String ID, BufferedImage bigImage, int xpos, int ypos) {
        xpos--;
        ypos--;
        try {
            BufferedImage img = ImageUtils.createImage(bigImage, xpos * WIDTH_BIG,
                    ypos * WIDTH_BIG, WIDTH_BIG, WIDTH_BIG);
            return new GFXAppearance(ID, img, WIDTH_HALF / 2, WIDTH_HALF);
        } catch (Exception e) {
            System.err.println("Error loading image ");
        }
        return null;
    }

    private GFXAppearance createHalfAppearance(String ID, BufferedImage bigImage, int xpos,
                                               int ypos) {
        xpos--;
        ypos--;
        try {
            BufferedImage img = ImageUtils.createImage(bigImage, xpos * WIDTH_HALF,
                    ypos * WIDTH_HALF, WIDTH_HALF, WIDTH_HALF);
            return new GFXAppearance(ID, img, -8, 0);
        } catch (Exception e) {
            System.err.println("Error loading image ");
        }
        return null;
    }

    private GFXAppearance createTerrainAppearance(String ID, int xpos, int ypos) {
        xpos--;
        ypos--;
        BufferedImage bigImage = configuration.IMAGE_TERRAIN;
        BufferedImage bigDarkImage = configuration.IMAGE_DARK_TERRAIN;
        BufferedImage bigNiteImage = configuration.IMAGE_NIGHT_TERRAIN;
        BufferedImage bigDarkNiteImage = configuration.IMAGE_DARK_NIGHT_TERRAIN;

        try {
            BufferedImage img = ImageUtils.createImage(bigImage, xpos * WIDTH_NORMAL,
                    ypos * CELL_HEIGHT, WIDTH_NORMAL, CELL_HEIGHT);
            BufferedImage darkimg = ImageUtils.createImage(bigDarkImage,
                    xpos * WIDTH_NORMAL, ypos * CELL_HEIGHT, WIDTH_NORMAL, CELL_HEIGHT);
            BufferedImage niteimg = ImageUtils.createImage(bigNiteImage,
                    xpos * WIDTH_NORMAL, ypos * CELL_HEIGHT, WIDTH_NORMAL, CELL_HEIGHT);
            BufferedImage darkniteimg = ImageUtils.createImage(bigDarkNiteImage,
                    xpos * WIDTH_NORMAL, ypos * CELL_HEIGHT, WIDTH_NORMAL, CELL_HEIGHT);
            return new GFXAppearance(ID, img, darkimg, niteimg, darkniteimg,
                    0, 0);
        } catch (Exception e) {
            System.err.println("Error loading terrain image ");
        }
        return null;
    }

    private GFXAppearance createTerrainAppearance(String ID, int xpos, int ypos, int xoff,
                                                  int yoff) {
        xpos--;
        ypos--;
        BufferedImage bigImage = configuration.IMAGE_TERRAIN;
        BufferedImage bigDarkImage = configuration.IMAGE_DARK_TERRAIN;
        BufferedImage bigNiteImage = configuration.IMAGE_NIGHT_TERRAIN;
        BufferedImage bigDarkNiteImage = configuration.IMAGE_DARK_NIGHT_TERRAIN;

        try {
            BufferedImage img = ImageUtils.createImage(bigImage, xpos * WIDTH_NORMAL,
                    ypos * CELL_HEIGHT, WIDTH_NORMAL, CELL_HEIGHT);
            BufferedImage darkimg = ImageUtils.createImage(bigDarkImage,
                    xpos * WIDTH_NORMAL, ypos * CELL_HEIGHT, WIDTH_NORMAL, CELL_HEIGHT);
            BufferedImage niteimg = ImageUtils.createImage(bigNiteImage,
                    xpos * WIDTH_NORMAL, ypos * CELL_HEIGHT, WIDTH_NORMAL, CELL_HEIGHT);
            BufferedImage darkniteimg = ImageUtils.createImage(bigDarkNiteImage,
                    xpos * WIDTH_NORMAL, ypos * CELL_HEIGHT, WIDTH_NORMAL, CELL_HEIGHT);
            return new GFXAppearance(ID, img, darkimg, niteimg, darkniteimg,
                    xoff, yoff);
        } catch (Exception e) {
            System.err.println("Error loading terrain image ");
        }
        return null;
    }

    private GFXAppearance createXAppearance(String ID, BufferedImage bigImage, int xpos,
                                            int ypos, int width, int height) {
        try {
            BufferedImage img = ImageUtils.createImage(bigImage, xpos, ypos, width,
                    height);
            return new GFXAppearance(ID, img, (width - WIDTH_NORMAL) / 2,
                    height - WIDTH_NORMAL);
        } catch (Exception e) {
            System.err.println("Error loading image ");
        }
        return null;
    }

    private GFXAppearance createXAppearance(String ID, BufferedImage bigImage, int xpos,
                                            int ypos, int width, int height, int yoff) {
        try {
            BufferedImage img = ImageUtils.createImage(bigImage, xpos, ypos, width,
                    height);
            return new GFXAppearance(ID, img, (width - WIDTH_NORMAL) / 2,
                    height - WIDTH_NORMAL + yoff);
        } catch (Exception e) {
            System.err.println("Error loading image ");
        }
        return null;
    }

    private void add(Appearance... appearances) {
        this.addAll(Arrays.asList(appearances));
    }

    private void SetAppearances() {

        add(createTerrainAppearance("VOID", 4, 5),
                new GFXAppearance("NOTHING", null, null, null, null, 0, 0),

                /* NEEDED */
                createTerrainAppearance("COFFIN", 14, 2),
                createTerrainAppearance("DOOR", 11, 2),

                createAppearance("SHADOW", configuration.IMAGE_SHADOW, 1, 1),
                createAppearance("CHRISTOPHER_B", configuration.IMAGE_CHARACTERS, 1, 5),
                createAppearance("SOLIEYU_B", configuration.IMAGE_CHARACTERS, 2, 5),
                createAppearance("BADBELMONT", configuration.IMAGE_CHARACTERS, 2, 6),
                createBigAppearance("PRELUDE_DRACULA", configuration.IMAGE_BIG_MONSTERS, 4, 5),

                /* Town */
                createTerrainAppearance("TOWN_GRASS", 4, 1),
                createTerrainAppearance("TOWN_WALL", 1, 1),
                createTerrainAppearance("HOUSE_FLOOR", 9, 1),
                createTerrainAppearance("TOWN_DOOR", 1, 11),
                createTerrainAppearance("MIDWALKWAY", 2, 1),
                createTerrainAppearance("TOWN_CHURCH_FLOOR", 10, 1),
                createTerrainAppearance("TOWN_WATERWAY", 7, 1),
                createTerrainAppearance("BRICKWALKWAY", 6, 1),
                createTerrainAppearance("BRICKWALKWAY2", 10, 1),
                createTerrainAppearance("TOWN_ROOF", 14, 1),
                createTerrainAppearance("TOWN_STAIRSDOWN", 15, 1),
                createTerrainAppearance("TOWN_STAIRSUP", 16, 1),
                createTerrainAppearance("TOWN_TREE", 12, 1),
                createTerrainAppearance("TOWN_STAIRS", 3, 1),

                /* Dark Forest */
                createTerrainAppearance("FOREST_TREE", 8, 1),
                createTerrainAppearance("FOREST_TREE_1", 12, 1),
                createTerrainAppearance("FOREST_TREE_2", 13, 1),
                createTerrainAppearance("FOREST_GRASS", 17, 1),
                createTerrainAppearance("FOREST_DIRT", 19, 1),
                createTerrainAppearance("WRECKED_CHARRIOT", 15, 2),
                createTerrainAppearance("WRECKED_WHEEL", 14, 2),
                createTerrainAppearance("SIGN_POST", 16, 2),

                /* Castle Bridge */
                createTerrainAppearance("WOODEN_BRIDGE", 17, 3),
                createTerrainAppearance("DARK_LAKE", 13, 3),
                createTerrainAppearance("BRIDGE_WALKWAY", 16, 3),
                createTerrainAppearance("BRIDGE_COLUMN", 14, 3),
                createTerrainAppearance("STONE_BLOCK", 1, 1),

                /* Castle Garden */
                createTerrainAppearance("GARDEN_GRASS", 17, 1),
                createTerrainAppearance("GARDEN_WALKWAY", 19, 1),
                createTerrainAppearance("DEAD_STUMP", 4, 2),
                createTerrainAppearance("GARDEN_TREE", 5, 2),
                createTerrainAppearance("GARDEN_TORCH", 3, 2),
                createTerrainAppearance("GARDEN_FENCE", 6, 2),
                createTerrainAppearance("CASTLE_DOOR", 11, 2),
                createTerrainAppearance("GARDEN_FOUNTAIN_CENTER", 9, 2),
                createTerrainAppearance("GARDEN_FOUNTAIN_AROUND", 7, 2),
                createTerrainAppearance("GARDEN_FOUNTAIN_POOL", 8, 2),
                createTerrainAppearance("GARGOYLESTATUE", 2, 2),
                createTerrainAppearance("HUMANSTATUE", 1, 2),
                createTerrainAppearance("GARDEN_DOOR", 11, 1),
                createTerrainAppearance("GARDEN_WALL", 10, 2),
                createTerrainAppearance("CASTLE_WALL", 10, 2),

                /* Marble Hall */
                createTerrainAppearance("GRAY_COLUMN", 15, 3),
                createTerrainAppearance("MARBLE_COLUMN", 5, 3),
                createTerrainAppearance("MARBLE_FLOOR", 1, 3),
                createTerrainAppearance("MARBLE_STAIRS", 2, 3),
                createTerrainAppearance("MARBLE_MIDFLOOR", 3, 3),
                createTerrainAppearance("BIG_WINDOW", 8, 3),
                createTerrainAppearance("RED_CURTAIN", 6, 3),
                createTerrainAppearance("GODNESS_STATUE", 7, 3),
                createTerrainAppearance("RED_CARPET", 4, 3),

                /* Moat */
                createTerrainAppearance("MOSS_COLUMN", 7, 4),
                createTerrainAppearance("MOSS_FLOOR", 2, 4),
                createTerrainAppearance("RUSTY_PLATFORM", 3, 10),
                createTerrainAppearance("MOSS_WATERWAY_ETH", 8, 4, 0, -10),
                createTerrainAppearance("MOSS_WATERWAY", 8, 4, 0, -10),
                /*
                 * createAppearance("MOAT_DOWN", imgConfig.getTerrainImage(), 128,
                 * 164,WIDTH_NORMAL,WIDTH_NORMAL, 0,0), createAppearance("MOAT_UP",
                 * imgConfig.getTerrainImage(), 160, 154,WIDTH_NORMAL,42, 0,10),
                 */
                createAppearance("MOAT_DOWN", configuration.IMAGE_TERRAIN, 5, 4),
                createAppearance("MOAT_UP", configuration.IMAGE_TERRAIN, 6, 4),
                createTerrainAppearance("MOSS_MIDFLOOR", 4, 4),
                createTerrainAppearance("MOSS_STAIRS", 3, 4),
                // createTAppearance("MOAT_UP", 6, 4),

                /* Sewers */
                createTerrainAppearance("SEWERS_FLOOR", 8, 4),
                createTerrainAppearance("SEWERS_WALL", 1, 4),
                createTerrainAppearance("SEWERS_DOWN", 9, 10),
                createTerrainAppearance("SEWERS_UP", 10, 10),
                createTerrainAppearance("WEIRD_MACHINE", 8, 10),

                /* Alchemy Lab */
                createTerrainAppearance("RED_FLOOR", 3, 5),
                createTerrainAppearance("RED_WALL", 1, 5),
                createTerrainAppearance("SMALL_WINDOW", 2, 5),

                /* Chapel */
                createTerrainAppearance("CHURCH_WALL", 11, 4),
                createTerrainAppearance("CHURCH_FLOOR", 13, 4),
                createTerrainAppearance("CHURCH_WOODEN_BARRIER_H", 19, 4),
                createTerrainAppearance("CHURCH_WOODEN_BARRIER_V", 20, 4),
                createTerrainAppearance("CHURCH_CHAIR", 18, 4),
                createTerrainAppearance("CHURCH_CONFESSIONARY", 11, 5),
                createTerrainAppearance("CHURCH_CARPET", 14, 4),
                createTerrainAppearance("ATRIUM", 14, 5),
                createTerrainAppearance("CHURCH_STAINED_WINDOW", 12, 4),
                createTerrainAppearance("CHURCH_FLOOR_H", 13, 4),
                createTerrainAppearance("QUARTERS_WALL", 11, 4), /* Pending */
                createTerrainAppearance("QUARTERS_FLOOR", 13, 4), /* Pending */

                /* left some unused */

                /* Ruins */
                /**/createTerrainAppearance("STONE_WALL", 4, 1),
                /**/createTerrainAppearance("STONE_FLOOR", 4, 1),

                /* Ruins */
                createTerrainAppearance("RUINS_COLUMN", 4, 6),
                createTerrainAppearance("RUINS_FLOOR", 2, 6),
                createTerrainAppearance("RUINS_WALL", 1, 6),
                createTerrainAppearance("RUINS_FLOOR_H", 5, 6),
                createTerrainAppearance("RUINS_STAIRS", 6, 6),
                createTerrainAppearance("RUINS_DOOR", 3, 11),

                /* Caves */
                createTerrainAppearance("CAVE_WALL", 1, 7),
                createTerrainAppearance("CAVE_FLOOR", 2, 7),
                createTerrainAppearance("CAVE_WATER", 5, 7), /* Missing */
                createTerrainAppearance("LAVA", 4, 7),

                /* Warehouse */
                createTerrainAppearance("WAREHOUSE_WALL", 1, 12),
                createTerrainAppearance("WAREHOUSE_FLOOR", 2, 12),

                /* Courtyard */ /* Missing */
                createTerrainAppearance("COURTYARD_WALL", 1, 6),
                createTerrainAppearance("COURTYARD_FLOOR", 2, 6),
                createTerrainAppearance("COURTYARD_COLUMN", 4, 6),
                createTerrainAppearance("COURTYARD_FENCE", 6, 2),
                createTerrainAppearance("COURTYARD_GRASS", 18, 1),
                createTerrainAppearance("COURTYARD_FLOWERS", 13, 2),
                createTerrainAppearance("COURTYARD_FOUNTAIN_CENTER", 9, 2),
                createTerrainAppearance("COURTYARD_FOUNTAIN_AROUND", 7, 2),
                createTerrainAppearance("COURTYARD_FOUNTAIN_POOL", 8, 2),
                createTerrainAppearance("COURTYARD_TREE", 12, 2),
                createTerrainAppearance("COURTYARD_RUINED_WALL", 1, 6),
                createTerrainAppearance("COURTYARD_STAIRS", 6, 6),

                createTerrainAppearance("DINING_CHAIR", 15, 5), /* Missing */
                createTerrainAppearance("DINING_TABLE", 8, 10), /* Missing */
                createTerrainAppearance("MARBLE_STAIRSUP", 6, 4),
                createTerrainAppearance("MARBLE_STAIRSDOWN", 5, 4),

                /* Dungeon */
                createTerrainAppearance("DUNGEON_FLOOR", 2, 10),
                createTerrainAppearance("DUNGEON_DOOR", 4, 11),
                createTerrainAppearance("DUNGEON_WALL", 1, 10),
                createTerrainAppearance("DUNGEON_PASSAGE", 3, 10),
                createTerrainAppearance("DUNGEON_DOWN", 9, 10),
                createTerrainAppearance("DUNGEON_UP", 10, 10),

                /* Frank Lab, goes with Dungeon Theme */
                createTerrainAppearance("WIRES", 7, 10),
                createTerrainAppearance("FRANK_TABLE", 8, 10),

                /* Clock Tower */ /* Missing */
                createTerrainAppearance("TOWER_FLOOR", 13, 4),
                createTerrainAppearance("TOWER_WALL", 11, 4),
                createTerrainAppearance("TOWER_COLUMN", 4, 6),
                createTerrainAppearance("TOWER_WINDOW", 11, 4),
                createTerrainAppearance("TOWER_DOWN", 9, 10),
                createTerrainAppearance("TOWER_UP", 10, 10),
                createTerrainAppearance("CAMPANARIUM", 13, 4),
                createTerrainAppearance("TOWER_FLOOR_H", 5, 10),
                createTerrainAppearance("TOWER_STAIRS", 4, 10),
                createTerrainAppearance("CLOCK_GEAR_1", 4, 6),
                createTerrainAppearance("CLOCK_GEAR_2", 4, 6),
                createTerrainAppearance("CLOCK_GEAR_3", 4, 6),
                createTerrainAppearance("CLOCK_GEAR_4", 4, 6),

                /* Castle Keep */
                createTerrainAppearance("BARRED_WINDOW", 2, 8),
                createTerrainAppearance("DRACULA_THRONE1", 6, 8),
                createTerrainAppearance("DRACULA_THRONE2", 5, 8),
                createTerrainAppearance("DRACULA_THRONE2_X", 4, 8),
                createTerrainAppearance("BALCONY", 3, 8),
                createTerrainAppearance("STONE_STAIRWAY", 8, 8),
                createTerrainAppearance("KEEP_FLOOR", 7, 8),
                createTerrainAppearance("KEEP_WALL", 1, 8),
                createTerrainAppearance("KEEP_CARPET", 16, 4),

                /* Void */
                createTerrainAppearance("VOID_STAR", 1, 9),
                createTerrainAppearance("VOID_SUN", 2, 9),

                createAppearance("TELEPORT", configuration.IMAGE_TERRAIN, 193, 99,
                        WIDTH_NORMAL, CELL_HEIGHT, 0, WIDTH_HALF),

                // Sacred weapons
                createHalfAppearance("ART_CARD_SOL", configuration.IMAGE_ITEMS, 1, 1),
                createHalfAppearance("ART_CARD_MOONS", configuration.IMAGE_ITEMS, 2, 1),
                createHalfAppearance("ART_CARD_DEATH", configuration.IMAGE_ITEMS, 3, 1),
                createHalfAppearance("ART_CARD_LOVE", configuration.IMAGE_ITEMS, 4, 1),
                createHalfAppearance("RED_CARD", configuration.IMAGE_ITEMS, 5, 1),
                createHalfAppearance("GOLDEN_MEDALLION", configuration.IMAGE_ITEMS, 6, 1),
                createHalfAppearance("SILVER_MEDALLION", configuration.IMAGE_ITEMS, 7, 1),
                createHalfAppearance("THORN_BRACELET", configuration.IMAGE_ITEMS, 8, 1),
                createHalfAppearance("LIFE_POTION", configuration.IMAGE_ITEMS, 9, 1),
                createHalfAppearance("FLAME_BOOK", configuration.IMAGE_ITEMS, 1, 2),
                createHalfAppearance("ICE_BOOK", configuration.IMAGE_ITEMS, 2, 2),
                createHalfAppearance("LIT_BOOK", configuration.IMAGE_ITEMS, 3, 2),
                createHalfAppearance("HEART_CONTAINER", configuration.IMAGE_ITEMS, 4, 2),
                createHalfAppearance("MIRACLE_POTION", configuration.IMAGE_ITEMS, 5, 2),
                createHalfAppearance("TEPES_RING", configuration.IMAGE_ITEMS, 6, 2),
                createHalfAppearance("CLUE_PAGE2", configuration.IMAGE_ITEMS, 8, 2),
                createHalfAppearance("CLUE_PAGE3", configuration.IMAGE_ITEMS, 9, 2),
                createHalfAppearance("JUKEBOX", configuration.IMAGE_ITEMS, 9, 2),
                createHalfAppearance("CLUE_PAGE1", configuration.IMAGE_ITEMS, 7, 2),
                createHalfAppearance("JUMPING_WING", configuration.IMAGE_ITEMS, 1, 3),
                createHalfAppearance("FIRE_GEM", configuration.IMAGE_ITEMS, 2, 3),
                createHalfAppearance("FLAME_ITEM", configuration.IMAGE_ITEMS, 3, 3),
                createHalfAppearance("MAGIC_SHIELD", configuration.IMAGE_ITEMS, 8, 4),
                createHalfAppearance("LIGHT_CRYSTAL", configuration.IMAGE_ITEMS, 4, 3),
                createHalfAppearance("LANTERN", configuration.IMAGE_ITEMS, 5, 3),
                createHalfAppearance("SOUL_RECALL", configuration.IMAGE_ITEMS, 6, 3),
                createHalfAppearance("SUN_CARD", configuration.IMAGE_ITEMS, 7, 3),
                createHalfAppearance("MOON_CARD", configuration.IMAGE_ITEMS, 8, 3),
                createHalfAppearance("HEAL_POTION", configuration.IMAGE_ITEMS, 9, 3),
                createHalfAppearance("HEAL_HERB", configuration.IMAGE_ITEMS, 1, 4),
                createHalfAppearance("OXY_HERB", configuration.IMAGE_ITEMS, 1, 4),
                createHalfAppearance("BIBUTI", configuration.IMAGE_ITEMS, 2, 4),
                createHalfAppearance("GARLIC", configuration.IMAGE_ITEMS, 4, 4),
                createHalfAppearance("TORCH", configuration.IMAGE_ITEMS, 5, 4),
                createHalfAppearance("SILK_BAG", configuration.IMAGE_ITEMS, 6, 4),
                createHalfAppearance("LAUREL", configuration.IMAGE_ITEMS, 7, 4),
                createHalfAppearance("VARMOR", configuration.IMAGE_ITEMS, 1, 8),
                createHalfAppearance("VEST", configuration.IMAGE_ITEMS, 2, 8),
                createHalfAppearance("STUDDED_LEATHER", configuration.IMAGE_ITEMS, 3, 8),
                createHalfAppearance("LEATHER_ARMOR", configuration.IMAGE_ITEMS, 4, 8),
                createHalfAppearance("CLOTH_TUNIC", configuration.IMAGE_ITEMS, 5, 8),
                createHalfAppearance("FINE_GARMENT", configuration.IMAGE_ITEMS, 5, 8),
                createHalfAppearance("CUIRASS", configuration.IMAGE_ITEMS, 6, 8),
                createHalfAppearance("SUIT", configuration.IMAGE_ITEMS, 7, 8),
                createHalfAppearance("PLATE", configuration.IMAGE_ITEMS, 8, 8),
                createHalfAppearance("DIAMOND_PLATE", configuration.IMAGE_ITEMS, 9, 8),
                createHalfAppearance("BOW", configuration.IMAGE_ITEMS, 1, 11),
                createHalfAppearance("HOLBEIN_DAGGER", configuration.IMAGE_ITEMS, 2, 11),
                createHalfAppearance("WEREBANE", configuration.IMAGE_ITEMS, 3, 11),
                createHalfAppearance("SHOTEL", configuration.IMAGE_ITEMS, 4, 11),
                createHalfAppearance("COMBAT_KNIFE", configuration.IMAGE_ITEMS, 5, 11),
                createHalfAppearance("STAKE", configuration.IMAGE_ITEMS, 6, 11),
                createHalfAppearance("BASELARD", configuration.IMAGE_ITEMS, 7, 11),
                createHalfAppearance("KAISER_KNUCKLE", configuration.IMAGE_ITEMS, 1, 12),
                createHalfAppearance("MARTIAL_ARMBAND", configuration.IMAGE_ITEMS, 2, 12),
                createHalfAppearance("TULKAS_FIST", configuration.IMAGE_ITEMS, 3, 12),
                createHalfAppearance("SPIKY_KNUCKLES", configuration.IMAGE_ITEMS, 4, 12),
                createHalfAppearance("COMBAT_GAUNTLET", configuration.IMAGE_ITEMS, 5, 12),
                createHalfAppearance("KNUCKLES", configuration.IMAGE_ITEMS, 6, 12),
                createHalfAppearance("GAUNTLET", configuration.IMAGE_ITEMS, 7, 12),
                createHalfAppearance("HAMMER_JUSTICE", configuration.IMAGE_ITEMS, 1, 13),
                createHalfAppearance("MORNING_STAR", configuration.IMAGE_ITEMS, 2, 13),
                createHalfAppearance("FLAIL", configuration.IMAGE_ITEMS, 3, 13),
                createHalfAppearance("MACE", configuration.IMAGE_ITEMS, 4, 13),
                createHalfAppearance("SILVER_HANDGUN", configuration.IMAGE_ITEMS, 1, 14),
                createHalfAppearance("REVOLVER", configuration.IMAGE_ITEMS, 2, 14),
                createHalfAppearance("HANDGUN", configuration.IMAGE_ITEMS, 3, 14),
                createHalfAppearance("AGUEN", configuration.IMAGE_ITEMS, 4, 14),
                createHalfAppearance("CROSSBOW", configuration.IMAGE_ITEMS, 5, 14),
                createHalfAppearance("ROD", configuration.IMAGE_ITEMS, 1, 15),
                createHalfAppearance("STAFF", configuration.IMAGE_ITEMS, 2, 15),
                createHalfAppearance("BLADE_RINGSET", configuration.IMAGE_ITEMS, 1, 16),
                createHalfAppearance("COMBAT_RINGS", configuration.IMAGE_ITEMS, 2, 16),
                createHalfAppearance("SPIKED_RINGS", configuration.IMAGE_ITEMS, 3, 16),
                createHalfAppearance("RINGS", configuration.IMAGE_ITEMS, 4, 16),
                createHalfAppearance("TOWER_SHIELD", configuration.IMAGE_ITEMS, 1, 17),
                createHalfAppearance("BUCKLER", configuration.IMAGE_ITEMS, 2, 17),
                createHalfAppearance("WOODEN_SHIELD", configuration.IMAGE_ITEMS, 3, 17),
                createHalfAppearance("ROUND_SHIELD", configuration.IMAGE_ITEMS, 4, 17),
                createHalfAppearance("SHIELD", configuration.IMAGE_ITEMS, 5, 17),
                createHalfAppearance("DUALBLADE_SPEAR", configuration.IMAGE_ITEMS, 1, 18),
                createHalfAppearance("HALBERD", configuration.IMAGE_ITEMS, 2, 18),
                createHalfAppearance("ALCARDE_SPEAR", configuration.IMAGE_ITEMS, 3, 18),
                createHalfAppearance("BATTLE_SPEAR", configuration.IMAGE_ITEMS, 4, 18),
                createHalfAppearance("LONG_SPEAR", configuration.IMAGE_ITEMS, 5, 18),
                createHalfAppearance("SHORT_SPEAR", configuration.IMAGE_ITEMS, 6, 18),
                createHalfAppearance("MASAMUNE", configuration.IMAGE_ITEMS, 1, 19),
                createHalfAppearance("CRISSAEGRIM", configuration.IMAGE_ITEMS, 2, 19),
                createHalfAppearance("TERMINUS", configuration.IMAGE_ITEMS, 3, 19),
                createHalfAppearance("MOURNEBLADE", configuration.IMAGE_ITEMS, 4, 19),
                createHalfAppearance("OSAFUNE", configuration.IMAGE_ITEMS, 5, 19),
                createHalfAppearance("MORMEGIL", configuration.IMAGE_ITEMS, 6, 19),
                createHalfAppearance("GRAM", configuration.IMAGE_ITEMS, 7, 19),
                createHalfAppearance("RAPIER", configuration.IMAGE_ITEMS, 8, 19),
                createHalfAppearance("BASTARDSWORD", configuration.IMAGE_ITEMS, 9, 19),
                createHalfAppearance("BROADSWORD", configuration.IMAGE_ITEMS, 1, 22),
                createHalfAppearance("VORPAL_BLADE", configuration.IMAGE_ITEMS, 1, 20),
                createHalfAppearance("FIREBRAND", configuration.IMAGE_ITEMS, 2, 20),
                createHalfAppearance("ICEBRAND", configuration.IMAGE_ITEMS, 3, 20),
                createHalfAppearance("GURTHANG", configuration.IMAGE_ITEMS, 4, 20),
                createHalfAppearance("KATANA", configuration.IMAGE_ITEMS, 5, 20),
                createHalfAppearance("FALCHION", configuration.IMAGE_ITEMS, 6, 20),
                createHalfAppearance("HARPER", configuration.IMAGE_ITEMS, 7, 20),
                createHalfAppearance("HADOR", configuration.IMAGE_ITEMS, 8, 20),
                createHalfAppearance("GLADIUS", configuration.IMAGE_ITEMS, 9, 20),
                createHalfAppearance("CUTLASS", configuration.IMAGE_ITEMS, 1, 21),
                createHalfAppearance("CLAYMORE", configuration.IMAGE_ITEMS, 2, 21),
                createHalfAppearance("ETHANOS_BLADE", configuration.IMAGE_ITEMS, 3, 21),
                createHalfAppearance("FLAMBERGE", configuration.IMAGE_ITEMS, 4, 21),
                createHalfAppearance("SABRE", configuration.IMAGE_ITEMS, 5, 21),
                createHalfAppearance("MABLUNG", configuration.IMAGE_ITEMS, 6, 21),
                createHalfAppearance("SCIMITAR", configuration.IMAGE_ITEMS, 7, 21),
                createHalfAppearance("ESTOC", configuration.IMAGE_ITEMS, 8, 21),
                createHalfAppearance("SHORT_SWORD", configuration.IMAGE_ITEMS, 9, 21),
                createHalfAppearance("BWAKA_KNIFE", configuration.IMAGE_ITEMS, 1, 24),
                createHalfAppearance("CHAKRAM", configuration.IMAGE_ITEMS, 2, 24),
                createHalfAppearance("BUFFALO_STAR", configuration.IMAGE_ITEMS, 3, 24),
                createHalfAppearance("SHURIKEN", configuration.IMAGE_ITEMS, 4, 24),
                createHalfAppearance("THROWING_KNIFE", configuration.IMAGE_ITEMS, 5, 24),
                createHalfAppearance("LIT_WHIP", configuration.IMAGE_ITEMS, 1, 25),
                createHalfAppearance("FLAME_WHIP", configuration.IMAGE_ITEMS, 2, 25),
                createHalfAppearance("VKILLERW", configuration.IMAGE_ITEMS, 3, 25),
                createHalfAppearance("WHIP", configuration.IMAGE_ITEMS, 4, 25),
                createHalfAppearance("CHAIN_WHIP", configuration.IMAGE_ITEMS, 5, 25),
                createHalfAppearance("THORN_WHIP", configuration.IMAGE_ITEMS, 6, 25),
                createHalfAppearance("LEATHER_WHIP", configuration.IMAGE_ITEMS, 7, 25),

                // Monsters
                createAppearance("R_SKELETON", configuration.IMAGE_MONSTERS, 1, 1),
                createAppearance("GZOMBIE", configuration.IMAGE_MONSTERS, 4, 2),
                createAppearance("ZOMBIE", configuration.IMAGE_MONSTERS, 7, 8),
                createAppearance("WHITE_SKELETON", configuration.IMAGE_MONSTERS, 1, 1),
                createAppearance("PANTHER", configuration.IMAGE_MONSTERS, 5, 3),
                createBigAppearance("WARG", configuration.IMAGE_BIG_MONSTERS, 7, 1),
                createAppearance("BLACK_KNIGHT", configuration.IMAGE_MONSTERS, 9, 4),
                createAppearance("APE_SKELETON", configuration.IMAGE_MONSTERS, 7, 3),
                createBigAppearance("PARANTHROPUS", configuration.IMAGE_BIG_MONSTERS, 1, 1),
                createAppearance("BAT", configuration.IMAGE_MONSTERS, 8, 3),
                createAppearance("SKULL_HEAD", configuration.IMAGE_MONSTERS, 2, 6),
                createAppearance("SKULL_LORD", configuration.IMAGE_MONSTERS, 3, 6),
                createAppearance("MERMAN", configuration.IMAGE_MONSTERS, 9, 5),
                createAppearance("WEREBEAR", configuration.IMAGE_MONSTERS, 6, 6),
                createAppearance("HUNCHBACK", configuration.IMAGE_MONSTERS, 6, 7),
                createAppearance("BONE_ARCHER", configuration.IMAGE_MONSTERS, 2, 1),
                createAppearance("SKELETON_PANTHER", configuration.IMAGE_MONSTERS, 6, 3),
                createAppearance("BONE_PILLAR", configuration.IMAGE_MONSTERS, 9, 6),
                createAppearance("AXE_KNIGHT", configuration.IMAGE_MONSTERS, 1, 5),
                createAppearance("MEDUSA_HEAD", configuration.IMAGE_MONSTERS, 4, 6),
                createAppearance("DURGA", configuration.IMAGE_MONSTERS, 1, 4),
                createAppearance("SKELETON_ATHLETE", configuration.IMAGE_MONSTERS, 3, 1),
                createAppearance("BLADE_SOLDIER", configuration.IMAGE_MONSTERS, 1, 2),
                createAppearance("BONE_HALBERD", configuration.IMAGE_MONSTERS, 4, 1),
                createAppearance("CROW", configuration.IMAGE_MONSTERS, 4, 8),
                createAppearance("BLOOD_SKELETON", configuration.IMAGE_MONSTERS, 9, 1),
                createAppearance("LIZARD_SWORDSMAN", configuration.IMAGE_MONSTERS, 7, 5),
                createBigAppearance("COCKATRICE", configuration.IMAGE_BIG_MONSTERS, 4, 1),
                createAppearance("COOPER_ARMOR", configuration.IMAGE_MONSTERS, 10, 4),
                createAppearance("GHOUL", configuration.IMAGE_MONSTERS, 8, 8),
                createAppearance("SALOME", configuration.IMAGE_MONSTERS, 7, 4),
                createAppearance("ECTOPLASM", configuration.IMAGE_MONSTERS, 3, 3),
                createBigAppearance("RULER_SWORD_LV1", configuration.IMAGE_BIG_MONSTERS, 2,
                        2),
                createBigAppearance("BEAST_DEMON", configuration.IMAGE_BIG_MONSTERS, 2, 1),
                createBigAppearance("DEVIL", configuration.IMAGE_BIG_MONSTERS, 3, 1),
                createAppearance("BALLOON_POD", configuration.IMAGE_MONSTERS, 5, 7),
                createAppearance("LILITH", configuration.IMAGE_MONSTERS, 5, 4),
                createAppearance("BONE_MUSKET", configuration.IMAGE_MONSTERS, 5, 1),
                createAppearance("KILLER_PLANT", configuration.IMAGE_MONSTERS, 3, 7),
                createAppearance("VAMPIRE_BAT", configuration.IMAGE_MONSTERS, 9, 3),
                createBigAppearance("DEATH_MANTIS", configuration.IMAGE_BIG_MONSTERS, 5, 2),
                createAppearance("DHURON", configuration.IMAGE_MONSTERS, 7, 2),
                createAppearance("DRAGON_SKULL_CANNON", configuration.IMAGE_MONSTERS, 10,
                        6),
                createAppearance("MUMMY_MAN", configuration.IMAGE_MONSTERS, 5, 2),
                createAppearance("ZELDO", configuration.IMAGE_MONSTERS, 8, 2),
                createAppearance("MUD_MAN", configuration.IMAGE_MONSTERS, 2, 3),
                createAppearance("CAGNAZOO", configuration.IMAGE_MONSTERS, 4, 5),
                createBigAppearance("ALRAUNE", configuration.IMAGE_BIG_MONSTERS, 4, 4),
                createBigAppearance("GOLEM", configuration.IMAGE_BIG_MONSTERS, 2, 3),
                createAppearance("ARACHNE", configuration.IMAGE_MONSTERS, 3, 4),
                createAppearance("SPEAR_SKELETON", configuration.IMAGE_MONSTERS, 8, 1),

                createAppearance("KNIFE_MERMAN", configuration.IMAGE_MONSTERS, 10, 5),
                createAppearance("MASTER_LIZARD", configuration.IMAGE_MONSTERS, 8, 5),
                createAppearance("WHIP_SKELETON", configuration.IMAGE_MONSTERS, 6, 1),
                createAppearance("FROZEN_SHADE", configuration.IMAGE_MONSTERS, 10, 2),
                createAppearance("MINOTAUR", configuration.IMAGE_MONSTERS, 7, 6),
                createBigAppearance("TRITON", configuration.IMAGE_BIG_MONSTERS, 6, 2),
                createAppearance("NOVA_SKELETON", configuration.IMAGE_MONSTERS, 10, 1),
                createBigAppearance("ARMOR_LORD", configuration.IMAGE_BIG_MONSTERS, 1, 3),
                createAppearance("FLEA_ARMOR", configuration.IMAGE_MONSTERS, 7, 7),
                createAppearance("BUER", configuration.IMAGE_MONSTERS, 4, 7),
                createAppearance("WIGHT", configuration.IMAGE_MONSTERS, 9, 2),
                createAppearance("SPECTER", configuration.IMAGE_MONSTERS, 4, 3),
                createBigAppearance("RULER_SWORD_LV2", configuration.IMAGE_BIG_MONSTERS, 3,
                        2),
                createAppearance("CURLY", configuration.IMAGE_MONSTERS, 2, 4),
                createBigAppearance("FIRE_WARG", configuration.IMAGE_BIG_MONSTERS, 1, 2),
                createAppearance("BONE_ARK", configuration.IMAGE_MONSTERS, 1, 7),
                createAppearance("MIMIC", configuration.IMAGE_MONSTERS, 5, 6),
                createBigAppearance("MANTICORE", configuration.IMAGE_BIG_MONSTERS, 7, 2),
                createAppearance("FLAME_KNIGHT", configuration.IMAGE_MONSTERS, 2, 5),
                createBigAppearance("ARMOR_GUARDIAN", configuration.IMAGE_BIG_MONSTERS, 1, 4),
                createBigAppearance("DEMON_LORD", configuration.IMAGE_BIG_MONSTERS, 6, 1),
                createAppearance("HEAT_SHADE", configuration.IMAGE_MONSTERS, 1, 3),
                createBigAppearance("FLESH_GOLEM", configuration.IMAGE_BIG_MONSTERS, 4, 3),
                createAppearance("WEREWOLF", configuration.IMAGE_MONSTERS, 8, 6),
                createBigAppearance("ALURA_UNE", configuration.IMAGE_BIG_MONSTERS, 5, 4),
                createAppearance("DRAHIGNAZOO", configuration.IMAGE_MONSTERS, 5, 5),
                createAppearance("SUCCUBUS", configuration.IMAGE_MONSTERS, 6, 4),
                createAppearance("BLADE_MASTER", configuration.IMAGE_MONSTERS, 2, 2),
                createBigAppearance("BASILISK", configuration.IMAGE_BIG_MONSTERS, 5, 1),
                createAppearance("GARGOYLE", configuration.IMAGE_MONSTERS, 6, 5),
                createAppearance("HARPY", configuration.IMAGE_MONSTERS, 4, 4),
                createAppearance("KICKER_SKELETON", configuration.IMAGE_MONSTERS, 7, 1),
                createBigAppearance("BEHEMOTH", configuration.IMAGE_BIG_MONSTERS, 6, 3),
                createBigAppearance("DISCUS_LORD", configuration.IMAGE_BIG_MONSTERS, 7, 3),
                createBigAppearance("GIANT_ARMOR", configuration.IMAGE_BIG_MONSTERS, 2, 4),
                createAppearance("WITCH", configuration.IMAGE_MONSTERS, 8, 4),
                createAppearance("MANDRAGORA", configuration.IMAGE_MONSTERS, 8, 7),
                createBigAppearance("IRON_GOLEM", configuration.IMAGE_BIG_MONSTERS, 2, 3),
                createBigAppearance("VICTORY_ARMOR", configuration.IMAGE_BIG_MONSTERS, 3, 4),
                createBigAppearance("RULER_SWORD_LV3", configuration.IMAGE_BIG_MONSTERS, 4,
                        2),
                createAppearance("SPEAR_KNIGHT", configuration.IMAGE_MONSTERS, 3, 9),
                createAppearance("FLYING_SPEAR_SKELETON", configuration.IMAGE_MONSTERS, 4,
                        9),

                createBigAppearance("GIANTBAT", configuration.IMAGE_BIG_MONSTERS, 2, 5),
                createBigAppearance("DEATH", configuration.IMAGE_BIG_MONSTERS, 3, 5),
                createAppearance("SICKLE", configuration.IMAGE_MONSTERS, 2, 9),
                createBigAppearance("DRACULA", configuration.IMAGE_BIG_MONSTERS, 4, 5),
                createBigAppearance("MEDUSA", configuration.IMAGE_BIG_MONSTERS, 1, 5),
                createAppearance("SNAKE", configuration.IMAGE_MONSTERS, 1, 9),
                createBigAppearance("FRANK", configuration.IMAGE_BIG_MONSTERS, 7, 4),
                createAppearance("IGOR", configuration.IMAGE_MONSTERS, 10, 8),
                createBigAppearance("DEMON_DRACULA", configuration.IMAGE_BIG_MONSTERS, 5, 5),
                createBigAppearance("AKMODAN", configuration.IMAGE_BIG_MONSTERS, 6, 5),
                createBigAppearance("DRAGON_KING", configuration.IMAGE_BIG_MONSTERS, 1, 6),
                createBigAppearance("ORLOX", configuration.IMAGE_BIG_MONSTERS, 4, 6),
                createBigAppearance("WATER_DRAGON", configuration.IMAGE_BIG_MONSTERS, 2, 6),
                createBigAppearance("LEGION", configuration.IMAGE_BIG_MONSTERS, 7, 5),
                createBigAppearance("CERBERUS", configuration.IMAGE_BIG_MONSTERS, 3, 6),
                createAppearance("DOPPELGANGER", configuration.IMAGE_MONSTERS, 6, 5),

                createAppearance("S_CAT", configuration.IMAGE_MONSTERS, 5, 9),
                createAppearance("S_BIRD", configuration.IMAGE_MONSTERS, 6, 9),
                createAppearance("S_TURTLE", configuration.IMAGE_MONSTERS, 7, 9),
                createBigAppearance("S_TIGER", configuration.IMAGE_MONSTERS, 8, 9),
                createAppearance("S_EAGLE", configuration.IMAGE_MONSTERS, 9, 9),
                createAppearance("S_TORTOISE", configuration.IMAGE_MONSTERS, 7, 9),
                createBigAppearance("S_DRAGON", configuration.IMAGE_MONSTERS, 6, 5),

                // Features
                createXAppearance("CANDLE", configuration.IMAGE_FEATURES, 0, 112,
                        WIDTH_HALF, WIDTH_NORMAL),
                createHalfAppearance("SMALLHEART", configuration.IMAGE_FEATURES, 2, 1),
                createHalfAppearance("DAGGER", configuration.IMAGE_FEATURES, 3, 1),
                createHalfAppearance("AXE", configuration.IMAGE_FEATURES, 4, 1),
                createHalfAppearance("VIAL", configuration.IMAGE_FEATURES, 8, 1),
                createHalfAppearance("CROSS", configuration.IMAGE_FEATURES, 5, 1),
                createHalfAppearance("CLOCK", configuration.IMAGE_FEATURES, 6, 1),
                createHalfAppearance("BIGHEART", configuration.IMAGE_FEATURES, 1, 2),
                createHalfAppearance("KEY", configuration.IMAGE_FEATURES, 2, 2),
                createHalfAppearance("UPGRADE", configuration.IMAGE_FEATURES, 3, 2),
                createHalfAppearance("ROSARY", configuration.IMAGE_FEATURES, 5, 2),
                createHalfAppearance("COIN", configuration.IMAGE_FEATURES, 6, 2),
                createHalfAppearance("RED_MONEY_BAG", configuration.IMAGE_FEATURES, 7, 2),
                createHalfAppearance("BLUE_MONEY_BAG", configuration.IMAGE_FEATURES, 8, 2),
                createHalfAppearance("WHITE_MONEY_BAG", configuration.IMAGE_FEATURES, 9, 2),
                createHalfAppearance("CROWN", configuration.IMAGE_FEATURES, 1, 3),
                createHalfAppearance("CHEST", configuration.IMAGE_FEATURES, 2, 3),
                createHalfAppearance("MOAUI_HEAD", configuration.IMAGE_FEATURES, 3, 3),
                createHalfAppearance("RAINBOW_MONEY_BAG", configuration.IMAGE_FEATURES, 10,
                        2),
                createHalfAppearance("POT_ROAST", configuration.IMAGE_FEATURES, 4, 3),
                createHalfAppearance("INVISIBILITY_POTION", configuration.IMAGE_FEATURES, 5,
                        3),
                createHalfAppearance("BIBLE", configuration.IMAGE_FEATURES, 7, 1),
                createHalfAppearance("CRYSTAL", configuration.IMAGE_FEATURES, 9, 1),
                createHalfAppearance("FIST", configuration.IMAGE_FEATURES, 10, 1),
                createHalfAppearance("REBOUND_CRYSTAL", configuration.IMAGE_FEATURES, 9, 1),
                createHalfAppearance("MUPGRADE", configuration.IMAGE_FEATURES, 4, 2),
                createXAppearance("URN_FLAME", configuration.IMAGE_FEATURES, WIDTH_NORMAL,
                        112, WIDTH_HALF, WIDTH_NORMAL, 12),
                createHalfAppearance("BLAST_CRYSTAL", configuration.IMAGE_FEATURES, 9, 1),

                createXAppearance("FLAME", configuration.IMAGE_EFFECTS, 416, 446,
                        WIDTH_NORMAL, WIDTH_NORMAL),
                createAppearance("MOUND", configuration.IMAGE_EFFECTS, 11, 17),

                // Characters
                createAppearance("VKILLER", configuration.IMAGE_CHARACTERS, 1, 1),
                createAppearance("VANQUISHER", configuration.IMAGE_CHARACTERS, 3, 1),
                createAppearance("RENEGADE", configuration.IMAGE_CHARACTERS, 5, 1),
                createAppearance("INVOKER", configuration.IMAGE_CHARACTERS, 1, 2),
                createAppearance("MANBEAST", configuration.IMAGE_CHARACTERS, 3, 2),
                createAppearance("BEAST", configuration.IMAGE_CHARACTERS, 5, 2),
                createAppearance("KNIGHT", configuration.IMAGE_CHARACTERS, 1, 3),
                createAppearance("VKILLER_W", configuration.IMAGE_CHARACTERS, 2, 1),
                createAppearance("SONIA_B", configuration.IMAGE_CHARACTERS, 2, 1),
                createAppearance("VANQUISHER_W", configuration.IMAGE_CHARACTERS, 4, 1),
                createAppearance("RENEGADE_W", configuration.IMAGE_CHARACTERS, 6, 1),
                createAppearance("INVOKER_W", configuration.IMAGE_CHARACTERS, 2, 2),
                createAppearance("MANBEAST_W", configuration.IMAGE_CHARACTERS, 4, 2),
                createAppearance("BEAST_W", configuration.IMAGE_CHARACTERS, 6, 2),
                createAppearance("KNIGHT_W", configuration.IMAGE_CHARACTERS, 2, 3),

                createAppearance("MORPHED_WOLF", configuration.IMAGE_MONSTERS, 1, 10),
                createAppearance("MORPHED_WOLF2", configuration.IMAGE_MONSTERS, 2, 10),
                createAppearance("MORPHED_BAT", configuration.IMAGE_MONSTERS, 3, 10),
                createAppearance("MORPHED_BAT2", configuration.IMAGE_MONSTERS, 4, 10),
                createAppearance("MORPHED_MYST", configuration.IMAGE_MONSTERS, 5, 10),
                createAppearance("MORPHED_MYST2", configuration.IMAGE_MONSTERS, 6, 10),
                createAppearance("MORPHED_WEREBEAR", configuration.IMAGE_MONSTERS, 7, 10),
                createAppearance("MORPHED_WEREDEMON", configuration.IMAGE_MONSTERS, 8, 10),
                createAppearance("MORPHED_WEREWOLF", configuration.IMAGE_MONSTERS, 10, 10),
                createAppearance("MORPHED_WEREBEAST", configuration.IMAGE_MONSTERS, 9, 10),
                createAppearance("MORPHED_LUPINE", configuration.IMAGE_CHARACTERS, 5, 2),

                createAppearance("SOLIEYU_B_KID", configuration.IMAGE_CHARACTERS, 6, 6),
                createAppearance("MAN", configuration.IMAGE_CHARACTERS, 3, 3),
                createAppearance("WOMAN", configuration.IMAGE_CHARACTERS, 4, 3),
                createAppearance("OLDMAN", configuration.IMAGE_CHARACTERS, 5, 3),
                createAppearance("OLDWOMAN", configuration.IMAGE_CHARACTERS, 6, 3),
                createAppearance("MERCHANT", configuration.IMAGE_CHARACTERS, 1, 4),
                createAppearance("PRIEST", configuration.IMAGE_CHARACTERS, 2, 4),
                createAppearance("DOG", configuration.IMAGE_CHARACTERS, 3, 4),
                createAppearance("HOSTAGE_GUY", configuration.IMAGE_CHARACTERS, 4, 4),
                createAppearance("HOSTAGE_GIRL", configuration.IMAGE_CHARACTERS, 5, 4),
                createAppearance("CLARA", configuration.IMAGE_CHARACTERS, 1, 6),
                createAppearance("VINDELITH", configuration.IMAGE_CHARACTERS, 1, 6),
                createAppearance("CLAW", configuration.IMAGE_CHARACTERS, 5, 5),
                createAppearance("MAIDEN", configuration.IMAGE_CHARACTERS, 4, 5),
                createAppearance("MELDUCK", configuration.IMAGE_CHARACTERS, 3, 5),
                createAppearance("ICEY", configuration.IMAGE_CHARACTERS, 4, 6),
                createAppearance("LARDA", configuration.IMAGE_CHARACTERS, 3, 3),
                createAppearance("CHRISTOPHER_BELMONT_NPC", configuration.IMAGE_CHARACTERS,
                        3, 6),
                createAppearance("BARRETT", configuration.IMAGE_CHARACTERS, 5, 6));
    }

}