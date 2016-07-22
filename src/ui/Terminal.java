package ui;

import models.*;
import models.figures.Figure;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * Created by ilnar on 18.07.16.
 */
public class Terminal implements UserInterface {

    @Override
    public void showTable(Table t) {
        Figure[][] figures = t.getTable();
        System.out.println();
        System.out.println();
        System.out.print("    ");
        for (int i = 0; i < figures.length; i++)
            System.out.print(" " + (char)(i + 65) + " ");
        System.out.println();
        System.out.println();
        for (int i = 0; i < figures.length; i++){
            System.out.print(figures.length - i + "   ");
            for (int j = 0; j < figures[i].length; j++){
                boolean isWhiteCell = (i + j) % 2 == 0;
                if (figures[i][j] == null) {
                    System.out.print(isWhiteCell ? "   " : "[ ]");
                    continue;
                }

                String symbol = figures[i][j].getType().getSymbol();
                if (figures[i][j].getColor() == Color.BLACK)
                    symbol = makeBold(symbol);

                if (isWhiteCell){
                    System.out.print(" " + symbol + " ");
                } else {
                    System.out.print("[" + symbol + "]");
                }

            }
            System.out.println("   " + (figures.length - i));
        }
        System.out.println();
        System.out.print("    ");
        for (int i = 0; i < figures.length; i++)
            System.out.print(" " + (char)(i + 65) + " ");
        System.out.println();
    }

    @Override
    public void showMessage(String message) {
        System.out.println(message);
    }

    @Override
    public MenuAction showMenu() {

        try(Scanner scanner = new Scanner(System.in)){
            printChoiceOptions();

            while (true){
                switch (scanner.nextLine()){
                    case "1":
                        return MenuAction.PLAY_WITH_PLAYER;
                    case "2":
                        return MenuAction.PLAY_WITH_BOT;
                    case "3":
                        return MenuAction.BOT_WITH_BOT;
                    case "4":
                        return MenuAction.SHOW_MATCH_HISTORY;
                    case "5":
                        return MenuAction.EXIT_GAME;
                    default:
                        printChoiceOptions();
                        break;
                }
            }
        }
    }

    private void printChoiceOptions(){
        System.out.println("Choose of of the menu actions: (Write only number [1-5])");
        System.out.println("1. Play vs Human");
        System.out.println("2. Play vs Bot");
        System.out.println("3. See Bot vs Bot");
        System.out.println("4. Watch the match history");
        System.out.println("5. Exit");
    }

    @Override
    public void showMatchHistory() {
        LinkedList<GameResult> gameResults = GameResult.loadResults();

        System.out.println("Match History:\n");

        for (GameResult gameResult: gameResults){
            System.out.println("White: " + gameResult.getWhitePlayerName());
            System.out.println("Black: " + gameResult.getBlackPlayerName());
            System.out.println("Winner: " + gameResult.getWinnerPlayerColor());
            System.out.println();
        }
    }

    @Override
    public String showNicknameTypeView() {
        String name = "";

        try (Scanner scanner = new Scanner(System.in)){
          name = scanner.nextLine();
        }

        return name;
    }

    @Override
    public Move showMoveTypeView() { // a2 - 10, h7 - 67
        Move move = null;

        String fromCoordinates = "";
        String toCoordinates = "";

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                try {
                    System.out.println("Write where do you want to move FROM: (Example: f5)");
                    fromCoordinates = scanner.nextLine();
                    System.out.println("Write where do you want to move TO: (Example: f5)");
                    toCoordinates = scanner.nextLine();

                    move = new Move(new Coordinate(fromCoordinates), new Coordinate(toCoordinates));
                    break;
                } catch (IllegalArgumentException iae) {
                    System.out.println("You can't choice the cell that is not in the board!");
                }
            }
        }

        return move;
    }

    @Override
    public void actionToContinue() {
        System.out.println("Press enter to continue...");

        try (Scanner scanner = new Scanner(System.in)){
            scanner.nextLine();
        }
    }

    public static String makeBold(String str){
        return "\u001B[1m"+ str + (char)27 + "[00;00m";
    }


}
