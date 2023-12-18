package com.ing.adventcode.year2023.advent10;

import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Advent10 {
    List<String> content = new ArrayList<>();
    //List<Long> longContent = new ArrayList<>();
    String[][] theMap = null;
    Dimension start0 = new Dimension( 43, 92);

    Dimension start1 = new Dimension( 0, 2);
    Dimension start2 = new Dimension( 12, 4);
    Dimension start3 = new Dimension( 3, 0);

    List<Dimension> path = new ArrayList<>();
    public Advent10() {
        try {
            File file = ResourceUtils.getFile("classpath:year2023/advent10/orig.txt");
            Scanner scanner = new Scanner(file);

            //instructions = scanner.nextLine().split("\\s+");

            while (scanner.hasNextLine()) {
                String aux = scanner.nextLine();
                content.add( aux );
                // longContent = Arrays.stream(aux.split(" ")).mapToLong(x -> new Long(x)).boxed().collect(Collectors.toList());
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
        int result = 0;

        Dimension start = start0; // start0 = orig

        Dimension prev = start;
        Dimension pointer = new Dimension( prev.x +1, prev.y);
        path.add( pointer );

        Dimension aux = null;

        long it = 1;
        do {
            aux = nextStep( prev, pointer );
            prev = pointer;
            pointer = aux;
            path.add( pointer );

            it++;
        }
        while ( pointer.x != start.x || pointer.y != start.y );

        return "" + it / 2;
    }

    private Dimension nextStep( Dimension prev, Dimension pointer ) {
        String s = theMap[pointer.y][pointer.x];
        if ( "-".equals( theMap[pointer.y][pointer.x] ) && prev.x < pointer.x ) return new Dimension( pointer.x + 1, pointer.y );
        if ( "-".equals( theMap[pointer.y][pointer.x] ) && prev.x > pointer.x ) return  new Dimension( pointer.x - 1, pointer.y );

        if ( "|".equals( theMap[pointer.y][pointer.x] ) && prev.y < pointer.y ) return new Dimension( pointer.x, pointer.y + 1 );
        if ( "|".equals( theMap[pointer.y][pointer.x] ) && prev.y > pointer.y ) return new Dimension( pointer.x, pointer.y - 1 );

        if ( "F".equals( theMap[pointer.y][pointer.x] ) && prev.x > pointer.x ) return new Dimension( pointer.x, pointer.y + 1 );
        if ( "F".equals( theMap[pointer.y][pointer.x] ) && prev.y > pointer.y ) return new Dimension( pointer.x + 1, pointer.y );

        if ( "7".equals( theMap[pointer.y][pointer.x] ) && prev.x < pointer.x ) return new Dimension( pointer.x, pointer.y + 1 );
        if ( "7".equals( theMap[pointer.y][pointer.x] ) && prev.y > pointer.y ) return new Dimension( pointer.x - 1, pointer.y );

        if ( "J".equals( theMap[pointer.y][pointer.x] ) && prev.x < pointer.x ) return new Dimension( pointer.x, pointer.y - 1 );
        if ( "J".equals( theMap[pointer.y][pointer.x] ) && prev.y < pointer.y ) return new Dimension( pointer.x - 1, pointer.y );

        if ( "L".equals( theMap[pointer.y][pointer.x] ) && prev.x > pointer.x ) return new Dimension( pointer.x, pointer.y - 1 );
        if ( "L".equals( theMap[pointer.y][pointer.x] ) && prev.y < pointer.y ) return new Dimension( pointer.x + 1, pointer.y );

        return null;
    }

    public String solvePartTwo() {
        long result = 0;
        solvePartOne();

        for ( int y = 0; y < content.size(); y++ ) {
            for ( int x = 0; x < content.get(0).length(); x++ ) {
                if ( pathContains( x, y ) == null ) {
                    result += isInsideTravelLeft( x, y )? 1:0;
                }
            }
        }

        return "" + result;
    }

    private boolean isInsideTravelLeft(int x, int y) {
        long countPipes = 0;
        for ( int i = x; i >= 0; i-- ) {
            String sDown = pathContains( i, y );
            String sUp = pathContains( i, y - 1 );
            if ( "|".equals( sDown ) && "|".equals( sUp )) countPipes++;
            if ( "|".equals( sDown ) && "7".equals( sUp )) countPipes++;
            if ( "|".equals( sDown ) && "F".equals( sUp )) countPipes++;

            if ( "L".equals( sDown ) && "|".equals( sUp )) countPipes++;
            if ( "L".equals( sDown ) && "7".equals( sUp )) countPipes++;
            if ( "L".equals( sDown ) && "F".equals( sUp )) countPipes++;

            if ( "J".equals( sDown ) && "|".equals( sUp )) countPipes++;
            if ( "J".equals( sDown ) && "7".equals( sUp )) countPipes++;
            if ( "J".equals( sDown ) && "F".equals( sUp )) countPipes++;

        }
        if ( countPipes % 2 == 0 ) return false;
        return true;
    }

    private String pathContains( int x, int  y ) {
        for ( Dimension d : path ) {
            if ( d.equals( x, y ) ) {
                return theMap[ y ][ x ];
            }
        }
        return null;
    }

}

class Dimension {
    int x;
    int y;

    public Dimension(int i, int j) {
        x = i;
        y = j;
    }

    public String toString() {
        return x + "," + y;
    }

    public boolean equals( int i, int j ) {
        if ( x == i && y == j ) return true;
        return false;
    }
}