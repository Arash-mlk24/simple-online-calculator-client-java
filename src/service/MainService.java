package service;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class MainService implements AppService {

    private int port;
    private String host;
    private Socket socket = null;
    private DataInputStream input = null;
    private DataOutputStream output = null;

    public void setPort(int port) {
        this.port = port;
    }
    public void setHost(String host) {
        this.host = host;
    }

    public MainService() { }

    @Override
    public String run() {
        System.out.println("Starting connection...");
        try {

            socket = new Socket(host, port);
            System.out.println("Connection established!");

            return "Connection Established!";

        } catch(IOException e) {

            System.out.println("Error occurred while connecting to server: " + e);
            return "Connection Failed!";

        }

    }

    public void sendMessage() {
        System.out.println("Starting Messaging...");
        try {

            input = new DataInputStream(System.in);
            output = new DataOutputStream(socket.getOutputStream());

        } catch(IOException e) {

            System.out.println("Error occurred while connecting to server: " + e);

        }

        String line = "";

        while (!line.equals("exit")) {
            try {

                input = new DataInputStream(System.in);

                System.out.println("input: ");
                line = input.readLine();

                output.writeUTF(line);

                input = new DataInputStream(socket.getInputStream());
                line = input.readUTF();

                if (!line.equals("VALID!")) {
                    System.out.println(line);
                }

            } catch(IOException e) {

                System.out.println("Error occurred while reading message: " + e);

            }
        }

        // close the connection
        try {
            input.close();
            output.close();
            socket.close();
        } catch(IOException e) {
            System.out.println("Error caught while closing socket: " + e);
        }

        System.out.println("Connection closed!");

    }



}
