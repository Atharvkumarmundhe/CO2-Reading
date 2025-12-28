package client;

import common.Constants;
import common.ValidationUtils;

public class ClientMain {
    public static void main(String[] args) {
        String host = Constants.DEFAULT_HOST;
        int port = Constants.DEFAULT_PORT;

        if (args.length >= 2) {
            host = args[0];
            try {
                port = Integer.parseInt(args[1]);
                if (!ValidationUtils.isValidPort(port)) {
                    System.out.println("Invalid port range. Using default: " + Constants.DEFAULT_PORT);
                    port = Constants.DEFAULT_PORT;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid port. Using default: " + Constants.DEFAULT_PORT);
                port = Constants.DEFAULT_PORT;
            }
        } else if (args.length == 1) {
            host = args[0];
        }

        System.out.println("=".repeat(50));
        System.out.println("CO2 Logging Client");
        System.out.println("Server: " + host + ":" + port);
        System.out.println("Type 'exit' to quit");
        System.out.println("=".repeat(50));

        CO2Client client = new CO2Client(host, port);
        client.start();
    }
}