package no.larsla.pong;

import android.graphics.Canvas;
import android.graphics.Typeface;
import android.os.SystemClock;
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
    private String message;

    public GameLayer(int screenWidth, int screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight - 50;

        board = new Board(this.screenWidth, this.screenHeight);
        paddle = new Paddle(new Image(R.drawable.paddle), this.screenWidth, this.screenHeight, true);
        paddleComputer = new Paddle(new Image(R.drawable.paddle), this.screenWidth, this.screenHeight, false);
        ball = new Ball(new Image(R.drawable.ball), this.screenWidth, this.screenHeight);

        playerScore = 0;
        computerScore = 0;
        message = "";
    }

    // Main event loop; checks if ball collides with wall, a paddle, or if a paddle scores a point
    @Override
    public void update(float dt) {
        if ((ball.getPosition().getX() < 8) || (ball.getPosition().getX() > screenWidth - 8)) {
            ball.setSpeed(-ball.getSpeed().getX() * 1.1f, ball.getSpeed().getY());
        }
        else if ((ball.collides(paddle)) || (ball.collides(paddleComputer))) {
            ball.setSpeed(ball.getSpeed().getX(), -ball.getSpeed().getY() * 1.05f);
        }
        else if (ball.getPosition().getY() < 0) {
            playerScore++;
            ball.reset();
        }
        else if (ball.getPosition().getY() > screenHeight) {
            computerScore++;
            ball.reset();
        }

        // Moves the computer paddle's x-position according to the balls position
        float paddleX = paddleComputer.getPosition().getX();
        float ballX = ball.getPosition().getX();

        float distanceX = ballX - paddleX;

        if (distanceX != 0) {
            if (ballX < paddleX) {
                paddleComputer.setXSpeed(-350);
            } else if (ballX > paddleX) {
                paddleComputer.setXSpeed(350);
            }
        }

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
        canvas.drawText("" + message, (screenWidth / 2), (screenHeight / 2), font);
    }

    public void gameWon() {
        if (playerScore > computerScore) {
            message = "You won!";
            update(0);
        }
        else {
            message = "You lost...";
            update(0);
        }
        resetGame();
    }

    public Paddle getPaddle() {
        return this.paddle;
    }
    public int getPlayerScore() {
        return this.playerScore;
    }
    public int getComputerScore() {
        return this.computerScore;
    }
    public void resetGame() {
        playerScore = 0;
        computerScore = 0;
        ball.reset();
        message = "";
    }
}
