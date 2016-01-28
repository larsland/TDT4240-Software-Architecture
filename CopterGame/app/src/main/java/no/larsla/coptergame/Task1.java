package no.larsla.coptergame;

import android.content.Context;
import android.graphics.Canvas;
import android.view.Display;
import android.view.WindowManager;

import sheep.game.Sprite;
import sheep.game.State;
import sheep.graphics.Image;

/**
 * Created by lars on 28.01.16.
 */
public class Task1 extends State {
    private Image copterImage = new Image(R.drawable.copter_east);
    private Image backgroundImage = new Image(R.drawable.background);
    private Image verticalWallImage = new Image(R.drawable.wall_vertical);

    private Sprite backgroundSprite;
    private Sprite westWallSprite;
    private Sprite eastWallSprite;
    private Sprite copterSprite;

    private static final int speed = 150;

    private int screenWidth;
    private int screenHeight;

    public Task1(int screenWidth, int screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;

        copterSprite = new Sprite(copterImage);
        backgroundSprite = new Sprite(backgroundImage);
        westWallSprite = new Sprite(verticalWallImage);
        eastWallSprite = new Sprite(verticalWallImage);

        westWallSprite.setPosition(4, 215);
        eastWallSprite.setPosition(760, 215);

        copterSprite.setPosition(40, 120);
        copterSprite.setSpeed(speed, 0);

        westWallSprite.update(0);
        eastWallSprite.update(0);

    }

    public void draw(Canvas canvas) {
        backgroundSprite.draw(canvas);
        copterSprite.draw(canvas);
        westWallSprite.draw(canvas);
        eastWallSprite.draw(canvas);
    }

    public void update(float dt) {
        if(copterSprite.collides(westWallSprite)) {
            System.out.println("Copter collided with west wall!");
            copterSprite.setScale(1, 1);
            copterSprite.setSpeed(speed, copterSprite.getSpeed().getY());

        }
        else if(copterSprite.collides(eastWallSprite)) {
            System.out.println("Copter collided with east wall!");
            copterSprite.setScale(-1, 1);
            copterSprite.setSpeed(-speed, copterSprite.getSpeed().getY());

        }
        copterSprite.update(dt);



    }



}
