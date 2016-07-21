package models;

import models.Move;
import models.Player;
import models.Table;
import ui.UserInterface;

import static ui.UserInterface.*;

/**
 * Created by ilnar on 20.07.16.
 */
public class GameSession {

    private final Table table;

    private final Player whitePlayer;

    private final Player blackPlayer;

    private final UserInterface ui;

    public GameSession(Table table, Player whitePlayer, Player blackPlayer, UserInterface ui) {
        this.table = table;
        this.whitePlayer = whitePlayer;
        this.blackPlayer = blackPlayer;
        this.ui = ui;
    }

    public GameResult run() {
        MenuAction menuAction = ui.showMenu();

        switch (menuAction){
            case PLAY_WITH_BOT:
                System.out.println("Please, write you nickname:");
//                whitePlayer.setName(ui.showNicknameTyperView());
                blackPlayer.setName("BOT");
                break;
            case PLAY_WITH_PLAYER:
                System.out.println("Please, write nickname of the first player:");
//                whitePlayer.setName(ui.showNicknameTyperView());
                System.out.println("Please, write nickname of the second player:");
//                blackPlayer.setName(ui.showNicknameTyperView());
                break;
            case BOT_WITH_BOT:
                whitePlayer.setName("BOT WHITE");
                blackPlayer.setName("BOT BLACK");
                break;
        }

        Color winnerPlayerColor = Color.BLACK;

        return new GameResult(whitePlayer.getName(), blackPlayer.getName(), winnerPlayerColor);
    }
}
