package models.figures;

import javafx.util.Pair;
import models.Color;
import models.Coordinate;
import models.Table;

import java.util.List;

/**
 * Created by ilnar on 18.07.16.
 */
public class Bishop extends Figure {
    public static final int[] dr = new int[]{-1, 1,  1, -1};
    public static final int[] dc = new int[]{ 1, 1, -1, -1};

    public Bishop(Table table, Color color) {
        super(table, color, FigureType.BISHOP);
    }

    public List<Coordinate> getPossibleMoves() {
        return applyArray(dr, dc);
    }
}
