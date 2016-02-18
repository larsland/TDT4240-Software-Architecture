package no.larsla.pong;

import java.util.Random;
import sheep.game.Sprite;
import sheep.graphics.Image;

public class Ball extends Sprite {

    private int xPos, yPos;
    private static final Ball INSTANCE = new Ball();

    private Ball() {
        super(new Image(R.drawable.ball));
        xPos = Main.screenWidth / 2;
        yPos = Main.screenHeight / 2;
        setPosition(xPos, yPos);
        setSpeed(300, -500);
    }

    public static Ball getInstance() {
        return INSTANCE;
    }

    @Override
    public void update(float dt) {
        super.update(dt);
    }

    // Function to reset position of the ball, and sets the speed of the ball to a random direction
    public void reset() {
        setPosition(xPos, yPos);
        Random randomNum = new Random();
        int ballDirection = randomNum.nextInt(2);
        if (ballDirection == 0) {
            setSpeed(300, -500);
        }
        else if (ballDirection == 1) {
            setSpeed(-300, -500);
        }
    }
}
