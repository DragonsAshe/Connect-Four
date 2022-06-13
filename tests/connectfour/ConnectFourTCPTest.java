package connectfour;

import network.TCPStream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;
public class ConnectFourTCPTest {
    public static final String AMY = "AMY";
    public static final int PORTNUMBER = 5555;
    private static final String BOB = "Bob";
    private static int port = 0;
    public static final long TEST_THREAD_SLEEP_DURATION = 1000;

    private int getPortNumber() {
        if(ConnectFourTCPTest.port == 0) {
            ConnectFourTCPTest.port = PORTNUMBER;
        } else {
            ConnectFourTCPTest.port++;
        }

        System.out.println("use port number " + ConnectFourTCPTest.port);
        return ConnectFourTCPTest.port;
    }

    @Test
    public void integrationTest1() throws IOException, InterruptedException {
        // there are players in this test: Alice and Bob

        // create Alice's game engine
        ConnectFour aliceGameEngine = new ConnectFour(7,6,AMY);
        // create real protocol engine on Alice's side
        ConnectFourTCPProtocolEngine aliceTicTacToeTCPProtocolEngine =
                new ConnectFourTCPProtocolEngine(aliceGameEngine, AMY);

        aliceGameEngine.setProtocolEngine(aliceTicTacToeTCPProtocolEngine);

        // create Bob's game engine
        ConnectFour bobGameEngine = new ConnectFour(7,6,BOB);
        // create real protocol engine on Bob's side
        ConnectFourTCPProtocolEngine bobTicTacToeTCPProtocolEngine =
                new ConnectFourTCPProtocolEngine(bobGameEngine, BOB);

        bobGameEngine.setProtocolEngine(bobTicTacToeTCPProtocolEngine);

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //                                           setup tcp                                                    //
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////
        int port = this.getPortNumber();
        // this stream plays TCP server role during connection establishment
        TCPStream aliceSide = new TCPStream(port, true, "amySide");
        // this stream plays TCP client role during connection establishment
        TCPStream bobSide = new TCPStream(port, false, "bobSide");
        // start both stream
        aliceSide.start(); bobSide.start();
        // wait until TCP connection is established
        aliceSide.waitForConnection(); bobSide.waitForConnection();

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //                                       launch protocol engine                                           //
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // give protocol engines streams and launch
        aliceTicTacToeTCPProtocolEngine.handleConnection(aliceSide.getInputStream(), aliceSide.getOutputStream());
        bobTicTacToeTCPProtocolEngine.handleConnection(bobSide.getInputStream(), bobSide.getOutputStream());

        // give it a moment - important stop this test thread - to threads must be launched
        System.out.println("give threads a moment to be launched");
        Thread.sleep(TEST_THREAD_SLEEP_DURATION);

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //                                             test results                                               //
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////

        // pieces must not be same
        Assertions.assertNotSame(aliceGameEngine.getStatus(), bobGameEngine.getStatus());

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //                                             tidy up                                                    //
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////

        aliceTicTacToeTCPProtocolEngine.close();
        bobTicTacToeTCPProtocolEngine.close();

        // stop test thread to allow operating system to close sockets
        Thread.sleep(TEST_THREAD_SLEEP_DURATION);

        // Thread.sleep(Long.MAX_VALUE); // debugging
    }

