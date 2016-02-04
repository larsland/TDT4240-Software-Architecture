package no.larsla.pong;

import sheep.game.Sprite;
import sheep.graphics.Image;

public class Ball extends Sprite {

    private int xPos, yPos;

    public Ball(Image image, int screenWidth, int screenHeight) {
        super(image);
        xPos = screenWidth / 2;
        yPos = screenHeight / 2;
        setPosition(xPos, yPos);
        setSpeed(300, -700);
    }

    @Override
    public void update(float dt) {
        super.update(dt);
    }

    public void reset() {
        setPosition(xPos, yPos);
        setSpeed(300, -700);
    }
}
