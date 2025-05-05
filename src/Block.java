public class Block {
    private int[][][] shape;
    private int rotation;
    private int x, y;

    public Block(int[][][] shape, int x, int y) {
        this.shape = shape;
        this.x = x;
        this.y = y;
        this.rotation = 0;
    }

    public void moveLeft() {
        if (canMove(-1, 0)) x--;
    }

    public void moveRight() {
        if (canMove(1, 0)) x++;
    }

    public void moveDown() {
        if (canMove(0, 1)) y++;
    }

    public void rotate() {
        int nextRotation = (rotation + 1) % shape.length;
        if (canRotate(nextRotation)) {
            rotation = nextRotation;
        }
    }

    public int[][] getCurrentShape() {
        return shape[rotation];
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    // Helper method to enforce boundary rules
    private boolean isInAccessibleArea(int x, int y) {
        return x >= 2 && x <= 10 && y >= 0 && y < 23;
    }

    private boolean canMove(int dx, int dy) {
        int[][] shapeNow = getCurrentShape();
        for (int i = 0; i < shapeNow.length; i++) {
            for (int j = 0; j < shapeNow[i].length; j++) {
                if (shapeNow[i][j] == 1) {
                    int newX = x + j + dx;
                    int newY = y + i + dy;
                    if (!isInAccessibleArea(newX, newY)) return false;
                }
            }
        }
        return true;
    }

    private boolean canRotate(int nextRotation) {
        int[][] shapeNext = shape[nextRotation];
        for (int i = 0; i < shapeNext.length; i++) {
            for (int j = 0; j < shapeNext[i].length; j++) {
                if (shapeNext[i][j] == 1) {
                    int newX = x + j;
                    int newY = y + i;
                    if (!isInAccessibleArea(newX, newY)) return false;
                }
            }
        }
        return true;
    }
}