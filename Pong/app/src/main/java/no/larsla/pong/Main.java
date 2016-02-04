package no.larsla.pong;

import android.app.Activity;
import android.os.Bundle;
import sheep.game.Game;

public class Main extends Activity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Game game = new Game(this, null);
        game.pushState(new GameState(game.getResources()));
        setContentView(game);
    }
}
