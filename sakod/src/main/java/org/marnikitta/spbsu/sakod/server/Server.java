package org.marnikitta.spbsu.sakod.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.time.LocalTime;
import java.util.TreeSet;

import static java.time.temporal.ChronoUnit.MINUTES;

public class Server {
    int portNumber = 4444;

    public static void main(String[] args) throws IOException {
        new Server().run();
    }

    private TreeSet<LocalTime> list = new TreeSet<>();

    public void run() throws IOException {
        load();

        ServerSocket serverSocket = new ServerSocket(portNumber);
        serverSocket.setSoTimeout(10000);

        try {
            System.out.println("Waiting for client on port " +
                    serverSocket.getLocalPort() + "...");
            Socket server = serverSocket.accept();
            System.out.println("Just connected to " + server.getRemoteSocketAddress());

            DataInputStream in = new DataInputStream(server.getInputStream());
            DataOutputStream out = new DataOutputStream(server.getOutputStream());

            while (true) {
                String inp = in.readUTF();
                if (inp.equals("-1")) {
                    break;
                }
                System.out.println("Client:" + inp);
                out.writeUTF("Hello " + locToString(getNearest(inp)));
            }
            server.close();
        } catch (SocketTimeoutException s) {
            System.out.println("Socket timed out!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void load() throws FileNotFoundException {
        BufferedReader br = new BufferedReader(new FileReader("data.txt"));
        br.lines().map(Server::convert).forEach(list::add);
    }

    private static LocalTime convert(String time) {
        String[] hm = time.split(":");
        return LocalTime.of(Integer.parseInt(hm[0]), Integer.parseInt(hm[1]));
    }

    private LocalTime getNearest(String time) {
        LocalTime ti = convert(time);
        LocalTime fl = list.floor(ti);
        LocalTime ce = list.ceiling(ti);

        if (fl == null) {
            return ce;
        }
        if (ce == null) {
            return fl;
        }

        return MINUTES.between(fl, ce) < MINUTES.between(ti, ce) ? fl : ce;
    }

    private String locToString(LocalTime tiae) {
        return tiae.getHour() + ":" + tiae.getMinute();
    }
}
