package year2022.advent11;

import com.ing.adventcode.year2022.advent11.Advent11;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Advent11Test {
    @Test
    public void adventTestPartOne() {
        Advent11 advent = new Advent11();
        String s = advent.solvePartOne();
        System.out.println( "solvePartOne " + s );

        assertEquals( "10605", s );
    }

    @Test
    public void adventTestParTwo() {
        Advent11 advent = new Advent11();
        String s = "" + advent.solvePartTwo();
        System.out.println( "solvePartTwo " + s );

        assertEquals( "", s );
    }
}
