package server;

import common.Constants;
import java.io.*;
import java.nio.file.*;
import java.util.concurrent.locks.ReentrantLock;

public class DataLogger {
    private static DataLogger instance;
    private final ReentrantLock lock;
    private final String csvFile;

    private DataLogger(String filename) {
        this.csvFile = filename;
        this.lock = new ReentrantLock(true);
        initializeCSV();
    }

    public static synchronized DataLogger getInstance() {
        if (instance == null) {
            instance = new DataLogger(Constants.CSV_FILE);
        }
        return instance;
    }

    private void initializeCSV() {
        lock.lock();
        try {
            Path filePath = Paths.get(csvFile);
            if (!Files.exists(filePath)) {
                Files.createDirectories(filePath.getParent());
                try (PrintWriter writer = new PrintWriter(new FileWriter(csvFile))) {
                    writer.println(Constants.CSV_HEADER);
                }
                System.out.println("[INFO] Created new CSV file: " + csvFile);
            }
        } catch (IOException e) {
            System.err.println("[ERROR] Failed to initialize CSV: " + e.getMessage());
        } finally {
            lock.unlock();
        }
    }
    public boolean logReading(CO2Reading reading) {
        lock.lock();
        try (FileWriter fw = new FileWriter(csvFile, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {

            out.println(reading.toCSV());
            return true;

        } catch (IOException e) {
            System.err.println("[ERROR] Failed to write to CSV: " + e.getMessage());
            return false;
        } finally {
            lock.unlock();
        }
    }

    public String getCsvFilePath() {
        return csvFile;
    }
}