package no.larsla.coptergame;

import sheep.game.Sprite;
import sheep.graphics.Image;

public class Copter extends Sprite {
    private int xPos, yPos;

    public Copter(Image image, int screenWidth, int screenHeight, int speedX, int speedY) {
        super(image);
        xPos = screenWidth / 2;
        yPos = screenHeight / 2;
        setPosition(xPos, yPos);
        setSpeed(speedX, speedY);
    }

    @Override
    public void update(float dt) {
        super.update(dt);
    }
}
