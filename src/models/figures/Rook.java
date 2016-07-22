package models.figures;

import models.Color;
import models.Move;
import models.Table;

import java.util.List;

/**
 * Created by ilnar on 18.07.16.
 */
public class Rook extends Figure {
    private static final int[] dr = new int[]{-1, 0, 1, 0};
    private static final int[] dc = new int[]{0, 1, 0, -1};

    public Rook(Table table, Color color) {
        super(table, color, FigureType.ROOK);
    }

    public List<Move> getPseudoLegalMoves() {
        return applyArray(dr, dc);
    }
}
