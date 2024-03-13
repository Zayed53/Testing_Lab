import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class calculateTest {
    @Test
    public void test1(){
        calculate calc = new calculate();
        assertEquals(10, calc.combination(5, 2));
    }

    @Test
    public void test2(){
        calculate calc = new calculate();
        assertEquals(66, calc.combination(12, 10));
    }
    @Test
    public void test4(){
        calculate calc = new calculate();
        assertEquals(105, calc.combination(15, 2));
    }
    @Test
    public void test3(){
        calculate calc = new calculate();
        assertEquals(1, calc.combination(6, 6));
    }

    @Test
    public void test5(){
        calculate calc = new calculate();
        assertEquals(15, calc.combination(15, 14));
    }

    @Test
    public void test6(){
        calculate calc = new calculate();
        assertEquals(1, calc.combination(3, 0));
    }


    @Test
    public void test8(){
        calculate calc = new calculate();
        assertThrows(IllegalArgumentException.class,() ->{        assertEquals(1, calc.combination(0, 0));});
    }


    @Test
    public void test9(){
        calculate calc = new calculate();
        assertThrows(IllegalArgumentException.class,() ->{        assertEquals(1, calc.combination(-14, 0));});
    }


    @Test
    public void test10(){
        calculate calc = new calculate();
        assertThrows(IllegalArgumentException.class,() ->{        assertEquals(1, calc.combination(1000000, 0));});
    }

    @Test
    public void test11(){
        calculate calc = new calculate();
        assertThrows(IllegalArgumentException.class,() ->{        assertEquals(1, calc.combination(2.0, 0));});
    }
}