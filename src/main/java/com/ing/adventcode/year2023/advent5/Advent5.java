package com.ing.adventcode.year2023.advent5;

import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.List;

public class Advent5 {
    Random r = new Random();
    long[] seeds = null;
    Map<String, List<Ranger>> theMap = null;
    public Advent5() {
        try {
            File file = ResourceUtils.getFile("classpath:year2023/advent5/orig.txt");
            List<String> content = Files.readAllLines(file.toPath());

            processInput( content );
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void processInput( List<String> content ) {
        theMap = new LinkedHashMap<>();
        List<Ranger> currentRanger = null;
        for ( String s : content ) {
            String[] split = s.split(":");
            String aux = split[0].trim();
            if ( "".equals( aux ) ) continue;
            else if ( "seeds".equals( aux ) ) processSeeds( split[1].trim() );
            else if ( !Character.isDigit( aux.trim().charAt(0) ) ) {
                currentRanger = new ArrayList<>();
                theMap.put(aux, currentRanger);
            }
            else {
                String[] s1 = aux.split(" ");
                currentRanger.add( new Ranger( s1[0], s1[1], s1[2]) );
            }
        }
    }

    private void processSeeds(String seeds) {
        this.seeds = Arrays.stream(seeds.split(" ")).mapToLong(Long::parseLong).toArray();
    }

    public String solvePartOne() {
        long lowestLocation = Long.MAX_VALUE;

        for ( long s: seeds ) {
            long location = findLocation( s );
            if ( location <= lowestLocation ) lowestLocation = location;
        }
        return "" + lowestLocation;
    }

    private long findLocation(long s) {
        Long destination = s;
        for ( String key : theMap.keySet() ) {
            destination = getDestination(destination, key);
        }
        return destination;
    }

    private Long getDestination(long s, String key) {
        List<Ranger> rangers = theMap.get(key);
        for ( Ranger r : rangers) {
            long destination = r.travel(s);
            if ( destination > 0 ) return destination;
        }
        return s;
    }

    public String solvePartTwo() {
        long lowestLocation = Long.MAX_VALUE;
        SeedMachine seedMachine = new SeedMachine( seeds );
        while( seedMachine.hasNext() ) {
            long next = seedMachine.next();

            long location = findLocation( next );
            if (location < lowestLocation) {
                lowestLocation = location;
            }

        }
        return "" + lowestLocation;
    }

}

class Ranger {
    Long destination;
    Long source;
    Long length;

    public Ranger(String d, String s, String l) {
        destination = Long.parseLong(d);
        source = Long.parseLong(s);
        length = Long.parseLong(l);
    }

    public long travel(long i) {
        if ( i >= source && i <= source + length ) {
            return destination + ( i - source );
        }
        return -1;
    }


}

class LLong {
    Long seed;
    Long number;

    public LLong(long l0, long l1) {
        seed = l0;
        number = l1;
    }
}
class SeedMachine {
    Stack<LLong> seeds = new Stack<>();
    long pointer = 0;
    public SeedMachine(long[] s) {
        for ( int i = 0; i < s.length; i = i + 2 ) {
            seeds.add( 0, new LLong( s[i], s[i+1] ) );
        }
        System.out.println( "poping " + seeds.peek().seed );
    }

    public boolean hasNext() {
        return seeds.size() > 0;
    }

    public long next() {
        LLong peek = seeds.peek();
        Long result = peek.seed + pointer++;
        if ( pointer >= peek.number ) {
            seeds.pop();
            pointer = 0;
            if ( hasNext() ) System.out.println( "poping " + seeds.peek().seed );
        }
        return result;
    }
}