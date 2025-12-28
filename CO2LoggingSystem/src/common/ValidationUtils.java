package common;

public class ValidationUtils {

    public static boolean isValidUserID(String userID) {
        if (userID == null || userID.trim().isEmpty()) {
            return false;
        }
        String sanitized = sanitizeInput(userID);
        return sanitized.matches("^[A-Za-z0-9]{" +
                Constants.MIN_USERID_LENGTH + "," +
                Constants.MAX_USERID_LENGTH + "}$");
    }

    public static boolean isValidPostcode(String postcode) {
        if (postcode == null) return false;
        String sanitized = sanitizeInput(postcode);
        return sanitized.matches("^[A-Z]{1,2}[0-9][A-Z0-9]?\\s*[0-9][A-Z]{2}$");
    }

    public static boolean isValidCO2Reading(double co2ppm) {
        return co2ppm >= Constants.MIN_CO2 && co2ppm <= Constants.MAX_CO2;
    }

    public static String sanitizeInput(String input) {
        if (input == null) return "";
        return input.trim()
                .replace(",", "")
                .replace("\"", "")
                .replace("\n", "")
                .replace("\r", "");
    }

    public static boolean isValidPort(int port) {
        return port >= 1024 && port <= 65535;
    }
}