import java.util.HashMap;

public class MoveStorage {
    HashMap<Integer, Move> allMoves;

    public MoveStorage() {
        allMoves = new HashMap<>();
    }

    private static int getKey(Position position, Speed speed) {
        return position.getKey() | (speed == null ? 0 : (speed.getKey() << 16));
    }

    public Move getMove(Position position, Speed speed) {
        Integer key = getKey(position, speed);

        if (allMoves.containsKey(key)) {
            return allMoves.get(key);
        }

        Move newMove = new Move(position, speed);
        allMoves.put(newMove.getCombined(), newMove);
        return newMove;
    }


}
