package models;

import ui.Terminal;

import java.util.Scanner;

/**
 * Created by Anvar on 22.07.16.
 */
public class HumanPlayer extends Player {

    protected HumanPlayer(String name, Color color) {
        super(name, color);
    }

    @Override
    public Move nextMove(Table table) {
        Move move = null;

        String fromCoordinates = "";
        String toCoordinates = "";

//        try (Scanner scanner = new Scanner(System.in)) {
        try {
            while (true) {
                try {
                    System.out.println("Write where do you want to move FROM: (Example: f5)");
                    fromCoordinates = Terminal.scanner.nextLine();
                    System.out.println("Write where do you want to move TO: (Example: f5)");
                    toCoordinates = Terminal.scanner.nextLine();

                    move = new Move(new Coordinate(fromCoordinates), new Coordinate(toCoordinates));
                    break;
                } catch (IllegalArgumentException iae) {
                    System.out.println("You can't choice the cell that is not in the board!");
                }
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }

        return move;
    }

    @Override
    public void messageReceiver(String s) {
        System.err.println("Message: " + s);
    }
}
