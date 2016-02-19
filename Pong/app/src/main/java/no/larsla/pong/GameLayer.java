package no.larsla.pong;

import android.graphics.Canvas;
import android.graphics.Typeface;
import sheep.game.Layer;
import sheep.graphics.Font;
import sheep.graphics.Image;
import sheep.math.BoundingBox;

public class GameLayer extends Layer{
    private Paddle paddle;
    private Paddle paddleComputer;
    private Board board;
    private Ball ball;
    private int playerScore;
    private int computerScore;
    private String message;
    private Difficulty diff;

    public GameLayer() {
        board = new Board();
        paddle = new Paddle(new Image(R.drawable.paddle), true);
        paddleComputer = new Paddle(new Image(R.drawable.paddle), false);
        ball = Ball.getInstance();

        diff = Difficulty.EASY;
        playerScore = 0;
        computerScore = 0;
        message = "";
    }

    // Main event loop; checks if ball collides with wall, a paddle, or if a paddle scores a point
    @Override
    public void update(float dt) {
        if (checkTerminalState()) {
            gameWon();
        }

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

        moveComputer();
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

    // Changes the difficulty according to the current state of ComputerState
    public void changeDifficulty() {
        switch (diff) {
            case HARD: this.paddleComputer.setPaddleSpeed(350);
                this.diff = Difficulty.EASY;
                break;
            case EASY: this.paddleComputer.setPaddleSpeed(800);
                this.diff = Difficulty.HARD;
                break;
        }
    }

    // Checks if player or computer has reached 21 points
    public boolean checkTerminalState() {
        return (playerScore == 21) || (computerScore == 21);
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

    // Moves the computer paddle's x-position according to the balls position
    public void moveComputer() {
        float paddleX = paddleComputer.getPosition().getX();
        float ballX = ball.getPosition().getX();
        float distanceX = ballX - paddleX;

        if (distanceX != 0) {
            if (ballX < paddleX) {
                paddleComputer.setXSpeed(-paddleComputer.getPaddleSpeed());
            } else if (ballX > paddleX) {
                paddleComputer.setXSpeed(paddleComputer.getPaddleSpeed());
            }
        }
    }

    public Paddle getPaddle() {
        return this.paddle;
    }
    public void resetGame() {
        playerScore = 0;
        computerScore = 0;
        ball.reset();
        message = "";
    }
    public String getDiff() {
        return (diff.toString());
    }
}
