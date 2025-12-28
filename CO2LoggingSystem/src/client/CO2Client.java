package client;

import common.Constants;
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class CO2Client {
    private final String host;
    private final int port;

    public CO2Client(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void start() {
        System.out.println("Connecting to " + host + ":" + port + "...");

        try (Socket socket = new Socket(host, port);
             BufferedReader in = new BufferedReader(
                     new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(
                     socket.getOutputStream(), true);
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("Connected successfully!");

            Thread responseThread = new Thread(() -> {
                try {
                    String response;
                    while ((response = in.readLine()) != null) {
                        if (response.startsWith("ERROR") ||
                                response.startsWith("SUCCESS") ||
                                response.startsWith("END")) {
                            System.out.println("Server: " + response);
                            if (response.startsWith("END")) break;
                        } else if (response.startsWith("PROMPT")) {
                            System.out.println(response.substring(8));
                        } else {
                            System.out.println("Server: " + response);
                        }
                    }
                } catch (IOException e) {
                    if (!socket.isClosed()) {
                        System.err.println("Connection lost: " + e.getMessage());
                    }
                }
            });
            responseThread.start();

            while (scanner.hasNextLine()) {
                String input = scanner.nextLine();
                out.println(input);

                if (input.equalsIgnoreCase("exit")) {
                    break;
                }
            }

            responseThread.join(2000);

            System.out.println("Disconnected from server.");

        } catch (UnknownHostException e) {
            System.err.println("Unknown host: " + host);
        } catch (ConnectException e) {
            System.err.println("Cannot connect to server at " + host + ":" + port);
            System.err.println("Make sure the server is running.");
        } catch (IOException e) {
            System.err.println("I/O error: " + e.getMessage());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Client interrupted");
        }
    }
}