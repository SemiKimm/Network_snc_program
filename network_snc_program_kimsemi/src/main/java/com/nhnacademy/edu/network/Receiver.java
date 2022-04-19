package com.nhnacademy.edu.network;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Receiver extends Thread {
    private final Log log = LogFactory.getLog(Receiver.class);
    private final DataInputStream in;

    public Receiver(Socket socket) throws IOException {
        this.in = new DataInputStream(socket.getInputStream());
    }

    @Override
    public void run() {
        while (isReceivable()) {
            receiveMessage();
        }
    }

    private boolean isReceivable() {
        return this.in != null;
    }

    private void receiveMessage() {
        try {
            log.info(in.readUTF());
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}