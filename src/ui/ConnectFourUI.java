package ui;

import connectfour.*;
import network.GameSessionEstablishedListener;
import network.TCPStream;
import network.TCPStreamCreatedListener;

import java.io.*;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class ConnectFourUI implements TCPStreamCreatedListener, GameSessionEstablishedListener{
    private static final String PRINT = "print";
    private static final String EXIT = "exit";
    private static final String CONNECT = "connect";
    private static final String OPEN = "open";
    private static final String INSERT = "insert";
    private static final int DEFAULT_COLUMNS = 7;
    private static final int DEFAULT_ROWS = 6;
    private final PrintStream outStream;
    private final BufferedReader inBufferedReader;
    private final ConnectFourGame gameEngine;
    private final String playerName;
    private TCPStream tcpStream;
    private ConnectFourTCPProtocolEngine protocolEngine;
    private String partnerName;

    public static void main(String[] args) {
        System.out.println("Welcome to Connect Four version 0.1");

        if (args.length < 1) {
            System.err.println("need playerName as parameter");
            System.exit(1);
        }

        System.out.println("Welcome " + args[0]);
        System.out.println("Let's play a game");

        ConnectFourUI userCmd = new ConnectFourUI(args[0], System.out, System.in);

        userCmd.printUsage();
        userCmd.runCommandLoop();
    }

    public ConnectFourUI(String playerName, PrintStream os, InputStream is) {
        this.outStream = os;
        this.inBufferedReader = new BufferedReader(new InputStreamReader(is));
        this.playerName = playerName;

        this.gameEngine = new ConnectFour(DEFAULT_COLUMNS, DEFAULT_ROWS, playerName);
    }

    public void printUsage() {

        String b = "\n" +
                "\n" +
                "valid commands:" +
                "\n" +
                CONNECT +
                ".. connect as tcp client" +
                "\n" +
                OPEN +
                ".. open port become tcp server" +
                "\n" +
                PRINT +
                ".. print board" +
                "\n" +
                INSERT +
                ".. insert a piece" +
                "\n" +
                EXIT +
                ".. exit";

        this.outStream.println(b);
    }

    public void runCommandLoop() {
        boolean again = true;

        while (again) {
            String cmdLineString;

            try {
                // read user input
                cmdLineString = inBufferedReader.readLine();

                // finish that loop if less than nothing came in
                if (cmdLineString == null) break;

                // trim whitespaces on both sides
                cmdLineString = cmdLineString.trim();

                // extract command
                int spaceIndex = cmdLineString.indexOf(' ');
                spaceIndex = spaceIndex != -1 ? spaceIndex : cmdLineString.length();

                // got command string
                String commandString = cmdLineString.substring(0, spaceIndex);

                // extract parameters string - can be empty
                String parameterString = cmdLineString.substring(spaceIndex);
                parameterString = parameterString.trim();
                // start command loop
                switch (commandString) {
                    case PRINT -> this.doPrint();
                    case CONNECT -> this.doConnect(parameterString);
                    case OPEN -> this.doOpen();
                    case INSERT -> {
                        this.doInsert(parameterString);
                        // redraw
                        this.doPrint();
                    } // convenience
                    case "q", EXIT -> {
                        again = false;
                        this.doExit();
                    } // end loop

                    default -> {
                        this.outStream.println("unknown command:" + cmdLineString);
                        this.printUsage();
                    }
                }
            } catch (IOException ex) {
                this.outStream.println("cannot read from input stream - fatal, give up");
                try {
                    this.doExit();
                } catch (IOException e) {
                    // ignore
                }
            }  catch (RuntimeException ex) {
                this.outStream.println("runtime problems: " + ex.getLocalizedMessage());
            }  catch (GameException ex) {
                this.outStream.println("game exception: " + ex.getLocalizedMessage());
            } catch (StatusException ex) {
                this.outStream.println(("status exception: " + ex.getLocalizedMessage()));
            }
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                           ui method implementations                                        //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private void doInsert(String parameterString) throws StatusException,GameException{
        // call guards
        this.checkConnectionStatus();

        StringTokenizer st = new StringTokenizer(parameterString);
        int coordinate = Integer.parseInt(st.nextToken());

        this.gameEngine.insert(1, coordinate);

    }

    private void doExit() throws IOException {
        // shutdown engines which needs to be
        this.protocolEngine.close();
    }

    private void doOpen() {
        if (this.alreadyConnected()) return;

        this.tcpStream = new TCPStream(ConnectFourGame.DEFAULT_PORT, true, this.playerName);
        this.tcpStream.setStreamCreationListener(this);
        this.tcpStream.start();
    }

    private void doConnect(String parameterString) {
        if (this.alreadyConnected()) return;

        String hostname;

        try {
            StringTokenizer st = new StringTokenizer(parameterString);
            hostname = st.nextToken();
        }
        catch (NoSuchElementException e) {
            System.out.println("no hostname provided - take localhost");
            hostname = "localhost";
        }

        this.tcpStream = new TCPStream(ConnectFourGame.DEFAULT_PORT, false, this.playerName);
        this.tcpStream.setRemoteEngine(hostname);
        this.tcpStream.setStreamCreationListener(this);
        this.tcpStream.start();
        this.gameEngine.changePlayerPiece('R');
    }

    private void doPrint() throws IOException {

        this.outStream.println(this.gameEngine.boardToString());

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                                  guards                                                    //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Guard method - checks if already connected
     */
    private void checkConnectionStatus() throws StatusException{
        if (this.protocolEngine == null) {
            throw new StatusException("not yet connected - call connect or open before");
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                       helper: don't repeat yourself                                        //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private boolean alreadyConnected() {
        if (this.tcpStream != null) {
            System.err.println("connection already established or connection attempt in progress");
            return true;
        }

        return false;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                              listener                                                      //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void streamCreated(TCPStream stream) {
        // connection established - setup protocol engine
        System.out.println("stream created - setup engine - we can play quite soon.");
        this.protocolEngine = new ConnectFourTCPProtocolEngine(this.gameEngine, this.playerName);
        this.gameEngine.setProtocolEngine(protocolEngine);

        this.protocolEngine.subscribeGameSessionEstablishedListener(this);

        try {
            protocolEngine.handleConnection(stream.getInputStream(), stream.getOutputStream());
        } catch (IOException e) {
            System.err.println("cannot get streams from tcpStream - fatal, give up: " + e.getLocalizedMessage());
            System.exit(1);
        }
    }

    @Override
    public void gameSessionEstablished(boolean oracle, String partnerName) {
        System.out.println("game session created");
        this.partnerName = partnerName;

        if(oracle) {
            System.out.println("your turn");
        } else {
            System.out.println("wait for game partner to set a piece");
        }
    }

    public void changed() {
        try {
            this.doPrint();
        } catch (IOException e) {
            System.err.println("very very unexpected: " + e.getLocalizedMessage());
        }
    }
}