package connectfour;

public interface ConnectFourInsert {

    /**
     * Method to insert a piece into the board
     * @param piece the piece that will be inserted
     * @param column The column that the piece should be inserted to
     */
    void insert(int piece, int column) throws GameException, StatusException;
}
