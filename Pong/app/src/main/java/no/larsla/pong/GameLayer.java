package no.larsla.pong;

import android.graphics.Canvas;
import android.graphics.Typeface;

import sheep.game.Layer;
import sheep.game.Sprite;
import sheep.graphics.Font;
import sheep.graphics.Image;
import sheep.math.BoundingBox;

public class GameLayer extends Layer {

    private Paddle paddle;
    private Paddle paddleComputer;
    private Board board;
    private Ball ball;
    private Image wallImage = new Image(R.drawable.wall);
    private Sprite westWallSprite;
    private Sprite eastWallSprite;
    private Sprite line;
    private int canvasWidth;
    private int canvasHeight;
    private int playerScore;
    private int computerScore;

    public GameLayer() {
        westWallSprite = new Sprite(wallImage);
        eastWallSprite = new Sprite(wallImage);

        paddle = new Paddle(new Image(R.drawable.paddle));
        paddleComputer = new Paddle(new Image(R.drawable.paddle));
        ball = new Ball(new Image(R.drawable.ball));

        line = new Sprite(new Image(R.drawable.line));
        line.setPosition(0, 570);

        playerScore = 0;
        computerScore = 0;

        westWallSprite.setPosition(4, 0);
        eastWallSprite.setPosition(764, 0);

        westWallSprite.update(0);
        eastWallSprite.update(0);
        line.update(0);
    }

    @Override
    public void update(float dt) {
        if ((ball.collides(paddle)) || (ball.collides(paddleComputer))) {
            ball.setSpeed(ball.getSpeed().getX(), -ball.getSpeed().getY() * 1.05f);
        }
        else if ((ball.collides(westWallSprite) || (ball.collides(eastWallSprite)))) {
            ball.setSpeed(-ball.getSpeed().getX() * 1.1f, ball.getSpeed().getY());
        }
        else if (ball.getPosition().getY() < 0) {
            playerScore++;
            ball.reset();
        }
        else if (ball.getPosition().getY() > canvasHeight) {
            computerScore++;
            ball.reset();
        }

        paddleComputer.setPosition(ball.getPosition().getX(), 84);
        paddleComputer.update(dt);
        paddle.update(dt);
        ball.update(dt);
    }

    @Override
    public void draw(Canvas canvas, BoundingBox boundingBox) {
        canvasWidth = canvas.getWidth();
        canvasHeight = canvas.getHeight();

        line.draw(canvas);
        paddle.draw(canvas);
        paddleComputer.draw(canvas);
        ball.draw(canvas);
        westWallSprite.draw(canvas);
        eastWallSprite.draw(canvas);

        Font font = new Font(255, 255, 255, 100, Typeface.SANS_SERIF, Typeface.BOLD);
        canvas.drawText("" + playerScore, 30, 1000, font);
        canvas.drawText("" + computerScore, 30, 100, font);
    }

    public Paddle getPaddle() {
        return this.paddle;
    }
}
