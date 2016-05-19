package org.tomat.spbsu.sakod.server;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        new Client().run();
    }

    public void run() {
        String serverName = "127.0.0.1";
        int port = 4444;
        try {
            System.out.println("Connecting to " + serverName +
                    " on port " + port);
            Socket client = new Socket(serverName, port);
            System.out.println("Just connected to "
                    + client.getRemoteSocketAddress());

            OutputStream outToServer = client.getOutputStream();

            DataOutputStream out = new DataOutputStream(outToServer);
            InputStream inFromServer = client.getInputStream();
            DataInputStream in = new DataInputStream(inFromServer);

            String msg = "";
            Scanner sc = new Scanner(System.in);
            while (true) {
                msg = sc.next();
                if (msg.equals("-1")) {
                    out.writeUTF(msg);
                    break;
                }
                out.writeUTF(msg);
                System.out.println("Server: " + in.readUTF());
            }

            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
