package no.larsla.pong;

import sheep.game.Sprite;
import sheep.graphics.Image;

public class Paddle extends Sprite {

    public Paddle(Image image) {
        super(image);
        setPosition(400, 1050);
    }

    @Override
    public void update(float dt) {
        super.update(dt);
    }
}
