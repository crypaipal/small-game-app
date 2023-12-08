package game;

import java.awt.*;
import java.awt.geom.Ellipse2D;

class Point {
    public static final int POINT_WIDTH = 15;
    public static final int POINT_HEIGHT = 15;
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, POINT_WIDTH, POINT_HEIGHT);
    }

    public void move(int dx) {
        x += dx;
    }
}