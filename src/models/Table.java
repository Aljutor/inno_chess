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
        this(new String[]{
                "rnbqkbnr",
                "pppppppp",
                "........",
                "........",
                "........",
                "........",
                "PPPPPPPP",
                "RNBQKBNR"
        });
    }

    public Table(String[] board) {
        table = new Figure[DEFAULT_SIZE][];
        for (int i = DEFAULT_SIZE - 1; i >= 0; i--) {
            table[i] = new Figure[DEFAULT_SIZE];
            for (int j = 0; j < DEFAULT_SIZE; j++) {
                Color c = Character.isUpperCase(board[i].charAt(j)) ? Color.WHITE : Color.BLACK;
                switch (Character.toUpperCase(board[i].charAt(j))) {
                    case 'R':
                        table[i][j] = new Rook(this, c);
                        break;
                    case 'B':
                        table[i][j] = new Bishop(this, c);
                        break;
                    case 'K':
                        table[i][j] = new King(this, c);
                        break;
                    case 'N':
                        table[i][j] = new Knight(this, c);
                        break;
                    case 'P':
                        table[i][j] = new Pawn(this, c);
                        break;
                    case 'Q':
                        table[i][j] = new Queen(this, c);
                        break;
                }
                if (table[i][j] != null) {
                    table[i][j].setCoor(new Coordinate(i, j));
                }
            }
        }
    }

    private Table(Figure[][] table) {
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

    /**
     * Don't use this method. Use {@link #checkCoordinate(Coordinate)} instead.
     * @param r
     * @param c
     * @param color
     * @return
     */
    public boolean checkMoveDumb(int r, int c, Color color) {
        if (0 <= r && r < Table.DEFAULT_SIZE && 0 <= c && c < Table.DEFAULT_SIZE) {
            if (table[r][c] == null || table[r][c].getColor() != color) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if {@code coor} firs the table.
     * @param coor
     * @return
     */
    private boolean checkCoordinate(Coordinate coor) {
        if (0 <= coor.getR() && coor.getR() < Table.DEFAULT_SIZE) {
            if (0 <= coor.getC() && coor.getC() < Table.DEFAULT_SIZE) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check is {@code move} is valid for {@code color}.
     *
     * {@link Move} is valid, if it fits table size
     * and {@link #isCheck(Color)} will be false.
     * @param move
     * @param color
     * @return
     */
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

    @Override
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

    /**
     * Tries to make {@code move}.
     * @param move move to be applied.
     * @param color whose turn is now
     * @return true is valid move was applied, false otherwise.
     * @see Move
     */
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

    /**
     * Get all figures list with given {@code color}.
     * @param color
     * @return
     */
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

    /**
     * Get all pseudo-legal moves for {@code color}.
     * <p>
     * @param color Player's color
     * @return list of all moves available
     */
    public List<Move> getMovesByColor(Color color) {
        List<Move> result = new ArrayList<>();
        getFiguresByColor(color).forEach(figure -> {
            result.addAll(figure.getPseudoLegalMoves());
        });
        return result;
    }

    /**
     * Get first figure with given {@code color} and {@code type}.
     * @param color figure's color
     * @param type figure's type
     * @return {@link null} is no figure on table, else {@link Figure} itself.
     */
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

    /**
     * Check there is check for {@code color} player.
     * <p>
     * Check is a game position, when {@link King} is
     * under attack, but threat can be removed.
     * @param color Player's color
     * @return
     */
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

    /**
     * Check there is checkmate for {@code color} player.
     * <p>
     * Checkmate is a game position, when {@link King} is
     * under attack and there is no way to remove the threat.
     * @param color Player's color
     * @return
     */
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

    /**
     * Check if player {@code color}> has stalemate or not.
     * <p>
     * Stalemate is game position, when {@code color} player
     * has no valid moves and there is {@link #isCheck(Color)} is false.
     * @param color Player's color
     * @return
     */
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
