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



    public Move nextMove(Table table){

        List<Figure> figureList = table.getFiguresByColor(color);
        Move nextMove = null;

        if (moveCounter < 5){ //First 4 stating moves will be random;
            Random randomGenerator = new Random();

            if (moveCounter == 0){
                int pawnPosition = randomGenerator.nextInt(8);
                if (this.color == color.WHITE)
                {
                    return new Move(new Coordinate(1, pawnPosition), new Coordinate(4, pawnPosition));
                }else{
                    return new Move(new Coordinate(6, pawnPosition), new Coordinate(4, pawnPosition));
                }
            }


            boolean found = false;
            while (!found){
                List<Move> movies = figureList.get(randomGenerator.nextInt(figureList.size())).getPossibleMoves();
                if (movies.size() > 0){
                    found = true;
                    return movies.get(randomGenerator.nextInt(movies.size()));
                }
            }
        }

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

        this.moveCounter++;
        return nextMove;
    }

    @Override
    public void messageReceiver(String s) {

    }
}
