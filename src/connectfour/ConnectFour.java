package connectfour;

import java.util.ArrayList;

public class ConnectFour {
    private final ArrayList<ArrayList<Integer>> board = new ArrayList<>();

    //Initialize the board to have 7 columns
    public void init(int size) {
        for(int i = 0; i < size ; i++) {
            board.add(new ArrayList<>(6)) ;
        }
    }

    // Method to insert a piece into the column that is specified
    public void insert(int position, int player){
        if(board.get(position).size() != 6){
            board.get(position).add(player);
        } // Throw Exception here
    }

    // Method to print the current board
    public void print(){
        for(int row = 5; row >= 0; row--) {
            for(int column = 0; column < 7; column++) {
                if(board.get(column).size()-1 < row) {
                    System.out.print("0 ");
                }else{
                    System.out.print(board.get(column).get(row) + " ");
                }
            }
            System.out.println();
        }
    }
    public static void main(String[] args){
        ConnectFour game = new ConnectFour();
        game.init(7);
        game.print();
    }
}