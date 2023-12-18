package com.ing.adventcode.year2023.advent1;

import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class Advent1 {
    List<String> content;
    String[] numbers = new String[] { "one", "two", "three", "four", "five", "six", "seven", "eight", "nine" };
    public Advent1() {
        try {
            File file = ResourceUtils.getFile("classpath:year2023/advent1/input0.txt");
            content = Files.readAllLines(file.toPath());
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String solvePartOne() {
        int result = 0;
        for ( String aux: content ) {
            result += processNumber( aux ) * 10 + processNumberLast(aux);
        }
        return "" + result;
    }

    public String solvePartTwo() {
        int result = 0;
        for ( String aux: content ) {
            String aux2 = preparseNumbers( aux );
            result += processNumber( aux2 ) * 10 + processNumberLast(aux2);
        }
        return "" + result;
    }
    private String preparseNumbers(String aux) {
        int i = 1;
        for ( String n: numbers ) {
            aux = aux.replaceAll( n, n + i++ + n);
        }
        return aux;
    }
    private int processNumber(String aux) {
        for ( Character c: aux.toCharArray() ) {
            if ( Character.isDigit( c ) ) return Integer.parseInt("" + c );
        }
        return 0;
    }
    private int processNumberLast(String aux) {
        String result = null;
        for ( Character c: aux.toCharArray() )
            if ( Character.isDigit( c ) ) result = "" + c;
        return Integer.parseInt( result );
    }
}
