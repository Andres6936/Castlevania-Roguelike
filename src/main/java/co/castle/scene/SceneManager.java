package co.castle.scene;

import co.castle.conf.KeyBindings;
import co.castle.conf.UserActions;
import co.castle.conf.UserCommands;
import co.castle.game.PlayerGenerator;
import co.castle.main.ApplicationGraphics;
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

    /**
     * Predefined characters is used.
     */
    private IScene arenaScene;

    /**
     * The user choice the characters to use.
     */
    private IScene newGameScene;

    /**
     * Predefined characters is used.
     */
    private IScene loadGameScene;

    /**
     * Predefined characters is used.
     */
    private IScene prologueScene;

    /**
     * Predefined characters is used.
     */
    private IScene trainingScene;

    /**
     * Predefined characters is used.
     */
    private IScene highScoreScene;

    /**
     * Selector interface
     */
    private final UISelector selector;

    /**
     * The backend renderer to use for the application, for default Swing.
     * Note, the use of memory static is avoided, only once instance of
     * this class is allowed.
     */
    private final ApplicationGraphics renderer = new ApplicationGraphics();

    public SceneManager() {
        PlayerGenerator.thus = new GFXPlayerGenerator(renderer);
        Display.thus = new GraphicsDisplay(renderer);


        UserInterface userInterface = UserInterface.getUI();
        KeyBindings keyBindings = new KeyBindings();
        Display.setKeyBindings(keyBindings);
        var userActions = new UserActions(keyBindings);
        var userCommands = new UserCommands(keyBindings);
        selector = new GFXUISelector(userActions, userInterface, keyBindings, renderer);

        ((GFXUserInterface) userInterface).init(renderer, userCommands, userActions);

        menuScene = new MenuScene(renderer);
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
            if (newGameScene == null) newGameScene = new NewGameScene(selector, renderer);
            current = newGameScene;
        } else if (nextScene == TypeScene.LOAD_GAME) {
            // Lazy Evaluation
            if (loadGameScene == null) loadGameScene = new LoadGameScene();
            current = loadGameScene;
        } else if (nextScene == TypeScene.HIGH_SCORE) {
            // Lazy Evaluation
            if (highScoreScene == null) highScoreScene = new HighScoreScene(renderer);
            current = highScoreScene;
        }
    }

    public boolean isRunning() {
        return running;
    }
}
