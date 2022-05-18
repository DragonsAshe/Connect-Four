package connectfour;

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
     * Method converts the board into a printable string
     * @return returns a String that holds the board
     */
    String boardToString();

    /**
     * Method that puts the partner name into the hashmap
     * @param partnerName The partners name
     */
    void setEnemy(String partnerName);
}
