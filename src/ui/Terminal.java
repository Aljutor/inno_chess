package ui;

import models.Table;

import java.util.Scanner;

/**
 * Created by ilnar on 18.07.16.
 */
public class Terminal implements UserInterface {

    @Override
    public void showTable(Table t) {

    }

    @Override
    public void showMessage(String message) {
        System.out.println(message);
    }

    @Override
    public MenuAction showMenu() {

        try(Scanner scanner = new Scanner(System.in)){
            System.out.println("Choose of of the menu actions: (Write only number)");
            System.out.println("1. Play vs Human");
            System.out.println("2. Play vs Bot");
            System.out.println("3. See Bot vs Bot");
            System.out.println("4. Watch the match history");
            System.out.println("5. Exit");
        }

        return null;
    }

    @Override
    public void showMatchHistory() {

    }


}
