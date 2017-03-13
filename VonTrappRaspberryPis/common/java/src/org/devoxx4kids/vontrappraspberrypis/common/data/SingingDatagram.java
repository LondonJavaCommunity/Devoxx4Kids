package org.devoxx4kids.vontrappraspberrypis.common.data;

import java.net.DatagramPacket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

/**
 * This class represents a packet containing data containing
 * information about a note to be sung.
 *
 * @author Carl Jokl
 * @since 11/03/2017.
 */
public class SingingDatagram {

    private final PlayedNote note;

    public SingingDatagram(final DatagramPacket packet) {
        final IntBuffer intFields = ByteBuffer.wrap(packet.getData()).asIntBuffer();
        note = new PlayedNote(Note.fromId(intFields.get()), intFields.get());
    }

    public SingingDatagram(final PlayedNote note) {
        this.note = note;
    }

    public PlayedNote getNote() {
        return note;
    }

    public DatagramPacket toPacket(final SocketAddress destination) {
        final ByteBuffer dataBuffer = ByteBuffer.allocateDirect(8);
        dataBuffer.asIntBuffer().put(new int[] {note.getNote().getId(), note.getDurationMillis()});
        final byte[] data = dataBuffer.compact().array();
        return new DatagramPacket(data, data.length, destination);
    }
}
