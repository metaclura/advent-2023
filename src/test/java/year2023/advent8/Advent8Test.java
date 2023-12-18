package year2023.advent8;

import com.ing.adventcode.year2023.advent8.Advent8;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Advent8Test {
    @Test
    public void adventTestPartOne() {
        Advent8 advent = new Advent8();
        String s = advent.solvePartOne();
        System.out.println( "solvePartOne " + s );

        assertEquals( "6", s );
    }

    @Test
    public void adventTestParTwo() {
        Advent8 advent = new Advent8();
        String s = "" + advent.solvePartTwo();
        System.out.println( "solvePartTwo " + s );

        assertEquals( "6", s );
    }
}
