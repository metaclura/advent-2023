package year2023.advent10;

import com.ing.adventcode.year2023.advent10.Advent10;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Advent10Test {
    @Test
    public void adventTestPartOne() {
        Advent10 advent = new Advent10();
        String s = advent.solvePartOne();
        System.out.println( "solvePartOne " + s );

        assertEquals( "8", s );
    }

    @Test
    public void adventTestParTwo() {
        Advent10 advent = new Advent10();
        String s = "" + advent.solvePartTwo();
        System.out.println( "solvePartTwo " + s );

        assertEquals( "0", s );
    }
}
