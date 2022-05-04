package view;

import connectfour.Board;

import java.io.PrintStream;

public class ConnectFourPrintStreamView extends PrintStreamView {
    private final Board board;

    public ConnectFourPrintStreamView(Board board) {
        this.board = board;
    }

    @Override
    public void print(PrintStream ps) {
        for(int row = 5; row >= 0; row--) {
            for(int column = 0; column < 7; column++) {
                if(board.get(column).size()-1 < row) {
                    ps.print("0 ");
                }else{
                    ps.print(board.get(column).get(row) + " ");
                }
            }
            ps.println();
        }
    }
}
