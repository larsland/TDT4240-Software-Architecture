package no.larsla.coptergame;

import sheep.game.Sprite;
import sheep.graphics.Image;

public class Copter extends Sprite {
    private int xPos, yPos;
    private int speedX, speedY;

    public Copter(Image image, int screenWidth, int screenHeight, int speedX, int speedY) {
        super(image);
        xPos = screenWidth / 2;
        yPos = screenWidth / 2;
        setPosition(xPos, yPos);
        this.speedX = speedX;
        this.speedY = speedY;
    }

    @Override
    public void update(float dt) {
        super.update(dt);
    }
}
