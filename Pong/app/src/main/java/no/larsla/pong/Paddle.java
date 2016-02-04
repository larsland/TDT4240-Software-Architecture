package no.larsla.pong;

import sheep.game.Sprite;
import sheep.graphics.Image;

public class Paddle extends Sprite {

    public Paddle(Image image, int screenWidth, int screenHeight) {
        super(image);
        setPosition(screenWidth / 2, screenHeight - 80);
    }

    @Override
    public void update(float dt) {
        super.update(dt);
    }
}
