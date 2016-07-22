package models;

/**
 * Created by ilnar on 19.07.16.
 */
public class Coordinate {
    private final int r;

    private final int c;

    public Coordinate(int r, int c) {
        this.r = r;
        this.c = c;
    }

    public Coordinate(String s) {
        if (s.charAt(1) < '1' || s.charAt(1) > '8' || s.charAt(0) < 'a' || s.charAt(0) > 'h'){
            throw new IllegalArgumentException();
        }
        this.r = s.charAt(1) - '1';
        this.c = s.charAt(0) - 'a';
    }

    public int getR() {
        return r;
    }

    public int getC() {
        return c;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Coordinate) {
            Coordinate co = (Coordinate) o;
            return co.c == c && co.r == r;
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("%s%d", String.valueOf((char)(c + 'a')), r + 1);
    }
}
