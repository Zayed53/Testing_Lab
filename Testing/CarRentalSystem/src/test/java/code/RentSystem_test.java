package code;

import code.Car;
import code.RentSystem;
import code.Van;
import org.junit.jupiter.api.*;

import java.io.*;
import java.lang.reflect.Field;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RentSystem_test {
    private static RentSystem testrent;


    @BeforeAll
    public static void setup() throws NoSuchFieldException, IllegalAccessException {
        testrent= new RentSystem();


//        Field carsField = RentSystem.class.getDeclaredField("cars");
//        carsField.setAccessible(true);
//        mockcars = new Car[50];
//        carsField.set(testrent, mockcars);

//        Field vansField = RentSystem.class.getDeclaredField("vans");
//        vansField.setAccessible(true);
//        Van[] mockVans = new Van[50];
//        vansField.set(testrent, mockVans);
    }
    @Test
    @Order(1)
    public void add_car_test()  {

        String input = "car\n2022\nToyota\nCorolla\n1234\n4\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Scanner sc = new Scanner(System.in);
        testrent.add(sc);
        assertEquals("C_1234", testrent.cars[0].getVehicleId());


    }

    @Test
    @Order(2)
    public void add_van_test() {

        String input = "van\n2022\nFord\nTransit\n1234\n12/02/2023\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);


        Scanner sc = new Scanner(System.in);


        testrent.add(sc);

        assertEquals("V_1234", testrent.vans[0].getVehicleId());


    }

    @Test
    public void rent_car_test()  {

        String input = "C_1234\n1\n12/03/2024\n3\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Scanner sc = new Scanner(System.in);
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        System.setOut(new PrintStream(outputStream));
        testrent.rent(sc);

        assertEquals(1, testrent.cars[0].vehicleStatus);
//        assertEquals("Vehicle C_1234 is now rented by customer 1", outputStream.toString().trim());
    }





    @Test
    public void return_rent_car_test()  {

        String input = "C_1234\n14/03/2024\n3\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Scanner sc = new Scanner(System.in);
        testrent.returnCar(sc);

        assertEquals(0, testrent.cars[0].vehicleStatus);


    }

    @Test
    public void rent_van_test()  {
        testrent.vans[0] = new Van("V_123", 2019, "Honda", "Odyssey", 0, new VehicleType(15, new DateTime(1, 1, 2023)));
        String input = "V_123\nC_789\n02/01/2023\n5\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        testrent.rent(new Scanner(System.in));
        assertEquals(1, testrent.vans[0].vehicleStatus);
    }




    @Test
    public void vehicle_maintenance_test_car() {
        testrent.cars[0]= new Car("C_1234", 2022, "Toyota", "Corolla", 0, new VehicleType(4));
        testrent.vans[0]= new Van("V_123", 2022, "Ford", "Transit", 0, new VehicleType(15, new DateTime(12, 2, 2023)));
        String input = "C_1234\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));


        Scanner sc = new Scanner(System.in);

        testrent.vehicleMaintenance(sc);

        String printedOutput = outputStream.toString().trim();

        System.setOut(System.out);
        printedOutput=printedOutput.substring(printedOutput.indexOf(": ") + 2);
        String expectedMessage = "Vehicle C_1234 is now under maintenance";

        assertEquals(expectedMessage, printedOutput);
    }

    @Test
    public void vehicle_maintenance_test_van() {
        testrent.cars[0]= new Car("C_1234", 2022, "Toyota", "Corolla", 0, new VehicleType(4));
        testrent.vans[0]= new Van("V_123", 2022, "Ford", "Transit", 0, new VehicleType(15, new DateTime(12, 2, 2023)));
        String input = "V_123\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));


        Scanner sc = new Scanner(System.in);

        testrent.vehicleMaintenance(sc);

        String printedOutput = outputStream.toString().trim();

        System.setOut(System.out);
        printedOutput=printedOutput.substring(printedOutput.indexOf(": ") + 2);
        String expectedMessage = "Vehicle V_123 is now under maintenance";

        assertEquals(expectedMessage, printedOutput);
    }

    @Test
    public void completeMaintenanceTest_car() {


        String input = "C_1234\n31/03/2024\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);


        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));


        Scanner sc = new Scanner(System.in);
        testrent.completeMaintenance(sc);


        String printedOutput = outputStream.toString().trim();
        printedOutput=printedOutput.substring(printedOutput.indexOf(": ") + 2);

        String expectedMessage = "Vehicle C_1234 has all maintenance completed and ready for rent";

        assertEquals(expectedMessage, printedOutput);
    }

    @Test
    public void completeMaintenanceVanTest() {
        testrent.cars[1]= new Car("C_1234", 2022, "Toyota", "Corolla", 0, new VehicleType(4));
        testrent.vans[1]= new Van("V_123", 2022, "Ford", "Transit", 0, new VehicleType(15, new DateTime(12, 2, 2023)));

        String input = "V_123\n31/03/2024\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);


        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));


        Scanner sc = new Scanner(System.in);
        testrent.completeMaintenance(sc);


        String printedOutput = outputStream.toString().trim();
        printedOutput=printedOutput.substring(printedOutput.indexOf(" :") +2);

        String expectedMessage = "Vehicle V_123 has all maintenance completed and ready for rent";

        assertEquals(expectedMessage, printedOutput);
    }

    @Test
    public void completeMaintenanceIncorrectIDTest() {
        this.testrent.vans[0]= new Van("V_123", 2022, "Ford", "Transit", 0, new VehicleType(15, new DateTime(12, 2, 2023)));
        this.testrent.cars[0]= new Car("C_1234", 2022, "Toyota", "Corolla", 0, new VehicleType(4));

        String input = "C_43\n31/03/2024\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);


        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));


        Scanner sc = new Scanner(System.in);
        testrent.completeMaintenance(sc);


        String printedOutput = outputStream.toString().trim();
        printedOutput=printedOutput.substring(printedOutput.indexOf(": ") + 2);
        String expectedMessage = "ID is incorrect, Please try again";

        assertEquals(expectedMessage, printedOutput);


    }

    @Test
    @Order(3)
    public void testGetDetailsWithCarsAndVans() {

        this.testrent.vans[0]= new Van("V_123", 2022, "Ford", "Transit", 0, new VehicleType(15, new DateTime(12, 2, 2023)));
        this.testrent.cars[0]= new Car("C_1234", 2022, "Toyota", "Corolla", 0, new VehicleType(4));

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));


        testrent.getDetails();


        System.setOut(System.out);


        String printedOutput = outputStream.toString().trim();


        String expectedOutput = "***********Cars**********\n" +
                testrent.cars[0].getDetails() + "\n" +
                "***********Vans**********\n" +
                testrent.vans[0].getDetails();

        // Check if the printed output matches the expected output
        assertEquals(expectedOutput, printedOutput);
    }







}
