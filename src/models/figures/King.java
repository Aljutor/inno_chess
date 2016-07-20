package models.figures;

import models.Color;
import models.Coordinate;
import models.Move;
import models.Table;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ilnar on 18.07.16.
 */
public class King extends Figure {

    public King(Table table, Color color) {
        super(table, color, FigureType.KING);
    }

    public List<Move> getPossibleMoves() {
        List<Move> result = new ArrayList<>();
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (!(i == 0 && j == 0)) {
                    if (table.checkMove(r() + i, c() + j, color)) {
                        result.add(new Move(coor, new Coordinate(r() + i, c() + j)));
                    }
                }
            }
        }
        return result;
    }
}
