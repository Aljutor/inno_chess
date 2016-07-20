package models.figures;

import models.Color;
import models.Coordinate;
import models.Move;
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

    private void addTransformation(List<Move> result, Coordinate to) {
        if ((color == Color.WHITE && to.getR() == Table.DEFAULT_SIZE - 1) ||
                (color == Color.BLACK && to.getR() == 0)) {
            result.add(new Move(coor, to, FigureType.ROOK));
            result.add(new Move(coor, to, FigureType.KNIGHT));
            result.add(new Move(coor, to, FigureType.BISHOP));
            result.add(new Move(coor, to, FigureType.QUEEN));
        }
    }

    public List<Move> getPossibleMoves() {
        List<Move> result = new LinkedList<>();
        int koff = 1;
        if (color == Color.BLACK) {
            koff = -1;
        }
        if (color == Color.BLACK) {
            if (r() == 6 && table.checkMove(4, c(), color) && table.getFigure(5, c()) == null && table.getFigure(4, c()) == null) {
                result.add(new Move(coor, new Coordinate(4, c())));
            }
        } else {
            if (r() == 1 && table.checkMove(3, c(), color) && table.getFigure(2, c()) == null && table.getFigure(3, c()) == null) {
                result.add(new Move(coor, new Coordinate(3, c())));
            }
        }
        if (table.checkMove(r() + koff, c() - 1, color) && table.getFigure(r() + koff, c() - 1) != null) {
            Coordinate to = new Coordinate(r() + koff, c() - 1);
            result.add(new Move(coor, to));
            addTransformation(result, to);
        }
        if (table.checkMove(r() + koff, c(), color) && table.getFigure(r() + koff, c()) == null) {
            Coordinate to = new Coordinate(r() + koff, c());
            result.add(new Move(coor, to));
            addTransformation(result, to);
        }
        if (table.checkMove(r() + koff, c() + 1, color) && table.getFigure(r() + koff, c() + 1) != null) {
            Coordinate to = new Coordinate(r() + koff, c() + 1);
            result.add(new Move(coor, to));
            addTransformation(result, to);
        }
        return result;
    }
}
