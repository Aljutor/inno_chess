package models;

import models.figures.FigureType;

/**
 * Created by Semyon Bessonov on 20.07.2016.
 */
public class Move {
    public final Coordinate from;
    public final Coordinate to;
    public final FigureType castPawn;

    public Move(Coordinate from, Coordinate to) {
        this.from = from;
        this.to   = to;
        this.castPawn = null;
    }

    public Move(Coordinate from, Coordinate to, FigureType castPawn) {
        this.from = from;
        this.to = to;
        this.castPawn = castPawn;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Move) {
            Move o = (Move) obj;
            return from.equals(o.from) && to.equals(o.to) && castPawn == o.castPawn;
        }
        return false;
    }
}
