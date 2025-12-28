package server;

import common.ValidationUtils;
import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private final Socket clientSocket;
    private final DataLogger dataLogger;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
        this.dataLogger = DataLogger.getInstance();
    }

    @Override
    public void run() {
        String clientAddress = clientSocket.getInetAddress().getHostAddress();
        System.out.println("[INFO] Thread started for client: " + clientAddress);

        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(
                     clientSocket.getOutputStream(), true)) {

            out.println("=== CO2 Environmental Monitoring System ===");
            out.println("Welcome! Please submit your CO2 reading.");

            // Get User ID
            out.println("PROMPT: Enter User ID (6-12 alphanumeric characters):");
            String userID = ValidationUtils.sanitizeInput(in.readLine());

            if (!ValidationUtils.isValidUserID(userID)) {
                out.println("ERROR: Invalid User ID. Must be 6-12 alphanumeric characters.");
                return;
            }

            // Get Postcode
            out.println("PROMPT: Enter Postcode (UK format, e.g., CF10 3AT):");
            String postcode = ValidationUtils.sanitizeInput(in.readLine());

            if (!ValidationUtils.isValidPostcode(postcode)) {
                out.println("ERROR: Invalid postcode format. Use UK format (e.g., CF10 3AT)");
                return;
            }

            // Get CO2 Reading
            out.println("PROMPT: Enter CO2 concentration in ppm (300-5000):");
            String co2Input = in.readLine();

            double co2ppm;
            try {
                co2ppm = Double.parseDouble(co2Input);
                if (!ValidationUtils.isValidCO2Reading(co2ppm)) {
                    out.println("ERROR: CO2 must be between 300-5000 ppm");
                    return;
                }
            } catch (NumberFormatException e) {
                out.println("ERROR: CO2 must be a valid number");
                return;
            }

            // Create and save reading
            CO2Reading reading = new CO2Reading(userID, postcode, co2ppm);

            if (dataLogger.logReading(reading)) {
                out.println("SUCCESS: Reading logged successfully!");
                out.println("Details: " + reading);
                System.out.println("[LOG] Saved reading: " + reading);
            } else {
                out.println("ERROR: Failed to save reading to database");
            }

            out.println("END: Connection closing. Thank you!");

        } catch (IOException e) {
            System.err.println("[ERROR] Client handler error for " + clientAddress + ": " + e.getMessage());
        } finally {
            try {
                clientSocket.close();
                System.out.println("[INFO] Connection closed for client: " + clientAddress);
            } catch (IOException e) {
                System.err.println("[WARN] Error closing socket: " + e.getMessage());
            }
        }
    }
}