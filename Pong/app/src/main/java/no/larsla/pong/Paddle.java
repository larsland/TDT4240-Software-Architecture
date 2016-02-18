package no.larsla.pong;

import sheep.game.Sprite;
import sheep.graphics.Image;

public class Paddle extends Sprite {

    public Paddle(Image image, boolean player) {
        super(image);
        if (player) {
            setPosition(Main.screenWidth / 2, Main.screenHeight - 250);
        }
        else {
            setPosition(Main.screenWidth / 2, 150);
        }
    }

    @Override
    public void update(float dt) {
        super.update(dt);
    }
}
