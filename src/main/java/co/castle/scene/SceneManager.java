package co.castle.scene;

public class SceneManager {
    private boolean running = true;
    private IScene current;

    public void draw() {

    }

    public void update() {

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
