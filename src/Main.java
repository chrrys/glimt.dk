import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        if (args.length > 1) {
            System.setIn(new FileInputStream(args[1]));
        }
        Map map = Map.FromStdIn();
        Move[] endMovements = BreadthFirst(map);
        if (endMovements.length == 0) {
            System.out.println("-1");
        } else {
            for (Move m : endMovements) {
                StdOut.println(m.distTo + 1);
                System.out.println(map.Visualize(m));
            }
        }

        System.out.println("Hello world!");
    }

    public static Move[] BreadthFirst(Map map) {
        Queue<Move> q = new Queue<>();
        Position[] startPositions = map.getPositionsOfType(CellType.START);
        Speed startSpeed = new Speed(0, 0);
        MoveStorage storage = new MoveStorage();

        for (Position startPosition : startPositions) {
            Move s = storage.getMove(startPosition, startSpeed);
            s.marked = true;
            s.distTo = 0;
            q.enqueue(s);
        }

        ArrayList<Move> movesAtEndPosition = new ArrayList<>();

        while (!q.isEmpty()) {
            Move v = q.dequeue();
            Move[] validMoves = map.getValidMovesFromLast(v, storage);
            for (Move w : validMoves) {
                if (w.marked) {
                    continue;
                }
                w.edgeTo = v;
                w.distTo = v.distTo + 1;
                w.marked = true;
                Position position = w.getPosition();
                if (map.isPositionFinish(position)) {
                    movesAtEndPosition.add(w);
                } else {
                    q.enqueue(w);
                }
            }
        }

        return movesAtEndPosition.toArray(new Move[0]);
    }

}
