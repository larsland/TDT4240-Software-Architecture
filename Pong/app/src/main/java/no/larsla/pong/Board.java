package no.larsla.pong;

import android.graphics.Canvas;

import sheep.game.Sprite;
import sheep.graphics.Image;

public class Board {
    private Image wall = new Image(R.drawable.wall);
    private Image line = new Image(R.drawable.line);

    private Sprite wallSpriteWest, wallSpriteEast, lineSprite;

    public Board() {
        wallSpriteWest = new Sprite(wall);
        wallSpriteEast = new Sprite(wall);
        lineSprite = new Sprite(line);

        wallSpriteWest.setPosition(4, 0);
        wallSpriteEast.setPosition(764, 0);
        lineSprite.setPosition(0, 570);

        wallSpriteWest.update(0);
        wallSpriteEast.update(0);
        lineSprite.update(0);
    }

    public void draw(Canvas canvas) {
        wallSpriteEast.draw(canvas);
        wallSpriteWest.draw(canvas);
        lineSprite.draw(canvas);
    }

    public Sprite getWallSpriteWest() {
        return wallSpriteWest;
    }
    public Sprite getWallSpriteEast() {
        return wallSpriteEast;
    }
}
