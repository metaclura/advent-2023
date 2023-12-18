package year2020.advent1;

import com.ing.adventcode.year2020.advent1.Advent1;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Advent1Test {
    @Test
    public void adventTestPartOne() {
        Advent1 advent = new Advent1();
        String s = advent.solvePartOne();
        System.out.println( "solvePartOne " + s );

        assertEquals( "514579", s );
    }

    // 10834441
    @Test
    public void adventTestParTwo() {
        Advent1 advent = new Advent1();
        String s = "" + advent.solvePartTwo();
        System.out.println( "solvePartTwo " + s );

        assertEquals( "241861950", s );
    }
}
