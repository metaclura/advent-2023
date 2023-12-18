package year2020.advent4;

import com.ing.adventcode.year2020.advent1.Advent1;
import com.ing.adventcode.year2020.advent4.Advent4;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Advent4Test {
    @Test
    public void adventTestPartOne() {
        Advent4 advent = new Advent4();
        String s = advent.solvePartOne();
        System.out.println( "solvePartOne " + s );

        assertEquals( "2", s );
    }

    // 10834441
    @Test
    public void adventTestParTwo() {
        Advent4 advent = new Advent4();
        String s = "" + advent.solvePartTwo();
        System.out.println( "solvePartTwo " + s );

        assertEquals( "", s );
    }
}
