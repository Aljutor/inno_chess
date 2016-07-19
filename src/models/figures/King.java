package models.figures;

import javafx.util.Pair;
import models.Color;
import models.Table;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ilnar on 18.07.16.
 */
public class King extends Figure {

    public King(Table table, Color color) {
        super(table, color);
    }

    public static List<Pair<Integer, Integer>> getPossibleMoves(Table table, int r, int c) {
        List<Pair<Integer, Integer>> result = new ArrayList<>();
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (!(i == 0 && j == 0)) {
                    if (table.checkMove(r + i, c + j)) {
                        result.add(new Pair<>(r + i, c + j));
                    }
                }
            }
        }
        return result;
    }
}
