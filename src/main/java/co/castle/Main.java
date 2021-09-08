package co.castle;

import co.castle.scene.SceneManager;

public final class Main
{
    public static void main( String[] args) {
		var sceneManager = new SceneManager();
		while (sceneManager.isRunning()) {
			sceneManager.update();
			sceneManager.draw();
			sceneManager.process();
		}
	}
}