public class Move {
    public boolean marked;
    public int distTo;
    public Move edgeTo;
    private Position position;
    private Speed speed;

    public Move(Position position, Speed speed) {
        this.position = position;
        this.speed = speed;
    }

    @Override
    public String toString() {
        return position.toString() + " " + speed.toString();
    }

    public String getRepresentation() {
        return speed.getRepresentation();
    }

    public Integer getCombined() {
        return position.getKey() | (speed.getKey() << 16);
    }

    public Position getPosition() {
        return position;
    }

    public Speed getSpeed() {
        return speed;
    }

    public boolean atPos(Position position) {
        return this.position.isEqual(position);
    }

    public Move getFirstMoveAtPos(Position pos) {
        Move move = this;
        do {
            if (move.atPos(pos)) {
                return move;
            }
            move = move.edgeTo;
        } while (move != null);
        return null;
    }
}
