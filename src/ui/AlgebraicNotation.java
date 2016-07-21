package ui;

import javafx.beans.binding.StringBinding;
import javafx.scene.control.Tab;
import models.Move;
import models.Table;
import models.figures.Figure;
import models.figures.FigureType;

/**
 * Created by Semyon Bessonov on 21.07.2016.
 */
public class AlgebraicNotation {

    private static String columnToString(int n){
        String column = "";
        switch (n){
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
        return column;
    }

     public static String getString(Table table, Move move){

         String prefix = "";
         Figure figure = table.getFigure(move.from);
         switch (figure.getType()){
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

         String column = columnToString(move.to.getC());


         String capture ="";
         if (table.getFigure(move.to) != null){
             if (figure.getType() == FigureType.PAWN){
                 capture = columnToString(move.from.getC()) + "x";
             }else
                 capture = "x";

         }

         return prefix + capture + column + (move.to.getR() +1);
     }
}
