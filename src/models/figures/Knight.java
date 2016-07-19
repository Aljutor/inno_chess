package models.figures;

import javafx.util.Pair;
import models.Color;
import models.Consts;
import models.Table;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ilnar on 18.07.16.
 */
public class Knight extends Figure {
    private static final int[] dr = new int[]{-2, -1, 1, 2,  2,  1, -1, -2};
    private static final int[] dc = new int[]{ 1,  2, 2, 1, -1, -2, -2, -1};

    public Knight(Table table, Color color) {
        super(table, color);
    }

    public static List<Pair<Integer, Integer>> getPossibleMoves(Table table, int r, int c) {
        List<Pair<Integer, Integer>> result = new ArrayList<>();
        for (int i = 0; i < dr.length; i++) {
            int newR = r + dr[i];
            int newC = c + dc[i];
            if (table.checkMove(newR, newC)) {
                result.add(new Pair<>(newR, newC));
            }
        }
        return result;
    }
}
