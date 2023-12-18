package year2022.advent10;

import com.ing.adventcode.year2022.advent10.Advent10;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Advent10Test {
    @Test
    public void adventTestPartOne() {
        Advent10 advent = new Advent10();
        String s = advent.solvePartOne();
        System.out.println( "solvePartOne " + s );

        assertEquals( "", s );
    }

    @Test
    public void adventTestParTwo() {
        Advent10 advent = new Advent10();
        String s = "" + advent.solvePartTwo();
        System.out.println( "solvePartTwo " + s );

        assertEquals( "", s );
    }
}
