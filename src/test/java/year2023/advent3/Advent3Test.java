package year2023.advent3;

import com.ing.adventcode.year2023.advent3.Advent3;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Advent3Test {
    @Test
    public void adventTestPartOne() {
        Advent3 advent = new Advent3();
        String s = advent.solvePartOne();
        System.out.println( "solvePartOne " + s );

        assertEquals( "4361", s ); // 520140 // 517805
    }

    @Test
    public void adventTestParTwo() {
        Advent3 advent = new Advent3();
        String s = "" + advent.solvePartTwo();
        System.out.println( "solvePartTwo " + s );

        assertEquals( "467835", s );
    }
}
