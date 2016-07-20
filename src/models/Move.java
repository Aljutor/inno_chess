package models;

/**
 * Created by Semyon Bessonov on 20.07.2016.
 */
public class Move {
    public final Coordinate from;
    public final Coordinate to;

    public Move(Coordinate from, Coordinate to) {
        this.from = from;
        this.to = to;
    }
}