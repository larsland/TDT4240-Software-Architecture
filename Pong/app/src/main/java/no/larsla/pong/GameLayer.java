package no.larsla.pong;

import android.graphics.Canvas;
import android.graphics.Typeface;
import android.os.SystemClock;
import sheep.game.Layer;
import sheep.graphics.Font;
import sheep.graphics.Image;
import sheep.gui.TextButton;
import sheep.gui.WidgetAction;
import sheep.gui.WidgetListener;
import sheep.math.BoundingBox;

public class GameLayer extends Layer implements WidgetListener{
    private Paddle paddle;
    private Paddle paddleComputer;
    private Board board;
    private Ball ball;
    private int playerScore;
    private int computerScore;
    private String message;

    public GameLayer() {
        board = new Board();
        paddle = new Paddle(new Image(R.drawable.paddle), true);
        paddleComputer = new Paddle(new Image(R.drawable.paddle), false);
        ball = Ball.getInstance();

        playerScore = 0;
        computerScore = 0;
        message = "";
    }

    // Main event loop; checks if ball collides with wall, a paddle, or if a paddle scores a point
    @Override
    public void update(float dt) {
        if ((ball.getPosition().getX() < 8) || (ball.getPosition().getX() > Main.screenWidth - 8)) {
            ball.setSpeed(-ball.getSpeed().getX() * 1.1f, ball.getSpeed().getY());
        }
        else if ((ball.collides(paddle)) || (ball.collides(paddleComputer))) {
            ball.setSpeed(ball.getSpeed().getX(), -ball.getSpeed().getY() * 1.05f);
        }
        else if (ball.getPosition().getY() < 0) {
            playerScore++;
            ball.reset();
        }
        else if (ball.getPosition().getY() > Main.screenHeight) {
            computerScore++;
            ball.reset();
        }

        // Moves the computer paddle's x-position according to the balls position
        float paddleX = paddleComputer.getPosition().getX();
        float ballX = ball.getPosition().getX();

        float distanceX = ballX - paddleX;
        if (ballX < paddleX) {
            paddleComputer.setXSpeed(-paddleComputer.getPaddleSpeed());
        } else if (ballX > paddleX) {
            paddleComputer.setXSpeed(paddleComputer.getPaddleSpeed());
        }
/*
        if (distanceX != 0) {

        }
        */

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
        canvas.drawText("" + playerScore, 30, (Main.screenHeight - 100), font);
        canvas.drawText("" + message, (Main.screenWidth / 2), (Main.screenHeight / 2), font);
    }

    public void changeDifficulty(ComputerState state) {
        if (state instanceof EasyState) {
            this.paddleComputer.setPaddleSpeed(350);
        }
        else if (state instanceof HardState) {
            this.paddleComputer.setPaddleSpeed(800);
        }
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

    @Override
    public void actionPerformed(WidgetAction widgetAction) {

    }
}
