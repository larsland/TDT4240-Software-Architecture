package no.larsla.coptergame;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import sheep.game.Sprite;
import sheep.game.State;
import sheep.graphics.Image;

public class Task1 extends State {
    private Image verticalWallImage = new Image(R.drawable.wall_vertical);
    private Image horizontalWallImage = new Image(R.drawable.wall_horizontal);

    private Sprite westWallSprite;
    private Sprite eastWallSprite;
    private Sprite northWallSprite;
    private Sprite southWallSprite;
    private Copter copterSprite;

    private int screenWidth;
    private int screenHeight;

    public Task1(Resources res, int screenWidth, int screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight - 50;
        westWallSprite = new Sprite(verticalWallImage);
        eastWallSprite = new Sprite(verticalWallImage);
        northWallSprite = new Sprite(horizontalWallImage);
        southWallSprite = new Sprite(horizontalWallImage);

        copterSprite = new Copter(new Image(R.drawable.copter_east), this.screenWidth, this.screenHeight, -200, 150);

        // Assigning wall sprite positions
        westWallSprite.setPosition(4, 0);
        eastWallSprite.setPosition(this.screenWidth - 4, 0);
        northWallSprite.setPosition(0, 8);
        southWallSprite.setPosition(0, this.screenHeight - 50);

        // Initial update of wall sprites
        eastWallSprite.update(0);
        westWallSprite.update(0);
        northWallSprite.update(0);
        southWallSprite.update(0);
    }

    public void draw(Canvas canvas) {
        canvas.drawColor(Color.rgb(254, 0, 254));
        copterSprite.draw(canvas);
        westWallSprite.draw(canvas);
        eastWallSprite.draw(canvas);
        northWallSprite.draw(canvas);
        southWallSprite.draw(canvas);
    }

    // Detects collision between copter sprite and wall sprites, changes sprite scale and speed
    // direction according to which wall the copter collides with
    public void update(float dt) {
        if(copterSprite.collides(westWallSprite)) {
            copterSprite.setScale(1, 1);
            copterSprite.setSpeed(-copterSprite.getSpeed().getX(), copterSprite.getSpeed().getY());
        }
        else if(copterSprite.collides(eastWallSprite)) {
            copterSprite.setScale(-1, 1);
            copterSprite.setSpeed(-copterSprite.getSpeed().getX(), copterSprite.getSpeed().getY());
        }
        else if ((copterSprite.collides(northWallSprite)) || (copterSprite.collides(southWallSprite))) {
            copterSprite.setSpeed(copterSprite.getSpeed().getX(), -copterSprite.getSpeed().getY());
        }
        copterSprite.update(dt);
    }
} // Class
