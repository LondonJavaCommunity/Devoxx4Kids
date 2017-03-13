package org.devoxx4kids.vontrappraspberrypis.server;

import org.devoxx4kids.vontrappraspberrypis.common.data.HelloDatagram;
import org.devoxx4kids.vontrappraspberrypis.common.data.PlayedNote;
import org.devoxx4kids.vontrappraspberrypis.common.data.SingingDatagram;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * This class is used to listen for connections from clients/
 *
 * @author Carl Jokl
 * @since 11/03/2017.
 */
public class Maria implements Runnable {

    private final Random randomClientPicker = new Random(System.currentTimeMillis());
    private final ConnectionHandler connectionHandler;
    private final Map<String, RemoteClientInfo> clients = new HashMap<String, RemoteClientInfo>();
    private final Queue<PlayedNote> pendingNotes = new ArrayBlockingQueue<PlayedNote>(64);
    private boolean run;

    private ConnectionHandler createConnectionHandler(final int connectionPort) {
        try {
            return new ConnectionHandler(connectionPort, this);
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return null;
    }

    private ConnectionHandler createConnectionHandler() {
        return createConnectionHandler(ConnectionHandler.DEFAULT_SERVER_SOCKET);
    }

    public Maria(final int connectionPort) {
        connectionHandler = createConnectionHandler(connectionPort);
    }

    public Maria() {
        connectionHandler = createConnectionHandler();
    }

    public void handleHello(final HelloDatagram hello) {
        clients.put(hello.getClientName(), new RemoteClientInfo(hello));
    }

    public void queueNote(final PlayedNote note) {
        pendingNotes.add(note);
    }

    public void queueNotes(final PlayedNote[] notes) {
        pendingNotes.addAll(Arrays.asList(notes));
    }

    public void queueNotes(final Collection<PlayedNote> notes) {
        pendingNotes.addAll(notes);
    }

    public ConnectionHandler getConnectionHandler() {
        return connectionHandler;
    }

    public void stop() {
        connectionHandler.stop();
        run = false;
    }

    @Override
    public void run() {
       run = true;
       while (run) {
           PlayedNote currentNote;
           while (!clients.isEmpty() && (currentNote = pendingNotes.poll()) != null) {
               if (currentNote.isRest()) {
                   final long started = System.currentTimeMillis();
                   while ((System.currentTimeMillis() - started) > currentNote.getDurationMillis()) {
                       try {
                           Thread.sleep(currentNote.getDurationMillis());
                       }
                       catch (InterruptedException ie) {
                           ie.printStackTrace();
                       }
                   }
               }
               else {
                   final RemoteClientInfo[] clientsArray = clients.values().toArray(new RemoteClientInfo[clients.size()]);
                   final RemoteClientInfo client = clientsArray[randomClientPicker.nextInt(clientsArray.length)];
                   connectionHandler.send(new SingingDatagram(currentNote), client);
               }
           }
       }
    }
}
