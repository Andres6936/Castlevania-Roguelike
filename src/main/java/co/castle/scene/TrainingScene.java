package co.castle.scene;

import co.castle.levelgen.LevelMetaData;
import co.castle.ui.UISelector;
import sz.fov.FOV;

public class TrainingScene extends GenericScene implements IScene {

    public TrainingScene(UISelector selector) {
        super(selector, "SOLIEYU_KID");

        LevelMetaData md = new LevelMetaData();
        md.setLevelID("TRAINING");
        levelMetadata.put("TRAINING", md);

        loadLevel("TRAINING");

        turns = 0;
        timeSwitch = DAY_LENGTH;

        player.setFOV(new FOV());
        player.getLevel().addMessage("Greetings " + player.getName()
                + ", welcome to the game... Press '?' for Help");
        userInterface.refresh();
        checkTimeSwitch();
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
        return null;
    }
}
