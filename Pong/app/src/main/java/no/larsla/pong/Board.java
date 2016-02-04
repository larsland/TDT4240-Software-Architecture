package no.larsla.pong;

import sheep.game.Sprite;
import sheep.graphics.Image;

public class Board {
    private Image wall = new Image(R.drawable.wall);
    private Image line = new Image(R.drawable.line);
    private Sprite wallSpriteWest, wallSpriteEast;

    public Board() {
        wallSpriteWest = new Sprite(wall);
        wallSpriteEast = new Sprite(wall);
    }

}
