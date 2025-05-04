/*
 * This is where the "gameboard" or screen for the Tetris game
 * If you run it here, you can see how it currently is 
 * I did it so I can visually see where the buttons were
 */
import javax.swing.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameWindow extends JPanel {
    private Timer fallTimer;
    private Block currentPiece;

    // The block shapes
    private final int[][][][] blocks = {
        // I
        {
            {
                {0, 0, 0, 0},
                {1, 1, 1, 1},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
            },
            {
                {0, 1, 0, 0},
                {0, 1, 0, 0},
                {0, 1, 0, 0},
                {0, 1, 0, 0}
            }
        },
        // O
        {
            {
                {1, 1},
                {1, 1}
            }
        },
        // T
        {
            {
                {0, 1, 0},
                {1, 1, 1},
                {0, 0, 0}
            },
            {
                {0, 1, 0},
                {0, 1, 1},
                {0, 1, 0}
            },
            {
                {0, 0, 0},
                {1, 1, 1},
                {0, 1, 0}
            },
            {
                {0, 1, 0},
                {1, 1, 0},
                {0, 1, 0}
            }
        },
        // S
        {
            {
                {0, 1, 1},
                {1, 1, 0},
                {0, 0, 0}
            },
            {
                {0, 1, 0},
                {0, 1, 1},
                {0, 0, 1}
            }
        },
        // Z
        {
            {
                {1, 1, 0},
                {0, 1, 1},
                {0, 0, 0}
            },
            {
                {0, 0, 1},
                {0, 1, 1},
                {0, 1, 0}
            }
        },
        // J
        {
            {
                {1, 0, 0},
                {1, 1, 1},
                {0, 0, 0}
            },
            {
                {0, 1, 1},
                {0, 1, 0},
                {0, 1, 0}
            },
            {
                {0, 0, 0},
                {1, 1, 1},
                {0, 0, 1}
            },
            {
                {0, 1, 0},
                {0, 1, 0},
                {1, 1, 0}
            }
        },
        // L
        {
            {
                {0, 0, 1},
                {1, 1, 1},
                {0, 0, 0}
            },
            {
                {0, 1, 0},
                {0, 1, 0},
                {0, 1, 1}
            },
            {
                {0, 0, 0},
                {1, 1, 1},
                {1, 0, 0}
            },
            {
                {1, 1, 0},
                {0, 1, 0},
                {0, 1, 0}
            }
        }
    };

    public GameWindow() {
        setLayout(null); // Using absolute positioning
        setBackground(Color.LIGHT_GRAY);
        setFocusable(true);
        requestFocusInWindow();

        JButton start = new JButton("Start");
        JButton pause = new JButton("Pause");
        JButton exit = new JButton("Exit");

        start.setBounds(900, 680, 70, 40);
        pause.setBounds(830, 680, 70, 40);
        exit.setBounds(770, 680, 70, 40);

        add(start);
        add(pause);
        add(exit);

        start.addActionListener(e -> startGame());
        pause.addActionListener(e -> pauseGame());
        exit.addActionListener(e -> {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("scores.txt", true))) {
                writer.write("Score: " + (int)(Math.random() * 1000));
                writer.newLine();
            }    
            catch (IOException ex) {
                ex.printStackTrace();
            }
            System.exit(0);
        });

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (currentPiece == null) return;

                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT -> currentPiece.moveLeft();
                    case KeyEvent.VK_RIGHT -> currentPiece.moveRight();
                    case KeyEvent.VK_DOWN -> currentPiece.moveDown();
                    case KeyEvent.VK_UP -> currentPiece.rotate();
                }
                repaint();
                // Debugging keyboard movements
                // System.out.println("Key pressed: " + e.getKeyCode());
            }
        });
    }

    public void startGame() {
        int index = (int)(Math.random() * blocks.length);
        int[][][] shape = blocks[index];
        currentPiece = new Block(shape, 4, 0);
        repaint();
        fallTimer = new Timer(500, e -> {
            if (currentPiece != null) {
                currentPiece.moveDown();
                repaint();
            }
        });
        fallTimer.start();
        repaint();
        // Reads keyboard arrows
        this.grabFocus();
    }
    
    public void pauseGame() {
        if (fallTimer != null) {
            fallTimer.stop();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (currentPiece != null) {
            g.setColor(Color.BLUE);
            int[][] shape = currentPiece.getCurrentShape();
            int blockSize = 30;

            for (int i = 0; i < shape.length; i++) {
                for (int j = 0; j < shape[i].length; j++) {
                    if (shape[i][j] == 1) {
                        int x = (currentPiece.getX() + j) * blockSize;
                        int y = (currentPiece.getY() + i) * blockSize;
                        g.fillRect(x, y, blockSize, blockSize);
                        g.setColor(Color.BLACK);
                        g.drawRect(x, y, blockSize, blockSize);
                        g.setColor(Color.BLUE);
                    }
                }
            }
        }
    }

    @Override
    public void addNotify() {
        super.addNotify();
        requestFocusInWindow();
    }
}