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

    public Table() {
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
                if (table[i][j] != null) {
                    table[i][j].setCoor(new Coordinate(i, j));
                }
            }
        }
    }

    public Table(Figure[][] table) {
        this.table = table;
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

    public boolean checkMove(int r, int c, Color color) {
        if (0 <= r && r < Table.DEFAULT_SIZE && 0 <= c && c < Table.DEFAULT_SIZE) {
            if (table[r][c] == null || table[r][c].getColor() != color) {
                return true;
            }
        }
        return false;
    }


    public Table clone() {
        Figure[][] table = new Figure[DEFAULT_SIZE][DEFAULT_SIZE];
        Table tableObj = new Table(table);

        for (int i = 0; i < DEFAULT_SIZE; i++) {
            for (int j = 0; j < DEFAULT_SIZE; j++) {
                Figure figure = this.table[i][j];
                if (figure != null) {
                    Figure new_figure = new Pawn(tableObj, figure.getColor());
                    switch (figure.getType()) {
                        case KING:
                            new_figure = new King(tableObj, figure.getColor());
                            break;
                        case QUEEN:
                            new_figure = new Queen(tableObj, figure.getColor());
                            break;
                        case ROOK:
                            new_figure = new Rook(tableObj, figure.getColor());
                            break;
                        case BISHOP:
                            new_figure = new Bishop(tableObj, figure.getColor());
                            break;
                        case KNIGHT:
                            new_figure = new Knight(tableObj, figure.getColor());
                            break;
                        case PAWN:
                            new_figure = new Pawn(tableObj, figure.getColor());
                            break;
                    }
                    tableObj.table[i][j] = new_figure;
                }
            }
        }
        return tableObj;
    }

    public boolean doMove(Move move, Color color) {
        Coordinate coorFrom = move.from;
        Coordinate coorTo = move.to;
        Figure figure = table[coorFrom.getR()][coorFrom.getC()];

        if (figure.getColor() != color) {
            return false;
        }
        if (!figure.getPossibleMoves().contains(coorTo)) {
            return false;
        }

        figure.setCoor(coorTo);
        table[coorTo.getR()][coorTo.getC()] = figure;
        table[coorFrom.getR()][coorFrom.getC()] = null;
        return true;
    }

    public List<Figure> getColoredFigures(Color color) {
        List<Figure> result = new ArrayList<>();
        for (int i = 0; i < DEFAULT_SIZE; i++) {
            for (int j = 0; j < DEFAULT_SIZE; j++) {
                if (table[i][j] != null && table[i][j].getColor() == color) {
                    result.add(table[i][j]);
                }
            }
        }
        return result;
    }
}
