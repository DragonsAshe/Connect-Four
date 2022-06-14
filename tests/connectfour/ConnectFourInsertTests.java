package connectfour;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ConnectFourInsertTests {

    //Tests if the method throws an exception when a column is too full
    @Test
    void insertTest1(){
        DebugEngine game = new ConnectFour(7,6, "A");

        Assertions.assertThrows(GameException.class, () -> {
            game.insert(1,1);
            game.insert(2,1);
            game.insert(1,1);
            game.insert(2,1);
            game.insert(1,1);
            game.insert(2,1);
            game.insert(1,1);
        });
    }

    //Tests if method throws exception if someone tries to insert in a column outside the board
    @Test
    void insertTest2(){
        DebugEngine game = new ConnectFour(7,6, "A");
        game.amIStarting(true);
        Assertions.assertThrows(GameException.class, () -> game.insert(1,8));
    }

    //Test if the entire board can be completely filled without any exceptions
    @Test
    void insertTest3(){
        DebugEngine game = new ConnectFour(7,6, "A");

        Assertions.assertDoesNotThrow(() -> {
            game.insert(1,1);
            game.insert(1,1);
            game.insert(1,1);
            game.insert(1,1);
            game.insert(1,1);
            game.insert(1,1);
            game.insert(1,2);
            game.insert(1,2);
            game.insert(1,2);
            game.insert(1,2);
            game.insert(1,2);
            game.insert(1,2);
            game.insert(1,3);
            game.insert(1,3);
            game.insert(1,3);
            game.insert(1,3);
            game.insert(1,3);
            game.insert(1,3);
            game.insert(1,4);
            game.insert(1,4);
            game.insert(1,4);
            game.insert(1,4);
            game.insert(1,4);
            game.insert(1,4);
            game.insert(1,5);
            game.insert(1,5);
            game.insert(1,5);
            game.insert(1,5);
            game.insert(1,5);
            game.insert(1,5);
            game.insert(1,6);
            game.insert(1,6);
            game.insert(1,6);
            game.insert(1,6);
            game.insert(1,6);
            game.insert(1,6);
            game.insert(1,7);
            game.insert(1,7);
            game.insert(1,7);
            game.insert(1,7);
            game.insert(1,7);
            game.insert(1,7);
            System.out.println(game.boardToString());
        });
    }
}
