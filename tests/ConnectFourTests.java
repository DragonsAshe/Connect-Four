import connectfour.ConnectFour;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ConnectFourTests {

    @Test
    void winTest1(){
        ConnectFour game = new ConnectFour(7,6);
        String name1 = "Amy";
        String name2 = "Bob";

        game.insert(4,name1);
        game.insert(5, name2);
        game.insert(3, name1);
        Assertions.assertFalse(game.win(name1));
        game.insert(6, name2);
        game.insert(2, name1);
        game.insert(7, name2);
        game.insert(1, name1);
        Assertions.assertTrue(game.win(name1));
        Assertions.assertFalse(game.win(name2));
    }

}
