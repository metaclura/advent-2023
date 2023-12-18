package year2021.advent3;

import com.ing.adventcode.year2021.advent3.Advent3;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Advent3Test {
    @Test
    public void adventTestPartOne() {
        Advent3 advent = new Advent3();
        System.out.println( advent.solvePartOne() );
        assertEquals( "", advent.toString() );
    }

    @Test
    public void adventTestParTwo() {
        Advent3 advent = new Advent3();
        String s = advent.solvePartTwo();
        System.out.println( s );

        assertEquals( "230", s );
    }
}
