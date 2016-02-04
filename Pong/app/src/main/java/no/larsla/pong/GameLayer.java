package no.larsla.pong;

import android.graphics.Canvas;
import android.graphics.Typeface;
import sheep.game.Layer;
import sheep.graphics.Font;
import sheep.graphics.Image;
import sheep.math.BoundingBox;

public class GameLayer extends Layer {
    private Paddle paddle;
    private Paddle paddleComputer;
    private Board board;
    private Ball ball;
    private int playerScore;
    private int computerScore;
    private int screenWidth;
    private int screenHeight;

    public GameLayer(int screenWidth, int screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight - 50;

        board = new Board(this.screenWidth, this.screenHeight);
        paddle = new Paddle(new Image(R.drawable.paddle), this.screenWidth, this.screenHeight);
        paddleComputer = new Paddle(new Image(R.drawable.paddle), this.screenWidth, this.screenHeight);
        ball = new Ball(new Image(R.drawable.ball), this.screenWidth, this.screenHeight);

        playerScore = 0;
        computerScore = 0;

    }

    @Override
    public void update(float dt) {
        if ((ball.collides(paddle)) || (ball.collides(paddleComputer))) {
            ball.setSpeed(ball.getSpeed().getX(), -ball.getSpeed().getY() * 1.05f);
        }
        else if ((ball.collides(board.getWallSpriteEast()) || (ball.collides(board.getWallSpriteWest())))) {
            ball.setSpeed(-ball.getSpeed().getX() * 1.1f, ball.getSpeed().getY());
        }
        else if (ball.getPosition().getY() < 0) {
            playerScore++;
            ball.reset();
        }
        else if (ball.getPosition().getY() > screenHeight) {
            computerScore++;
            ball.reset();
        }

        paddleComputer.setPosition(ball.getPosition().getX(), 80);
        paddleComputer.update(dt);
        paddle.update(dt);
        ball.update(dt);
    }

    @Override
    public void draw(Canvas canvas, BoundingBox boundingBox) {
        board.draw(canvas);
        paddle.draw(canvas);
        paddleComputer.draw(canvas);
        ball.draw(canvas);

        Font font = new Font(255, 255, 255, 100, Typeface.SANS_SERIF, Typeface.BOLD);
        canvas.drawText("" + computerScore, 30, 100, font);
        canvas.drawText("" + playerScore, 30, (screenHeight - 100), font);
    }

    public Paddle getPaddle() {
        return this.paddle;
    }
}
