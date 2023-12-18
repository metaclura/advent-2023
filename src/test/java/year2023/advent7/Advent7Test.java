package year2023.advent7;

import com.ing.adventcode.year2023.advent7.Advent7;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Advent7Test {
    @Test //246647055 //246532713
    public void adventTestPartOne() {
        Advent7 advent = new Advent7();
        String s = advent.solvePartOne();
        System.out.println( "solvePartOne " + s );

        assertEquals( "6440", s );
    }

    // 10834441
    @Test
    public void adventTestParTwo() {
        Advent7 advent = new Advent7();
        String s = "" + advent.solvePartTwo();
        System.out.println( "solvePartTwo " + s );

        assertEquals( "5905", s );
    }
}
