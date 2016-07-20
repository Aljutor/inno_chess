package models.figures;

import models.Color;
import models.Coordinate;
import models.Table;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ilnar on 18.07.16.
 */
public class Queen extends Figure {
    private static final int[] dr = new int[]{-1, 0, 1,  0, -1, 1,  1, -1};
    private static final int[] dc = new int[]{ 0, 1, 0, -1,  1, 1, -1, -1};

    public Queen(Table table, Color color) {
        super(table, color, FigureType.QUEEN);
    }

    public List<Coordinate> getPossibleMoves() {
        return applyArray(dr, dc);
    }
}
