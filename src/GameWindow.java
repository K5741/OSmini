/*
 * This is where the "gameboard" or screen for the Tetris game
 * If you run it here, you can see how it currently is 
 * I did it so I can visually see where the buttons were
 */
import javax.swing.*;
import java.awt.*;

public class GameWindow extends JPanel {
    private GameBoard gameBoard; // Custom panel you'll define

    public GameWindow() {
        setLayout(null); // Using absolute positioning
        initUI();
    }

    private void initUI() {
        JButton start = new JButton("Start");
        JButton pause = new JButton("Pause");
        JButton exit = new JButton("Exit");

        start.setBounds(900, 680, 70, 40);
        pause.setBounds(830, 680, 70, 40);
        exit.setBounds(770, 680, 70, 40);

        gameBoard = new GameBoard(); // Your custom panel
        gameBoard.setBounds(1, 132, 1000, 600);

        start.addActionListener(e -> gameBoard.startGame());
        pause.addActionListener(e -> gameBoard.pauseGame());
        exit.addActionListener(e -> System.exit(0));

        add(start);
        add(pause);
        add(exit);
        add(gameBoard);
    }

    // Dummy GameBoard class to make this example compile
    static class GameBoard extends JPanel {
        public GameBoard() {
            setBackground(Color.LIGHT_GRAY);
        }

        public void startGame() {
            System.out.println("Game started");
        }

        public void pauseGame() {
            System.out.println("Game paused");
        }
    }

    // To test the GameWindow in a JFrame
    public static void main(String[] args) {
        JFrame frame = new JFrame("Game Window");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1024, 768);
        frame.setContentPane(new GameWindow());
        frame.setVisible(true);
    }
}