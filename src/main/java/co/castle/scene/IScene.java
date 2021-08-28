package co.castle.scene;

import co.castle.main.ApplicationGraphics;

public interface IScene {

    ApplicationGraphics renderer = new ApplicationGraphics();

    void draw();

    void update();

    TypeScene process();
}
