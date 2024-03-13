package code;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Rental_Record_Test {
    private static RentalRecord testrentalRecord;

    @BeforeAll
    public static void setup() {
        DateTime rentDate = new DateTime(15, 3, 2024);
        DateTime estimatedReturnDate = new DateTime(20, 3, 2024);
        testrentalRecord = new RentalRecord("R_001", rentDate, estimatedReturnDate);
    }

    @Test
    public void testGetEstimatedReturnDate() {

        DateTime expectedEstimatedReturnDate = new DateTime(20, 3, 2024);


        DateTime actualEstimatedReturnDate = testrentalRecord.getEstimatedReturnDate();


        assertEquals(expectedEstimatedReturnDate.getFormattedDate(), actualEstimatedReturnDate.getFormattedDate());
    }

    @Test
    public void testGetRentDate() {

        DateTime expectedRentDate = new DateTime(15, 3, 2024);
        DateTime actualRentDate = testrentalRecord.getRentDate();


        assertEquals(expectedRentDate.getFormattedDate(), actualRentDate.getFormattedDate());
    }

    @Test
    @Order(1)
    public void testToString_NoAdditionalData() {
        String expected = "R_001:15/03/2024:20/03/2024:none:none:none";
        String actual = testrentalRecord.toString();
        assertEquals(expected, actual);
    }

    @Test
    @Order(3)
    public void testToString_WithAdditionalData() {
        DateTime actualReturnDate = new DateTime(2022, 3, 18);
        Double rentalFee = 50.0;
        Double lateFee = 10.0;

        testrentalRecord.setData(actualReturnDate, rentalFee, lateFee);

        String expected = "R_001:15/03/2024:20/03/2024:12/09/0023:50.0:10.0";
        String actual = testrentalRecord.toString();

        assertEquals(expected, actual);
    }

    @Test
    @Order(2)
    public void testGetDetails_NoAdditionalData() {
        String expected = "Record ID:             R_001\n" +
                "Rent Date:             15/03/2024\n" +
                "Estimated Return Date: 20/03/2024";

        String actual = testrentalRecord.getDetails();
        assertEquals(expected, actual);
    }

    @Test
    @Order(4)
    public void testGetDetails_WithAdditionalData() {
        DateTime actualReturnDate = new DateTime(18, 3, 2024);
        Double rentalFee = 50.0;
        Double lateFee = 10.0;

        testrentalRecord.setData(actualReturnDate, rentalFee, lateFee);

        String expected = "Record ID:             R_001\n" +
                "Rent Date:             15/03/2024\n" +
                "Estimated Return Date: 20/03/2024\n" +
                "Actual Return Date:    18/03/2024\n" +
                "Rental Fee:            50.00\n" +
                "Late Fee:              10.00";

        String actual = testrentalRecord.getDetails();
        assertEquals(expected, actual);
    }
}
