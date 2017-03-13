package org.devoxx4kids.vontrappraspberrypis.client;

import org.devoxx4kids.vontrappraspberrypis.common.data.HelloDatagram;
import org.devoxx4kids.vontrappraspberrypis.common.data.SingingDatagram;

import java.io.IOException;
import java.net.*;

/**
 * This class handles client connections to the server.
 *
 * @author Carl Jokl
 * @since 11/03/2017.
 */
public class ConnectionHandler implements Runnable {

    public static final int DEFAULT_PORT = 1965;

    private final DatagramSocket socket;
    private final VonTrappSinger singer;
    private final int serverPort;
    private boolean run;

    private DatagramSocket createSocket() {
        try {
            return new DatagramSocket();
        }
        catch (SocketException se) {
            se.printStackTrace();
        }
        return null;
    }

    public ConnectionHandler(final VonTrappSinger singer) {
        this(singer, DEFAULT_PORT);
    }

    public ConnectionHandler(final VonTrappSinger singler, final int serverPort)  {
        this.socket = createSocket();
        this.singer = singler;
        this.serverPort = serverPort;
    }

    private void broadcastToServer() {
        try {
            socket.setBroadcast(true);
            final SocketAddress broadcastAddress = new InetSocketAddress(serverPort);
            final HelloDatagram hello = new HelloDatagram(singer.getName(), socket.getLocalSocketAddress());
            socket.send(hello.toPacket(broadcastAddress));
            socket.setBroadcast(false);
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @Override
    public void run() {
        run = true;
        System.out.printf("Listening for server singing datagram packets on port: %d!\n", socket.getLocalPort());
        final DatagramPacket rawPacket = new DatagramPacket(new byte[8], 8);
        while (run) {
            try {
                socket.receive(rawPacket);
                final SingingDatagram singingDatagram = new SingingDatagram(rawPacket);
                singer.handleNote(singingDatagram.getNote());
            }
            catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }
}
