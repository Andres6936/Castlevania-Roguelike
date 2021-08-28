package co.castle.scene;

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

    public SceneManager() {
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
        }
    }

    public boolean isRunning() {
        return running;
    }
}
