package org.devoxx4kids.vontrappraspberrypis.common.data;

/**
 * This class represents a note that is played for a given duration of time.
 *
 * @author Carl Jokl
 * @since 11/03/2017.
 */
public class PlayedNote {

    private final Note note;
    private final int durationMillis;

    public PlayedNote(final Note note, final int durationMills) {
        this.note = note;
        this.durationMillis = durationMills;
    }

    public int getDurationMillis() {
           return durationMillis;
       }

    public Note getNote() {
           return note;
       }

    public boolean isRest() {
        return note == Note.REST;
    }

    public boolean isIndefinate() {
           return durationMillis == 0;
       }

    public boolean isStop() {
           return durationMillis < 0;
       }
}
