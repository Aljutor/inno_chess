package models.figures;

public enum FigureType {
    PAWN("P"), BISHOP("B"), KNIGHT("N"), ROOK("R"), QUEEN("Q"), KING("K");

    String symbol;

    FigureType(String symbol){
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
