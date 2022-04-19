package com.nhnacademy.edu.network;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Sender extends Thread {
    private final Log log = LogFactory.getLog(Sender.class);
    private final DataOutputStream out;

    public Sender(Socket socket) throws IOException {
        this.out = new DataOutputStream(socket.getOutputStream());
    }

    @Override
    public void run() {
        try {
            sendMessage();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    private boolean isSend() {
        return this.out != null;
    }

    private void sendMessage() throws IOException {
        try (Scanner scanner = new Scanner(System.in)) {
            while (isSend()) {
                this.out.writeUTF(scanner.nextLine());
            }
        }
    }
}