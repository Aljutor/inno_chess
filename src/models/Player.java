package models;

/**
 * Created by ilnar on 18.07.16.
 */
public abstract class Player {
    protected String name;
    protected Color color;

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }

    public abstract Move nextMove(Table table);
}
