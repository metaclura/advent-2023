package com.ing.adventcode.year2021.advent3;
// 1:20
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

// Primero 18:50
public class Advent3 {
    String[] content;
    public Advent3() {
        Path filePath = Path.of("/Users/tg01ej/Developer/middle/Advent/src/main/resources/year2021/advent3/input0.txt");

        try {
            String aux = Files.readString(filePath);
            content = aux.split("\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String solvePartOne() {
        int positions = content[0].length();
        char[] resultG = new char[ positions ];
        char[] resultE = new char[ positions ];
        for ( int i = 0; i < positions; i++ ) {
            int auxTotal = 0;
            for (String aux : content) {
                char[] charArray = aux.toCharArray();
                auxTotal += charArray[ i ] - '0';
            }
            if ( auxTotal >= content.length/2 ) {
                resultG[ i ] = '1';
                resultE[ i ] = '0';
            }
            else {
                resultG[ i ] = '0';
                resultE[ i ] = '1';
            }
        }

        return "" + Integer.parseInt( new String(resultG), 2)  * Integer.parseInt( new String(resultE), 2);
    }

    public String mostCommon(List<String> s, int i ) {
        int auxTotal = 0;
        for (String aux : s) {
            char[] charArray = aux.toCharArray();
            auxTotal += charArray[ i ] - '0';
        }
        if ( auxTotal >= s.size()/2.0 ) {
            return "1";
        }

        return "0";
    }

    public String leastCommon(List<String> s, int i ) {
        int auxTotal = 0;
        for (String aux : s) {
            char[] charArray = aux.toCharArray();
            auxTotal += charArray[ i ] - '0';
        }
        if ( auxTotal >= s.size()/2.0 ) {
            return "0";
        }

        return "1";
    }

    public String solvePartTwo() {
        return "" + Integer.parseInt( new String(solveO2()), 2)  * Integer.parseInt( new String(solveCO2()), 2);
    }

    public String solveO2() {
        List<String> l = new ArrayList<String>(Arrays.asList(content));
        ListIterator<String> stringListIterator = l.listIterator();
        for ( int  i = 0; i < content[0].length(); i++ ) {
            String majority = mostCommon(l, i);

            while (stringListIterator.hasNext()) {
                String next = stringListIterator.next();
                if ( !next.startsWith( majority, i ) ) {
                    stringListIterator.remove();
                }
            }
            if ( l.size() <= 1 ) break;
            while( stringListIterator.hasPrevious() ) stringListIterator.previous();
        }
        return l.get(0);
    }

    public String solveCO2() {
        List<String> l = new ArrayList<String>(Arrays.asList(content));
        ListIterator<String> stringListIterator = l.listIterator();
        for ( int  i = 0; i < content[0].length(); i++ ) {
            String majority = leastCommon(l, i);

            while (stringListIterator.hasNext()) {
                String next = stringListIterator.next();
                if ( !next.startsWith( majority, i ) ) {
                    stringListIterator.remove();
                }
            }
            if ( l.size() <= 1 ) break;
            while( stringListIterator.hasPrevious() ) stringListIterator.previous();

        }
        return l.get(0);
    }

    @Override
    public String toString() {
        return "";
    }
}
