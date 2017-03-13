package org.devoxx4kids.vontrappraspberrypis.client;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * This class represents the main application.
 *
 * @author Carl Jokl
 * @since 11/03/2017.
 */
public class App {

    public static void main(String[] args) {
        try {
            final VonTrappSinger singer = new VonTrappSinger(InetAddress.getLocalHost().getHostName());
            final Thread singerThread = new Thread(singer);
            singerThread.start();
        }
        catch (UnknownHostException uhe) {
            uhe.printStackTrace();
        }
    }
}
