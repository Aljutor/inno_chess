package models;

import javafx.scene.control.Tab;
import models.figures.*;

import java.util.ArrayList;
import java.util.List;

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
                if (table[i][j] != null){
                    table[i][j].setCoor(new Coordinate(i, j));
                }
            }
        }
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        this.whoseTurn = Color.WHITE;
    }

    public Table(Figure[][] table, Player firstPlayer, Player secondPlayer, Color whoseTurn){
        this.table = table;
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        this.whoseTurn = whoseTurn;
    }

    public Figure[][] getTable() {
        return table;
    }

    public Figure getFigure(Coordinate coor) {
        return getFigure(coor.getR(), coor.getC());
    }

    public Figure getFigure(int r, int c) {
        return table[r][c];
    }

    public Color whoseTurn() {
        return whoseTurn;
    }

    public Table nextTurn() {
        Move move;
        Table new_table;
        if (whoseTurn == Color.WHITE) {
            move = firstPlayer.nextMove(this);
            System.out.println("WHITE: " + move.from.getR() + " " + move.from.getC() + "|" +  move.to.getR() + " " + move.to.getC());

            new_table = this.doMove(move);
            new_table.whoseTurn = Color.BLACK;
            return new_table;
        } else {

            move = secondPlayer.nextMove(this);
            System.out.println("BLACK: " + move.from.getR() + " " + move.from.getC() + "|" +  move.to.getR() + " " + move.to.getC());

            new_table = this.doMove(move);
            new_table.whoseTurn = Color.WHITE;
            return new_table;
        }
    }

    public boolean checkMove(Coordinate coor) {
        return checkMove(coor.getR(), coor.getC());
    }

    public boolean checkMove(int r, int c) {
        if (0 <= r && r < Table.DEFAULT_SIZE && 0 <= c && c < Table.DEFAULT_SIZE) {
            if (table[r][c] == null || table[r][c].getColor() != whoseTurn()) {
                return true;
            }
        }
        return false;
    }

    public Table clone(){
        Figure[][] table = new Figure[DEFAULT_SIZE][DEFAULT_SIZE];
        Table tableObj = new Table(table, this.firstPlayer, this.secondPlayer, this.whoseTurn);

        for (int i = 0; i < DEFAULT_SIZE; i++) {
            for (int j = 0; j < DEFAULT_SIZE; j++) {
                Figure figure = this.table[i][j];
                if (figure != null) {
                    Figure newFigure = new Pawn(tableObj, figure.getColor());
                    switch (figure.getType()){
                        case KING:
                            newFigure = new King(tableObj, figure.getColor());
                            break;
                        case QUEEN:
                            newFigure = new Queen(tableObj, figure.getColor());
                            break;
                        case ROOK:
                            newFigure = new Rook(tableObj, figure.getColor());
                            break;
                        case BISHOP:
                            newFigure = new Bishop(tableObj, figure.getColor());
                            break;
                        case KNIGHT:
                            newFigure = new Knight(tableObj, figure.getColor());
                            break;
                        case PAWN:
                            newFigure = new Pawn(tableObj, figure.getColor());
                            break;
                        }
                    newFigure.setCoor(new Coordinate(i,j));
                    tableObj.table[i][j] = newFigure;
                    }
                }
            }
        return tableObj;
    }

    public Table doMove(Move move){
        Coordinate coorFrom = move.from;
        Coordinate coorTo   = move.to;

        Table newTable = this.clone();

        newTable.table[coorTo.getR()][coorTo.getC()]     =  newTable.getFigure(coorFrom).setCoor(coorTo);
        newTable.table[coorFrom.getR()][coorFrom.getC()] = null;

        return newTable;

    }

    public List<Figure> getColoredFigures(Color color) {
        List<Figure> result = new ArrayList<>();

        for (int i = 0; i < DEFAULT_SIZE; i++) {
            for (int j = 0; j < DEFAULT_SIZE; j++) {
                Figure f = table[i][j];
                if (f != null && f.getColor() == color) {
                        result.add(f);
                }
            }
        }
        return result;
    }
}
