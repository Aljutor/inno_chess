package models;

import models.Move;
import models.Player;
import models.Table;

/**
 * Created by ilnar on 20.07.16.
 */
public class Controller {

    private final Table table;

    private final Player whitePlayer;

    private final Player blackPlayer;

    public Controller(Table table, Player whitePlayer, Player blackPlayer) {
        this.table = table;
        this.whitePlayer = whitePlayer;
        this.blackPlayer = blackPlayer;
    }



    public void run() {

    }
}
