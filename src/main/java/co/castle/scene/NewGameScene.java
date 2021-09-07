package co.castle.scene;

import co.castle.game.Game;
import co.castle.game.PlayerGenerator;
import co.castle.player.GameSessionInfo;
import co.castle.player.Player;
import co.castle.player.PlayerEventListener;
import co.castle.ui.UISelector;
import co.castle.ui.UserInterface;
import co.castle.ui.graphicsUI.GFXUISelector;

public class NewGameScene implements IScene {

    private Game currentGame;
    private final UISelector selector = new GFXUISelector();
    private final UserInterface userInterface;

    public NewGameScene() {
        userInterface = UserInterface.getUI();
        currentGame = new Game();
        currentGame.setCanSave(false);
        currentGame.setInterfaces(userInterface, selector);
        Player player = PlayerGenerator.thus.generatePlayer();
        player.setGameSessionInfo(new GameSessionInfo());
        player.setSelector(selector);
        player.setPlayerEventListener(new PlayerEventListener() {
            @Override
            public void informEvent(int code) {

            }

            @Override
            public void informEvent(int code, Object param) {

            }
        });
        userInterface.setPlayer(player);
        selector.setPlayer(player);
        userInterface.setGameOver(false);
        userInterface.addCommandListener(pCommand -> {

        });
    }

    @Override
    public void draw() {

    }

    @Override
    public void update() {

    }

    @Override
    public TypeScene process() {
        return null;
    }
}
