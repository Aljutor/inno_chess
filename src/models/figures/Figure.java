package models.figures;

import javafx.util.Pair;
import models.Table;

import java.util.List;

/**
 * Created by ilnar on 18.07.16.
 */
public interface Figure {

    static List<Pair<Integer, Integer>> getPossibleMoves(Table table, int x, int y) {
        return null;
    }
}
