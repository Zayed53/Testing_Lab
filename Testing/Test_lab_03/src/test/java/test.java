import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Timeout;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class test {
    private ArrayList<String> list;

    @BeforeAll
    public static void m1() {
        System.out.println("Using @BeforeAll, executed before all test cases");
    }

    @BeforeEach
    public void m2() {
        list = new ArrayList<>();
        System.out.println("Using @BeforeEach annotations, executed before each test cases");
    }

    @AfterAll
    public static void m3() {
        System.out.println("Using @AfterAll, executed after all test cases");
    }

    @AfterEach
    public void m4() {
        list.clear();
        System.out.println("Using @AfterEach, executed after each test cases");
    }

    @Test
    public void m5() {
        list.add("test");
        assertFalse(list.isEmpty());
        assertEquals(1, list.size());
    }

    @Disabled
    @Test
    public void m6() {
        System.out.println("Using @Disabled, this execution is ignored");
    }

    @Test
    @Timeout(value = 20, unit = TimeUnit.NANOSECONDS)
    public void m7() {
        for(int i = 0; i < 1000; i++) {
            System.out.println("Time Out After " + i);
        }
    }
}
