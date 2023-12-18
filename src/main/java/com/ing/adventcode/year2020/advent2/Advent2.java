package com.ing.adventcode.year2020.advent2;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Advent2 {
    String[] command;

    public Advent2() {
        try {
            Path filePath = Path.of("/Users/tg01ej/Developer/middle/Advent/src/main/resources/year2020/advent2/orig.txt");
            command = Files.readString(filePath).trim().split("\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String solvePartOne() {
        int result = 0;
        for ( String s : command) {
            result += parseCommand( s.split(": ")[0], s.split(": ")[1]);
        }
        return "" + result;
    }

    private int parseCommand(String command, String validation) {
        char letter = command.split( " ")[1].toCharArray()[0];
        int initVal = Integer.parseInt( command.split( " ")[0].split( "-" )[0] );
        int endVal = Integer.parseInt( command.split( " ")[0].split( "-" )[1] );

        int i = 0;
        for ( char c : validation.toCharArray() ) {
            if ( c == letter ) i++;
        }

        return i >= initVal && i <= endVal? 1: 0;

    }

    private int parseCommand2(String command, String validation) {
        char letter = command.split( " ")[1].toCharArray()[0];
        int initVal = Integer.parseInt( command.split( " ")[0].split( "-" )[0] );
        int endVal = Integer.parseInt( command.split( " ")[0].split( "-" )[1] );

        int i = 0;
        if ( validation.toCharArray()[ initVal-1 ] != validation.toCharArray()[ endVal-1 ] &&
                (validation.toCharArray()[ initVal-1 ] == letter ||
                validation.toCharArray()[ endVal-1 ] == letter) )
                return 1;
        return 0;

    }
    public String solvePartTwo() {
        int result = 0;
        for ( String s : command) {
            result += parseCommand2( s.split(": ")[0], s.split(": ")[1]);
        }
        return "" + result;
    }

}