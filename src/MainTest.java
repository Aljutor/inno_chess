import bot.Bot;
import models.*;
import ui.AlgebraicNotation;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

/**
 * Created by Semyon Bessonov on 21.07.2016.
 */
public class MainTest {
    public static void main(String[] args){

        Player botA = new Bot("Bot A", Color.WHITE);
        Player botB = new Bot("Bot B", Color.BLACK);

        Table table = new Table();

        Controller controller = new Controller(table, botA, botB);

        PrintWriter out = null;
        PrintWriter pgn = null;
        try {
            out = new PrintWriter(new FileOutputStream("output.txt"));
            pgn = new PrintWriter(new FileOutputStream("output.pgn"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        pgn.println("" +
                "[Event \"\"]\n" +
                "[Site \"\"]\n" +
                "[Date \"\"]\n" +
                "[Round \"\"]\n" +
                "[White \"\"]\n" +
                "[Black \"\"]\n" +
                "[Result \"\"]"
        );

        for (int n = 1; n < 1000; n++){
            Move moveA = botA.nextMove(table.clone());

            pgn.print(n +"." +AlgebraicNotation.getString(table,moveA) + "{" +  AlgebraicNotation.coorToString(moveA.from) +"}");

            table.doMove(moveA,Color.WHITE);

            out.printf("Turn: %d\n", n);
            out.println(table);

            if (table.isMate(Color.BLACK)){
                break;
            }

            Move moveB = botB.nextMove(table.clone());

            pgn.print(" "    +AlgebraicNotation.getString(table,moveB) +  "{" +  AlgebraicNotation.coorToString(moveB.from)  +"}" + "\n");

            table.doMove(moveB,Color.BLACK);

            out.println(table);
            if (table.isMate(Color.WHITE)){
                break;
            }
        }
        out.close();
        pgn.close();
    }
}
