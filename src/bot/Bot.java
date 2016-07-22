package bot;

import models.*;
import models.figures.Figure;

import javax.swing.text.TabExpander;
import java.util.List;
import java.util.Random;

/**
 * Created by Semyon Bessonov on 20.07.2016.
 */
public class Bot extends Player {
    int moveCounter = 0;

    int DEPTH =   3;
    int ALPHA =   -90000000;
    int BETA  =   90000000;

    Color opColor;



    Evaluation evaluation = new Evaluation();
    Random random = new Random();

    public Bot(String name, Color color) {
        super(name, color);

        if (color == Color.WHITE){
            opColor = Color.BLACK;
        }else {
            opColor = Color.WHITE;
        }
    }

    public Move nextMove(Table table){
        this.moveCounter++;
        Move nextMove = null;

        if (this.moveCounter < 2){
            int col = random.nextInt(8);
            if(this.color == Color.WHITE){
                return new Move(new Coordinate(1, col), new Coordinate(2,col));
            }else {
                return new Move(new Coordinate(6, col), new Coordinate(5,col));
            }
        }

        int maxRank  = -900000000;

        List<Figure> figureList = table.getFiguresByColor(color);

        for (Figure f: figureList){
            for (Move m: f.getLegalMoves()){

                Table mTable = table.clone();
                mTable.doMove(m, color);

                int tmp = AlphaBeta(mTable, DEPTH - 1, ALPHA, BETA, this.color);

                if (tmp > maxRank){
                    maxRank  = tmp;
                    nextMove = m;
                }
            }
        }


        if (nextMove == null) {
            System.out.println("{STRANGE NO MOVE FOUND}");
        }

        return nextMove;
    }

    private int AlphaBeta(Table table, int depth, int alpha, int beta, Color color){
        if (depth <= 0 ) {
            return evaluation.estimate(table, color);
        }

        if (color == this.color){
            int score = -900000000;
            for (Figure f: table.getFiguresByColor(color)) {
                for (Move m : f.getLegalMoves()) {

                    Table mTable = table.clone();
                    mTable.doMove(m, color);
                    int tmp = AlphaBeta(mTable, depth - 1, alpha, beta, this.opColor);

                    if (score < tmp) {
                        score = tmp;
                    }

                    if (alpha < score) {
                        alpha = score;
                    }

                    if (alpha >= beta) {
                        return alpha;
                    }
                }
            }
        }else {
            int score = 900000000;
            for (Figure f: table.getFiguresByColor(color)) {
                for (Move m : f.getLegalMoves()) {

                    Table mTable = table.clone();
                    mTable.doMove(m, color);
                    int tmp = AlphaBeta(mTable, depth - 1, alpha, beta, this.color);

                    if (score > tmp) {
                        score = tmp;
                    }

                    if (beta > score) {
                        beta = score;
                    }

                    if (alpha >= beta) {
                        return alpha;
                    }
                }
            }
        }




        return alpha;
    }

    @Override
    public void messageReceiver(String s) {
        System.err.printf("Message: %s\n", s);
    }
}