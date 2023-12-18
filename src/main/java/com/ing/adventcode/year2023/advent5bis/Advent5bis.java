package com.ing.adventcode.year2023.advent5bis;

import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.List;


public class Advent5bis {
    List<Dimension> seeds = new ArrayList<>();
    Map<Integer, Interval> theMap = new LinkedHashMap<>();
    public Advent5bis() {
        try {
            File file = ResourceUtils.getFile("classpath:year2023/advent5/orig.txt");
            List<String> content = Files.readAllLines(file.toPath());

            processInput(content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void processInput(List<String> content) {
        Interval interval0 = new Interval();
        theMap.put( 0, interval0 );
        long[] s1 = Arrays.stream(content.get(0).split(":")[1].trim().split(" ")).mapToLong(Long::parseLong).toArray();
        for ( int i = 0; i < s1.length; i = i + 2 ) {
            interval0.from.add( new Dimension( s1[i], s1[i] + s1[i+1] - 1 ) );
            interval0.to.add( new Dimension( s1[i], s1[i] + s1[i+1] - 1 ) );
        }

        int index = 0;
        for ( int i = 1; i < content.size(); i++ ) {
            String s = content.get(i);
            if ( "".equals( s ) ) continue;
            if ( s.contains(":") ) {
                index++;
                theMap.put( index, new Interval() );
            }
            else {
                Interval interval = theMap.get(index);
                long[] array = Arrays.stream(s.split("\\s+")).mapToLong(Long::parseLong).toArray();

                interval.from.add( new Dimension( array[ 0 ], array[0] + array[ 2 ] - 1 ) );
                interval.to.add( new Dimension( array[ 1 ], array[1] + array[ 2 ] - 1 ) );
            }
        }
    }


    public String solvePartOne() {
        long lowestLocation = Long.MAX_VALUE;


        return "" + lowestLocation;
    }

    public String solvePartTwo() {
        long a = System.currentTimeMillis();

        for ( long location = 0; location < Long.MAX_VALUE; location++ ) {
            if ( findSeed( location ) > 0 ) {
                System.out.println( "------>" + (System.currentTimeMillis() - a) );

                return "" + location;
            }
        }

        return "";
    }

    private long findSeed(long location) {
        long l = theMap.get(theMap.keySet().size() - 1).travelRangeExtended(location);
        for ( int i = theMap.keySet().size() - 2; i >= 1; i--) {
            l = theMap.get(i).travelRangeExtended(l);
        }
        l = theMap.get(0).travelRange(l);
        return l;
    }
}

class Interval {
    List<Dimension> from = new ArrayList<>();
    List<Dimension> to = new ArrayList<>();

    public long travelRange( long orig ) {
        for (int i = 0; i < from.size(); i++ ) {
            if ( orig >= from.get(i).x && orig <= from.get(i).y ) return to.get(i).x + orig - from.get(i).x;
        }
        return -1;
    }
    public long travelRangeExtended( long orig ) {
        for (int i = 0; i < from.size(); i++ ) {
            if ( orig >= from.get(i).x && orig <= from.get(i).y ) return to.get(i).x + orig - from.get(i).x;
        }
        return orig;
    }
}

class Dimension {
    long x;
    long y;
    public Dimension(long i, long j) {
        x = i;
        y = j;
    }

    public String toString() {
        return "[ " + x + " ... " + y + " ]";
    }
}