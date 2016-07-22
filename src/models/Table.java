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
        table = new Figure[DEFAULT_SIZE][];
        for (int i = 0; i < DEFAULT_SIZE; i++) {
            table[i] = new Figure[DEFAULT_SIZE];
        }
        if (v == 1) {
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
        } else if (v == 2) {
            table[0][7] = new King(this, Color.WHITE);
            table[1][2] = new Rook(this, Color.WHITE);
            table[1][4] = new Bishop(this, Color.WHITE);
            table[2][5] = new Queen(this, Color.WHITE);
            table[2][7] = new Knight(this, Color.WHITE);
            table[3][6] = new Rook(this, Color.WHITE);
            table[4][0] = new Pawn(this, Color.WHITE);
            table[5][0] = new Pawn(this, Color.BLACK);
            table[6][1] = new King(this, Color.BLACK);
            table[7][1] = new Rook(this, Color.BLACK);
            table[7][3] = new Rook(this, Color.BLACK);
        } else if (v == 3) {
            table[0] = new Figure[]{new Rook(this, Color.WHITE), new Knight(this, Color.WHITE), new Bishop(this, Color.WHITE), new Queen(this, Color.WHITE), new King(this, Color.WHITE), new Bishop(this, Color.WHITE), new Knight(this, Color.WHITE), new Rook(this, Color.WHITE)};
            table[1] = new Figure[]{null, null, null, null, null, new Pawn(this, Color.WHITE), new Pawn(this, Color.WHITE), new Pawn(this, Color.WHITE)};

            table[3] = new Figure[]{new Pawn(this, Color.WHITE), new Pawn(this, Color.WHITE), new Pawn(this, Color.WHITE), new Pawn(this, Color.BLACK), null, null, null, null};
            table[4] = new Figure[]{null, null, null, null, null, new Pawn(this, Color.WHITE), null, null};
            table[5] = new Figure[]{null, null, null, null, null, new Knight(this, Color.BLACK), new Pawn(this, Color.BLACK), new Bishop(this, Color.BLACK)};
            table[6] = new Figure[]{new Pawn(this, Color.BLACK), new Pawn(this, Color.BLACK), new Pawn(this, Color.BLACK), new Pawn(this, Color.BLACK), new Queen(this, Color.BLACK), null, null, new Pawn(this, Color.BLACK)};
            table[7] = new Figure[]{new Rook(this, Color.BLACK), new Knight(this, Color.BLACK), new Bishop(this, Color.BLACK), null, new King(this, Color.BLACK), null, null, new Rook(this, Color.BLACK)};
        } else if (v == 4) {
            table[3][2] = new Rook(this, Color.BLACK);
            table[4][0] = new Pawn(this, Color.BLACK);
            table[5][2] = new King(this, Color.BLACK);
            table[6][6] = new King(this, Color.WHITE);
            table[7][5] = new Rook(this, Color.BLACK);
        }
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

    public boolean checkMoveDumb(int r, int c, Color color) {
        if (0 <= r && r < Table.DEFAULT_SIZE && 0 <= c && c < Table.DEFAULT_SIZE) {
            if (table[r][c] == null || table[r][c].getColor() != color) {
                return true;
            }
        }
        return false;
    }

    private boolean checkCoordinate(Coordinate coor) {
        if (0 <= coor.getR() && coor.getR() < Table.DEFAULT_SIZE) {
            if (0 <= coor.getC() && coor.getC() < Table.DEFAULT_SIZE) {
                return true;
            }
        }
        return false;
    }

    public boolean checkMove(Move move, Color color) {
        if (checkCoordinate(move.from) && getFigure(move.from) != null && getFigure(move.from).getColor() == color) {
            if (checkCoordinate(move.to) && (getFigure(move.to) == null || getFigure(move.to).getColor() != color)) {
                Table copy = clone();
                if (copy.doMove(move, color) && !copy.isCheck(color)) {
                    return true;
                }
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
        if (!figure.getPseudoLegalMoves().contains(move)) {
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

    public List<Move> getMovesByColor(Color color) {
        List<Move> result = new ArrayList<>();
        getFiguresByColor(color).forEach(figure -> {
            result.addAll(figure.getPseudoLegalMoves());
        });
        return result;
    }

    public Figure getFigureByType(Color color, FigureType type) {
        for (int i = 0; i < DEFAULT_SIZE; i++) {
            for (int j = 0; j < DEFAULT_SIZE; j++) {
                if (table[i][j] != null && table[i][j].getColor() == color && table[i][j].getType() == type) {
                    return table[i][j];
                }
            }
        }
        return null;
    }

    public boolean isCheck(Color color) {
        Coordinate king = getFigureByType(color, FigureType.KING).getCoor();
        List<Move> moves = getMovesByColor((color == Color.WHITE ? Color.BLACK : Color.WHITE));
        for (Move move : moves) {
            if (king.equals(move.to)) {
                return true;
            }
        }
        return false;
    }

    public boolean isMate(Color color) {
        if (!isCheck(color)) {
            return false;
        }
        List<Move> moves = getMovesByColor(color);
        for (Move move : moves) {
            Table copy = clone();
            copy.doMove(move, color);
            if (!copy.isCheck(color)) {
                return false;
            }
        }
        return true;
    }

    public boolean isStalemate(Color color) {
        return !isMate(color) && getMovesByColor(color).size() == 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = DEFAULT_SIZE - 1; i >= 0; i--) {
            for (int j = 0; j < DEFAULT_SIZE; j++) {
                Figure f = table[i][j];
                String cur = ".";
                if (f == null) {
                    cur = ".";
                } else {
                    cur = f.getType().getSymbol();
                }
                if (f != null && f.getColor() == Color.BLACK) {
                    cur = cur.toLowerCase();
                }
                sb.append(cur);
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
