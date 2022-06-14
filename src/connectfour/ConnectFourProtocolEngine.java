package connectfour;

import network.GameSessionEstablishedListener;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static connectfour.ConnectFourTCPProtocolEngine.METHOD_SET;

public abstract class ConnectFourProtocolEngine implements ConnectFourInsert {

    void serializeSet(int piece, int column, OutputStream os) throws GameException {
        DataOutputStream dos = new DataOutputStream(os);

        // write method id
        try {
            dos.writeInt(METHOD_SET);
            // serialize symbol
            dos.writeInt(2);
            // serialize position coordinates
            dos.writeInt(column);
        } catch (IOException e) {
            throw new GameException(e.getLocalizedMessage());
        }
    }

    InsertCommand deserializeInsert(InputStream is) throws IOException {
        DataInputStream dis = new DataInputStream(is);
        // read serialized symbol
        int piece = dis.readInt();
        // read s coordinate
        // read i coordinate
        int column = dis.readInt();

        // call method - but no need to keep result - it isn't sent back.
        return new InsertCommand(piece, column);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                         oracle creation listener                                      //
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////

    private final List<GameSessionEstablishedListener> sessionCreatedListenerList = new ArrayList<>();

    public void subscribeGameSessionEstablishedListener(GameSessionEstablishedListener ocListener) {
        this.sessionCreatedListenerList.add(ocListener);
    }

    public void unsubscribeGameSessionEstablishedListener(GameSessionEstablishedListener ocListener) {
        this.sessionCreatedListenerList.remove(ocListener);
    }

    void notifyGamesSessionEstablished(boolean oracle, String partnerName) {
        // call listener
        if (!this.sessionCreatedListenerList.isEmpty()) {
            for (GameSessionEstablishedListener oclistener : this.sessionCreatedListenerList) {
                new Thread(() -> {
                    try {
                        Thread.sleep(1); // block a moment to let read thread start - just in case
                    } catch (InterruptedException e) {
                        // will not happen
                    }
                    oclistener.gameSessionEstablished(oracle, partnerName);
                }).start();
            }
        }
    }
}