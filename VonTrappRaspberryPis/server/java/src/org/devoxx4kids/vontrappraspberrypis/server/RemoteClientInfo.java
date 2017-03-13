package org.devoxx4kids.vontrappraspberrypis.server;

import org.devoxx4kids.vontrappraspberrypis.common.data.HelloDatagram;

import java.net.SocketAddress;

/**
 * Information about the remote client.
 */
class RemoteClientInfo {

    private final String clientName;
    private final SocketAddress clientAddress;

    public RemoteClientInfo(HelloDatagram helloDatagram) {
        this(helloDatagram.getClientName(), helloDatagram.getClientAddress());
    }

    public RemoteClientInfo(final String clientName, final SocketAddress clientAddress) {
        this.clientName = clientName;
        this.clientAddress = clientAddress;
    }

    public SocketAddress getClientAddress() {
        return clientAddress;
    }

    public String getClientName() {
        return clientName;
    }
}
