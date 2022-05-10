import connectfour.ConnectFour;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ConnectFourWinTests {

    @Test
    void horizontalWinTest1(){
        ConnectFour game = new ConnectFour(7,6);
        String name1 = "A";
        String name2 = "B";

        game.insert(4,name1);
        game.insert(3, name1);
        Assertions.assertFalse(game.win(name1));
        game.insert(2, name1);
        game.insert(1, name1);
        Assertions.assertTrue(game.win(name1));
        Assertions.assertFalse(game.win(name2));
    }

    @Test
    void horizontalWinTest2(){
        ConnectFour game = new ConnectFour(7,6);
        String name1 = "A";
        String name2 = "B";

        game.insert(7,name1);
        game.insert(6, name1);
        Assertions.assertFalse(game.win(name1));
        game.insert(5, name1);
        game.insert(4, name1);
        Assertions.assertTrue(game.win(name1));
        Assertions.assertFalse(game.win(name2));
    }

    @Test
    void horizontalWinTest3(){
        ConnectFour game = new ConnectFour(7,6);
        String name1 = "A";
        String name2 = "B";

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
    void horizontalWinTest4(){
        ConnectFour game = new ConnectFour(7,6);
        String name1 = "A";
        String name2 = "B";

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
    void descendingDiagonalWinTest1(){
        ConnectFour game = new ConnectFour(7,6);
        String name1 = "A";
        String name2 = "B";

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
    void descendingDiagonalWinTest2(){
        ConnectFour game = new ConnectFour(7,6);
        String name1 = "A";
        String name2 = "B";

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
    void verticalWinTest1(){
        ConnectFour game = new ConnectFour(7,6);
        String name1 = "A";
        String name2 = "B";

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
    void verticalWinTest2(){
        ConnectFour game = new ConnectFour(7,6);
        String name1 = "A";
        String name2 = "B";

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
    void verticalWinTest3(){
        ConnectFour game = new ConnectFour(7,6);
        String name1 = "A";
        String name2 = "B";

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
    void verticalWinTest4(){
        ConnectFour game = new ConnectFour(7,6);
        String name1 = "A";
        String name2 = "B";

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
    void ascendingDiagonalWinTest1(){
        ConnectFour game = new ConnectFour(7,6);
        String name1 = "A";
        String name2 = "B";

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
    void ascendingDiagonalWinTest2(){
        ConnectFour game = new ConnectFour(7,6);
        String name1 = "A";
        String name2 = "B";

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
