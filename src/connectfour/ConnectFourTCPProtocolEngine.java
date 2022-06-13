package connectfour;

import network.ProtocolEngine;

import java.io.*;
import java.util.Random;

public class ConnectFourTCPProtocolEngine extends ConnectFourProtocolEngine
        implements Runnable, ProtocolEngine {
    private static final String DEFAULT_NAME = "anonymousProtocolEngine";
    private final String name;
    private OutputStream os;
    private InputStream is;
    private final ConnectFourGame gameEngine;

    //    public static final int METHOD_PICK = 0;
    public static final int METHOD_SET = 1;
//    public static final int RESULT_PICK = 2;

    private Thread protocolThread = null;
    //    private Thread pickWaitThread = null;
//    private TicTacToePiece pickResult;
    private boolean oracle;
    private String partnerName;

    /**
     * constructor has an additional name - helps debugging.
     * @param gameEngine game engine
     * @param name name
     */
    public ConnectFourTCPProtocolEngine(ConnectFourGame gameEngine, String name) {
        this.gameEngine = gameEngine;
        this.name = name;
    }

    public ConnectFourTCPProtocolEngine(ConnectFourGame gameEngine) {
        this(gameEngine, DEFAULT_NAME);
    }

    public void insert(int piece, int column) throws GameException {
        this.log("send set message to other side");
        this.serializeSet(piece, column, this.os);
    }

    private void deserializeInsert() throws GameException {
        this.log("deserialize received set message");

        try {
            InsertCommand insertCommand = this.deserializeInsert(this.is);
            // call method - but no need to keep result - it isn't sent back.
            this.gameEngine.insert(2 , insertCommand.getColumn());
            System.out.println(this.gameEngine.boardToString());
        } catch (IOException | StatusException e) {
            throw new GameException("could not deserialize command", e);
        }
    }

    boolean read() throws GameException {
        this.log("Protocol Engine: read from input stream");
        DataInputStream dis = new DataInputStream(this.is);

        // read method id
        try {
            int commandID = dis.readInt();
            //                case METHOD_PICK: this.deserializePick(); return true;
            if (commandID == METHOD_SET) {
                this.deserializeInsert();
                return true;
//                case RESULT_PICK: this.deserializeResultPick(); return true;
            }
            this.log("unknown method, throw exception id == " + commandID);
            return false;
        } catch (IOException e) {
            this.log("IOException caught - most probably connection close - stop thread / stop engine");
            try {
                this.close();
            } catch (IOException ioException) {
                // ignore
            }
            return false;
        }
    }

    @Override
    public void run() {
        this.log("Protocol Engine started - flip a coin");
        long seed = this.hashCode() * System.currentTimeMillis();
        Random random = new Random(seed);

        int localInt, remoteInt;
        try {
            DataOutputStream dos = new DataOutputStream(this.os);
            DataInputStream dis = new DataInputStream(this.is);
            do {
                localInt = random.nextInt();
                this.log("flip and take number " + localInt);
                dos.writeInt(localInt);
                remoteInt = dis.readInt();
            } while(localInt == remoteInt);

            this.oracle = localInt < remoteInt;
            this.log("Flipped a coin and got an oracle == " + this.oracle);
            //this.oracleSet = true;

            // finally - exchange names
            dos.writeUTF(this.name);
            this.partnerName = dis.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.notifyGamesSessionEstablished(ConnectFourTCPProtocolEngine.this.oracle,
                ConnectFourTCPProtocolEngine.this.partnerName);
        this.gameEngine.amIStarting(this.oracle);

        try {
            boolean again = true;
            while(again) {
                again = this.read();
            }
        } catch (GameException e) {
            this.logError();
            e.printStackTrace();
            // leave while - end thread
        }
    }

    @Override
    public void handleConnection(InputStream is, OutputStream os) throws IOException {
        this.is = is;
        this.os = os;

        this.protocolThread = new Thread(this);
        this.protocolThread.start();
    }

    @Override
    public void close() throws IOException {
        if(this.os != null) { this.os.close();}
        if(this.is != null) { this.is.close();}
    }

    private String produceLogString(String message) {
        StringBuilder sb = new StringBuilder();
        if(this.name != null) {
            sb.append(this.name);
            sb.append(": ");
        }

        sb.append(message);

        return sb.toString();
    }

    private void log(String message) {
        System.out.println(this.produceLogString(message));
    }

    private void logError() {
        System.err.println(this.produceLogString("exception called in protocol engine thread - fatal and stop"));
    }
}