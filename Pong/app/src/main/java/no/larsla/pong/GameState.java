package no.larsla.pong;

import sheep.game.State;
import sheep.game.World;
import sheep.input.TouchListener;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.WindowManager;

public class GameState extends State implements TouchListener {


    private World gameWorld;
    private GameLayer gameLayer;
    private static final GameState INSTANCE = new GameState();

    private GameState() {
        gameWorld = new World();
        gameLayer = new GameLayer();
        gameWorld.addLayer(gameLayer);
    }

    public static GameState getInstance() {
        return INSTANCE;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawColor(Color.BLACK);
        gameWorld.draw(canvas);
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
}
