package models.figures;

import javafx.util.Pair;
import models.Color;
import models.Coordinate;
import models.Table;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by ilnar on 18.07.16.
 */
public class Pawn extends Figure {
    public Pawn(Table table, Color color) {
        super(table, color, FigureType.PAWN);
    }

    public List<Coordinate> getPossibleMoves() {
        List<Coordinate> result = new LinkedList<>();
        int koff = 1;
        if (color == Color.BLACK) {
            koff = -1;
        }
        if (color == Color.BLACK) {
            if (r == 6 && table.checkMove(4, c) && table.getFigure(4, c) == null) {
                result.add(new Coordinate(4, c));
            }
        } else {
            if (r == 1 && table.checkMove(3, c) && table.getFigure(3, c) == null) {
                result.add(new Coordinate(3, c));
            }
        }
        if (table.checkMove(r + koff, c) && table.getFigure(r + koff, c) == null) {
            result.add(new Coordinate(r + koff, c));
        }
        if (table.checkMove(r + koff, c - 1) && table.getFigure(r + koff, c - 1) != null) {
            result.add(new Coordinate(r + koff, c - 1));
        }
        if (table.checkMove(r + koff, c + 1) && table.getFigure(r + koff, c + 1) != null) {
            result.add(new Coordinate(r + koff, c + 1));
        }
        return result;
    }
}
