package models;

import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Created by Anvar on 21.07.16.
 */
public class GameResult {

    private final String whitePlayerName;

    private final String blackPlayerName;

    private final Color winnerPlayerColor;

    public GameResult(String whitePlayerName, String blackPlayerName, Color winnerPlayerColor) {
        this.whitePlayerName = whitePlayerName;
        this.blackPlayerName = blackPlayerName;
        this.winnerPlayerColor = winnerPlayerColor;
    }

    public void save() {
        try (Writer output = new BufferedWriter(new FileWriter("match_history", true))) {
            output.write(whitePlayerName + "\n");
            output.write(blackPlayerName + "\n");
            output.write(winnerPlayerColor + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static LinkedList<GameResult> loadResults() {
        LinkedList<GameResult> gameResults = new LinkedList<>();

        try (Scanner input = new Scanner(new FileReader("match_history"))) {
            while (input.hasNextLine()) {
                String whitePlayerName = input.nextLine();
                String blackPlayerName = input.nextLine();
                String winnerPlayerColorString = input.nextLine();
                Color winnerPlayerColor = Color.UNDEFINED;

                try {
                    winnerPlayerColor = Color.valueOf(winnerPlayerColorString);
                } catch (IllegalArgumentException iae) {
                    System.err.println("Match history file not in a right format!");
                    return gameResults;
                }

                gameResults.add(new GameResult(whitePlayerName, blackPlayerName, winnerPlayerColor));
            }
        } catch (IOException e) {
            System.err.println("No match history or file is in wrong format!");
        }

        return gameResults;
    }

    public String getWhitePlayerName() {
        return whitePlayerName;
    }

    public String getBlackPlayerName() {
        return blackPlayerName;
    }

    public Color getWinnerPlayerColor() {
        return winnerPlayerColor;
    }
}
