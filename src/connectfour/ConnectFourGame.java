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

    void changePlayerPiece(char piece);

    void setProtocolEngine(ConnectFourProtocolEngine protocolEngine);
}
