package connectfour;

public class InsertCommand {
    private final int piece;
    private final int column;

    public InsertCommand(int piece, int column) {
        this.piece = piece;
        this.column = column;
    }

    int getPiece() { return this.piece; }

    int getColumn() { return this.column; }
}
