package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

class GamePanel extends JPanel implements ActionListener, KeyListener {
    private static final int OBSTACLE_SPEED = 10;
    private static final int POINT_SPEED = 7;
    private Player player;
    private List<Point> points;
    private List<Obstacle> obstacles;
    private boolean gameRunning;

    public GamePanel() {
        setPreferredSize(new Dimension(SmallGame.GAME_WIDTH, SmallGame.GAME_HEIGHT));
        player = new Player(50, 300);
        points = new ArrayList<>();
        obstacles = new ArrayList<>();
        gameRunning = false;

        setFocusable(true);
        addKeyListener(this);

        Timer timer = new Timer(15, this);
        timer.start();

        Timer pointTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(gameRunning) {
                    addPoint();
                }
            }
        });
        pointTimer.start();

        Timer obstacleTimer = new Timer(700, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(gameRunning) {
                    addObstacle();
                }
            }
        });
        obstacleTimer.start();
    }

    private void updateGame() {
        checkCollisions();

        Iterator<Obstacle> obstacleIterator = obstacles.iterator();
        while (obstacleIterator.hasNext()) {
            Obstacle obstacle = obstacleIterator.next();
            obstacle.move(-OBSTACLE_SPEED);
            if(obstacle.getX() + obstacle.getWidth() <= 0) {
                obstacleIterator.remove();
            }
        }

        Iterator<Point> pointIterator = points.iterator();
        while (pointIterator.hasNext()) {
            Point point = pointIterator.next();
            point.move(-POINT_SPEED);
            if(point.getX() + Point.POINT_WIDTH <= 0) {
                pointIterator.remove();
            }
        }

        repaint();
    }

    private void checkCollisions() {
        Rectangle playerBounds = player.getBounds();

        Iterator<Point> pointIterator = points.iterator();
        while(pointIterator.hasNext()) {
            if(playerBounds.intersects(pointIterator.next().getBounds())) {
                player.incrementScore();
                pointIterator.remove();
            }
        }

        for (Obstacle obstacle : obstacles) {
            if (playerBounds.intersects(obstacle.getBounds())) {
                endGame();
                return;
            }
        }
    }

    private void endGame() {
        gameRunning = false;
        player = new Player(50, 300);
        points.clear();
        obstacles.clear();
    }

    private void addPoint() {
        Random random = new Random();
        int x = SmallGame.GAME_WIDTH;
        int y = random.nextInt(SmallGame.GAME_HEIGHT - Point.POINT_HEIGHT);
        points.add(new Point(x, y));
    }

    private void addObstacle() {
        Random random = new Random();
        int x = SmallGame.GAME_WIDTH;
        int height = random.nextInt(20, 111);
        int y = random.nextInt(SmallGame.GAME_HEIGHT - height);
        obstacles.add(new Obstacle(x, y, height));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, SmallGame.GAME_WIDTH, SmallGame.GAME_HEIGHT);

        g.setColor(Color.BLUE);
        g.fillOval(player.getX(), player.getY(), Player.PLAYER_WIDTH, Player.PLAYER_HEIGHT);

        g.setColor(Color.GREEN);
        for (Point point : points) {
            g.fillOval(point.getX(), point.getY(), Point.POINT_WIDTH, Point.POINT_HEIGHT);
        }

        g.setColor(Color.RED);
        for (Obstacle obstacle : obstacles) {
            g.fillRect(obstacle.getX(), obstacle.getY(), obstacle.getWidth(), obstacle.getHeight());
        }

        g.setColor(Color.WHITE);
        g.drawString("Score: " + player.getScore(), 10, 20);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (gameRunning) {
            updateGame();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (!gameRunning) {
            gameRunning = true;
        }

        if (key == KeyEvent.VK_UP) {
            player.move(0, -10);
        } else if (key == KeyEvent.VK_DOWN) {
            player.move(0, 10);
        } else if (key == KeyEvent.VK_LEFT) {
            player.move(-5, 0);
        } else if (key == KeyEvent.VK_RIGHT) {
            player.move(5, 0);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}
