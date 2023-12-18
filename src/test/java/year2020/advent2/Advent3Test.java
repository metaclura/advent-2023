package year2020.advent2;

import com.ing.adventcode.year2020.advent2.Advent2;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Advent3Test {
    @Test
    public void adventTestPartOne() {
        Advent2 advent = new Advent2();
        String s = advent.solvePartOne();
        System.out.println( "solvePartOne " + s );

        assertEquals( "2", s );
    }

    @Test
    public void adventTestParTwo() {
        Advent2 advent = new Advent2();
        String s = "" + advent.solvePartTwo();
        System.out.println( "solvePartTwo " + s );

        assertEquals( "1", s );
    }
}
