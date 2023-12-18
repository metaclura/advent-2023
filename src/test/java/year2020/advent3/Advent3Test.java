package year2020.advent3;

import com.ing.adventcode.year2020.advent3.Advent3;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Advent3Test {
    @Test
    public void adventTestPartOne() {
        Advent3 advent = new Advent3();
        String s = advent.solvePartOne();
        System.out.println( "solvePartOne " + s );

        assertEquals( "7", s );
    }

    @Test // 428316104 428316104
    public void adventTestParTwo() {
        Advent3 advent = new Advent3();
        String s = "" + advent.solvePartTwo();
        System.out.println( "solvePartTwo " + s );

        assertEquals( "336", s );
    }
}
