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
public abstract class Figure {
    protected final FigureType type;

    protected final Color color;

    protected final Table table;

    protected Coordinate coor;

    public Figure(Table table, Color color, FigureType type) {
        this.table = table;
        this.color = color;
        this.type = type;
    }

    public Color getColor() {
        return color;
    }

    public Coordinate getCoor() {
        return coor;
    }

    protected int r() {
        return coor.getR();
    }

    protected int c() {
        return coor.getC();
    }

    public Figure setCoor(Coordinate coor) {
        this.coor = coor;
        return this;
    }

    public FigureType getType(){
        return type;
    }

    protected List<Move> applyArray(int[] dr, int[] dc) {
        List<Move> result = new ArrayList<>();
        for (int i = 0; i < dr.length; i++) {
            for (int newR = r() + dr[i], newC = c() + dc[i]; table.checkMoveDumb(newR, newC, color); newR += dr[i], newC += dc[i]) {
                result.add(new Move(coor, new Coordinate(newR, newC)));
                if (table.getFigure(newR, newC) != null) {
                    break;
                }
            }
        }
        return result;
    }

    public List<Move> getLegalMoves() {
        List<Move> possible = getPseudoLegalMoves();
        List<Move> valid = new ArrayList<>();
        for (Move move : possible) {
            if (table.checkMove(move, color)) {
                valid.add(move);
            }
        }
        return valid;
    }
    abstract public List<Move> getPseudoLegalMoves();
}

