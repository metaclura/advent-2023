package year2023.advent12;

import com.ing.adventcode.year2023.advent12g.Advent12;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Advent12Test {
    @Test
    public void adventTestPartOne() {
        Advent12 advent = new Advent12( "orig" );
        String s = advent.solvePartOne();
        System.out.println( "solvePartOne " + s );

        assertEquals( "7236", s );
    }

    @Test
    public void adventTestParTwo() {
        Advent12 advent = new Advent12( "orig");
        String s = advent.solvePartTwo();
        System.out.println( "solvePartTwo " + s );

        assertEquals( "11607695322318", s );
    }
}
