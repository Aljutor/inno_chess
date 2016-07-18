package models.figures;

import javafx.util.Pair;
import models.Consts;
import models.Table;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ilnar on 18.07.16.
 */
public class Rook implements Figure {
    private static final int[] dr = new int[]{-1, 0, 1, 0};
    private static final int[] dc = new int[]{0, 1, 0, -1};

    public static List<Pair<Integer, Integer>> getPossibleMoves(Table table, int r, int c) {
        List<Pair<Integer, Integer>> result = new ArrayList<>();
        for (int i = 0; i < dr.length; i++) {
            int newR = dr[i];
            int newC = dc[i];
            do {
                newR++;
                if (table.checkMove(newR, newC)) {
                    result.add(new Pair<>(newR, newC));
                    if (table.getFigure(newR, newC) != Consts.EMPTY) {
                        break;
                    }
                }
            } while (table.checkMove(newR, newC));
        }
        return result;
    }
}
