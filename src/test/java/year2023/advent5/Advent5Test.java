package year2023.advent5;

import com.ing.adventcode.year2023.advent5.Advent5;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Advent5Test {
    @Test
    public void adventTestPartOne() {
        Advent5 advent = new Advent5();
        String s = advent.solvePartOne();
        System.out.println( "solvePartOne " + s );

        assertEquals( "35", s );
    }

    // 10834441
    @Test
    public void adventTestParTwo() {
        Advent5 advent = new Advent5();
        String s = "" + advent.solvePartTwo();
        System.out.println( "solvePartTwo " + s );

        assertEquals( "46", s );
    }
}
