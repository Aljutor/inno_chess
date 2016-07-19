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
    public Bishop(Table table, Color color) {
        super(table, color);
    }

    public List<Coordinate> getPossibleMoves() {
        return null;
    }
}
