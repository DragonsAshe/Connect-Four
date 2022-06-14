package connectfour;

public interface ConnectFourGame extends ConnectFourInsert{
    int DEFAULT_PORT = 1337;
    /**
     * Method that checks if a given player has won
     * @param localPlayerName The player to check if he won
     * @return true or false if the player won
     */
    boolean win (int localPlayerName);

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

    /**
     * Method to change the player piece from the default Y to R
     * @param piece i fogor
     */
    void changePlayerPiece(char piece);

    /**
     * Method that sets the protocol engine
     * @param protocolEngine The protocol engine
     */
    void setProtocolEngine(ConnectFourProtocolEngine protocolEngine);

    /**
     * A method that sets the status to the correct one after the coin got fliped
     * @param status true == ready, false == waiting
     */
    void amIStarting(boolean status);

    /**
     * Method that subscribes to the change listener (When there is an input from the tcp it will print the board)
     * @param changeListener The change listener
     */
    void subscribeChangeListener(LocalBoardChangeListener changeListener);
}
