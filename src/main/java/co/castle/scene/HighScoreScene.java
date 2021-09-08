package co.castle.scene;

import co.castle.game.GameFiles;
import co.castle.player.HiScore;
import sz.csi.KeyCode;

import java.awt.*;

public class HighScoreScene implements IScene {
    public HighScoreScene() {
        HiScore[] scores = GameFiles.loadScores("hiscore.tbl");

        int leftMargin;
        int widthAdjustment;

        if (renderer.assets.SCREEN_WIDTH == 1024) {
            leftMargin = 9;
            widthAdjustment = 112;
        } else {
            leftMargin = 0;
            widthAdjustment = 0;
        }
        renderer.drawImage(renderer.assets.IMAGE_HISCORES);
//        addornedTextArea.setBounds(8, 110, 782, 395);
//        addornedTextArea.paintAt(renderer.getGraphics2D(), 8 + widthAdjustment, 110);
        renderer.setFontToPanel(renderer.assets.FONT_TITLE);
        renderer.printAtPixelCentered(renderer.assets.SCREEN_WIDTH / 2, 160,
                "The most brave of Belmonts", Color.WHITE);
        renderer.setFontToPanel(renderer.assets.FONT_TEXT);
        renderer.print(18 + leftMargin, 8, "SCORE", renderer.assets.COLOR_BOLD);
        renderer.print(25 + leftMargin, 8, "DATE", renderer.assets.COLOR_BOLD);
        renderer.print(36 + leftMargin, 8, "TURNS", renderer.assets.COLOR_BOLD);
        renderer.print(43 + leftMargin, 8, "DEATH", renderer.assets.COLOR_BOLD);

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
        renderer.print(7 + leftMargin, 20, "[space] to continue", renderer.assets.COLOR_BOLD);
        renderer.refresh();
        renderer.waitKey(KeyCode.SPACE);
    }

    @Override
    public void draw() {

    }

    @Override
    public void update() {

    }

    @Override
    public TypeScene process() {
        return TypeScene.NONE;
    }
}