    @Test
    public void integrationTestFullGame() throws GameException, StatusException, IOException, InterruptedException {
        // there are players in this test: Alice and Bob

        // create Alice's game engine
        ConnectFour aliceGameEngine = new ConnectFour(AMY);
        // create real protocol engine on Alice's side
        ConnectFourTCPProtocolEngine aliceTicTacToeTCPProtocolEngine =
                new ConnectFourTCPProtocolEngine(aliceGameEngine, AMY);

        aliceGameEngine.setProtocolEngine(aliceTicTacToeTCPProtocolEngine);

        // create Bob's game engine
        ConnectFour bobGameEngine = new ConnectFour(BOB);
        // create real protocol engine on Bob's side
        ConnectFourTCPProtocolEngine bobTicTacToeTCPProtocolEngine =
                new ConnectFourTCPProtocolEngine(bobGameEngine, BOB);

        bobGameEngine.setProtocolEngine(bobTicTacToeTCPProtocolEngine);

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //                                           setup tcp                                                    //
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////
        int port = this.getPortNumber();
        // this stream plays TCP server role during connection establishment
        TCPStream aliceSide = new TCPStream(port, true, "amySide");
        // this stream plays TCP client role during connection establishment
        TCPStream bobSide = new TCPStream(port, false, "bobSide");
        // start both stream
        aliceSide.start(); bobSide.start();
        // wait until TCP connection is established
        aliceSide.waitForConnection(); bobSide.waitForConnection();

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //                                       launch protocol engine                                           //
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // give protocol engines streams and launch
        aliceTicTacToeTCPProtocolEngine.handleConnection(aliceSide.getInputStream(), aliceSide.getOutputStream());
        bobTicTacToeTCPProtocolEngine.handleConnection(bobSide.getInputStream(), bobSide.getOutputStream());

        // give it a moment - important stop this test thread - to threads must be launched
        System.out.println("give threads a moment to be launched");
        Thread.sleep(TEST_THREAD_SLEEP_DURATION);

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //                                             run scenario                                               //
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ConnectFour playerFirst = aliceGameEngine.getStatus() == Status.READY ? aliceGameEngine : bobGameEngine;
        ConnectFour playerSecond = aliceGameEngine.getStatus() == Status.READY ? bobGameEngine : aliceGameEngine;

        playerFirst.insert(1, 4);
        Assertions.assertFalse(playerFirst.hasWon() && playerSecond.hasWon());
        Thread.sleep(TEST_THREAD_SLEEP_DURATION);

        playerSecond.insert(1, 3);
        Assertions.assertFalse(playerFirst.hasWon() && playerSecond.hasWon());
        Thread.sleep(TEST_THREAD_SLEEP_DURATION);

        playerFirst.insert(1, 4);
        Assertions.assertFalse(playerFirst.hasWon() && playerSecond.hasWon());
        Thread.sleep(TEST_THREAD_SLEEP_DURATION);

        playerSecond.insert(1, 3);
        Assertions.assertFalse(playerFirst.hasWon() && playerSecond.hasWon());
        Thread.sleep(TEST_THREAD_SLEEP_DURATION);

        playerFirst.insert(1,3);
        Assertions.assertFalse(playerFirst.hasWon() && playerSecond.hasWon());
        Thread.sleep(TEST_THREAD_SLEEP_DURATION);

        playerSecond.insert(1, 4);
        Assertions.assertFalse(playerFirst.hasWon() && playerSecond.hasWon());
        Thread.sleep(TEST_THREAD_SLEEP_DURATION);

        playerFirst.insert(1, 4);
        Assertions.assertFalse(playerFirst.hasWon() && playerSecond.hasWon());
        Thread.sleep(TEST_THREAD_SLEEP_DURATION);

        playerSecond.insert(1, 2);
        Assertions.assertFalse(playerFirst.hasWon() && playerSecond.hasWon());
        Thread.sleep(TEST_THREAD_SLEEP_DURATION);

        playerFirst.insert(1, 2);
        Assertions.assertFalse(playerFirst.hasWon() && playerSecond.hasWon());
        Thread.sleep(TEST_THREAD_SLEEP_DURATION);

        playerSecond.insert(1, 5);
        Assertions.assertFalse(playerFirst.hasWon() && playerSecond.hasWon());
        Thread.sleep(TEST_THREAD_SLEEP_DURATION);

        playerFirst.insert(1, 1);
        Thread.sleep(TEST_THREAD_SLEEP_DURATION);

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //                                             test results                                               //
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////

        Assertions.assertTrue(playerFirst.hasWon());
        Assertions.assertTrue(playerSecond.hasLost());

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //                                             tidy up                                                    //
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////

        aliceTicTacToeTCPProtocolEngine.close();
        bobTicTacToeTCPProtocolEngine.close();

        // stop test thread to allow operating system to close sockets
        Thread.sleep(TEST_THREAD_SLEEP_DURATION);

        // Thread.sleep(Long.MAX_VALUE); // debugging
    }
}
