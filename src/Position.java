public class Position {
    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Position(Position position, Speed speed) {
        this(position.getX() + speed.getDeltaX(), position.getY() + speed.getDeltaY());
        System.out.println("test");


    }

    @Override
    public String toString() {
        return String.format("[%d,%d]", x, y);
    }

    public int getKey() {
        return x | (y << 8);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    public boolean isEqual(Position position) {
        return position != null && x == position.getX() && y == position.getY();
    }
}

