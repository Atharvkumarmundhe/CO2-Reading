import common.ValidationUtils;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ValidationTest {

    @Test
    public void testValidUserID() {
        assertTrue(ValidationUtils.isValidUserID("user123"));
        assertTrue(ValidationUtils.isValidUserID("USER456"));
        assertTrue(ValidationUtils.isValidUserID("test123456"));
    }

    @Test
    public void testInvalidUserID() {
        assertFalse(ValidationUtils.isValidUserID("usr"));
        assertFalse(ValidationUtils.isValidUserID("verylonguserid123"));
        assertFalse(ValidationUtils.isValidUserID("user@123"));
        assertFalse(ValidationUtils.isValidUserID(""));
        assertFalse(ValidationUtils.isValidUserID(null));
    }

    @Test
    public void testValidPostcode() {
        assertTrue(ValidationUtils.isValidPostcode("CF10 3AT"));
        assertTrue(ValidationUtils.isValidPostcode("M1 1AA"));
        assertTrue(ValidationUtils.isValidPostcode("SW1A 1AA"));
    }

    @Test
    public void testInvalidPostcode() {
        assertFalse(ValidationUtils.isValidPostcode("12345"));
        assertFalse(ValidationUtils.isValidPostcode("CF10"));
        assertFalse(ValidationUtils.isValidPostcode(""));
        assertFalse(ValidationUtils.isValidPostcode(null));
    }

    @Test
    public void testValidCO2Reading() {
        assertTrue(ValidationUtils.isValidCO2Reading(415.5));
        assertTrue(ValidationUtils.isValidCO2Reading(300));
        assertTrue(ValidationUtils.isValidCO2Reading(5000));
    }

    @Test
    public void testInvalidCO2Reading() {
        assertFalse(ValidationUtils.isValidCO2Reading(100));
        assertFalse(ValidationUtils.isValidCO2Reading(6000));
        assertFalse(ValidationUtils.isValidCO2Reading(-50));
    }

    @Test
    public void testSanitizeInput() {
        assertEquals("test123", ValidationUtils.sanitizeInput("test123"));
        assertEquals("test123", ValidationUtils.sanitizeInput("  test123  "));
        assertEquals("test", ValidationUtils.sanitizeInput("test,123"));
        assertEquals("test", ValidationUtils.sanitizeInput("\"test\""));
        assertEquals("", ValidationUtils.sanitizeInput(null));
    }
}