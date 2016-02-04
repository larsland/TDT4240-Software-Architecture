package no.larsla.coptergame;

import android.graphics.Canvas;
import android.view.MotionEvent;
import sheep.game.Sprite;
import sheep.game.State;
import sheep.graphics.Image;
import sheep.gui.TextButton;
import sheep.input.TouchListener;

public class Task2 extends State implements TouchListener {
    private Image copterImage = new Image(R.drawable.copter_east);
    private Image backgroundImage = new Image(R.drawable.background);
    private TextButton coordinates = new TextButton(20, 20, "Coordinates");

    private Sprite backgroundSprite;
    private Sprite copterSprite;

    public Task2(int screenWidth, int screenHeight) {
        copterSprite = new Sprite(copterImage);
        backgroundSprite = new Sprite(backgroundImage);

        copterSprite.setPosition(40, 120);
    }

    public void draw(Canvas canvas) {
        backgroundSprite.draw(canvas);
        copterSprite.draw(canvas);
        coordinates.draw(canvas);
    }

    public void update(float dt) {
        copterSprite.update(dt);
    }

    public boolean onTouchMove(MotionEvent event) {
        try {
            Thread.sleep(100);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (copterSprite.getPosition().getX() < event.getX()) {
            copterSprite.setScale(1, 1);
        }
        else if (copterSprite.getPosition().getX() > event.getX()) {
            copterSprite.setScale(-1, 1);
        }

        String label = copterSprite.getPosition().toString();
        coordinates.setLabel(label);

        return true;
    }
} // Class



