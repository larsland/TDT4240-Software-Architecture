package no.larsla.coptergame;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import sheep.game.Game;
import sheep.game.State;
import sheep.graphics.Font;
import sheep.gui.TextButton;
import sheep.gui.WidgetAction;
import sheep.gui.WidgetListener;

public class TitleScreen extends State implements WidgetListener {

    private TextButton task1, task2, task3;
    private Game game;
    private int screenWidth, screenHeight;

    public TitleScreen(Game game, Resources res, int screenWidth, int screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.game = game;

        Font buttonFont = new Font(255, 255, 255, 100, Typeface.SANS_SERIF, Typeface.BOLD);
        Paint[] buttonStyle = {buttonFont, buttonFont};

        task1 = new TextButton((this.screenWidth/2) - 30, 200, "Task 1", buttonStyle);
        task2 = new TextButton((this.screenWidth/2) - 30, 400, "Task 2", buttonStyle);
        task3 = new TextButton((this.screenWidth/2) - 30, 600, "Task 3", buttonStyle);

        task1.addWidgetListener(this);
        task2.addWidgetListener(this);
        task3.addWidgetListener(this);

        addTouchListener(task1);
        addTouchListener(task2);
        addTouchListener(task3);
    }

    public void draw(Canvas canvas) {
        canvas.drawColor(Color.BLACK);
        task1.draw(canvas);
        task2.draw(canvas);
        task3.draw(canvas);

        Font font = new Font(255, 255, 255, 40, Typeface.SANS_SERIF, Typeface.BOLD);
        canvas.drawText("Welcome to larsland's helicopters", 80, 100, font);
    }

    // Controlls which task (state) to push to game, depending on which textButton is clicked
    @Override
    public void actionPerformed(WidgetAction widgetAction) {
        if (widgetAction.getSource() == task1) {
            game.pushState(new Task1(game.getResources(), screenWidth, screenHeight));
        }
        else if (widgetAction.getSource() == task2) {
            game.pushState(new Task2(game.getResources(), screenWidth, screenHeight));
        }
        else if (widgetAction.getSource() == task3) {
            game.pushState(new Task3(game.getResources(), screenWidth, screenHeight));
        }
    }
}// Class
