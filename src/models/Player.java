package models;

/**
 * Created by ilnar on 18.07.16.
 */
public abstract class Player {
    protected final String name;
    protected final Color color;

    protected Player(String name, Color color) {
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }

    public abstract Move nextMove(Table table);
    public abstract void messageReceiver(String s);
}
