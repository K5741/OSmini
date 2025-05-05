/*
 * Right now if you run it calls the popup character
 * That will be on the side or near the gameboard giving warnings
 */
import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Blah Tetris");
            GameWindow game = new GameWindow();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1300, 800);
            frame.setResizable(false);
            frame.add(game);
            frame.setVisible(true);
            game.requestFocusInWindow();
        });

        PopupThread spawn = new PopupThread();
        spawn.start();
    }
}