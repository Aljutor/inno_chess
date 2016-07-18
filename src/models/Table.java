package models;

import javafx.util.Pair;

/**
 * Created by ilnar on 18.07.16.
 */
public class Table {
    public static final int DEFAULT_SIZE = 8;
    private int[][] table;

    private int whoseTurn;

    private Player firstPlayer;

    private Player secondPlayer;

    public Table(Player firstPlayer, Player secondPlayer) {
        table = new int[DEFAULT_SIZE][];
        table[0] = new int[]{Consts.ROOK, Consts.KNIGHT, Consts.BISHOP, Consts.KING, Consts.QUEEN, Consts.BISHOP, Consts.KNIGHT, Consts.ROOK};
        table[1] = new int[]{Consts.PAWN, Consts.PAWN, Consts.PAWN, Consts.PAWN, Consts.PAWN, Consts.PAWN, Consts.PAWN, Consts.PAWN};
        table[2] = new int[DEFAULT_SIZE];
        table[3] = new int[DEFAULT_SIZE];
        table[4] = new int[DEFAULT_SIZE];
        table[5] = new int[DEFAULT_SIZE];
        table[6] = new int[]{Consts.PAWN, Consts.PAWN, Consts.PAWN, Consts.PAWN, Consts.PAWN, Consts.PAWN, Consts.PAWN, Consts.PAWN};
        table[7] = new int[]{Consts.ROOK, Consts.KNIGHT, Consts.BISHOP, Consts.KING, Consts.QUEEN, Consts.BISHOP, Consts.KNIGHT, Consts.ROOK};
        for (int i = 6; i < 8; i++) {
            for (int j = 0; j < table[i].length; j++) {
                table[i][j] |= Consts.BLACK;
            }
        }

        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
    }

    public int[][] getTable() {
        return table;
    }

    public int getFigure(int r, int c) {
        return table[r][c];
    }

    public int whoseTurn() {
        return whoseTurn;
    }

    public void nextTurn() {
        if (whoseTurn == Consts.WHITE) {
            firstPlayer.turn();
            whoseTurn = Consts.BLACK;
        } else {
            secondPlayer.turn();
            whoseTurn = Consts.WHITE;
        }
    }

    public boolean checkMove(int r, int c) {
        if (0 <= r && r < Table.DEFAULT_SIZE && 0 <= c && c < Table.DEFAULT_SIZE) {
            if (getFigure(r, c) != whoseTurn() || getFigure(r, c) == Consts.EMPTY) {
                return true;
            }
        }
        return false;
    }
}
