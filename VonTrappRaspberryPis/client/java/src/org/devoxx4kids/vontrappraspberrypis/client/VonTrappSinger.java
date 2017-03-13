package org.devoxx4kids.vontrappraspberrypis.client;

import org.devoxx4kids.vontrappraspberrypis.common.data.PlayedNote;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * This the main object handling client operations for the Von Trapp Raspberry Pi Client.
 *
 * @author Carl Jokl
 * @since 11/03/2017.
 */
public class VonTrappSinger implements Runnable {

    private final Queue<PlayedNote> pendingNotes = new ArrayBlockingQueue<PlayedNote>(64);
    private final String name;
    private boolean run;

    public VonTrappSinger(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void stop() {
        run = false;
    }

    @Override
    public void run() {
        run = true;
        PlayedNote note;
        while (run) {
            if ((note = pendingNotes.poll()) != null) {
                System.out.printf("Simulate playing note: '%s' for duration: %d milliseconds\n",
                        note.getNote().name(), note.getDurationMillis());
            }
            else {
                try {
                    synchronized (this) {
                        wait();
                    }
                }
                catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
            }
        }
    }

    public synchronized void handleNote(final PlayedNote note) {
        pendingNotes.add(note);
    }
}
