package year2022.advent8;

import com.ing.adventcode.year2022.advent7.Advent7;
import com.ing.adventcode.year2022.advent8.Advent8;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Advent8Test {
    @Test
    public void adventTestPartOne() {
        Advent8 advent = new Advent8();
        String s = advent.solvePartOne();
        System.out.println( s );
        assertEquals( "21", s );
    }

    @Test
    public void adventTestParTwo() {
        Advent8 advent = new Advent8();
        // String s = "" + advent.scenicDistance( 2, 3 );
        String s = "" + advent.solvePartTwo();
        System.out.println( s );

        assertEquals( "8", s );
    }
}
