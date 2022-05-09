package view;

import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

public class ConnectFourPrintStreamView implements PrintStreamView {
    private final ArrayList<ArrayList<String>> board;

    public ConnectFourPrintStreamView(ArrayList<ArrayList<String>> board) {
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
