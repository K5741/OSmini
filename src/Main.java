/*
 * Right now if you run it calls the popup character
 * That will be on the side or near the gameboard giving warnings
 */
import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                // Create pipe for communication
                PipedOutputStream pipeOut = new PipedOutputStream();
                PipedInputStream pipeIn = new PipedInputStream(pipeOut);

                // Create game window and popup thread using the pipe
                GameWindow gameWindow = new GameWindow(pipeIn);
                PopupThread popupThread = new PopupThread(pipeOut);

                // Game Window Setup
                JFrame frame = new JFrame("Blah Tetris");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(405, 800);
                frame.setResizable(false);
                frame.add(gameWindow);
                frame.setVisible(true);
                gameWindow.requestFocusInWindow();

                // Start the popup thread
                popupThread.start();

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}