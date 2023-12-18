package year2020.advent10;

import com.ing.adventcode.year2020.advent10.Advent10;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Advent10Test {
    @Test
    public void adventTestPartOne() {
        Advent10 advent = new Advent10();
        String s = advent.solvePartOne();
        System.out.println( "solvePartOne " + s );

        assertEquals( "jolts1: 22 - jolts3: 10", s );
    }

    @Test
    public void adventTestParTwo() {
        Advent10 advent = new Advent10();
        String s = "" + advent.solvePartTwo();
        System.out.println( "solvePartTwo " + s );

        assertEquals( "8", s );
    }
}
