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
    private final int rows = 22;
    private final int cols = 43;
    private int[][] board = new int[rows][cols];
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

        start.setBounds(1200, 700, 70, 40);
        pause.setBounds(1127, 700, 70, 40);
        exit.setBounds(1054, 700, 70, 40);

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
            if (canMoveDown(currentPiece)) {
                currentPiece.moveDown();
            } 
            else {
                lockBlock(currentPiece);
                currentPiece = generateNewBlock();
            }
            repaint();
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
        // Draw locked blocks
        int blockSize = 30;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (board[row][col] != 0) {
                    g.setColor(Color.GREEN);
                    g.fillRect(col * blockSize, row * blockSize, blockSize, blockSize);
                    g.setColor(Color.BLACK);
                    g.drawRect(col * blockSize, row * blockSize, blockSize, blockSize);
                }
            }
        }
        /*
        // To view board uncomment
         int cellSize = 30;
        int rows = 22;
        int cols = 43;

        // Optional: Fill background
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, cols * cellSize, rows * cellSize);

        // Set grid line color
        g.setColor(Color.DARK_GRAY);  // You can change this

        // Draw vertical lines
        for (int i = 0; i <= cols; i++) {
            g.drawLine(i * cellSize, 0, i * cellSize, rows * cellSize);
        }

        // Draw horizontal lines
        for (int i = 0; i <= rows; i++) {
            g.drawLine(0, i * cellSize, cols * cellSize, i * cellSize);
        }
         */
    }

    @Override
    public void addNotify() {
        super.addNotify();
        requestFocusInWindow();
    }

    private boolean canMoveDown(Block block) {
        int[][] shape = block.getCurrentShape();
        int x = block.getX();
        int y = block.getY();
    
        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[i].length; j++) {
                if (shape[i][j] == 1) {
                    int newY = y + i + 1;
                    int newX = x + j;
    
                    // Check bounds and if cell is filled
                    if (newY >= rows || newX < 0 || newX >= cols || board[newY][newX] != 0) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
    
    private void lockBlock(Block block) {
        int[][] shape = block.getCurrentShape();
        int x = block.getX();
        int y = block.getY();
    
        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[i].length; j++) {
                if (shape[i][j] == 1) {
                    board[y + i][x + j] = 1;
                }
            }
        }
    }
    
    private Block generateNewBlock() {
        int index = (int)(Math.random() * blocks.length);
        int[][][] shape = blocks[index];
        return new Block(shape, 4, 0);
    }    
}