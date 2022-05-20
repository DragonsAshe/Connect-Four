package connectfour;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class ConnectFour implements ConnectFourGame{
    private final Board board;
    private final HashMap<Integer, String> IDToPlayerName = new HashMap<>();
    private Status status = Status.INITIALIZED;

    public ConnectFour(int columns, int rows, String localPlayerName) {
        this.board = new Board(columns, rows);

        this.IDToPlayerName.put(1, localPlayerName);
    }

    @Override
    public void setEnemy(String partnerName) {
        this.IDToPlayerName.put(2, partnerName);
    }

    // Method to insert a piece into the column that is specified
    public void insert(int position) throws GameException{
        if (this.status != Status.ENDED) {
            if (board.get(position - 1).size() != 6) {
                int localPlayerID = 1;
                board.get(position - 1).add(localPlayerID);
            } else {
                throw new GameException("Given Column not on board");
            }
        }
    }

    public boolean win(int localPlayerName){
        ArrayList<Integer> heights = new ArrayList<>();
        for (int i = 0; i < this.board.getBoardLength(); i++) {
            heights.add(this.board.get(i).size());
        }
        if (horizontalWin(heights, localPlayerName) || verticalWin(localPlayerName) || ascendingDiagonalWin(localPlayerName) || descendingDiagonalWin(localPlayerName)){
            this.status = Status.ENDED;
            return true;
        }
        return false;
    }

    private boolean horizontalWin(ArrayList<Integer> heights, int localPlayerName) {
        int length = board.getBoardLength();

        for (int i = 0; i <= length - 4; i++) {
            for (int j = 0; j < Collections.min(heights.subList(i, i + 3)); j++) {
                if (this.board.getCell(i, j) == (localPlayerName) && this.board.getCell(i + 1, j) == (localPlayerName) && this.board.getCell(i + 2, j) == (localPlayerName) && this.board.getCell(i + 3, j) == (localPlayerName)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean verticalWin(int localPlayerName) {
        int length = this.board.getBoardLength();

        for (int i = 0; i <= length - 1; i++) {
            for (int j = 0; j <= this.board.get(i).size() - 4; j++) {
                if (this.board.getCell(i, j) == (localPlayerName) && this.board.getCell(i, j + 1) == (localPlayerName) && this.board.getCell(i, j + 2) == (localPlayerName) && this.board.getCell(i, j + 3) == (localPlayerName)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean ascendingDiagonalWin(int localPlayerName) {
        int length = this.board.getBoardLength();

        for (int i = 3; i <= length - 1; i++) {
            for (int j = 0; j <= this.board.get(i).size() - 1; j++) {
                if (this.board.getCell(i, j) == (localPlayerName) && this.board.getCell(i - 1, j + 1) == (localPlayerName) && this.board.getCell(i - 2, j + 2) == (localPlayerName) && this.board.getCell(i - 3, j + 3) == (localPlayerName)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean descendingDiagonalWin(int localPlayerName) {
        int length = this.board.getBoardLength();

        for (int i = 3; i <= length - 1; i++) {
            for (int j = 3; j <= this.board.get(i).size() - 1; j++) {
                if (this.board.getCell(i, j) == (localPlayerName) && this.board.getCell(i - 1, j - 1) == (localPlayerName) && this.board.getCell(i - 2, j - 2) == (localPlayerName) && this.board.getCell(i - 3, j - 3) == (localPlayerName)) {
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
                    b.append(IDToPlayerName.get(board.get(column).get(row))).append(" | ");
                }
            }
            b.append("\n");
        }
        return b.toString();
    }
}

class Board {
    private final ArrayList<ArrayList<Integer>> board;

    Board(int columns, int rows) {
        this.board = new ArrayList<>();
        for (int i = 0; i < columns; i++) {
            board.add(new ArrayList<>(rows));
        }
    }

    public ArrayList<Integer> get(int index) {
        return this.board.get(index);
    }

    public Integer getCell(int column, int row) {
        if (get(column).size() - 1 >= row) {
            return this.board.get(column).get(row);
        }
        return 0;
    }

    public int getBoardLength() {
        return this.board.size();
    }
}