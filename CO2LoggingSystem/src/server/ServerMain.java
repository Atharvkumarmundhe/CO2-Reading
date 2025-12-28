package server;

import common.Constants;

import java.io.IOException;

public class ServerMain {
    public static void main(String[] args) {
        int port = Constants.DEFAULT_PORT;

        if (args.length > 0) {
            try {
                port = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.out.println("Invalid port. Using default: " + Constants.DEFAULT_PORT);
                port = Constants.DEFAULT_PORT;
            }
        }

        try {
            CO2Server server = new CO2Server(port);

            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                System.out.println("\n[INFO] Received shutdown signal...");
                server.stop();
            }));

            server.start();

        } catch (IllegalArgumentException e) {
            System.err.println("[ERROR] " + e.getMessage());
            System.exit(1);
        } catch (IOException e) {
            System.err.println("[ERROR] Failed to start server: " + e.getMessage());
            System.err.println("Port " + port + " may be in use or unavailable.");
            System.exit(1);
        }
    }
}