package models.figures;

import models.Color;
import models.Coordinate;
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
        super(table, color, FigureType.KNIGHT);
    }

    public List<Coordinate> getPossibleMoves() {
        List<Coordinate> result = new ArrayList<>();
        for (int i = 0; i < dr.length; i++) {
            int newR = r + dr[i];
            int newC = c + dc[i];
            if (table.checkMove(newR, newC)) {
                result.add(new Coordinate(newR, newC));
            }
        }
        return result;
    }
}
