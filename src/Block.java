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
        x--;
    }

    public void moveRight() {
        x++;
    }

    public void moveDown() {
        y++;
    }

    public void rotate() {
        rotation = (rotation + 1) % shape.length;
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
}