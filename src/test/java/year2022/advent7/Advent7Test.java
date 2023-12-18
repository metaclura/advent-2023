package year2022.advent7;

import com.ing.adventcode.year2022.advent6.Advent6;
import com.ing.adventcode.year2022.advent7.Advent7;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Advent7Test {
    @Test
    public void adventTestPartOne() {
        Advent7 advent = new Advent7();
        String s = advent.solvePartOne();
        System.out.println( s );
        assertEquals( "1583951", s );
    }

    @Test
    public void adventTestParTwo() {
        Advent7 advent = new Advent7();
        String s = advent.solvePartTwo();
        System.out.println( s );

        assertEquals( "214171", s );
    }
}
