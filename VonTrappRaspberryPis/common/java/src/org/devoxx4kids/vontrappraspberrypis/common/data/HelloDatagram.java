package org.devoxx4kids.vontrappraspberrypis.common.data;

import java.net.DatagramPacket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/**
 * This class represents a simple packet used to broadcast to and detect the server.
 *
 * @author Carl Jokl
 * @since 11/03/2017.
 */
public class HelloDatagram {

    private final Charset UTF8 = Charset.forName("UTF-8");

    private final String clientName;
    private final SocketAddress clientAddress;

    public HelloDatagram(final DatagramPacket helloPacket) {
        clientName = UTF8.decode(ByteBuffer.wrap(helloPacket.getData())).toString();
        clientAddress = helloPacket.getSocketAddress();
    }

    public HelloDatagram(final String clientName, final SocketAddress clientAddress) {
        this.clientName = clientName;
        this.clientAddress = clientAddress;
    }

    public String getClientName() {
        return clientName;
    }

    public SocketAddress getClientAddress() {
        return clientAddress;
    }

    public DatagramPacket toPacket(final SocketAddress destinationAddress) {
        final byte[] data = UTF8.encode(clientName).array();
        return new DatagramPacket(data, data.length, destinationAddress);
    }
}
