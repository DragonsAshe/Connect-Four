package connectfour;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ConnectFourWinTests {

    @Test
    void horizontalWinTest1() {
        int name1 = 1;
        int name2 = 2;
        DebugEngine game = new ConnectFour(7,6, "A");

        game.insertDebug(4,name1);
        game.insertDebug(3, name1);
        Assertions.assertFalse(game.win(name1));
        game.insertDebug(2, name1);
        game.insertDebug(1, name1);
        Assertions.assertTrue(game.win(name1));
        Assertions.assertFalse(game.win(name2));
    }

    @Test
    void horizontalWinTest2() {
        int name1 = 1;
        int name2 = 2;
        DebugEngine game = new ConnectFour(7,6, "A");

        game.insertDebug(7,name1);
        game.insertDebug(6, name1);
        Assertions.assertFalse(game.win(name1));
        game.insertDebug(5, name1);
        game.insertDebug(4, name1);
        Assertions.assertTrue(game.win(name1));
        Assertions.assertFalse(game.win(name2));
    }

    @Test
    void horizontalWinTest3() {
        int name1 = 1;
        int name2 = 2;
        DebugEngine game = new ConnectFour(7,6, "A");

        game.insertDebug(1,name2);
        game.insertDebug(1,name2);
        game.insertDebug(1,name2);
        game.insertDebug(1,name2);
        game.insertDebug(1,name2);
        game.insertDebug(2, name2);
        game.insertDebug(2, name2);
        game.insertDebug(2, name2);
        game.insertDebug(2, name2);
        game.insertDebug(2, name2);
        game.insertDebug(3, name2);
        game.insertDebug(3, name2);
        game.insertDebug(3, name2);
        game.insertDebug(3, name2);
        game.insertDebug(3, name2);
        game.insertDebug(4, name2);
        game.insertDebug(4, name2);
        game.insertDebug(4, name2);
        game.insertDebug(4, name2);
        game.insertDebug(4, name2);
        game.insertDebug(1, name1);
        game.insertDebug(2, name1);
        game.insertDebug(3, name1);
        game.insertDebug(4, name1);
        Assertions.assertTrue(game.win(name1));
    }

    @Test
    void horizontalWinTest4() {
        int name1 = 1;
        int name2 = 2;
        DebugEngine game = new ConnectFour(7,6, "A");

        game.insertDebug(7,name2);
        game.insertDebug(7,name2);
        game.insertDebug(7,name2);
        game.insertDebug(7,name2);
        game.insertDebug(7,name2);
        game.insertDebug(6, name2);
        game.insertDebug(6, name2);
        game.insertDebug(6, name2);
        game.insertDebug(6, name2);
        game.insertDebug(6, name2);
        game.insertDebug(5, name2);
        game.insertDebug(5, name2);
        game.insertDebug(5, name2);
        game.insertDebug(5, name2);
        game.insertDebug(5, name2);
        game.insertDebug(4, name2);
        game.insertDebug(4, name2);
        game.insertDebug(4, name2);
        game.insertDebug(4, name2);
        game.insertDebug(4, name2);
        game.insertDebug(7, name1);
        game.insertDebug(6, name1);
        game.insertDebug(5, name1);
        game.insertDebug(4, name1);
        Assertions.assertTrue(game.win(name1));
    }

    @Test
    void descendingDiagonalWinTest1() {
        int name1 = 1;
        int name2 = 2;
        DebugEngine game = new ConnectFour(7,6, "A");

        game.insertDebug(1,name1);
        game.insertDebug(2, name2);
        game.insertDebug(2, name1);
        Assertions.assertFalse(game.win(name1));
        game.insertDebug(3, name2);
        game.insertDebug(4, name1);
        game.insertDebug(3, name2);
        game.insertDebug(3, name1);
        game.insertDebug(4, name1);
        game.insertDebug(4, name2);
        game.insertDebug(4, name1);
        System.out.println(game.boardToString());
        Assertions.assertTrue(game.win(name1));
        Assertions.assertFalse(game.win(name2));
    }

    @Test
    void descendingDiagonalWinTest2() {
        int name1 = 1;
        int name2 = 2;
        DebugEngine game = new ConnectFour(7,6, "A");

        game.insertDebug(4,name1);
        game.insertDebug(5, name2);
        game.insertDebug(5, name1);
        Assertions.assertFalse(game.win(name1));
        game.insertDebug(6, name2);
        game.insertDebug(7, name1);
        game.insertDebug(6, name2);
        game.insertDebug(6, name1);
        game.insertDebug(7, name1);
        game.insertDebug(7, name2);
        game.insertDebug(7, name1);
        System.out.println(game.boardToString());
        Assertions.assertTrue(game.win(name1));
        Assertions.assertFalse(game.win(name2));
    }

    @Test
    void verticalWinTest1() {
        int name1 = 1;
        int name2 = 2;
        DebugEngine game = new ConnectFour(7,6, "A");

        game.insertDebug(1,name1);
        game.insertDebug(1, name1);
        game.insertDebug(1, name1);
        Assertions.assertFalse(game.win(name1));
        game.insertDebug(1, name1);
        System.out.println(game.boardToString());
        Assertions.assertTrue(game.win(name1));
        Assertions.assertFalse(game.win(name2));
    }

    @Test
    void verticalWinTest2() {
        int name1 = 1;
        int name2 = 2;
        DebugEngine game = new ConnectFour(7,6, "A");

        game.insertDebug(7,name1);
        game.insertDebug(7, name1);
        game.insertDebug(7, name1);
        Assertions.assertFalse(game.win(name1));
        game.insertDebug(7, name1);
        System.out.println(game.boardToString());
        Assertions.assertTrue(game.win(name1));
        Assertions.assertFalse(game.win(name2));
    }

    @Test
    void verticalWinTest3() {
        int name1 = 1;
        int name2 = 2;
        DebugEngine game = new ConnectFour(7,6, "A");

        game.insertDebug(7,name2);
        game.insertDebug(7,name2);
        game.insertDebug(7,name1);
        game.insertDebug(7, name1);
        game.insertDebug(7, name1);
        Assertions.assertFalse(game.win(name1));
        game.insertDebug(7, name1);
        System.out.println(game.boardToString());
        Assertions.assertTrue(game.win(name1));
        Assertions.assertFalse(game.win(name2));
    }

    @Test
    void verticalWinTest4() {
        int name1 = 1;
        int name2 = 2;
        DebugEngine game = new ConnectFour(7,6, "A");

        game.insertDebug(1,name2);
        game.insertDebug(1,name2);
        game.insertDebug(1,name1);
        game.insertDebug(1, name1);
        game.insertDebug(1, name1);
        Assertions.assertFalse(game.win(name1));
        game.insertDebug(1, name1);
        System.out.println(game.boardToString());
        Assertions.assertTrue(game.win(name1));
        Assertions.assertFalse(game.win(name2));
    }

    @Test
    void ascendingDiagonalWinTest1() {
        int name1 = 1;
        int name2 = 2;
        DebugEngine game = new ConnectFour(7,6, "A");

        game.insertDebug(7,name1);
        game.insertDebug(6, name2);
        game.insertDebug(6, name1);
        Assertions.assertFalse(game.win(name1));
        game.insertDebug(5, name2);
        game.insertDebug(4, name1);
        game.insertDebug(5, name2);
        game.insertDebug(5, name1);
        game.insertDebug(4, name1);
        game.insertDebug(4, name2);
        game.insertDebug(4, name1);
        System.out.println(game.boardToString());
        Assertions.assertTrue(game.win(name1));
        Assertions.assertFalse(game.win(name2));
    }

    @Test
    void ascendingDiagonalWinTest2() {
        int name1 = 1;
        int name2 = 2;
        DebugEngine game = new ConnectFour(7,6, "A");

        game.insertDebug(4,name1);
        game.insertDebug(3, name2);
        game.insertDebug(3, name1);
        Assertions.assertFalse(game.win(name1));
        game.insertDebug(2, name2);
        game.insertDebug(1, name1);
        game.insertDebug(2, name2);
        game.insertDebug(2, name1);
        game.insertDebug(1, name1);
        game.insertDebug(1, name2);
        game.insertDebug(1, name1);
        System.out.println(game.boardToString());
        Assertions.assertTrue(game.win(name1), game.boardToString());
        Assertions.assertFalse(game.win(name2));

    }
}
