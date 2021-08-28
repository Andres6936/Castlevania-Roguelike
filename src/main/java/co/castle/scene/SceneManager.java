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
        } else if (nextScene == TypeScene.MENU) {
            current = menuScene;
        } else if (nextScene == TypeScene.ARENA) {
            // Lazy Evaluation
            if (arenaScene == null) arenaScene = new ArenaScene();
            current = arenaScene;
        } else if (nextScene == TypeScene.PROLOGUE) {
            // Lazy Evaluation
            if (prologueScene == null) prologueScene = new PrologueScene();
            current = prologueScene;
        } else if (nextScene == TypeScene.TRAINING) {
            // Lazy Evaluation
            if (trainingScene == null) trainingScene = new TrainingScene();
            current = trainingScene;
        } else if (nextScene == TypeScene.NEW_GAME) {
            // Lazy Evaluation
            if (newGameScene == null) newGameScene = new NewGameScene();
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
