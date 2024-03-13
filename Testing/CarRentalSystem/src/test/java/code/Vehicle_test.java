package code;

import org.junit.jupiter.api.*;

import java.security.PublicKey;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Vehicle_test {
    private static Vehicle car1;
    private static Vehicle car2;
    private static Vehicle van1;
    private static Vehicle van2;

    private static Vehicle car3;

    private static Vehicle van3;



    @BeforeAll
    public static void setup() {
        car1= new Car("C_123", 2022, "Toyota", "Corolla", 0, new VehicleType(4));
        van1= new Van("V_123", 2022, "Ford", "Transit", 0, new VehicleType(15, new DateTime(12, 2, 2023)));
        car2= new Car("C_1234", 2022, "Toyota", "Corolla", 1, new VehicleType(4));
        van2= new Van("V_1234", 2022, "Ford", "Transit", 1, new VehicleType(15, new DateTime(12, 2, 2023)));
        car3= new Car("C_12345", 2022, "Toyota", "Corolla", 0, new VehicleType(4));
        van3= new Van("V_12345", 2022, "Ford", "Transit", 0, new VehicleType(15, new DateTime(12, 2, 2023)));
    }

    @Test
    public void testRentCar() {
        assertTrue(car1.rent("1", new DateTime(10, 3, 2024), 5));
        assertEquals(1, car1.vehicleStatus);
    }

    @Test
    public void testRentVan() {
        assertTrue(van1.rent("2", new DateTime(13, 2, 2023), 7));
        assertEquals(1, van1.vehicleStatus);
    }



    @Test
    public void testRentCarInvalidRentDays() {

        assertFalse(car1.rent("4", new DateTime(20, 3, 2024), 1));
    }

    @Test
    public void testRentVanInvalidRentDays() {
        assertFalse(van2.rent("1", new DateTime(22, 3, 2024), 15));

    }

    @Test
    public void testRentcarAlreadyRented() {
        assertFalse(car2.rent("2", new DateTime(25, 3, 2024), 4));
    }

    @Test
    public void testRentvanAlreadyRented() {
        assertFalse(van2.rent("2", new DateTime(25, 3, 2024), 4));
    }


    @Test
    public void testRentCarMaximumDays() {
        assertFalse(car1.rent("8", new DateTime(30, 3, 2024), 15));
    }



    @Test
    public void testRentVanZeroDays() {
        assertFalse(van1.rent("10", new DateTime(5, 4, 2024), 0));
    }

    @Test
    public void testRentNegativeDays() {

        assertFalse(van1.rent("11", new DateTime(8, 4, 2024), -3));
        assertFalse(car1.rent("11", new DateTime(8, 4, 2024), -3));

    }

    @Test
    public void testGetVehicleId() {
        assertEquals("C_123", car1.getVehicleId());
        assertEquals("V_123", van1.getVehicleId());
    }

    @Test
    @Order(1)
    public void testPerformMaintenanceCar() {
        assertTrue(car3.performMaintenance());
        assertEquals(2, car3.vehicleStatus);
    }

    @Test
    @Order(2)
    public void testPerformMaintenanceVan() {
        assertTrue(van3.performMaintenance());
        assertEquals(2, van3.vehicleStatus);
    }


    @Test
    @Order(3)
    public void testPerformMaintenance() {
        assertFalse(van3.performMaintenance());
        assertEquals(2, van3.vehicleStatus);
        assertFalse(car3.performMaintenance());
        assertEquals(2, car3.vehicleStatus);
    }
    @Test
    public void testToStringCar() {
        String expectedCarString = "C_123:2022:Toyota:Corolla:4:Available";
        assertEquals(expectedCarString, car1.toString());
    }

    @Test
    public void testToStringVan() {
        String expectedVanString = "V_123:2022:Ford:Transit:15:Available:12/02/2023";
        assertEquals(expectedVanString, van1.toString());
    }

    @Test
    public void testGetDetailsCar() {
        String expectedCarDetails = "Vehicle ID:\tC_1234\n" +
                " Year:\t 2022\n" +
                " Make:\tToyota\n" +
                " Model:\tCorolla\n" +
                " Number of Seats:\t4\n" +
                " Status:\tRented\n" +
                "RENTAL RECORD:\tempty";
        assertEquals(expectedCarDetails, car2.getDetails());
    }

    @Test
    public void testGetDetailsVan() {
        String expectedVanDetails = "Vehicle ID:\tV_1234\n" +
                " Year:\t 2022\n" +
                " Make:\tFord\n" +
                " Model:\tTransit\n" +
                " Number of Seats:\t15\n" +
                " Status:\tRented\n" +
                "Last maintenance date:\t12/02/2023\n" +
                "RENTAL RECORD:\tempty";
        assertEquals(expectedVanDetails, van2.getDetails());
    }

    @Test
    public void testGetLastElementIndex() {

        assertEquals(-1, car1.getLastElementIndex());

        car1.records[0] = new RentalRecord("R_001", new DateTime(20, 3, 2024), new DateTime(15, 3, 2024));
        assertEquals(0, car1.getLastElementIndex());

        car1.records[1] = new RentalRecord("R_002", new DateTime(20, 3, 2024), new DateTime(25, 3, 2024));
        car1.records[2] = new RentalRecord("R_003", new DateTime(1, 4, 2024), new DateTime(5, 4, 2024));
        assertEquals(2, car1.getLastElementIndex());

        assertEquals(-1, van1.getLastElementIndex());


        van1.records[0] = new RentalRecord("R_101", new DateTime(12, 3, 2024), new DateTime(18, 3, 2024));
        assertEquals(0, van1.getLastElementIndex());

        van1.records[1] = new RentalRecord("R_102", new DateTime(22, 3, 2024), new DateTime(28, 3, 2024));
        van1.records[2] = new RentalRecord("R_103", new DateTime(5, 4, 2024), new DateTime(10, 4, 2024));
        assertEquals(2, van1.getLastElementIndex());
    }



}
