package org.devoxx4kids.vontrappraspberrypis.server;

import static org.devoxx4kids.vontrappraspberrypis.common.data.Note.*;
import org.devoxx4kids.vontrappraspberrypis.common.data.PlayedNote;

public class Server {

    public static void main(String[] args) {
        final Maria maria = new Maria();
        Thread connectionThread = new Thread(maria.getConnectionHandler());
        Thread mariaThread = new Thread(maria);
        connectionThread.start();
        mariaThread.start();
        maria.queueNotes(new PlayedNote[] {new PlayedNote(G5, 250),
                                           new PlayedNote(G5, 250)});
        Runtime.getRuntime().addShutdownHook(new Thread(new ShutdownHandler(maria)));
    }

    private static class ShutdownHandler implements Runnable {

        private final Maria maria;

        public ShutdownHandler(final Maria maria) {
            this.maria = maria;
        }

        @Override
        public void run() {
            maria.stop();
        }
    }
}
