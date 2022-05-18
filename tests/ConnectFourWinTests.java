import connectfour.ConnectFour;
import connectfour.GameException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ConnectFourWinTests {

    @Test
    void horizontalWinTest1() throws GameException {
        String name1 = "A";
        String name2 = "B";
        ConnectFour game = new ConnectFour(7,6, name1);
        game.setEnemy(name2);

        game.insert(4,name1);
        game.insert(3, name1);
        Assertions.assertFalse(game.win(name1));
        game.insert(2, name1);
        game.insert(1, name1);
        Assertions.assertTrue(game.win(name1));
        Assertions.assertFalse(game.win(name2));
    }

    @Test
    void horizontalWinTest2() throws GameException {
        String name1 = "A";
        String name2 = "B";
        ConnectFour game = new ConnectFour(7,6, name1);
        game.setEnemy(name2);

        game.insert(7,name1);
        game.insert(6, name1);
        Assertions.assertFalse(game.win(name1));
        game.insert(5, name1);
        game.insert(4, name1);
        Assertions.assertTrue(game.win(name1));
        Assertions.assertFalse(game.win(name2));
    }

    @Test
    void horizontalWinTest3() throws GameException {
        String name1 = "A";
        String name2 = "B";
        ConnectFour game = new ConnectFour(7,6, name1);
        game.setEnemy(name2);

        game.insert(1,name2);
        game.insert(1,name2);
        game.insert(1,name2);
        game.insert(1,name2);
        game.insert(1,name2);
        game.insert(2, name2);
        game.insert(2, name2);
        game.insert(2, name2);
        game.insert(2, name2);
        game.insert(2, name2);
        game.insert(3, name2);
        game.insert(3, name2);
        game.insert(3, name2);
        game.insert(3, name2);
        game.insert(3, name2);
        game.insert(4, name2);
        game.insert(4, name2);
        game.insert(4, name2);
        game.insert(4, name2);
        game.insert(4, name2);
        game.insert(1, name1);
        game.insert(2, name1);
        game.insert(3, name1);
        game.insert(4, name1);
        Assertions.assertTrue(game.win(name1));
    }

    @Test
    void horizontalWinTest4() throws GameException {
        String name1 = "A";
        String name2 = "B";
        ConnectFour game = new ConnectFour(7,6, name1);
        game.setEnemy(name2);

        game.insert(7,name2);
        game.insert(7,name2);
        game.insert(7,name2);
        game.insert(7,name2);
        game.insert(7,name2);
        game.insert(6, name2);
        game.insert(6, name2);
        game.insert(6, name2);
        game.insert(6, name2);
        game.insert(6, name2);
        game.insert(5, name2);
        game.insert(5, name2);
        game.insert(5, name2);
        game.insert(5, name2);
        game.insert(5, name2);
        game.insert(4, name2);
        game.insert(4, name2);
        game.insert(4, name2);
        game.insert(4, name2);
        game.insert(4, name2);
        game.insert(7, name1);
        game.insert(6, name1);
        game.insert(5, name1);
        game.insert(4, name1);
        Assertions.assertTrue(game.win(name1));
    }

    @Test
    void descendingDiagonalWinTest1() throws GameException {
        String name1 = "A";
        String name2 = "B";
        ConnectFour game = new ConnectFour(7,6, name1);
        game.setEnemy(name2);

        game.insert(1,name1);
        game.insert(2, name2);
        game.insert(2, name1);
        Assertions.assertFalse(game.win(name1));
        game.insert(3, name2);
        game.insert(4, name1);
        game.insert(3, name2);
        game.insert(3, name1);
        game.insert(4, name1);
        game.insert(4, name2);
        game.insert(4, name1);
        System.out.println(game.boardToString());
        Assertions.assertTrue(game.win(name1));
        Assertions.assertFalse(game.win(name2));
    }

    @Test
    void descendingDiagonalWinTest2() throws GameException {
        String name1 = "A";
        String name2 = "B";
        ConnectFour game = new ConnectFour(7,6, name1);
        game.setEnemy(name2);

        game.insert(4,name1);
        game.insert(5, name2);
        game.insert(5, name1);
        Assertions.assertFalse(game.win(name1));
        game.insert(6, name2);
        game.insert(7, name1);
        game.insert(6, name2);
        game.insert(6, name1);
        game.insert(7, name1);
        game.insert(7, name2);
        game.insert(7, name1);
        System.out.println(game.boardToString());
        Assertions.assertTrue(game.win(name1));
        Assertions.assertFalse(game.win(name2));
    }

    @Test
    void verticalWinTest1() throws GameException {
        String name1 = "A";
        String name2 = "B";
        ConnectFour game = new ConnectFour(7,6, name1);
        game.setEnemy(name2);

        game.insert(1,name1);
        game.insert(1, name1);
        game.insert(1, name1);
        Assertions.assertFalse(game.win(name1));
        game.insert(1, name1);
        System.out.println(game.boardToString());
        Assertions.assertTrue(game.win(name1));
        Assertions.assertFalse(game.win(name2));
    }

    @Test
    void verticalWinTest2() throws GameException {
        String name1 = "A";
        String name2 = "B";
        ConnectFour game = new ConnectFour(7,6, name1);
        game.setEnemy(name2);

        game.insert(7,name1);
        game.insert(7, name1);
        game.insert(7, name1);
        Assertions.assertFalse(game.win(name1));
        game.insert(7, name1);
        System.out.println(game.boardToString());
        Assertions.assertTrue(game.win(name1));
        Assertions.assertFalse(game.win(name2));
    }

    @Test
    void verticalWinTest3() throws GameException {
        String name1 = "A";
        String name2 = "B";
        ConnectFour game = new ConnectFour(7,6, name1);
        game.setEnemy(name2);

        game.insert(7,name2);
        game.insert(7,name2);
        game.insert(7,name1);
        game.insert(7, name1);
        game.insert(7, name1);
        Assertions.assertFalse(game.win(name1));
        game.insert(7, name1);
        System.out.println(game.boardToString());
        Assertions.assertTrue(game.win(name1));
        Assertions.assertFalse(game.win(name2));
    }

    @Test
    void verticalWinTest4() throws GameException {
        String name1 = "A";
        String name2 = "B";
        ConnectFour game = new ConnectFour(7,6, name1);
        game.setEnemy(name2);

        game.insert(1,name2);
        game.insert(1,name2);
        game.insert(1,name1);
        game.insert(1, name1);
        game.insert(1, name1);
        Assertions.assertFalse(game.win(name1));
        game.insert(1, name1);
        System.out.println(game.boardToString());
        Assertions.assertTrue(game.win(name1));
        Assertions.assertFalse(game.win(name2));
    }

    @Test
    void ascendingDiagonalWinTest1() throws GameException {
        String name1 = "A";
        String name2 = "B";
        ConnectFour game = new ConnectFour(7,6, name1);
        game.setEnemy(name2);

        game.insert(7,name1);
        game.insert(6, name2);
        game.insert(6, name1);
        Assertions.assertFalse(game.win(name1));
        game.insert(5, name2);
        game.insert(4, name1);
        game.insert(5, name2);
        game.insert(5, name1);
        game.insert(4, name1);
        game.insert(4, name2);
        game.insert(4, name1);
        System.out.println(game.boardToString());
        Assertions.assertTrue(game.win(name1));
        Assertions.assertFalse(game.win(name2));
    }

    @Test
    void ascendingDiagonalWinTest2() throws GameException {
        String name1 = "A";
        String name2 = "B";
        ConnectFour game = new ConnectFour(7,6, name1);
        game.setEnemy(name2);

        game.insert(4,name1);
        game.insert(3, name2);
        game.insert(3, name1);
        Assertions.assertFalse(game.win(name1));
        game.insert(2, name2);
        game.insert(1, name1);
        game.insert(2, name2);
        game.insert(2, name1);
        game.insert(1, name1);
        game.insert(1, name2);
        game.insert(1, name1);
        System.out.println(game.boardToString());
        Assertions.assertTrue(game.win(name1));
        Assertions.assertFalse(game.win(name2));

    }
}
