package no.larsla.coptergame;

import sheep.game.State;
import android.content.res.Resources;
import android.graphics.*;

public class Task3 extends State {
    private CopterAnimated copter, copter2, copter3;
    private int canvasHeight, canvasWidth;


    public Task3(Resources resources, int screenWidth, int screenHeight) {
        // Initiation 3 CopterAnimated classes with different initial positions
        copter = new CopterAnimated(BitmapFactory.decodeResource(resources, R.drawable.copter_sheet), 0, 0, 60, 4);
        copter2 = new CopterAnimated(BitmapFactory.decodeResource(resources, R.drawable.copter_sheet), 200, 300, 60, 4);
        copter3 = new CopterAnimated(BitmapFactory.decodeResource(resources, R.drawable.copter_sheet), 400, 700, 60, 4);

        // Setting each copter's speed
        copter.setSpeed(500, 500);
        copter2.setSpeed(1000, 1000);
        copter3.setSpeed(2000, 2000);

        copter.update(System.currentTimeMillis());
        copter2.update(System.currentTimeMillis());
        copter3.update(System.currentTimeMillis());
    }

    public void draw(Canvas canvas) {
        canvasHeight = canvas.getHeight();
        canvasWidth = canvas.getWidth();

        canvas.drawColor(Color.rgb(254, 0, 254));
        copter.draw(canvas);
        copter2.draw(canvas);
        copter3.draw(canvas);

    }

    // Updates each sprite, and checks if any of them intersects with eachother.
    public void update(float dt) {
        checkWallCollision(copter);
        checkWallCollision(copter2);
        checkWallCollision(copter3);

        if ((copter.hitBox().intersect(copter2.hitBox())) ||
                (copter2.hitBox().intersect(copter.hitBox()))) {
            copter.setSpeed(-copter.getSpeed().getX(), -copter.getSpeed().getY());
            copter2.setSpeed(-copter2.getSpeed().getX(), -copter2.getSpeed().getY());
            copter.flip();
            copter2.flip();
        }
        if ((copter.hitBox().intersect(copter3.hitBox())) ||
                (copter3.hitBox().intersect(copter.hitBox()))) {
            copter.setSpeed(-copter.getSpeed().getX(), -copter.getSpeed().getY());
            copter3.setSpeed(-copter3.getSpeed().getX(), -copter3.getSpeed().getY());
            copter.flip();
            copter3.flip();
        }
        if ((copter3.hitBox().intersect(copter2.hitBox())) ||
                (copter2.hitBox().intersect(copter3.hitBox()))) {
            copter3.setSpeed(-copter3.getSpeed().getX(), -copter3.getSpeed().getY());
            copter2.setSpeed(-copter2.getSpeed().getX(), -copter2.getSpeed().getY());
            copter3.flip();
            copter2.flip();
        }


        copter.update(System.currentTimeMillis());
        copter2.update(System.currentTimeMillis());
    }

    /**
     * Checks if the given sprite class goes outside of the canvas, and reverse their speed if they do.
     * @param sprite
     */
    public void checkWallCollision(CopterAnimated sprite) {
        if ((sprite.getX() > (canvasWidth - sprite.getWidth())) || (sprite.getX() < 0)) {
            sprite.setSpeed(-sprite.getSpeed().getX(), sprite.getSpeed().getY());
            sprite.flip();
        }
        if ((sprite.getY() > (canvasHeight - sprite.getHeight())) || (sprite.getY() < 0)) {
            sprite.setSpeed(sprite.getSpeed().getX(), -sprite.getSpeed().getY());
        }
    }
}
