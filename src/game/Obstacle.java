package game;

import java.awt.*;

class Obstacle {
    private int x;
    private int y;
    public static final int OBSTACLE_WIDTH = 20;
    private int height;

    public Obstacle(int x, int y, int height) {
        this.x = x;
        this.y = y;
        this.height = height;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return OBSTACLE_WIDTH;
    }

    public int getHeight() {
        return height;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, OBSTACLE_WIDTH, height);
    }

    public void move(int dx) {
        x += dx;
    }
}
