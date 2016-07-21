package models;

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
        table[0] = new Figure[]{new Rook(this, Color.WHITE), new Knight(this, Color.WHITE), new Bishop(this, Color.WHITE), new Queen(this, Color.WHITE), new King(this, Color.WHITE), new Bishop(this, Color.WHITE), new Knight(this, Color.WHITE), new Rook(this, Color.WHITE)};
        table[1] = new Figure[]{new Pawn(this, Color.WHITE), new Pawn(this, Color.WHITE), new Pawn(this, Color.WHITE), new Pawn(this, Color.WHITE), new Pawn(this, Color.WHITE), new Pawn(this, Color.WHITE), new Pawn(this, Color.WHITE), new Pawn(this, Color.WHITE)};
        table[2] = new Figure[DEFAULT_SIZE];
        table[3] = new Figure[DEFAULT_SIZE];
        table[4] = new Figure[DEFAULT_SIZE];
        table[5] = new Figure[DEFAULT_SIZE];
        table[6] = new Figure[]{new Pawn(this, Color.BLACK), new Pawn(this, Color.BLACK), new Pawn(this, Color.BLACK), new Pawn(this, Color.BLACK), new Pawn(this, Color.BLACK), new Pawn(this, Color.BLACK), new Pawn(this, Color.BLACK), new Pawn(this, Color.BLACK)};
        table[7] = new Figure[]{new Rook(this, Color.BLACK), new Knight(this, Color.BLACK), new Bishop(this, Color.BLACK), new Queen(this, Color.BLACK), new King(this, Color.BLACK), new Bishop(this, Color.BLACK), new Knight(this, Color.BLACK), new Rook(this, Color.BLACK)};
        for (int i = 0; i < DEFAULT_SIZE; i++) {
            for (int j = 0; j < DEFAULT_SIZE; j++) {
                if (table[i][j] != null) {
                    table[i][j].setCoor(new Coordinate(i, j));
                }
            }
        }
    }

    public Table(int v) {
        this();
        table[0][5] = null;
        table[0][6] = null;
        table[1][3] = null;
        table[1][4] = new Knight(this, Color.WHITE);
        table[1][5] = new Knight(this, Color.BLACK);
        table[3][2] = new Bishop(this, Color.WHITE);
        table[5][2] = new Pawn(this, Color.BLACK);
        table[6][2] = null;
        table[6][3] = new Pawn(this, Color.WHITE);
        table[6][4] = new Bishop(this, Color.BLACK);
        table[7][4] = null;
        table[7][5] = new King(this, Color.BLACK);
        table[7][6] = null;
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
                    new_figure.setCoor(new Coordinate(i, j));
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
        if (!figure.getPossibleMoves().contains(move)) {
            return false;
        }

        if (figure instanceof Pawn) {
            if ((color == Color.WHITE && coorTo.getR() == DEFAULT_SIZE - 1) ||
                    (color == Color.BLACK && coorTo.getR() == 0)) {
                if (move.castPawn == null) {
                    return false;
                }
                switch (move.castPawn) {
                    case ROOK:
                        figure = new Rook(this, color);
                        break;
                    case KNIGHT:
                        figure = new Knight(this, color);
                        break;
                    case BISHOP:
                        figure = new Bishop(this, color);
                        break;
                    case QUEEN:
                        figure = new Queen(this, color);
                }
            }
        }
        figure.setCoor(coorTo);
        table[coorTo.getR()][coorTo.getC()] = figure;
        table[coorFrom.getR()][coorFrom.getC()] = null;
        return true;
    }

    public List<Figure> getFiguresByColor(Color color) {
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



    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = DEFAULT_SIZE - 1; i >= 0; i--) {
            for (int j = 0; j < DEFAULT_SIZE; j++) {
                Figure f = table[i][j];
                if (f == null) {
                    sb.append(".");
                } else if (f.getType() == FigureType.ROOK){
                    sb.append("R");
                } else if (f.getType() == FigureType.BISHOP){
                    sb.append("B");
                } else if (f.getType() == FigureType.KING){
                    sb.append("K");
                } else if (f.getType() == FigureType.KNIGHT){
                    sb.append("k");
                } else if (f.getType() == FigureType.PAWN){
                    sb.append("P");
                } else if (f.getType() == FigureType.QUEEN){
                    sb.append("Q");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
