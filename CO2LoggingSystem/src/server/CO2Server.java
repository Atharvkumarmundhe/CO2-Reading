package server;

import common.Constants;
import common.ValidationUtils;
import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class CO2Server {
    private ServerSocket serverSocket;
    private final ExecutorService threadPool;
    private volatile boolean isRunning;
    private final int port;

    public CO2Server(int port) throws IOException {
        if (!ValidationUtils.isValidPort(port)) {
            throw new IllegalArgumentException("Port must be between 1024 and 65535");
        }

        this.port = port;
        this.serverSocket = new ServerSocket(port);
        this.threadPool = Executors.newFixedThreadPool(Constants.MAX_CLIENTS);
        this.isRunning = true;

        System.out.println("=".repeat(50));
        System.out.println("CO2 Logging Server Started");
        System.out.println("Port: " + port);
        System.out.println("Max Clients: " + Constants.MAX_CLIENTS);
        System.out.println("CSV File: " + DataLogger.getInstance().getCsvFilePath());
        System.out.println("=".repeat(50));
    }

    public void start() {
        System.out.println("[INFO] Server listening for connections...");

        while (isRunning) {
            try {
                Socket clientSocket = serverSocket.accept();
                String clientInfo = clientSocket.getInetAddress().getHostAddress() +
                        ":" + clientSocket.getPort();

                System.out.println("[CONNECT] New client: " + clientInfo);
                System.out.println("[ACTIVE] Threads: " +
                        ((ThreadPoolExecutor) threadPool).getActiveCount() + "/" +
                        Constants.MAX_CLIENTS);

                ClientHandler handler = new ClientHandler(clientSocket);
                threadPool.execute(handler);

            } catch (IOException e) {
                if (isRunning) {
                    System.err.println("[ERROR] Accept error: " + e.getMessage());
                }
            }
        }
    }

    public void stop() {
        System.out.println("[INFO] Shutting down server...");
        isRunning = false;

        threadPool.shutdown();
        try {
            if (!threadPool.awaitTermination(5, TimeUnit.SECONDS)) {
                threadPool.shutdownNow();
            }
        } catch (InterruptedException e) {
            threadPool.shutdownNow();
            Thread.currentThread().interrupt();
        }

        try {
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
            }
        } catch (IOException e) {
            System.err.println("[ERROR] Closing server socket: " + e.getMessage());
        }

        System.out.println("[INFO] Server stopped gracefully.");
    }

    public int getPort() {
        return port;
    }
}
