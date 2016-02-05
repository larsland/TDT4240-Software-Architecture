package no.larsla.coptergame;

import android.graphics.Canvas;
import android.graphics.Color;

import sheep.game.Game;
import sheep.game.State;
import sheep.gui.TextButton;
import sheep.gui.WidgetAction;
import sheep.gui.WidgetListener;

/**
 * Created by lars on 05.02.16.
 */
public class TitleScreen extends State implements WidgetListener {

    private TextButton task1, task2, task3;

    public TitleScreen() {
        task1 = new TextButton(100, 200, "1");
        task2 = new TextButton(100, 400, "2");
        task3 = new TextButton(100, 600, "3");
    }


    public void draw(Canvas canvas) {
        canvas.drawColor(Color.BLACK);
    }

    @Override
    public void actionPerformed(WidgetAction widgetAction) {
        if (widgetAction.equals("1")) {
            //Game game = new Game(this, null);
            //game.pushState(new Task1(game.getResources(), 100, 100));

        }
    }
}
