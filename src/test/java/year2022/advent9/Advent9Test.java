package year2022.advent9;

import com.ing.adventcode.year2022.advent9.Advent9;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Advent9Test {
    @Test
    public void adventTestPartOne() {
        Advent9 advent = new Advent9();
        String s = advent.solvePartOne();
        System.out.println( "solvePartOne " + s );

        assertEquals( "13", s );
    }

    @Test
    public void adventTestParTwo() {
        Advent9 advent = new Advent9();
        String s = "" + advent.solvePartTwo();
        System.out.println( "solvePartTwo " + s );

        assertEquals( "36", s );
    }
}
