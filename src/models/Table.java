package models;

import models.figures.*;

/**
 * Created by ilnar on 18.07.16.
 */
public class Table {
    public static final int DEFAULT_SIZE = 8;
    private final Figure[][] table;

    private Color whoseTurn;

    private final Player firstPlayer;

    private final Player secondPlayer;

    public Table(Player firstPlayer, Player secondPlayer) {
        table = new Figure[DEFAULT_SIZE][];
        table[0] = new Figure[]{new Rook(this, Color.WHITE), new Knight(this, Color.WHITE), new Bishop(this, Color.WHITE), new King(this, Color.WHITE), new Queen(this, Color.WHITE), new Bishop(this, Color.WHITE), new Knight(this, Color.WHITE), new Rook(this, Color.WHITE)};
        table[1] = new Figure[]{new Pawn(this, Color.WHITE), new Pawn(this, Color.WHITE), new Pawn(this, Color.WHITE), new Pawn(this, Color.WHITE), new Pawn(this, Color.WHITE), new Pawn(this, Color.WHITE), new Pawn(this, Color.WHITE), new Pawn(this, Color.WHITE)};
        table[2] = new Figure[DEFAULT_SIZE];
        table[3] = new Figure[DEFAULT_SIZE];
        table[4] = new Figure[DEFAULT_SIZE];
        table[5] = new Figure[DEFAULT_SIZE];
        table[6] = new Figure[]{new Pawn(this, Color.BLACK), new Pawn(this, Color.BLACK), new Pawn(this, Color.BLACK), new Pawn(this, Color.BLACK), new Pawn(this, Color.BLACK), new Pawn(this, Color.BLACK), new Pawn(this, Color.BLACK), new Pawn(this, Color.BLACK)};
        table[7] = new Figure[]{new Rook(this, Color.BLACK), new Knight(this, Color.BLACK), new Bishop(this, Color.BLACK), new King(this, Color.BLACK), new Queen(this, Color.BLACK), new Bishop(this, Color.BLACK), new Knight(this, Color.BLACK), new Rook(this, Color.BLACK)};
        for (int i = 0; i < DEFAULT_SIZE; i++) {
            for (int j = 0; j < DEFAULT_SIZE; j++) {
                table[i][j].setCoor(new Coordinate(i, j));
            }
        }
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
    }

    public Figure[][] getTable() {
        return table;
    }

    public Figure getFigure(Coordinate coor) {
        return table[coor.getR()][coor.getC()];
    }

    public Color whoseTurn() {
        return whoseTurn;
    }

    public void nextTurn() {
        if (whoseTurn == Color.WHITE) {
            firstPlayer.turn();
            whoseTurn = Color.BLACK;
        } else {
            secondPlayer.turn();
            whoseTurn = Color.WHITE;
        }
    }

    public boolean checkMove(Coordinate coor) {
        if (0 <= coor.getR() && coor.getR() < Table.DEFAULT_SIZE && 0 <= coor.getC() && coor.getC() < Table.DEFAULT_SIZE) {
            if (table[coor.getR()][coor.getC()] == null || table[coor.getR()][coor.getC()].getColor() != whoseTurn()) {
                return true;
            }
        }
        return false;
    }
}
