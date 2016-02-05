package no.larsla.pong;

import sheep.game.Sprite;
import sheep.graphics.Image;

public class Paddle extends Sprite {

    public Paddle(Image image, int screenWidth, int screenHeight, boolean player) {
        super(image);
        if (player) {
            setPosition(screenWidth / 2, screenHeight - 250);
        }
        else {
            setPosition(screenWidth / 2, 150);
        }
    }

    @Override
    public void update(float dt) {
        super.update(dt);
    }
}
