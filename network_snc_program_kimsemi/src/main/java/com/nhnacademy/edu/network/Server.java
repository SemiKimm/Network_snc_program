package com.nhnacademy.edu.network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Server {
    private final Log log = LogFactory.getLog(Server.class);
    private final int port;

    public Server(int port) {
        this.port = port;
    }

    public void start() throws IOException {
        int k = Integer.MIN_VALUE;
        boolean b = true;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (b) {
                k++;
                b = k < Integer.MAX_VALUE;
                try {
                    Socket socket = serverSocket.accept();
                    ClientSession client = new ClientSession(socket);
                    client.start();
                } catch (IOException e) {
                    log.error("client access fail" + e.getMessage());
                }
            }
        }
    }

    public class ClientSession extends Thread {
        private final Socket socket;

        ClientSession(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                Thread sender = new Sender(this.socket);
                Thread receiver = new Receiver(this.socket);

                sender.start();
                receiver.start();
            } catch (IOException e) {
                log.error(e.getMessage());
            }
        }
    }
}