import bot.Bot;
import models.Color;
import models.Move;
import models.Player;
import models.Table;

/**
 * Created by Semyon Bessonov on 20.07.2016.
 */
public class Main {

    public static void main(String[] args){
        Bot botA = new Bot(Color.WHITE);
        Bot botB = new Bot(Color.BLACK);

        Table table = new Table(botA, botB);
        Move move;

        for (int i = 0; i < 100; i++){
            table = table.nextTurn();
        }
    }
}
