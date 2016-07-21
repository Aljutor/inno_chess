package ui;

import models.Table;
import models.figures.Figure;

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
                boolean isWhiteCell = (i + j) % 2 == 1;
                if (figures[i][j] == null) {
                    System.out.print(isWhiteCell ? "   " : "[ ]");
                    continue;
                }
                System.out.print((isWhiteCell ? " " : "[") + figures[i][j].getType().toString().charAt(0) + (isWhiteCell ? " " : "]"));
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

    }


}
