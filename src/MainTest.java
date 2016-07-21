import bot.Bot;
import models.*;
import ui.AlgebraicNotation;

/**
 * Created by Semyon Bessonov on 21.07.2016.
 */
public class MainTest {
    public static void main(String[] args){

        Player botA = new Bot("Bot A", Color.WHITE);
        Player botB = new Bot("Bot B", Color.BLACK);

        Table table = new Table();

        Controller controller = new Controller(table, botA, botB);

        System.out.println("" +
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

            System.out.print(n +"." +AlgebraicNotation.getString(table,moveA) + "{" +  AlgebraicNotation.coorToString(moveA.from) +"}");


            table.doMove(moveA,Color.WHITE);

            if (table.isMate(Color.BLACK)){
                System.exit(0);
            }

            Move moveB = botB.nextMove(table.clone());

            System.out.print(" "    +AlgebraicNotation.getString(table,moveB) +  "{" +  AlgebraicNotation.coorToString(moveB.from)  +"}" + "\n");

            table.doMove(moveB,Color.BLACK);

            if (table.isMate(Color.WHITE)){
                System.exit(0);
            }
        }
    }
}
