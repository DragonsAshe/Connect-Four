package connectfour;

import view.PrintStreamView;

import java.io.PrintStream;

public interface ConnectFourGame {
    /**
     * Method that checks if a given player has won
     * @param localPlayerName The player to check if he won
     * @return true or false if the player won
     */
    boolean win (String localPlayerName);

    /**
     * Method to insert a piece into the board
     * @param position The column that the piece should be inserted to
     * @param localPlayerName The player whose piece is getting inserted
     */
    void insert(int position, String localPlayerName);

    /**
     * Method calls a method that prints out the board
     * @return returns a print stream
     */
    PrintStreamView getPrintStreamView();
}
