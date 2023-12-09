package game;

import javax.swing.*;

public class SmallGame extends JFrame {
    public static final int GAME_WIDTH = 800;
    public static final int GAME_HEIGHT = 400;

    public SmallGame() {
        setTitle("Point collector game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(GAME_WIDTH + 14, GAME_HEIGHT + 36);
        setLocationRelativeTo(null);

        GamePanel panel = new GamePanel();
        Thread panelThread = new Thread(panel);
        panelThread.start();

        add(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SmallGame();
            }
        });
    }
}
