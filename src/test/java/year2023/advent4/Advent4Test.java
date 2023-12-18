package year2023.advent4;

import com.ing.adventcode.year2023.advent4.Advent4;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Advent4Test {
    @Test
    public void adventTestPartOne() {
        Advent4 advent = new Advent4();
        String s = advent.solvePartOne();
        System.out.println( "solvePartOne " + s );

        assertEquals( "13", s );
    }

    @Test
    public void adventTestParTwo() {
        Advent4 advent = new Advent4();
        String s = "" + advent.solvePartTwo();
        System.out.println( "solvePartTwo " + s );

        assertEquals( "30", s );
    }
}
