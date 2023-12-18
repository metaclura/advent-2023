package com.ing.adventcode.year2023.advent11;

import org.springframework.util.ResourceUtils;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class Advent11 {
    String caseName = "orig";
    List<String> content = new ArrayList<>();
    String[][] theMap = null;

    List<Integer> expCols = new ArrayList<>();
    List<Integer> expRows = new ArrayList<>();
    public Advent11() {
        try {
            File file = ResourceUtils.getFile("classpath:year2023/advent11/" + caseName + ".txt");
            Scanner scanner = new Scanner(file);

            int i = 0;
            while (scanner.hasNextLine()) {
                String aux = scanner.nextLine();
                content.add( aux );
                if ( !aux.contains( "#") ) {
                    //content.add( aux );
                    expRows.add( i );
                }
                i++;
            }


            theMap = new String[content.size()][content.get(0).split( "" ).length];

            for ( int y = 0; y < content.size(); y++ ) {
                for ( int x = 0; x < content.get(0).length(); x++ ) {
                    theMap[y][x] = content.get(y).split("")[x];
                }
            }



        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String solvePartOne() {
        long result = 0;
        expandYUniverse();
        Map<Integer, Dimension> galax = mapGalaxies();

        for ( int i = 0; i < galax.keySet().size(); i++ ) {
            for ( int j = i; j < galax.keySet().size(); j++ ) {
                result += distance( galax.get(i), galax.get(j) );
            }
        }

        return "" + result;
    }

    private long distance(Dimension d1, Dimension d2) {
        return Math.abs( d2.width - d1.width ) + Math.abs( d2.height - d1.height );
    }

    private long distance(int expFactor, Dimension d1, Dimension d2) {
        long xBase = Math.abs( d2.width - d1.width );
        long yBase = Math.abs( d2.height - d1.height );
        for ( Integer col : expCols ) {
            if ( d1.width < col && d2.width > col ) xBase += expFactor;
            else if ( d2.width < col && d1.width > col ) xBase += expFactor;
        }
        for ( Integer row : expRows ) {
            if ( d1.height < row && d2.height > row ) yBase += expFactor;
            else if ( d2.height < row && d1.height > row ) yBase += expFactor;
        }

        return xBase + yBase;
    }

    private Map<Integer, Dimension> mapGalaxies() {
        Map<Integer, Dimension> galax = new HashMap<>();
        int i = 0;
        for ( int y = 0; y < theMap.length; y++ ) {
            for ( int x = 0; x < theMap[0].length; x++ ) {
                if ( "#".equals( theMap[y][x] ) )
                    galax.put( i++, new Dimension(x, y));
            }
        }

        return galax;
    }

    private void expandYUniverse() {
        boolean expansible = true;

        for ( int x = 0; x < theMap[0].length; x++ ) {
            for (int y = 0; y < theMap.length; y++) {
                String s = theMap[y][x];
                if ( "#".equals( s ) ) expansible = false;
            }
            if ( expansible ) expCols.add( x );
            expansible = true;
        }
/*
        List<String> auxUniv = new ArrayList<>();

        for (int y = 0; y < content.size(); y++) {
            String s = content.get(y);
            for ( int x = 0; x < expCols.size(); x++ ) {
                int expCol = expCols.get(x);
                s = addChar(s, '.', expCol + x );
            }
            auxUniv.add(s);
        }

        theMap = new String[auxUniv.size()][auxUniv.get(0).split( "" ).length];

        for ( int y = 0; y < auxUniv.size(); y++ ) {
            for ( int x = 0; x < auxUniv.get(0).length(); x++ ) {
                theMap[y][x] = auxUniv.get(y).split("")[x];
            }
        }
*/
    }

    public String addChar(String str, char ch, int position) {
        return str.substring(0, position) + ch + str.substring(position);
    }

    // 82000210
    public String solvePartTwo() {
        long result = 0;
        expandYUniverse();
        Map<Integer, Dimension> galax = mapGalaxies();

        for ( int i = 0; i < galax.keySet().size(); i++ ) {
            for ( int j = i; j < galax.keySet().size(); j++ ) {
                result += distance( 1000000-1, galax.get(i), galax.get(j) );
            }
        }

        return "" + result;
    }


}

class Dim {
    long x;
    long y;

    public Dim(long x, long y) {
        this.x = x;
        this.y = y;
    }

    public Dim(Dim d) {
        this.x = d.x;
        this.y = d.y;
    }

    @Override
    public String toString() {
        return x + "," + y;
    }

    @Override
    public boolean equals( Object o ) {
        if ( x == ((Dim)o).x && y == ((Dim)o).y ) return true;
        return false;
    }

    @Override
    public int hashCode() {
        int aux = Long.hashCode(y);
        int sum = Long.hashCode(x) + aux;
        return sum * (sum + 1)/2 + aux;
    }
}