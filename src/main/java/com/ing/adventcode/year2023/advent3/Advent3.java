package com.ing.adventcode.year2023.advent3;



import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class Advent3 {
    Path file;
    String[] content;
    List<Integer> result = new ArrayList<>();
    Map<Dimension, List<Integer>> gears = new HashMap<>();
    public Advent3() {
        file = Path.of("/Users/tg01ej/Developer/middle/Advent/src/main/resources/year2023/advent3/input0.txt");


    }

    public String solvePartOne() {
        try {
            content = Files.readString(file).trim().split("\n");
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

        int i = 0;
        while ( i < content.length ) {
            try {
                processLine(i, content[i].toCharArray());
                i++;
            } catch (RuntimeException r) {
                if (r.getMessage().equals("Reset!")) i = 0;
                else {
                    System.out.println( "Chungooo!" );
                }
            }
        }
        /*for ( String c : content ) {
            System.out.println( c );
        }*/

        return "" + result.stream().collect(Collectors.summingInt(Integer::intValue));
    }

    private void processLine(int i, char[] charArray) {
        for ( int j = 0; j < charArray.length; j ++ ) {
            if ( Character.isDigit(charArray[ j ]) && checkArountDigit( i, j ) ) processNumber( i, j );
        }
    }

    private void processNumber(int i, int j) {
        Dimension number = getNumber(content[ i ], j);
        result.add( number.width );

        int digits = String.valueOf(number.width).length();
        String replaced = ".".repeat(digits);

        String aux = content[i].substring( 0, number.height ) + replaced;

        content[ i ] = aux + content[i].substring( aux.length(), content[ i ].length() );
        throw new RuntimeException( "Reset!" );
    }

    static Dimension getNumber(String s, int i ) {
        String[] n = s.split("" );
        StringBuilder f = new StringBuilder(); // buffer to store numbers
        f.append( n[ i ] );
        int pointer = 1;
        int posNumber = -1;
        boolean right = true;
        boolean left = true;

        do {
            if ( right && i + pointer < s.length() && ( n [ i + pointer ].matches("[0-9]+") ) )
                f.append( n[ i + pointer ] );
            else {
                right = false;
            }
            if ( left && pointer <= i && ( n [ i - pointer ].matches("[0-9]+") ) )
                f.insert( 0, n[ i - pointer ] );
            else {
                left = false;
                posNumber = posNumber < 0? i - pointer + 1: posNumber;
            }
            pointer++;
        } while( right || left );
        if ( f.toString().equals(".") ) {
            System.out.println();
        }
        return new Dimension( Integer.parseInt( f.toString() ), posNumber );
    }

    private boolean checkArountDigit( int i, int j) {
        boolean result = false;
        try { if ( !Character.isDigit( content[ i - 1 ].charAt( j )) && content[ i - 1 ].charAt( j ) != '.' ) return true; } catch (Exception e ){}
        try { if ( !Character.isDigit( content[ i - 1 ].charAt( j - 1 )) && content[ i - 1 ].charAt( j - 1 ) != '.' ) return true; } catch (Exception e ){}
        try { if ( !Character.isDigit( content[ i - 1 ].charAt( j + 1 )) && content[ i - 1 ].charAt( j + 1 ) != '.' ) return true; } catch (Exception e ){}

        try { if ( !Character.isDigit( content[ i ].charAt( j )) && content[ i ].charAt( j ) != '.' ) return true; } catch (Exception e ){}
        try { if ( !Character.isDigit( content[ i ].charAt( j - 1 )) && content[ i ].charAt( j - 1 ) != '.' ) return true; } catch (Exception e ){}
        try { if ( !Character.isDigit( content[ i ].charAt( j + 1 )) && content[ i ].charAt( j + 1 ) != '.' ) return true; } catch (Exception e ){}

        try { if ( !Character.isDigit( content[ i + 1 ].charAt( j )) && content[ i + 1 ].charAt( j ) != '.' ) return true; } catch (Exception e ){}
        try { if ( !Character.isDigit( content[ i + 1 ].charAt( j - 1 )) && content[ i + 1 ].charAt( j - 1 ) != '.' ) return true; } catch (Exception e ){}
        try { if ( !Character.isDigit( content[ i + 1 ].charAt( j + 1 )) && content[ i + 1 ].charAt( j + 1 ) != '.' ) return true; } catch (Exception e ){}

        return result;
    }


    public String solvePartTwo() {
        try {
            content = Files.readString(file).trim().split("\n");
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

        int i = 0;
        while ( i < content.length ) {
            try {
                processLineTwo(i, content[i].toCharArray());
                i++;
            } catch (RuntimeException r) {
                if (r.getMessage().equals("Reset!")) i = 0;
                else {
                    System.out.println( "Chungooo!" );
                }
            }
        }
        for ( String c : content ) {
            System.out.println( c );
        }

        int result = 0;
        for ( List<Integer> l : gears.values() ) {
            if ( l.size() == 2 ) {
                result += l.get(0) * l.get(1);
            }
        }


        return "" + result;
    }

    private void processLineTwo(int i, char[] charArray) {
        for ( int j = 0; j < charArray.length; j ++ ) {
            if ( charArray[ j ] == '*' ) {
                Dimension gearId = new Dimension(j, i);
                gears.put( gearId, new ArrayList<>());
                checkArountGear( gearId );
            }
        }
    }

    private void checkArountGear(Dimension gearId) {
        int result = 0;
        int i = gearId.height;
        int j = gearId.width;
        try { if ( Character.isDigit( content[ i - 1 ].charAt( j - 1 )) ) processGearNumber( gearId, i-1, j-1 ); } catch (Exception e ){}
        try { if ( Character.isDigit( content[ i - 1 ].charAt( j )) ) processGearNumber( gearId, i-1, j ); } catch (Exception e ){}
        try { if ( Character.isDigit( content[ i - 1 ].charAt( j + 1 )) ) processGearNumber( gearId, i-1, j +1 ); } catch (Exception e ){}

        try { if ( Character.isDigit( content[ i ].charAt( j - 1 )) ) processGearNumber( gearId, i, j-1 ); } catch (Exception e ){}
        try { if ( Character.isDigit( content[ i ].charAt( j + 1 )) ) processGearNumber( gearId, i, j +1 ); } catch (Exception e ){}

        try { if ( Character.isDigit( content[ i + 1 ].charAt( j - 1 )) ) processGearNumber( gearId, i + 1, j - 1 ); } catch (Exception e ){}
        try { if ( Character.isDigit( content[ i + 1 ].charAt( j )) ) processGearNumber( gearId, i + 1, j ); } catch (Exception e ){}
        try { if ( Character.isDigit( content[ i + 1 ].charAt( j + 1 )) ) processGearNumber( gearId, i + 1, j + 1 ); } catch (Exception e ){}

    }

    private void processGearNumber(Dimension gearId, int i, int j) {
        Dimension number = getNumber(content[ i ], j);
        //result.add( number.width );
        gears.get(gearId).add( number.width );

        int digits = String.valueOf(number.width).length();
        String replaced = ".".repeat(digits);

        String aux = content[i].substring( 0, number.height ) + replaced;

        content[ i ] = aux + content[i].substring( aux.length(), content[ i ].length() );
        throw new RuntimeException( "Reset!" );
    }
}
