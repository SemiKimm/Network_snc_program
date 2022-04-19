package com.nhnacademy.edu.network;

import java.io.IOException;
import java.net.Socket;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Client {
    private final Log log = LogFactory.getLog(Client.class);
    private final String hostName;
    private final int port;

    public Client(String hostName, int port) {
        this.hostName = hostName;
        this.port = port;
    }

    public void connect() {
        try {
            Socket socket = new Socket(hostName, port);

            Thread sender = new Sender(socket);
            Thread receiver = new Receiver(socket);

            sender.start();
            receiver.start();
        }  catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}
