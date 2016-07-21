package models;

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
