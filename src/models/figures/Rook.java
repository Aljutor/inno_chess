package models.figures;

import javafx.util.Pair;
import models.Color;
import models.Consts;
import models.Coordinate;
import models.Table;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ilnar on 18.07.16.
 */
public class Rook extends Figure {
    private static final int[] dr = new int[]{-1, 0, 1, 0};
    private static final int[] dc = new int[]{0, 1, 0, -1};

    public Rook(Table table, Color color) {
        super(table, color);
    }

    public List<Coordinate> getPossibleMoves() {
        List<Coordinate> result = new ArrayList<>();
        for (int i = 0; i < dr.length; i++) {
            int newR = coor.getR();
            int newC = coor.getC();
            Coordinate newCoor;
            do {
                newR += dr[i];
                newC += dc[i];
                newCoor = new Coordinate(newR, newC);
                if (table.checkMove(newCoor)) {
                    result.add(newCoor);
                    if (table.getFigure(newCoor) != null) {
                        break;
                    }
                }
            } while (table.checkMove(newCoor));
        }
        return result;
    }
}
