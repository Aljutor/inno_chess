package models;

import bot.Bot;
import models.Move;
import models.Player;
import models.Table;
import models.figures.Figure;
import models.figures.FigureType;
import ui.UserInterface;

import java.util.ArrayList;
import java.util.List;

import static ui.UserInterface.*;

/**
 * Created by ilnar on 20.07.16.
 */
public class GameSession {

    private final Table table;

    private final Player whitePlayer;

    private final Player blackPlayer;

    private final UserInterface ui;

    private final List<Move> history = new ArrayList<>();

    private int fiftyMoveRuleCounter = 0;

    public GameSession(UserInterface ui) {
        this.table = new Table();
        this.ui = ui;
        MenuAction menuAction = ui.showMenu();
        switch (menuAction){
            case PLAY_WITH_BOT:
                System.out.println("Please, write you nickname:");
                whitePlayer = new Bot("white", Color.WHITE);
                blackPlayer = new Bot("black", Color.BLACK);
                break;
            case PLAY_WITH_PLAYER:
                System.out.println("Please, write nickname of the first player:");
                whitePlayer = new Bot("white", Color.WHITE);
                System.out.println("Please, write nickname of the second player:");
                blackPlayer = new Bot("black", Color.BLACK);
                break;
            case BOT_WITH_BOT:
            default:
                whitePlayer = new Bot("white", Color.WHITE);
                blackPlayer = new Bot("black", Color.BLACK);
                break;
        }
    }

    public GameResult run() {
        Player current = whitePlayer;
        while (!table.isMate(current.getColor()) && !table.isStalemate(current.getColor()) && fiftyMoveRuleCounter < 100) {
            Move move = current.nextMove(table.clone());
            boolean capture = table.getFigure(move.to) != null;

            if (table.doMove(move, current.getColor())) {
                if (capture || table.getFigure(move.to).getType() == FigureType.PAWN) {
                    fiftyMoveRuleCounter = 0;
                }
                fiftyMoveRuleCounter++;
                history.add(move);
                current = (current.getColor() == Color.WHITE ? blackPlayer : whitePlayer);
            } else {
                current.messageReceiver("Invalid move");
            }
            ui.showTable(table);
        }

        if (table.isMate(current.getColor())) {
            current = (current.getColor() == Color.WHITE ? blackPlayer : whitePlayer);
            ui.showMessage("The end.\n" + current.getColor());
            return new GameResult(whitePlayer.getName(), blackPlayer.getName(), current.getColor());
        }
        ui.showMessage("The end.\nDraw");
        return new GameResult(whitePlayer.getName(), blackPlayer.getName(), null);
    }
}
