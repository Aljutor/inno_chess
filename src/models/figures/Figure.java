package models.figures;

import models.Color;
import models.Coordinate;
import models.Table;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ilnar on 18.07.16.
 */
public abstract class Figure {
    protected final FigureType type;

    protected final Color color;

    protected final Table table;

    protected int r;

    protected int c;

    public Figure(Table table, Color color, FigureType type) {
        this.table = table;
        this.color = color;
        this.type = type;
    }

    public Color getColor() {
        return color;
    }

    public Coordinate getCoor() {
        return new Coordinate(r, c);
    }

    public void setCoor(Coordinate coor) {
        this.r = coor.getR();
        this.c = coor.getC();
    }

    protected List<Coordinate> applyArray(int[] dr, int[] dc) {
        List<Coordinate> result = new ArrayList<>();
        for (int i = 0; i < dr.length; i++) {
            for (int newR = r + dr[i], newC = c + dc[i]; table.checkMove(newR, newC); newR += dr[i], newC += dc[i]) {
                result.add(new Coordinate(newR, newC));
                if (table.getFigure(newR, newC) != null) {
                    break;
                }
            }
        }
        return result;
    }

    abstract public List<Coordinate> getPossibleMoves();
}

enum FigureType {
    PAWN, BISHOP, KNIGHT, ROOK, QUEEN, KING
}
