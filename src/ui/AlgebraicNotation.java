package ui;

import models.Coordinate;
import models.Move;
import models.Table;
import models.figures.Figure;
import models.figures.FigureType;

/**
 * Created by Semyon Bessonov on 21.07.2016.
 */
public class AlgebraicNotation {

    public static String colToString(int i){
        String column = "";
        switch (i){
            case 0:
                column = "a";
                break;
            case 1:
                column = "b";
                break;
            case 2:
                column = "c";
                break;
            case 3:
                column = "d";
                break;
            case 4:
                column = "e";
                break;
            case 5:
                column = "f";
                break;
            case 6:
                column = "g";
                break;
            case 7:
                column = "h";
                break;
        }
        return  column;
    }

    public static String coorToString(Coordinate coor){
        return colToString(coor.getC()) + (coor.getR()+1);
    }

    public static String figurePrefix(Figure figure) {
        return figureTypePrefix(figure.getType());
    }

    public static String figureTypePrefix(FigureType figureType){
        String prefix = "";
        switch (figureType){
            case KING:
                prefix = "K";
                break;
            case QUEEN:
                prefix = "Q";
                break;
            case ROOK:
                prefix = "R";
                break;
            case BISHOP:
                prefix = "B";
                break;
            case KNIGHT:
                prefix = "N";
                break;
            case PAWN:
                prefix = "";
                break;
        }
        return prefix;

    }

     public static String getString(Table table, Move move){
         String prefix =  figurePrefix(table.getFigure(move.from));

         //Pawn promotion
         if (move.castPawn != null){
             String castPrefix = figureTypePrefix(move.castPawn);
             if (table.getFigure(move.to) != null){
                 return prefix+colToString(move.from.getC()) + "x"+coorToString(move.to)+"="+castPrefix;
             }
             return prefix+coorToString(move.to)+"="+castPrefix;
         }

         //Capture
         if (table.getFigure(move.to) != null){
             if (table.getFigure(move.from).getType() == FigureType.PAWN){
                 return prefix+colToString(move.from.getC())+"x"+coorToString(move.to);
             }

             return prefix+"x"+coorToString(move.to);
         }
         return prefix+ coorToString(move.to);
     }
}
