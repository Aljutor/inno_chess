package models.figures;

import javafx.util.Pair;
import models.Color;
import models.Table;

import java.util.List;

/**
 * Created by ilnar on 18.07.16.
 */
public class Pawn extends Figure {
    public Pawn(Table table, Color color) {
        super(table, color);
    }

    public static List<Pair<Integer, Integer>> getPossibleMoves(int[][] table, int x, int y) {
        return null;
    }
}
