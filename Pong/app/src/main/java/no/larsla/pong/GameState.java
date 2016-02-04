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
        gameWorld.update(dt);
    }

    public boolean onTouchMove(MotionEvent event) {
        gameLayer.getPaddle().setPosition(event.getX(), gameLayer.getPaddle().getY());
        return true;
    }
}
