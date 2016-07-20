package bot;

import models.Color;
import models.Table;
import models.figures.Figure;

import java.util.List;

/**
 * Created by Semyon Bessonov on 20.07.2016.
 */
public class Evaluation {

    private int KING_VALUE   = 20000;
    private int QUEEN_VALUE  = 1710;
    private int ROOK_VALUE   = 824;
    private int BISHOP_VALUE = 572;
    private int KNIGHT_VALUE = 521;
    private int PAWN_VALUE   = 100;



    public int value_rank(List<Figure> figures){
        int rank = 0;

        for (Figure f: figures){
            //Rank by figure value
            switch (f.getType()){
                case KING:
                    rank += KING_VALUE;
                case QUEEN:
                    rank += QUEEN_VALUE;
                case ROOK:
                    rank += ROOK_VALUE;
                case BISHOP:
                    rank += BISHOP_VALUE;
                case KNIGHT:
                    rank += KNIGHT_VALUE;
                case PAWN:
                    rank += PAWN_VALUE;
            }
        }
        return rank;
    }

    public int estimate(Table table){

        int rank = 0, white_rank = 0, black_rank = 0;
        List<Figure> figureList;

        figureList = table.getColoredFigures(Color.WHITE);

        white_rank += value_rank(figureList);

        figureList = table.getColoredFigures(Color.BLACK);

        black_rank += value_rank(figureList);


        switch (table.whoseTurn()){
            case WHITE:
                rank = (white_rank - black_rank);
                break;
            case BLACK:
                rank = (black_rank - white_rank);
                break;
        }

        return rank;
    }
}


