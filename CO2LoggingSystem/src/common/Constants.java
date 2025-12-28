package common;

public class Constants {
    public static final int DEFAULT_PORT = 5555;
    public static final String DEFAULT_HOST = "localhost";
    public static final String CSV_FILE = "co2_readings.csv";
    public static final String CSV_HEADER = "Timestamp,UserID,Postcode,CO2_PPM";
    public static final int MAX_CLIENTS = 4;
    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    // Validation constants
    public static final int MIN_CO2 = 300;
    public static final int MAX_CO2 = 5000;
    public static final int MIN_USERID_LENGTH = 6;
    public static final int MAX_USERID_LENGTH = 12;
}