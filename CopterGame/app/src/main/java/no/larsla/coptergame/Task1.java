package no.larsla.coptergame;

import android.graphics.Canvas;
import sheep.game.Sprite;
import sheep.game.State;
import sheep.graphics.Image;

public class Task1 extends State {
    private Image copterImage = new Image(R.drawable.copter_east);
    private Image backgroundImage = new Image(R.drawable.background);
    private Image verticalWallImage = new Image(R.drawable.wall_vertical);
    private Image horizontalWallImage = new Image(R.drawable.wall_horizontal);

    private Sprite backgroundSprite;
    private Sprite westWallSprite;
    private Sprite eastWallSprite;
    private Sprite northWallSprite;
    private Sprite southWallSprite;
    private Sprite copterSprite;

    private int canvasWidth;
    private int canvasHeight;

    // Initial speed for the copter sprite
    private int speedX = 200;
    private int speedY = 150;

    public Task1() {
        copterSprite = new Sprite(copterImage);
        backgroundSprite = new Sprite(backgroundImage);
        westWallSprite = new Sprite(verticalWallImage);
        eastWallSprite = new Sprite(verticalWallImage);
        northWallSprite = new Sprite(horizontalWallImage);
        southWallSprite = new Sprite(horizontalWallImage);

        // Assigning wall sprite positions
        westWallSprite.setPosition(4, 215);
        eastWallSprite.setPosition(760, 215);
        northWallSprite.setPosition(0, 8);
        southWallSprite.setPosition(0, this.canvasHeight-54);

        // Assigning initial position and speed to copter sprite
        copterSprite.setPosition(200, 120);
        copterSprite.setSpeed(speedX, speedY);

        // Initial update of wall sprites
        eastWallSprite.update(0);
        westWallSprite.update(0);
        northWallSprite.update(0);
        southWallSprite.update(0);
    }

    public void draw(Canvas canvas) {
        canvasWidth = canvas.getWidth();
        canvasHeight = canvas.getHeight();

        backgroundSprite.draw(canvas);
        copterSprite.draw(canvas);
        westWallSprite.draw(canvas);
        eastWallSprite.draw(canvas);
        northWallSprite.draw(canvas);
        southWallSprite.draw(canvas);
    }

    /**
     * Detects collision between copter sprite and wall sprites, changes sprite scale and speed
     * direction according to which wall the copter collides with
     */
    public void update(float dt) {
        if(copterSprite.collides(westWallSprite)) {
            System.out.println("Copter collided with west wall!");
            copterSprite.setScale(1, 1);
            copterSprite.setPosition((copterSprite.getPosition().getX()) - 100, copterSprite.getPosition().getY());
            copterSprite.setSpeed(speedX, copterSprite.getSpeed().getY());
        }
        else if(copterSprite.collides(eastWallSprite)) {
            System.out.println("Copter collided with east wall!");
            copterSprite.setScale(-1, 1);
            copterSprite.setPosition((copterSprite.getPosition().getX()) + 100, copterSprite.getPosition().getY());
            copterSprite.setSpeed(-speedX, copterSprite.getSpeed().getY());
        }
        else if(copterSprite.collides(northWallSprite)) {
            System.out.println("Copter collided with north wall!");
            copterSprite.setSpeed(copterSprite.getSpeed().getX(), -copterSprite.getSpeed().getY());
        }
        else if(copterSprite.collides(southWallSprite)) {
            System.out.println("Copter collided with south wall!");
            copterSprite.setSpeed(copterSprite.getSpeed().getX(), -speedY);
        }
        copterSprite.update(dt);
    }
} // Class
