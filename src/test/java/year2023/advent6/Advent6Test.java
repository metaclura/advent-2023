package year2023.advent6;

import com.ing.adventcode.year2023.advent6.Advent6;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Advent6Test {
    @Test
    public void adventTestPartOne() {
        Advent6 advent = new Advent6();
        String s = advent.solvePartOne();
        System.out.println( "solvePartOne " + s );

        assertEquals( "288", s );
    }

    // 10834441
    @Test
    public void adventTestParTwo() {
        Advent6 advent = new Advent6();
        String s = "" + advent.solvePartTwo();
        System.out.println( "solvePartTwo " + s );

        assertEquals( "71503", s );
    }
}
