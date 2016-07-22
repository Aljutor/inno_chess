package ui;

import models.*;
import models.figures.Figure;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * Created by ilnar on 18.07.16.
 */
public class Terminal implements UserInterface {

<<<<<<< HEAD
    public static Scanner scanner = new Scanner(System.in);
=======
    long lastShowTime;
>>>>>>> b565d370bb8c02a55e811c73306adce9a67f63b9

    @Override
    public void showTable(Table t) {
        long curTime = System.currentTimeMillis();
        while (curTime - lastShowTime < 1500) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ignored) {
            }
            curTime = System.currentTimeMillis();
        }
        lastShowTime = curTime;
        Figure[][] figures = t.getTable();
        System.out.println();
        System.out.println();
        System.out.print("    ");
        for (int i = 0; i < figures.length; i++)
            System.out.print(" " + (char)(i + 65) + " ");
        System.out.println();
        System.out.println();
        for (int i = figures.length - 1; i >= 0; i--){
            System.out.print((i + 1) + "   ");
            for (int j = 0; j < figures[i].length; j++){
                boolean isWhiteCell = (i + j) % 2 == 1;
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
            System.out.println("   " + (i + 1));
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

//        try(Scanner scanner = this.scanner){
        try {
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
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return MenuAction.EXIT_GAME;
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

//        try (Scanner scanner = new Scanner(System.in)){
        try {
          name = scanner.nextLine();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return name;
    }

    @Override
    public void actionToContinue() {
        System.out.println("Press enter to continue...");

//        try (Scanner scanner = new Scanner(System.in)){
//            scanner.nextLine();
//        }
    }

    public static String makeBold(String str){
        return "\u001B[1m"+ str + (char)27 + "[00;00m";
    }


}
