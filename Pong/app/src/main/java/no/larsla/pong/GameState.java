package no.larsla.pong;

import sheep.game.State;
import sheep.game.World;
import sheep.graphics.Font;
import sheep.gui.TextButton;
import sheep.gui.WidgetAction;
import sheep.gui.WidgetListener;
import sheep.input.TouchListener;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.WindowManager;

public class GameState extends State implements TouchListener, WidgetListener {

    private World gameWorld;
    private GameLayer gameLayer;
    private TextButton difficultyBtn;
    private StateContext sc = new StateContext();
    private static final GameState INSTANCE = new GameState();

    private GameState() {
        gameWorld = new World();
        gameLayer = new GameLayer();
        gameWorld.addLayer(gameLayer);

        Font buttonFont = new Font(255, 255, 255, 100, Typeface.SANS_SERIF, Typeface.BOLD);
        Paint[] buttonStyle = {buttonFont, buttonFont};
        difficultyBtn = new TextButton(Main.screenWidth-200, (Main.screenHeight/2) - 100, "+-", buttonStyle);
        difficultyBtn.addWidgetListener(this);

        addTouchListener(difficultyBtn);
    }

    public static GameState getInstance() {
        return INSTANCE;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawColor(Color.BLACK);
        gameWorld.draw(canvas);
        difficultyBtn.draw(canvas);
    }

    @Override
    public void update(float dt) {
        if (checkTerminalState()) {
            gameLayer.gameWon();
        }
        gameWorld.update(dt);
    }

    // Checks if player or computer has reached 21 points
    public boolean checkTerminalState() {
        return (gameLayer.getPlayerScore() == 21) || (gameLayer.getComputerScore() == 21);
    }

    // Moves player's paddle to touch events x-position
    public boolean onTouchMove(MotionEvent event) {
        gameLayer.getPaddle().setPosition(event.getX(), gameLayer.getPaddle().getY());
        return true;
    }

    @Override
    public void actionPerformed(WidgetAction widgetAction) {
        if (widgetAction.getSource() == difficultyBtn) {
            sc.change();
            gameLayer.changeDifficulty(sc.getState());
        }
    }
}
