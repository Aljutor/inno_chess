package bot;

import models.Color;
import models.Move;
import models.Table;
import models.figures.Figure;

import java.util.List;

/**
 * Created by Semyon Bessonov on 20.07.2016.
 */
public class Evaluation {

    //Pice values
    private int KING_VALUE   = 20000;
    private int QUEEN_VALUE  = 900;
    private int ROOK_VALUE   = 500;
    private int BISHOP_VALUE = 300;
    private int KNIGHT_VALUE = 300;
    private int PAWN_VALUE   = 100;

    //Values for mobility
    private int KING_MOB   = 1;
    private int QUEEN_MOB  = 2;
    private int ROOK_MOB   = 4;
    private int BISHOP_MOB = 4;
    private int KNIGHT_MOB = 2;
    private int PAWN_MOB   = 1;

    private int ROOK_7TH_LINE = 25;

    public int valRank(Figure figure){
        int rank = 0;
            switch (figure.getType()){
                case KING:
                    rank += KING_VALUE;
                    break;
                case QUEEN:
                    rank += QUEEN_VALUE;
                    break;
                case ROOK:
                    rank += ROOK_VALUE;
                    break;
                case BISHOP:
                    rank += BISHOP_VALUE;
                    break;
                case KNIGHT:
                    rank += KNIGHT_VALUE;
                    break;
                case PAWN:
                    rank += PAWN_VALUE;
                    break;
            }
        return rank;
    }

    public int posRank(Figure figure, Color color){
        int rank = 0;

        int[][] pawnTable = new int[8][8]; //Table for pawns positions
        pawnTable[7] = new int[]{0,   0,   0,    0,   0,   0,   0,  0};
        pawnTable[6] = new int[]{50,  50,  50,   50,  50,  50,  50, 50};
        pawnTable[5] = new int[]{10,  10,  20,   30,  30,  20,  10, 10};
        pawnTable[4] = new int[]{5,   5,   10,   25,  25,  10,  5,  5};
        pawnTable[3] = new int[]{0,   0,   0,    20,  20,  0,   0,  0};
        pawnTable[2] = new int[]{5,   -5,  -10,  0,   0,   -10, -5, 5};
        pawnTable[1] = new int[]{5,   10,  10,   -20, -20, 10,  10, 5};
        pawnTable[0] = new int[]{0,   0,   0,    0,   0,   0,   0,  0};


        int[][] knightTable = new int[8][8]; //Table for knights positions
        knightTable[7] = new int[]{-50,-40,-30,-30,-30,-30,-40,-50,};
        knightTable[6] = new int[]{-40,-20,  0,  0,  0,  0,-20,-40,};
        knightTable[5] = new int[]{-30,  0, 10, 15, 15, 10,  0,-30,};
        knightTable[4] = new int[]{-30,  5, 15, 20, 20, 15,  5,-30,};
        knightTable[3] = new int[]{-30,  0, 15, 20, 20, 15,  0,-30,};
        knightTable[2] = new int[]{-30,  5, 10, 15, 15, 10,  5,-30,};
        knightTable[1] = new int[]{-40,-20,  0,  5,  5,  0,-20,-40,};
        knightTable[0] = new int[]{-50,-40,-30,-30,-30,-30,-40,-50,};

        int[][]bishopTable = new int[8][8]; //Table for bishops positions
        bishopTable[7] = new int[]{-20,-10,-10,-10,-10,-10,-10,-20,};
        bishopTable[6] = new int[]{-10,  0,  0,  0,  0,  0,  0,-10,};
        bishopTable[5] = new int[]{-10,  0,  5, 10, 10,  5,  0,-10,};
        bishopTable[4] = new int[]{-10,  5,  5, 10, 10,  5,  5,-10,};
        bishopTable[3] = new int[]{-10,  0, 10, 10, 10, 10,  0,-10,};
        bishopTable[2] = new int[]{-10, 10, 10, 10, 10, 10, 10,-10,};
        bishopTable[1] = new int[]{-10,  5,  0,  0,  0,  0,  5,-10,};
        bishopTable[0] = new int[]{-20,-10,-10,-10,-10,-10,-10,-20,};

        int[][]kingMiddleTable = new int[8][8]; //Table for king positions in middle game
        kingMiddleTable[7] = new int[]{-30,-40,-40,-50,-50,-40,-40,-30,};
        kingMiddleTable[6] = new int[]{-30,-40,-40,-50,-50,-40,-40,-30,};
        kingMiddleTable[5] = new int[]{-30,-40,-40,-50,-50,-40,-40,-30,};
        kingMiddleTable[4] = new int[]{-30,-40,-40,-50,-50,-40,-40,-30,};
        kingMiddleTable[3] = new int[]{-20,-30,-30,-40,-40,-30,-30,-20,};
        kingMiddleTable[2] = new int[]{-10,-20,-20,-20,-20,-20,-20,-10,};
        kingMiddleTable[1] = new int[]{ 20, 20,  0,  0,  0,  0, 20, 20,};
        kingMiddleTable[0] = new int[]{ 20, 30, 10,  0,  0, 10, 30, 20};

        int[][]kingEndTable = new int[8][8]; //Table for king positions in end game
        kingEndTable[7] = new int[]{-20,-10,-10,-10,-10,-10,-10,-20,};
        kingEndTable[6] = new int[]{-10,  0,  0,  0,  0,  0,  0,-10,};
        kingEndTable[5] = new int[]{-10,  0,  5, 10, 10,  5,  0,-10,};
        kingEndTable[4] = new int[]{-10,  5,  5, 10, 10,  5,  5,-10,};
        kingEndTable[3] = new int[]{-10,  0, 10, 10, 10, 10,  0,-10,};
        kingEndTable[2] = new int[]{-10, 10, 10, 10, 10, 10, 10,-10,};
        kingEndTable[1] = new int[]{-10,  5,  0,  0,  0,  0,  5,-10,};
        kingEndTable[0] = new int[]{-20,-10,-10,-10,-10,-10,-10,-20,};

        int r = figure.getCoor().getR();
        int c = figure.getCoor().getC();

        int movies = figure.getLegalMoves().size();

        //Reverse coordinates for black
        if (color == Color.BLACK){
            r =(7 - r);
        }

        switch (figure.getType()){
            case KING:
                rank += kingMiddleTable[r][c] + movies * KING_MOB;
                break;
            case QUEEN:
                rank += 0 + movies * QUEEN_MOB;
                break;
            case ROOK:
                if (r == 6){
                    rank += ROOK_7TH_LINE;
                }
                rank += movies * ROOK_MOB;
                break;
            case BISHOP:
                rank += bishopTable[r][c] + movies * BISHOP_MOB;
                break;
            case KNIGHT:
                rank += knightTable[r][c] + movies * KING_MOB;
                break;
            case PAWN:
                rank += pawnTable[r][c]  + movies * PAWN_MOB;
                break;
        }

        return  rank;
    }


    public int estimate(Table table, Color color){

        int rank = 0, whiteRank = 0 , blackRank = 0;
        for (Figure f: table.getFiguresByColor(Color.WHITE)){
            whiteRank += valRank(f) + posRank(f, Color.WHITE);
        }

        for (Figure f: table.getFiguresByColor(Color.BLACK)){
            blackRank += valRank(f) + posRank(f, Color.BLACK);
        }

        rank = whiteRank - blackRank;

        if (color == Color.WHITE){
            return rank;
        }else{
            return -rank;
        }
    }
}


