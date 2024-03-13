package code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Van_test {
    public static Van testvan;
    public static Van testvan2;
    public static Van testVanUnderMaintanace;
    @BeforeAll
    public static void setup() {
        testvan= new Van("V_123", 2022, "Ford", "Transit", 0, new VehicleType(15, new DateTime(12, 2, 2023)));
        testvan2= new Van("V_1234", 2022, "Ford", "Transit", 1, new VehicleType(15, new DateTime(12, 2, 2023)));
        testVanUnderMaintanace= new Van("V_12345", 2022, "Ford", "Transit", 2, new VehicleType(15, new DateTime(12, 2, 2023)));
    }

    @Test
    public void testGetLateFee_PositiveDays() {
        DateTime startDate = new DateTime(4, 3, 2024);
        DateTime endDate = new DateTime(6, 3, 2024);
        assertEquals(598.0 , testvan.getLateFee(endDate, startDate));
    }

    @Test
    public void testGetLateFee_NegativeDays() {
        DateTime startDate = new DateTime(10, 3, 2024);
        DateTime endDate = new DateTime(1, 3, 2024);
        assertEquals(0.0, testvan.getLateFee(endDate, startDate));
    }

    @Test
    public void testGetLateFee_SameDay() {
        DateTime startDate = new DateTime(1, 3, 2024);
        DateTime endDate = new DateTime(1, 3, 2024);
        assertEquals(0.0, testvan.getLateFee(endDate, startDate));
    }

    @Test
    public void testReturnVehicleBeforeminimumdays() {

        testvan2.records[0] = new RentalRecord("R_001", new DateTime(2, 3, 2024), new DateTime(5, 3, 2024));
        assertFalse(testvan2.returnVehicle(new DateTime(4, 3, 2024)));


    }

    @Test
    public void testReturnNotRentedVehicle() {

        assertFalse(testvan.returnVehicle(new DateTime(4, 3, 2024)));

    }

    @Test
    public void testReturnVehicle() {

        testvan2.records[0] = new RentalRecord("R_001", new DateTime(2, 3, 2024), new DateTime(5, 3, 2024));
        assertTrue(testvan2.returnVehicle(new DateTime(6, 3, 2024)));
        assertEquals(0, testvan2.vehicleStatus);


    }

    @Test
    public void TestVanNotUnderMaintenance() {
        assertFalse(testvan.completeMaintenance(new DateTime(15, 2, 2023)));
        assertEquals(0, testvan.vehicleStatus);
    }

    @Test
    public void TestVanUnderMaintenance() {
        assertTrue(testVanUnderMaintanace.completeMaintenance(new DateTime(15, 2, 2023)));
        assertEquals(0, testVanUnderMaintanace.vehicleStatus);
    }

    @Test
    public void testGetDetailsEmptyRecords() {
        String expectedDetails = "Vehicle ID:\tV_123\n" +
                " Year:\t 2022\n" +
                " Make:\tFord\n" +
                " Model:\tTransit\n" +
                " Number of Seats:\t15\n" +
                " Status:\tAvailable\n" +
                "Last maintenance date:\t12/02/2023\n" +
                "RENTAL RECORD:\tempty";


        String actualDetails = testvan.getDetails();

        assertEquals(expectedDetails, actualDetails);
    }


    @Test
    public void testGetDetailsWithRecords() {
        testvan2.records[0] = new RentalRecord("R_001", new DateTime(2, 3, 2024), new DateTime(5, 3, 2024));

        String expectedDetails = "Vehicle ID:\tV_1234\n" +
                " Year:\t 2022\n" +
                " Make:\tFord\n" +
                " Model:\tTransit\n" +
                " Number of Seats:\t15\n" +
                " Status:\tRented\n" +
                "Last maintenance date:\t12/02/2023\n" +
                "RENTAL RECORD:\n" +
                "Record ID:             R_001\n" +
                "Rent Date:             02/03/2024\n" +
                "Estimated Return Date: 05/03/2024                     \n----------                     \n";

        String actualDetails = testvan2.getDetails();

        assertEquals(expectedDetails, actualDetails);
    }

}
