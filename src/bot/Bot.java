package bot;

import models.*;
import models.figures.Figure;

import java.util.List;
import java.util.Random;

/**
 * Created by Semyon Bessonov on 20.07.2016.
 */
public class Bot extends Player {
    int moveCounter = 0;
    public Bot(String name, Color color) {

        super(name, color);
    }

    static Random random;

    public Move nextMove(Table table){
        this.moveCounter++;
        List<Figure> figureList = table.getFiguresByColor(color);
        Move nextMove = null;






        
        if (random == null) {
            long time = System.currentTimeMillis();
            //time = 1469126667948L;
            System.out.printf("{Seed: %d}\n", time);
            random = new Random(time);
        }

        while (true){
            Figure f =  figureList.get(random.nextInt(figureList.size()));
            List<Move> moves = f.getPossibleMoves();
            if (moves.size() > 0) {

                Move move = moves.get(random.nextInt(moves.size()));
                if (move.to.getR() == move.from.getR() && move.to.getC() == move.from.getC()){
                    System.out.println("WTF");
                }
                return move;

            }
        }

        /*



        Evaluation evaluation = new Evaluation();
        int maxRank  = -90000; //Not best, but prevent no any move situation
        for (Figure f: figureList){
            Coordinate position = f.getCoor();
            List<Move> moves = f.getPossibleMoves();

            for (Move move: moves){
                Table newTable = table.clone();

                newTable.doMove(move, this.getColor());

                if (evaluation.estimate(newTable, this.color) > maxRank){
                    nextMove = move;
                }
            }
        }


        return nextMove;
        */

    }

    @Override
    public void messageReceiver(String s) {

    }
}
