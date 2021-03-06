package no.larsla.pong;

import sheep.game.Sprite;
import sheep.graphics.Image;

public class Paddle extends Sprite {

    private int speed = 350;

    public Paddle(Image image, boolean player) {
        super(image);
        if (player) {
            setPosition(Main.screenWidth / 2, Main.screenHeight - 250);
        }
        else {
            setPosition(Main.screenWidth / 2, 150);
        }
    }

    public void setPaddleSpeed(int speed) {
        this.speed = speed;
    }
    public int getPaddleSpeed() {
        return (speed);
    }

    @Override
    public void update(float dt) {
        super.update(dt);
    }
}
