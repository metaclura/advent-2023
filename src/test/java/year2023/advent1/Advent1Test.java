package year2023.advent1;

import com.ing.adventcode.year2023.advent1.Advent1;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Advent1Test {
    @Test
    public void adventTestPartOne() {
        Advent1 advent = new Advent1();
        String s = advent.solvePartOne();
        System.out.println( "solvePartOne " + s );

        assertEquals( "56465", s );
    }

    @Test
    public void adventTestParTwo() {
        Advent1 advent = new Advent1();
        String s = "" + advent.solvePartTwo();
        System.out.println( "solvePartTwo " + s );

        assertEquals( "55902", s );
    }
}
