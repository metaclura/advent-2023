package com.ing.adventcode.year2021.advent2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.ListIterator;

// Primero 7:00
// Segundo 20:00
public class Advent2 {
    String[] content;
    public Advent2() {
        Path filePath = Path.of("/Users/tg01ej/Developer/middle/Advent/src/main/resources/year2021/advent2/input0.txt");

        try {
            String aux = Files.readString(filePath);
            content = aux.split("\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String solvePartOne() {
        Integer resultHoriz = 0;
        Integer resultDeep = 0;

        for (String aux : content) {
            if ( aux.startsWith( "f" ) ) {
                resultHoriz += Integer.parseInt( aux.split( " " )[1] );
            }
            else if ( aux.startsWith( "u" ) ) {
                resultDeep -= Integer.parseInt( aux.split( " " )[1] );
            }
            else if ( aux.startsWith( "d" ) ) {
                resultDeep += Integer.parseInt( aux.split( " " )[1] );
            }

        }

        return "" + resultHoriz * resultDeep;
    }
    public String solvePartTwo() {
        Integer resultHoriz = 0;
        Integer resultDeep = 0;
        Integer resultAim = 0;

        for (String aux : content) {
            if ( aux.startsWith( "f" ) ) {
                resultHoriz += Integer.parseInt( aux.split( " " )[1] );
                resultDeep += resultAim * Integer.parseInt( aux.split( " " )[1] );
            }
            else if ( aux.startsWith( "u" ) ) {
                resultAim -= Integer.parseInt( aux.split( " " )[1] );
            }
            else if ( aux.startsWith( "d" ) ) {
                resultAim += Integer.parseInt( aux.split( " " )[1] );
            }
        }

        return "" + resultHoriz * resultDeep;
    }

    @Override
    public String toString() {
        return "";
    }
}
