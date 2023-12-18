package com.ing.adventcode.year2022.advent6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

//
public class Advent6 {
    List<String> input;
    public Advent6() {
        Path filePath = Path.of("/Users/tg01ej/Developer/middle/Advent/src/main/resources/year2022/advent6/input0.txt");

        try {
            String[] split = Files.readString(filePath).trim().split("");
            input = new ArrayList( Arrays.asList(split) );
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean different( int pos, int amount ) {
        for ( int i = pos; i > pos - amount + 1; i-- ) {
            // System.out.println( input.get( pos - 1 ) + "-" + input.get( i - 2 ) );
            if ( input.get( pos - 1 ).equals( input.get( i - 2 ) ) ) return false;
        }
        if ( amount > 1 ) return different( pos -1, amount - 1 );

        return true;
    }

    public String solvePartOne() {
        int count = 3;
        String next = "";
        Iterator<String> it = input.iterator();
        it.next();it.next();it.next();
        while( it.hasNext() ) {
            next = it.next();
            count++;

            if ( !next.equals( input.get( count - 2) ) &&
                    !next.equals( input.get( count - 3) ) &&
                    !next.equals( input.get( count - 4) ) &&
                    !input.get( count - 2).equals( input.get( count - 3)) &&
                    !input.get( count - 2).equals( input.get( count - 4)) &&
                    !input.get( count - 3).equals( input.get( count - 4)) ) {
                return "" + (count);
            }

        }

        return "";
    }

    public String solvePartTwo() {
        for ( int i = 14; i <= input.size(); i++ ) {
            if ( different( i, 14 ) ) {
                return "" + i;
            }
        }

        return "" + 0;
    }

}
