package org.devoxx4kids.vontrappraspberrypis.server;

import org.devoxx4kids.vontrappraspberrypis.common.data.HelloDatagram;
import org.devoxx4kids.vontrappraspberrypis.common.data.SingingDatagram;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * This class is for a connection handler that runs and received incoming hello packets and passes them to Maria.
 * It can also be used to send Singing Datagams back to the client.
 *
 * @author Carl Jokl
 * @since 11/03/2017.
 */
public class ConnectionHandler implements Runnable {

    public static final int DEFAULT_SERVER_SOCKET = 1965;

    private final DatagramSocket socket;
    private boolean running;
    private final Maria maria;

    public ConnectionHandler(final int connectionPort, final Maria maria) throws IOException {
        socket = new DatagramSocket(connectionPort);
        this.maria = maria;
    }

    public void send(final SingingDatagram packet, final RemoteClientInfo client) {
        try {
            socket.send(packet.toPacket(client.getClientAddress()));
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @Override
    public void run() {
        running = true;
        System.out.printf("Socket bound: '%s' Listening for packets on socket on port: %d...\n",
                          socket.isBound(),
                          socket.getLocalPort());
        final DatagramPacket rawPacket = new DatagramPacket(new byte[256], 256);
        while (running) {
            try {
                socket.receive(rawPacket);
                final HelloDatagram hello = new HelloDatagram(rawPacket);
                System.out.printf("Received hello packet from: '%s' on address: '%s'!\n",
                                  hello.getClientName(),
                                  hello.getClientAddress());
                maria.handleHello(hello);
            }
            catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }

    public void stop() {
        running = false;
    }
}
