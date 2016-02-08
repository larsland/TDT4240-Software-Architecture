package no.larsla.pong;

import sheep.game.State;
import sheep.game.World;
import sheep.input.TouchListener;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;

public class GameState extends State implements TouchListener {

    private World gameWorld;
    private GameLayer gameLayer;

    public GameState(int screenWidth, int screenHeight) {
        gameWorld = new World();
        gameLayer = new GameLayer(screenWidth, screenHeight);
        gameWorld.addLayer(gameLayer);
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
