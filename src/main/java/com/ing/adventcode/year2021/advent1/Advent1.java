package com.ing.adventcode.year2021.advent1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

// Primero 7:00
// Segundo 20:00
class Advent1 {
    String[] content;
    public Advent1() {
        Path filePath = Path.of("/Users/tg01ej/Developer/middle/Advent/src/main/resources/year2021/advent1/input0.txt");

        try {
            String aux = Files.readString(filePath);
            content = aux.split("\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String solvePartOne() {
        Integer prev = null;
        Integer result = 0;
        for ( String aux: content ) {
            int i = Integer.parseInt(aux);
            if ( prev != null &&  i > prev ) {
                    result++;
                }
                prev = i;
            }
        return result.toString();
    }
    public String solvePartTwo() {
        Integer prev = null;
        Integer result = 0;

        ListIterator<String> it = Arrays.asList(content).listIterator();
        try {
            while ( it.hasNext() ) {
                int i1 = Integer.parseInt(it.next()) + Integer.parseInt(it.next()) + Integer.parseInt(it.next());
                if (prev != null && (i1) > prev) {
                    result++;
                }
                prev = i1;
                it.previous();
                it.previous();
            }
        }
        catch( Exception e) {}

        return result.toString();
    }
    @Override
    public String toString() {
        return "";
    }
}
