package code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VehicleType_test {
    public static VehicleType testtypecar;

    public static VehicleType testtypevan;
    @BeforeAll
    public static void setup() {
        testtypecar=new VehicleType(4);
        testtypevan=new VehicleType(15, new DateTime(12, 2, 2024));
    }

    @Test
    public void testGetSeats_Car() {
        assertEquals(4, testtypecar.getSeats("car"));
    }

    @Test
    public void testGetSeats_Van() {
        assertEquals(15, testtypevan.getSeats("van"));
    }

    @Test
    public void testIndexOf() {

        assertEquals(1, testtypecar.indexOf("Monday"));
        assertEquals(4, testtypevan.indexOf("Thursday"));
        assertEquals(-1, testtypevan.indexOf("InvalidDay"));
    }

    @Test
    public void testGetLastMaintenance() {
        DateTime lastMaintenanceCar = new DateTime(10, 2, 2024);
        DateTime lastMaintenanceVan = new DateTime(12, 2, 2024);


        testtypecar.setLastMaintenance(lastMaintenanceCar);
        testtypevan.setLastMaintenance(lastMaintenanceVan);


        assertEquals(lastMaintenanceCar, testtypecar.getLastMaintenance());
        assertEquals(lastMaintenanceVan, testtypevan.getLastMaintenance());
    }

    @Test
    public void testCanBeRentedForMinimumDays_CarWeekday() {
        DateTime date = new DateTime(4, 3, 2024);
        assertEquals(2, testtypecar.canBeRentedForMinimumDays(date, "car"));
    }

    @Test
    public void testCanBeRentedForMinimumDays_CarWeekend() {
        DateTime date = new DateTime(16, 3, 2024);
        assertEquals(3, testtypecar.canBeRentedForMinimumDays(date, "car"));
    }

    @Test
    public void testCanBeRentedForMinimumDays_Van() {
        DateTime date = new DateTime(8, 3, 2024);
        assertEquals(1, testtypevan.canBeRentedForMinimumDays(date, "van"));
    }
    @Test
    public void testIsUnderMaintenance_VanWithinMaintenancePeriod() {
        DateTime rentDate = new DateTime(14, 2, 2024);
        assertFalse(testtypevan.IsUnderMaintenance(rentDate, "van", 9));
    }

    @Test
    public void testIsUnderMaintenance_VanExceedsMaintenancePeriod() {
        DateTime rentDate = new DateTime(1, 3, 2024);
        assertTrue(testtypevan.IsUnderMaintenance(rentDate, "van", 15));
    }

    @Test
    public void testIsUnderMaintenance_Car() {
        DateTime rentDate = new DateTime(4, 3, 2024);
        assertFalse(testtypecar.IsUnderMaintenance(rentDate, "car", 5));
    }


}
