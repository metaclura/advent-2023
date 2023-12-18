package year2022.advent6;

import com.ing.adventcode.year2022.advent5.Advent5;
import com.ing.adventcode.year2022.advent6.Advent6;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Advent6Test {
    @Test
    public void adventTestPartOne() {
        Advent6 advent = new Advent6();
        String s = advent.solvePartOne();
        System.out.println( s );
        assertEquals( "5", s );
    }

    @Test
    public void adventTestParTwo() {
        Advent6 advent = new Advent6();
        String s = advent.solvePartTwo();
        System.out.println( s );

        assertEquals( "3037", s );
    }
}
