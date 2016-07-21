package bot;

import models.*;
import models.figures.Figure;

import java.util.List;
import java.util.Random;

/**
 * Created by Semyon Bessonov on 20.07.2016.
 */
public class Bot extends Player {

    public Bot(String name, Color color) {
        super(name, color);
    }

    public Move nextMove(Table table){

        List<Figure> figureList = table.getFiguresByColor(color);
        Evaluation evaluation = new Evaluation();
        Random randomGenerator = new Random();

        int maxRank  = -90000;

        Move nextMove  =  figureList.get(0).getPossibleMoves().get(0);

        //Not best, but prevent no any move situation

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
    }

    @Override
    public void messageReceiver(String s) {

    }
}
