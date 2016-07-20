import models.*;
import models.figures.Figure;

import java.util.List;

/**
 * Created by Semyon Bessonov on 20.07.2016.
 */
public class Main {

    static int count = 0;
    static int captures = 0;
    static boolean captured = false;

    static void dfs(int v, Table table, Color color) {
//        if (v == 0) {
//            System.err.print(table);
//            System.err.println("--------");
//        }
//        if (captured) {
//            System.err.println(table);
//        }
        if (v == 0) {
            count++;
            return;
        }
        List<Figure> lift = table.getColoredFigures(color);
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
        dfs(4, table, Color.WHITE);
        System.out.println(count);
        System.out.println(captures);
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
