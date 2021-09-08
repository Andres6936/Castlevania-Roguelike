package co.castle.scene;

import co.castle.conf.gfx.data.Asset;
import co.castle.game.Game;
import co.castle.main.ApplicationGraphics;
import co.castle.ui.UserInterface;
import co.castle.ui.graphicsUI.GFXUserInterface;
import sz.csi.CharKey;

import java.awt.*;

public class MenuScene implements IScene {

    private int choice = 0;
    private int middlePoint = 0;
    private int pickerXCoordinate = 0;

    public MenuScene() {
        middlePoint = Asset.SCREEN_WIDTH / 2;
        pickerXCoordinate = (Asset.SCREEN_WIDTH / 2) - (ApplicationGraphics.assets.IMAGE_PICKER.getWidth() / 2);

        ((GFXUserInterface) UserInterface.getUI()).messageBox.setVisible(false);
        ((GFXUserInterface) UserInterface.getUI()).persistantMessageBox
                .setVisible(false);

        renderer.setFontToPanel(ApplicationGraphics.assets.FONT_TEXT);
        renderer.drawImage(ApplicationGraphics.assets.IMAGE_TITLE);

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

        renderer.saveBuffer();
    }

    @Override
    public void draw() {
        renderer.restore();

        renderer.drawImage(pickerXCoordinate, (int) ((356 + choice * 20) * Asset.SCREEN_SCALE),
                ApplicationGraphics.assets.IMAGE_PICKER);
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
    }

    @Override
    public void update() {
    }

    @Override
    public TypeScene process() {
        CharKey x = new CharKey(CharKey.NONE);
        x = renderer.inkey();
        switch (x.code) {
            case CharKey.A:
            case CharKey.a:
                return TypeScene.NEW_GAME;
            case CharKey.B:
            case CharKey.b:
                return TypeScene.LOAD_GAME;
            case CharKey.C:
            case CharKey.c:
                return TypeScene.PROLOGUE;
            case CharKey.D:
            case CharKey.d:
                return TypeScene.TRAINING;
            case CharKey.E:
            case CharKey.e:
                return TypeScene.ARENA;
            case CharKey.F:
            case CharKey.f:
                return TypeScene.HIGH_SCORE;
            case CharKey.G:
            case CharKey.g:
                return TypeScene.QUIT;
            case CharKey.UARROW:
                if (choice > 0)
                    choice--;
                return TypeScene.NONE;
            case CharKey.DARROW:
                if (choice < 6)
                    choice++;
                return TypeScene.NONE;
        }

        return TypeScene.NONE;
    }
}
