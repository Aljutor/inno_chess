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
            if (table.checkMoveDumb(to.getR(), to.getC(), color)) {
                result.add(new Move(coor, to, FigureType.ROOK));
                result.add(new Move(coor, to, FigureType.KNIGHT));
                result.add(new Move(coor, to, FigureType.BISHOP));
                result.add(new Move(coor, to, FigureType.QUEEN));
            }
        }
    }

    public List<Move> getPseudoLegalMoves() {
        List<Move> result = new LinkedList<>();
        int koff = 1;
        if (color == Color.BLACK) {
            koff = -1;
        }
        if (color == Color.BLACK) {
            Move move = new Move(coor, new Coordinate(4, getC()));
            if (getR() == 6 && table.checkMoveDumb(4, getC(), color) && table.getFigure(5, getC()) == null && table.getFigure(4, getC()) == null) {
                result.add(move);
            }
        } else {
            Move move = new Move(coor, new Coordinate(3, getC()));
            if (getR() == 1 && table.checkMoveDumb(3, getC(), color) && table.getFigure(2, getC()) == null && table.getFigure(3, getC()) == null) {
                result.add(move);
            }
        }
        if (table.checkMoveDumb(getR() + koff, getC() - 1, color) && table.getFigure(getR() + koff, getC() - 1) != null) {
            Move move = new Move(coor, new Coordinate(getR() + koff, getC() - 1));
            addTransformation(result, move.to);
            result.add(move);
        }
        if (table.checkMoveDumb(getR() + koff, getC(), color) && table.getFigure(getR() + koff, getC()) == null) {
            Move move = new Move(coor, new Coordinate(getR() + koff, getC()));
            addTransformation(result, move.to);
            result.add(move);
        }
        if (table.checkMoveDumb(getR() + koff, getC() + 1, color) && table.getFigure(getR() + koff, getC() + 1) != null) {
            Move move = new Move(coor, new Coordinate(getR() + koff, getC() + 1));
            addTransformation(result, move.to);
            result.add(move);
        }
        return result;
    }
}
