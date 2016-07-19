package models;

/**
 * Created by ilnar on 19.07.16.
 */
public class Coordinate {
    private int r;

    private int c;

    public Coordinate(int r, int c) {
        this.r = r;
        this.c = c;
    }

    public void set(int r, int c) {
        this.r = r;
        this.c = c;
    }

    public void add(int dr, int dc) {
        r += dr;
        c += dc;
    }

    public int getR() {
        return r;
    }

    public int getC() {
        return c;
    }
}
