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

    /**
     * Get {@link Coordinate} of figure.
     * @return
     */
    public Coordinate getCoor() {
        return coor;
    }

    /**
     * Get row coordinate of figure.
     * @return
     */
    protected int getR() {
        return coor.getR();
    }

    /**
     * Get column coordinate of figure.
     * @return
     */
    protected int getC() {
        return coor.getC();
    }

    /**
     * Set coordinate of figure.
     * @param coor
     * @return
     */
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
            for (int newR = getR() + dr[i], newC = getC() + dc[i]; table.checkMoveDumb(newR, newC, color); newR += dr[i], newC += dc[i]) {
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

    /**
     * Get all pseudo-legal moves.
     * <p>
     * Pseudo legal moves includes .
     * @return
     */
    abstract public List<Move> getPseudoLegalMoves();
}

