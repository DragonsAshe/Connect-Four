package connectfour;

import network.GameSessionEstablishedListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class ConnectFour implements ConnectFourGame, DebugEngine, GameSessionEstablishedListener{
    private final Board board;
    private final HashMap<Integer, Character> IDToPlayerName = new HashMap<>();
    private Status status = Status.INITIALIZED;
    private ConnectFourProtocolEngine protocolEngine;
    private final String localPlayerName;

    public ConnectFour(int columns, int rows, String localPlayerName) {
        this.board = new Board(columns, rows);

        this.IDToPlayerName.put(1, 'Y');
        this.IDToPlayerName.put(2, 'R');
        this.localPlayerName = localPlayerName;
    }

    public ConnectFour(String localPlayerName){
        this.board = new Board(7,6);

        this.IDToPlayerName.put(1, 'Y');
        this.localPlayerName = localPlayerName;
    }

    public void changePlayerPiece(){
        this.IDToPlayerName.remove(1);
        this.IDToPlayerName.remove(2);
        this.IDToPlayerName.put(1, 'R');
        this.IDToPlayerName.put(2, 'Y');
    }

    @Override
    public void resetPlayerPieces() {
        this.IDToPlayerName.remove(1);
        this.IDToPlayerName.remove(2);
        this.IDToPlayerName.put(1, 'Y');
        this.IDToPlayerName.put(2, 'R');
    }

    @Override
    public void insertDebug(int position, int player){
        if (this.status != Status.ENDED) {
            if (board.get(position - 1).size() != 6) {
                board.get(position - 1).add(player);
            }
        }
    }

    // Method to insert a piece into the column that is specified
    public void insert(int piece, int column) throws GameException, StatusException{
        if (this.status != Status.ENDED && this.status == Status.READY) {
            try {
                if (board.get(column - 1).size() != 6) {
                    board.get(column - 1).add(piece);
                } else {
                    throw new GameException("Column is full");
                }
            } catch (RuntimeException e){
                throw new GameException("Column not on board");
            }
            if(piece == 1){
                this.protocolEngine.insert(piece, column);
                this.status = Status.WAITING;
                this.win(piece);
            }
        }else if (piece != 1){
            if (board.get(column - 1).size() != 6) {
                board.get(column - 1).add(piece);
                this.status = Status.READY;
                this.notifyBoardChanged();
                this.win(piece);
            }
        } else {
            throw new StatusException("Wrong status");
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
                }else if (this.board.getCell(column, row) == 1){
                    //b.append(this.board.getCell(column, row)).append(" | ");
                    b.append(this.IDToPlayerName.get(1)).append(" | ");
                } else if (this.board.getCell(column, row) == 2){
                    b.append(this.IDToPlayerName.get(2)).append(" | ");
                }
            }
            b.append("\n");
        }
        return b.toString();
    }

    @Override
    public void amIStarting(boolean status) {
        if (status){
            this.status = Status.READY;
        } else {
            this.status = Status.WAITING;
        }
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                       constructor helper                                             //
    //////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void setProtocolEngine(ConnectFourProtocolEngine protocolEngine) {
        this.protocolEngine = protocolEngine;
        this.protocolEngine.subscribeGameSessionEstablishedListener(this);
    }

    public Status getStatus() {
        return this.status;
    }

    public boolean hasWon() {
        return this.status == Status.ENDED && this.win(1);
    }

    public boolean hasLost() {
        return this.status == Status.ENDED && !this.win(1);
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                            observed                                                 //
    /////////////////////////////////////////////////////////////////////////////////////////////////////////
    private final List<LocalBoardChangeListener> boardChangeListenerList = new ArrayList<>();

    public void subscribeChangeListener(LocalBoardChangeListener changeListener) {
        this.boardChangeListenerList.add(changeListener);
    }

    private void notifyBoardChanged() {
        // are there any listeners ?
        if(this.boardChangeListenerList.isEmpty()) return;

        // yes - there are - create a thread and inform them
        (new Thread(() -> {
            for(LocalBoardChangeListener listener : ConnectFour.this.boardChangeListenerList) {
                listener.changed();
            }
        })).start();
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                            listener                                                 //
    /////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void gameSessionEstablished(boolean oracle, String partnerName) {
        System.out.println(this.localPlayerName + ": gameSessionEstablished with " + partnerName + " | " + oracle);

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