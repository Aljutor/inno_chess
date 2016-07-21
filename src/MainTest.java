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

        for (int n = 1; n < 100; n++){
            Move moveA = botA.nextMove(table.clone());
            String moveStringA = AlgebraicNotation.getString(table,moveA);
            table.doMove(moveA,Color.WHITE);


            Move moveB = botB.nextMove(table.clone());
            String moveStringB = AlgebraicNotation.getString(table,moveB);
            table.doMove(moveB,Color.BLACK);

            System.out.println(n +". "
                    + moveStringA   /*+ " {" + moveA.from.getC() +":" + moveA.from.getR() + " - " + moveA.to.getC() +":" + moveA.to.getR() + "}" */
                    + " "
                    + moveStringB   /*+  " {" + moveB.from.getC() +":" + moveB.from.getR() + " - " + moveB.to.getC() +":" + moveB.to.getR() + "}" */
                    + " "
            )
            ;
        }

    }
}
