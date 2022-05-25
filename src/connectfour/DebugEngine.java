package connectfour;

public interface DebugEngine extends ConnectFourGame{

    /**
     * Method to insert a custom value into the bord used mainly for testing the win conditions
     * @param position The column on the board
     * @param player The value you want to insert
     */
    void insertDebug (int position, int player);

}
