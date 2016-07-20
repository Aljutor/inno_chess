package bot;

import models.*;
import models.figures.Figure;

import java.util.List;
import java.util.Random;

/**
 * Created by Semyon Bessonov on 20.07.2016.
 */
public class Bot{
    protected Color color;

    public Move nextMove(Table table){

        List<Figure> figureList = table.getColoredFigures(color);
        Evaluation evaluation = new Evaluation();
        Random randomGenerator = new Random();

        int        maxRank  = -90000;
        //Use this as rank can be negative, so start value should be very low to prevent suck

        //Getting random move to prevent no move situation;
        int rnd_indexFigures = randomGenerator.nextInt(figureList.size());
        int rnd_indexMoves   = randomGenerator.nextInt(figureList.get(rnd_indexFigures).getPossibleMoves().size());

        Coordinate nextMove  =  figureList.get(rnd_indexFigures).getPossibleMoves().get(rnd_indexMoves);
        Coordinate startMove =  figureList.get(rnd_indexFigures).getCoor();

        //Not best, but prevent no any move situation

        for (Figure f: figureList){
            Coordinate position = f.getCoor();

            List<Coordinate> coordinates = f.getPossibleMoves();

            for (Coordinate c: coordinates){
                Table new_table = table.doMove(position, c);
                int rank = evaluation.estimate(new_table);

                if (rank > maxRank){
                    nextMove  = c;
                    startMove = position;
                }
            }
        }
        return new Move(startMove, nextMove);
    }
}
