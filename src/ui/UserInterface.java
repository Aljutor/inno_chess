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

    /**
     * Shows up the chessboard
     * @param t
     */
    void showTable(Table t);

    /**
     * Output some message to the players
     * @param message
     */
    void showMessage(String message);

    /**
     * Output menu so player can choice game mode, see match history or exit the game
     * @return
     */
    MenuAction showMenu();

    /**
     * Shows match history to the player
     */
    void showMatchHistory();

    /**
     *
     * @param menuAction
     */
    void showNicknameTyperView(MenuAction menuAction);

}
