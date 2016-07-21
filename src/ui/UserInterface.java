package ui;

import models.Table;

import java.util.Scanner;

/**
 * Created by ilnar on 18.07.16.
 */
public interface UserInterface {

    enum MenuAction {
        PLAY_WITH_PLAYER,
        PLAY_WITH_BOT,
        BOT_WITH_BOT,
        SHOW_MATCH_HISTORY,
        EXIT_GAME
    }

    void showTable(Table t);

    void showMessage(String message);

    MenuAction showMenu();

    void showMatchHistory();

}
