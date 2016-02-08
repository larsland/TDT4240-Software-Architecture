package no.larsla.coptergame;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.MotionEvent;
import java.text.DecimalFormat;
import sheep.game.Sprite;
import sheep.game.State;
import sheep.graphics.Font;
import sheep.graphics.Image;
import sheep.input.TouchListener;

public class Task2 extends State implements TouchListener {
    private Image copterImage = new Image(R.drawable.copter_east);
    private Sprite copterSprite;
    private DecimalFormat f;

    public Task2(Resources res, int screenWidth, int screenHeight) {
        f = new DecimalFormat("##.00");
        copterSprite = new Sprite(copterImage);
        copterSprite.setPosition(screenWidth / 2, screenHeight / 2);
    }

    public void draw(Canvas canvas) {
        canvas.drawColor(Color.rgb(254, 0, 254));
        copterSprite.draw(canvas);
        Font font = new Font(255, 255, 255, 30, Typeface.SANS_SERIF, Typeface.BOLD);
        canvas.drawText("" + f.format(copterSprite.getPosition().getX()) + "   " + f.format(copterSprite.getPosition().getY()), 30, 30, font);
    }

    public void update(float dt) {
        copterSprite.update(dt);
    }

    public boolean onTouchMove(MotionEvent event) {
        // Flips the sprite to the correct direction, according to where click-event is
        if (copterSprite.getPosition().getX() < event.getX()) {
            copterSprite.setScale(1, 1);
        }
        else if (copterSprite.getPosition().getX() > event.getX()) {
            copterSprite.setScale(-1, 1);
        }

        // Gets x and y position of copter and click-event, calculates difference, and sets speed
        // according to the distance, so the copter will move towards the click-event
        float copterX = copterSprite.getPosition().getX();
        float copterY = copterSprite.getPosition().getY();
        float eventX = event.getX();
        float eventY = event.getY();

        float distanceX = eventX - copterX;
        float distanceY = eventY - copterY;

        if (distanceX != 0) {
            copterSprite.setXSpeed(distanceX / 2);
        }
        if (distanceY != 0) {
            copterSprite.setYSpeed(distanceY / 2);
        }
        return true;
    }
} // Class



