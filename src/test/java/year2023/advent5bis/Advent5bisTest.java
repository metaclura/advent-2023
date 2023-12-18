package year2023.advent5bis;

import com.ing.adventcode.year2023.advent5bis.Advent5bis;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Advent5bisTest {
    @Test
    public void adventTestPartOne() {
        Advent5bis advent = new Advent5bis();
        String s = advent.solvePartOne();
        System.out.println( "solvePartOne " + s );

        assertEquals( "35", s );
    }

    @Test
    public void adventTestParTwo() {
        Advent5bis advent = new Advent5bis();
        String s = "" + advent.solvePartTwo();
        System.out.println( "solvePartTwo " + s );

        assertEquals( "46", s );
    }
}
