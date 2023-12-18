package year2021.advent4;

import com.ing.adventcode.year2021.advent4.Advent4;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Advent4Test {
    @Test
    public void adventTestPartOne() {
        Advent4 advent = new Advent4();
        String s = advent.solvePartOne();
        System.out.println( s );

        assertEquals( "25410", s );
    }

    @Test
    public void adventTestParTwo() {
        Advent4 advent = new Advent4();
        String s = advent.solvePartTwo();
        System.out.println( s );

        assertEquals( "13398", s );
    }
}
