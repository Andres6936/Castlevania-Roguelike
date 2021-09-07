package co.castle.scene;

import co.castle.conf.KeyBindings;
import co.castle.conf.UserActions;
import co.castle.conf.UserCommands;
import co.castle.game.PlayerGenerator;
import co.castle.ui.Display;
import co.castle.ui.UISelector;
import co.castle.ui.UserInterface;
import co.castle.ui.graphicsUI.GFXPlayerGenerator;
import co.castle.ui.graphicsUI.GFXUISelector;
import co.castle.ui.graphicsUI.GFXUserInterface;
import co.castle.ui.graphicsUI.GraphicsDisplay;

public class SceneManager {

    /**
     * True while the app is running, false when the user want exit.
     */
    private boolean running = true;

    /**
     * Point to the current Scene.
     */
    private IScene current;

    /**
     * Is always the first scene that is renderer.
     */
    private final IScene menuScene;

    private IScene arenaScene;
    private IScene newGameScene;
    private IScene loadGameScene;
    private IScene prologueScene;
    private IScene trainingScene;

    private final UISelector selector;

    public SceneManager() {
        PlayerGenerator.thus = new GFXPlayerGenerator(IScene.renderer);
        Display.thus = new GraphicsDisplay();


        UserInterface userInterface = UserInterface.getUI();
        KeyBindings keyBindings = new KeyBindings();
        Display.setKeyBindings(keyBindings);
        var userActions = new UserActions(keyBindings);
        var userCommands = new UserCommands(keyBindings);
        selector = new GFXUISelector(userActions, userInterface, keyBindings);

        ((GFXUserInterface) userInterface).init(IScene.renderer, userCommands, userActions);

        menuScene = new MenuScene();
        // The first scene is the Menu.
        current = menuScene;
    }

    public void draw() {
        current.draw();
    }

    public void update() {
        current.update();
    }

    public void process() {
        TypeScene nextScene = current.process();
        if (nextScene == TypeScene.NONE) return;

        if (nextScene == TypeScene.QUIT) {
            running = false;
        } else if (nextScene == TypeScene.MENU) {
            current = menuScene;
        } else if (nextScene == TypeScene.ARENA) {
            // Lazy Evaluation
            if (arenaScene == null) arenaScene = new ArenaScene(selector);
            current = arenaScene;
        } else if (nextScene == TypeScene.PROLOGUE) {
            // Lazy Evaluation
            if (prologueScene == null) prologueScene = new PrologueScene(selector);
            current = prologueScene;
        } else if (nextScene == TypeScene.TRAINING) {
            // Lazy Evaluation
            if (trainingScene == null) trainingScene = new TrainingScene(selector);
            current = trainingScene;
        } else if (nextScene == TypeScene.NEW_GAME) {
            // Lazy Evaluation
            if (newGameScene == null) newGameScene = new NewGameScene(selector);
            current = newGameScene;
        } else if (nextScene == TypeScene.LOAD_GAME) {
            // Lazy Evaluation
            if (loadGameScene == null) loadGameScene = new LoadGameScene();
            current = loadGameScene;
        }
    }

    public boolean isRunning() {
        return running;
    }
}
