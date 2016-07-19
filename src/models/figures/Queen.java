package models.figures;

import javafx.util.Pair;
import models.Color;
import models.Table;

import java.util.List;

/**
 * Created by ilnar on 18.07.16.
 */
public class Queen extends Figure {
    private static final int[] dr = new int[]{-1, 0, 1, 0};
    private static final int[] dc = new int[]{0, 1, 0, -1};

    public Queen(Table table, Color color) {
        super(table, color);
    }

    public static List<Pair<Integer, Integer>> getPossibleMoves(int[][] table, int x, int y) {
        return null;
    }
}
