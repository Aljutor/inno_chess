package models.figures;

import models.Color;
import models.Coordinate;
import models.Table;

import java.util.List;

/**
 * Created by ilnar on 18.07.16.
 */
public abstract class Figure {

    protected final Color color;

    protected final Table table;

    protected Coordinate coor;

    public Figure(Table table, Color color) {
        this.table = table;
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public Coordinate getCoor() {
        return coor;
    }

    public void setCoor(Coordinate coor) {
        this.coor = coor;
    }

    abstract public List<Coordinate> getPossibleMoves();
}
