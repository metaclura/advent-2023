package year2022.advent5;

import com.ing.adventcode.year2022.advent5.Advent5;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Advent5Test {
    @Test
    public void adventTestPartOne() {
        Advent5 advent = new Advent5();
        String s = advent.solvePartOne();

        assertEquals( "QMBMJDFTD", s );
    }

    @Test
    public void adventTestParTwo() {
        Advent5 advent = new Advent5();
        String s = advent.solvePartTwo();
        System.out.println( s );

        assertEquals( "NBTVTJNFJ", s );
    }
}
