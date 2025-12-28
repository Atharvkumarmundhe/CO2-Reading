package server;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CO2Reading implements Serializable {
    private final String timestamp;
    private final String userID;
    private final String postcode;
    private final double co2ppm;

    public CO2Reading(String userID, String postcode, double co2ppm) {
        this.userID = userID;
        this.postcode = postcode;
        this.co2ppm = co2ppm;
        this.timestamp = LocalDateTime.now().format(
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        );
    }

    public String getTimestamp() { return timestamp; }
    public String getUserID() { return userID; }
    public String getPostcode() { return postcode; }
    public double getCo2ppm() { return co2ppm; }

    public String toCSV() {
        return String.format("\"%s\",\"%s\",\"%s\",%.2f",
                timestamp, userID, postcode, co2ppm);
    }

    @Override
    public String toString() {
        return String.format("CO2Reading{timestamp='%s', userID='%s', postcode='%s', co2ppm=%.2f}",
                timestamp, userID, postcode, co2ppm);
    }
}