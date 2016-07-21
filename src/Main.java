import models.*;
import models.figures.Figure;

import java.util.List;

/**
 * Created by Semyon Bessonov on 20.07.2016.
 */
public class Main {

    static int count = 0;
    static int captures = 0;
    static int checks = 0;
    static int mates = 0;
    static boolean captured = false;

    static void dfs(int v, Table table, Color color) {
//        if (v == 0) {
//            System.err.print(table);
//            System.err.println("--------");
//        }
//        if (captured) {
//            System.err.println(table);
//        }
        if (table.isCheck(color)) {
            checks++;
        }
        if (table.isMate(color)) {
            mates++;
        }
        if (v == 0) {
            count++;
            return;
        }
        List<Figure> lift = table.getFiguresByColor(color);
        for (Figure f : lift) {
            List<Move> c = f.getPossibleMoves();
            for (Move co : c) {
                Table t = table.clone();
                if (t.getFigure(co.to) != null) {
                    captures++;
//                    System.err.println("  Begin");
//                    System.err.println(table);
                    captured = true;
                }
                t.doMove(co, color);
                if (color == Color.BLACK) {
                    dfs(v - 1, t, Color.WHITE);
                } else {
                    dfs(v - 1, t, Color.BLACK);
                }
                captured = false;
            }
        }
    }

    public static void main(String[] args){
        Table table = new Table();
        System.out.println(table);
        dfs(5, table, Color.WHITE);
        System.out.printf("Count: %d\n", count);
        System.out.printf("Captures: %d\n", captures);
        System.out.printf("Checks: %d\n", checks);
        System.out.printf("Mates: %d\n", mates);
//        System.out.println(table.doMove(new Move(new Coordinate(6, 3), new Coordinate(4, 3)), Color.BLACK));
//        for (int i = 0; i < 8; i++) {
//            for (int j = 0; j < 8; j++) {
//                if (table.getFigure(i, j) != null) {
//                    System.out.printf("%d%d %s\n", i, j, table.getFigure(i, j).getPossibleMoves());
//                }
//            }
//        }
    }
}
