package co.castle.scene;

import co.castle.ui.UISelector;
import sz.csi.KeyCode;
import sz.fov.FOV;

import javax.swing.*;
import java.awt.*;

public class NewGameScene extends GenericScene implements IScene {

    public NewGameScene(UISelector selector) {
        super(selector, null);

        renderer.drawImage(renderer.assets.IMAGE_PROLOGUE);
        renderer.setFontToPanel(renderer.assets.FONT_TITLE);
        renderer.printAtPixel(156, 136, "prologue", Color.WHITE);
        // si.drawImage(311,64, IMG_GBAT);
        renderer.setFontToPanel(renderer.assets.FONT_TEXT);
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
        renderer.printAtPixel(156, 490, "[Space] to continue", renderer.assets.COLOR_BOLD);
        renderer.refresh();
        renderer.waitKey(KeyCode.SPACE);
        renderer.remove(t1);

        loadLevel("CHARRIOT_W");

        turns = 0;
        timeSwitch = (int) (DAY_LENGTH / 2.0);

        player.setFOV(new FOV());
        player.getLevel().addMessage("Greetings " + player.getName()
                + ", welcome to the game... Press '?' for Help");
        userInterface.refresh();
        checkTimeSwitch();
    }

    private static JTextArea createTempArea(int xpos, int ypos, int w, int h) {
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

    @Override
    public void draw() {

    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public TypeScene process() {
        return super.process();
    }
}
