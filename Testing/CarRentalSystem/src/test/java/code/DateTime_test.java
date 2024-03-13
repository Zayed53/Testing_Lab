package code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateTime_test {
    private static DateTime dateTime;

    @BeforeAll
    public static void setup() {
        dateTime = new DateTime(10, 3, 2024);
    }

    @Test
    public void testGetTime() {
        long expectedTime = dateTime.getTime();
        assertEquals(expectedTime, dateTime.getTime());
    }

    @Test
    public void testGetNameOfDay() {
        String expectedDay = "Sunday";
        assertEquals(expectedDay, dateTime.getNameOfDay());
    }

    @Test
    public void testToString() {
        String expectedString = "10/03/2024";
        assertEquals(expectedString, dateTime.toString());
    }

    @Test
    public void testGetCurrentTime() {
        String expectedCurrentTime = DateTime.getCurrentTime();
        assertEquals(expectedCurrentTime, DateTime.getCurrentTime());
    }

    @Test
    public void testGetFormattedDate() {
        String expectedFormattedDate = "10/03/2024";
        assertEquals(expectedFormattedDate, dateTime.getFormattedDate());
    }

    @Test
    public void testGetEightDigitDate() {
        String expectedEightDigitDate = "10032024";
        assertEquals(expectedEightDigitDate, dateTime.getEightDigitDate());
    }

    @Test
    public void testDiffDays() {
        DateTime startDate = new DateTime(10, 3, 2024);
        DateTime endDate = new DateTime(15, 3, 2024);
        int expectedDiffDays = 5;
        assertEquals(expectedDiffDays, DateTime.diffDays(endDate, startDate));
    }


}
