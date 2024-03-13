package code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Car_Test {
    public static Car testcar;
    public static Car testcar2;
    public static Car testCarUnderMaintanace;
    @BeforeAll
    public static void setup() {
        testcar= new Car("C_123", 2022, "Toyota", "Corolla", 0, new VehicleType(4));
        testcar2= new Car("C_1234", 2022, "Toyota", "Corolla", 1, new VehicleType(4));
        testCarUnderMaintanace= new Car("C_12345", 2022, "Toyota", "Corolla", 2, new VehicleType(4));
    }

    @Test
    public void testGetLateFee_PositiveDays() {
        DateTime startDate = new DateTime(1, 3, 2024);
        DateTime endDate = new DateTime(4, 3, 2024);
        assertEquals(292.5 , testcar.getLateFee(endDate, startDate));
    }

    @Test
    public void testGetLateFee_NegativeDays() {
        DateTime startDate = new DateTime(10, 3, 2024);
        DateTime endDate = new DateTime(1, 3, 2024);
        assertEquals(0.0, testcar.getLateFee(endDate, startDate));
    }

    @Test
    public void testGetLateFee_SameDay() {
        DateTime startDate = new DateTime(1, 3, 2024);
        DateTime endDate = new DateTime(1, 3, 2024);
        assertEquals(0.0, testcar.getLateFee(endDate, startDate));
    }

    @Test
    public void testReturnVehicleBeforeminimumdays() {

        testcar2.records[0] = new RentalRecord("R_001", new DateTime(2, 3, 2024), new DateTime(5, 3, 2024));
        assertFalse(testcar2.returnVehicle(new DateTime(4, 3, 2024)));


    }

    @Test
    public void testReturnNotRentedVehicle() {

        assertFalse(testcar.returnVehicle(new DateTime(4, 3, 2024)));

    }

    @Test
    public void testReturnVehicle() {

        testcar2.records[0] = new RentalRecord("R_001", new DateTime(2, 3, 2024), new DateTime(5, 3, 2024));
        assertTrue(testcar2.returnVehicle(new DateTime(6, 3, 2024)));
        assertEquals(0, testcar2.vehicleStatus);


    }

    @Test
    public void TestCarNotUnderMaintanace() {
        assertFalse(testcar.completeMaintenance());
    }

    @Test
    public void TestCarUnderMaintanace() {
        assertTrue(testCarUnderMaintanace.completeMaintenance());
        assertEquals(0, testCarUnderMaintanace.vehicleStatus);
    }

    @Test
    public void testGetDetailsEmptyRecords() {
        String expectedDetails = "Vehicle ID:\tC_123\n Year:\t 2022\n Make:\tToyota\n Model:\tCorolla\n Number of Seats:\t4\n Status:\tAvailable\nRENTAL RECORD:\tempty";


        String actualDetails = testcar.getDetails();

        assertEquals(expectedDetails, actualDetails);
    }


    @Test
    public void testGetDetailsWithRecords() {
        testcar2.records[0] = new RentalRecord("R_001", new DateTime(2, 3, 2024), new DateTime(5, 3, 2024));

        String expectedDetails = "Vehicle ID:\tC_1234\n Year:\t 2022\n Make:\tToyota\n Model:\tCorolla\n Number of Seats:\t4\n Status:\tRented\n" +
                "RENTAL RECORD\n" +
                "Record ID:             R_001\n" +
                "Rent Date:             02/03/2024\n" +
                "Estimated Return Date: 05/03/2024                     \n----------                     \n";

        String actualDetails = testcar2.getDetails();

        assertEquals(expectedDetails, actualDetails);
    }




}
