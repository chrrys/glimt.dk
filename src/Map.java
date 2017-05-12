import edu.princeton.cs.algs4.StdIn;

import java.util.ArrayList;

public class Map {
    private final int sizeX;
    private final int sizeY;
    private final CellType[][] theMatrix;

    public Map(int sizeX, int sizeY, String[] contents) {
        theMatrix = new CellType[sizeY][sizeX];
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        int y = 0;
        for (String line : contents) {
            if (line == null || line.equals(""))
                continue;
            for (int x = 0; x < line.length(); x++) {
                if (x < 0 || x >= this.sizeX || y < 0 || y >= this.sizeY) {
                    throw new IndexOutOfBoundsException();
                }
                char c = line.charAt(x);
                CellType t = CellType.getCellType(c);
                theMatrix[y][x] = t;
            }
            y++;
        }
    }

    public static Map FromStdIn() {
        int y = StdIn.readInt();
        int x = StdIn.readInt();
        String[] lines = StdIn.readAllLines();
        Map map = new Map(x, y, lines);
        return map;
    }

    private CellType getTypeAtPosition(Position pos) {
        return getTypeAtPosition(pos.getX(), pos.getY());
    }

    private CellType getTypeAtPosition(int indexX, int indexY) {
        if (indexX < 0 || indexX >= sizeX || indexY < 0 || indexY >= sizeY) {
            throw new IndexOutOfBoundsException();
        }
        return theMatrix[indexY][indexX];
    }

    public Position[] getPositionsOfType(CellType type) {
        ArrayList<Position> posList = new ArrayList<>();
        for (int y = 0; y < sizeY; y++) {
            for (int x = 0; x < sizeX; x++) {
                if (getTypeAtPosition(x, y) == type) {
                    Position position = new Position(x, y);
                    posList.add(position);
                }
            }
        }
        return posList.toArray(new Position[0]);
    }

    private boolean isPositionValidForMove(Position pos) {
        if (pos.getX() < 0 || pos.getX() >= sizeX || pos.getY() < 0 || pos.getY() >= sizeY) {
            return false;
        }
        return getTypeAtPosition(pos) != CellType.WALL;
    }

    public boolean isPositionFinish(Position newPosition) {
        return getTypeAtPosition(newPosition) == CellType.FINISH;
    }

    public Move[] getValidMovesFromLast(Move last, MoveStorage storage) {
        Position currentPosition = last.getPosition();
        if (isPositionFinish(currentPosition)) {
            return new Move[0];
        }
        Speed[] possibleSpeeds = last.getSpeed().getPossibleNextSpeeds();
        ArrayList<Move> moves = new ArrayList<>();
        for (Speed speed : possibleSpeeds) {
            Position newPosition = new Position(currentPosition, speed);
            if (isPositionValidForMove(newPosition)) {
                Move newMove = storage.getMove(newPosition, speed);
                moves.add(newMove);
            }
        }
        return moves.toArray(new Move[0]);
    }

    public String Visualize(Move endMovement) {
        StringBuilder result = new StringBuilder();
        for (int y = 0; y < sizeY; y++) {
            for (int x = 0; x < sizeX; x++) {
                Position pos = new Position(x, y);
                Move move = endMovement == null ? null : endMovement.getFirstMoveAtPos(pos);
                String s;
                if (move == null) {
                    s = getTypeAtPosition(x, y).getRepresentation();
                } else {
                    s = move.getRepresentation();
                }
                result.append(s);
            }
            result.append("\n");
        }
        return result.toString();
    }

}

