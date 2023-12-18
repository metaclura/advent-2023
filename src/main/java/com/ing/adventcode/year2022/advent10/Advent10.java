package com.ing.adventcode.year2022.advent10;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

public class Advent10 {


    String[] command;

    public Advent10() {
        try {
            Path filePath = Path.of("/Users/tg01ej/Developer/middle/Advent/src/main/resources/year2022/advent10/input2.txt");
            command = Files.readString(filePath).trim().split("\n");

        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String solvePartOne() {
        int[] ints = processCommmands(command);
        int result = ints[19] * 20 + ints[59] * 60 + ints[99] * 100 + ints[139] * 140 + ints[179] * 180 + ints[219] * 220;
        return "" + result;
    }

    private int[] processCommmands(String[] command) {
        int[] result = new int[ command.length * 3 ];
        int index = 0;
        int x = 1;
        for ( String s: command ){
            result[ index++ ] = x;
            if ( !s.equals( "noop") ) {
                result[ index++ ] = x;
                x += Integer.parseInt( s.split( " " )[1] );
                result[ index ] = x;
            }
        }
        return result;
    }

    public String solvePartTwo() {
        int[] ints = processCommmands(command);
        String[][] crt = new String[6][40];

        int tic = 0;
        for ( int y=0; y < crt.length; y++ ) {
            for ( int x=0; x < crt[y].length; x++ ) {
                crt[y][x] = paintPixel( ints[ tic++ ], x );
                System.out.print( crt[y][x] );
            }
            System.out.println();
        }


        return "";
    }

    public String paintPixel(int anInt, int x ) {
        if ( x-1 <= anInt && x+1 >= anInt  ) return "#";
        return ".";
    }


}
