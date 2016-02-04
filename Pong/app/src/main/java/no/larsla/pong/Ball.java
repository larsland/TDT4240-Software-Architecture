package no.larsla.pong;

import sheep.game.Sprite;
import sheep.graphics.Image;

public class Ball extends Sprite {

    public Ball(Image image) {
        super(image);
        setPosition(300, 570);
        setSpeed(300, -700);
    }

    @Override
    public void update(float dt) {
        super.update(dt);
    }

    public void reset() {
        setPosition(300, 570);
        setSpeed(300, -700);
    }
}
