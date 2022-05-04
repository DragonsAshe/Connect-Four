package connectfour;

import java.util.ArrayList;

public class Board {
    private final ArrayList<ArrayList<String>> board;

    Board(int columns, int rows) {
        this.board = new ArrayList<>();
        for (int i = 0; i < columns; i++) {
            board.add(new ArrayList<>(rows));
        }
    }

    public ArrayList<String> get(int index) {
        return this.board.get(index);
    }

    public String getCell(int column, int row) {
        if (get(column).size() - 1 >= row) {
            return this.board.get(column).get(row);
        }
        return "";
    }

    public int getBoardLength() {
        return this.board.size();
    }

}
