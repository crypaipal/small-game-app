package game;

import java.awt.*;

class Player {
    public static final int PLAYER_WIDTH = 30;
    public static final int PLAYER_HEIGHT = 30;
    private int x;
    private int y;
    private int score;

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
        this.score = 0;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getScore() {
        return score;
    }

    public void incrementScore() {
        score++;
    }

    public void move(int dx, int dy) {
        x += dx;
        y += dy;

        if (x < 0) {
            x = 0;
        } else if (x > SmallGame.GAME_WIDTH - PLAYER_WIDTH) {
            x = SmallGame.GAME_WIDTH - PLAYER_WIDTH;
        }

        if (y < 0) {
            y = 0;
        } else if (y > SmallGame.GAME_HEIGHT - PLAYER_HEIGHT) {
            y = SmallGame.GAME_HEIGHT - PLAYER_HEIGHT;
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, PLAYER_WIDTH, PLAYER_HEIGHT);
    }
}
