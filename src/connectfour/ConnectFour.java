package connectfour;

import java.util.ArrayList;
import java.util.Collections;

public class ConnectFour implements ConnectFourGame{
    private final Board board;

    public ConnectFour(int columns, int rows) {
        this.board = new Board(columns, rows);
    }

    // Method to insert a piece into the column that is specified
    public void insert(int position, String localPlayerName) {
        if (board.get(position - 1).size() != 6) {
            board.get(position - 1).add(localPlayerName);
        } // Throw Exception here
    }

    public boolean win(String localPlayerName){
        ArrayList<Integer> heights = new ArrayList<>();
        for (int i = 0; i < this.board.getBoardLength(); i++) {
            heights.add(this.board.get(i).size());
        }
        return horizontalWin(heights, localPlayerName) || verticalWin(localPlayerName) || ascendingDiagonalWin(localPlayerName) || descendingDiagonalWin(localPlayerName);
    }

    private boolean horizontalWin(ArrayList<Integer> heights, String localPlayerName) {
        int length = board.getBoardLength();

        for (int i = 0; i <= length - 4; i++) {
            for (int j = 0; j < Collections.min(heights.subList(i, i + 3)); j++) {
                if (this.board.getCell(i, j).equals(localPlayerName) && this.board.getCell(i + 1, j).equals(localPlayerName) && this.board.getCell(i + 2, j).equals(localPlayerName) && this.board.getCell(i + 3, j).equals(localPlayerName)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean verticalWin(String localPlayerName) {
        int length = this.board.getBoardLength();

        for (int i = 0; i <= length - 1; i++) {
            for (int j = 0; j <= this.board.get(i).size() - 4; j++) {
                if (this.board.getCell(i, j).equals(localPlayerName) && this.board.getCell(i, j + 1).equals(localPlayerName) && this.board.getCell(i, j + 2).equals(localPlayerName) && this.board.getCell(i, j + 3).equals(localPlayerName)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean ascendingDiagonalWin(String localPlayerName) {
        int length = this.board.getBoardLength();

        for (int i = 3; i <= length - 1; i++) {
            for (int j = 0; j <= this.board.get(i).size() - 1; j++) {
                if (this.board.getCell(i, j).equals(localPlayerName) && this.board.getCell(i - 1, j + 1).equals(localPlayerName) && this.board.getCell(i - 2, j + 2).equals(localPlayerName) && this.board.getCell(i - 3, j + 3).equals(localPlayerName)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean descendingDiagonalWin(String localPlayerName) {
        int length = this.board.getBoardLength();

        for (int i = 3; i <= length - 1; i++) {
            for (int j = 3; j <= this.board.get(i).size() - 1; j++) {
                if (this.board.getCell(i, j).equals(localPlayerName) && this.board.getCell(i - 1, j - 1).equals(localPlayerName) && this.board.getCell(i - 2, j - 2).equals(localPlayerName) && this.board.getCell(i - 3, j - 3).equals(localPlayerName)) {
                    return true;
                }
            }
        }
        return false;
    }

    public String boardToString() {
        StringBuilder b = new StringBuilder();
        for(int row = 5; row >= 0; row--) {
            b.append("| ");
            for(int column = 0; column < 7; column++) {
                if(board.get(column).size()-1 < row) {
                    b.append("0 | ");
                }else{
                    b.append(board.get(column).get(row)).append(" | ");
                }
            }
            b.append("\n");
        }
        return b.toString();
    }
}

class Board {
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