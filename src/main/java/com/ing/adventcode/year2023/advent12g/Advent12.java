package com.ing.adventcode.year2023.advent12g;

import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Advent12 {
    String caseName = null;
    List<String> content = new ArrayList<>();
    static HashMap<HotSpring, Long> cache = new HashMap<>();

    public Advent12(String name) {
        this.caseName = name;
        try {
            File file = ResourceUtils.getFile("classpath:year2023/advent12/" + caseName + ".txt");
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                content.add( scanner.nextLine() );
            }

        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String solvePartOne() {
        List<HotSpring> hotSprings = new ArrayList<>();

        for ( String s : content ) {
            String[] split = s.split(" ");
            int[] cAux = Arrays.stream(split[1].split(","))
                    .mapToInt(Integer::valueOf)
                    .toArray();
            StringBuilder criteria = new StringBuilder( "|" );
            int i = 0;
            for (int j : cAux) {
                criteria.append( "1".repeat( j ) );
                criteria.append( "|" );
            }

            StringBuilder spring = new StringBuilder();
            Character prev = null;
            for ( char c : split[0].toCharArray() ) {
                if ( c != '.' || (Character)c != prev )
                    spring.append( c );
                prev = c;
            }
            hotSprings.add( new HotSpring( "." + spring.toString() + ".", criteria.toString() ) );
        }

        long result = 0;
        for ( HotSpring hotSpring : hotSprings ) {
            cache.clear();
            long arrangements = hotSpring.arrangements();
            System.out.println( hotSpring + " - " + arrangements );
            result += arrangements;
        }

        return "" + result;
    }

    public String solvePartTwo() {
        List<HotSpring> hotSprings = new ArrayList<>();

        for ( String s : content ) {
            String[] split = s.split(" ");
            s = "." + split[0] + "?" + split[0] + "?" + split[0] + "?" + split[0] + "?" + split[0] + ".";

            int[] aux = Arrays.stream(split[1].split(","))
                    .mapToInt(Integer::valueOf)
                    .toArray();
            int[] cAux = new int[ aux.length * 5 ];
            for ( int i = 0; i < 5 * aux.length; i++ ) cAux[ i ] = aux[ i % aux.length ];

            StringBuilder criteria = new StringBuilder( "|" );
            int i = 0;
            for (int j : cAux) {
                criteria.append( "1".repeat( j ) );
                criteria.append( "|" );
            }

            StringBuilder spring = new StringBuilder();
            Character prev = null;
            for ( char c : s.toCharArray() ) {
                if ( c != '.' || (Character)c != prev )
                    spring.append( c );
                prev = c;
            }

            hotSprings.add( new HotSpring( spring.toString(), criteria.toString() ) );
        }


        long result = 0;

        for ( HotSpring hotSpring : hotSprings ) {
            cache.clear();
            long arrangements = hotSpring.arrangements();
            System.out.println( hotSpring + " - " + arrangements );
            result += arrangements;
        }

        return "" + result;
    }

    static class HotSpring {
        String origSpring;
        int springPointer;

        String criteria;
        int criteriaPointer;

        public HotSpring( String origSpring, String criteria ) {
            this.origSpring = origSpring;
            this.springPointer = 0;

            this.criteria = criteria;
            this.criteriaPointer = 0;
        }

        public HotSpring( String origSpring, int springPointer, String criteria, int criteriaPointer ) {
            this.origSpring = origSpring;
            this.springPointer = springPointer;

            this.criteria = criteria;
            this.criteriaPointer = criteriaPointer;
        }

        public long arrangements() {
            Long result = cache.get(this);
            if ( result != null ) return result;

            return countArrangements();
        }
        public long countArrangements() {
            // corte del recursivo
            if ( springPointer >= origSpring.length() ) {
                if ( criteriaPointer >= criteria.length() ) return 1;
                return 0;
            }
            else if ( criteriaPointer >= criteria.length() -1 ) {
                if ( origSpring.substring( springPointer, origSpring.length() ).indexOf( "#" ) < 0 ) return 1;
                return 0;
            }

            long result = 0;
            HotSpring h = null;
            if ( origSpring.charAt(springPointer) == '.'
                    || origSpring.charAt(springPointer) == '?' ) {
                // Principio o separador. Criterio en curso agotado legalmente. Saltar a siguiente criterio.
                if ( criteria.charAt( criteriaPointer ) == '|' ) {
                    h = new HotSpring(origSpring, springPointer + 1, criteria, criteriaPointer + 1 );
                    result += h.arrangements();
                }
                // Preparado el siguiente criterio
                else if ( criteria.charAt( criteriaPointer - 1 ) == '|' ) {
                    h = new HotSpring(origSpring, springPointer + 1, criteria, criteriaPointer );
                    result += h.arrangements();
                }
            }

            if ( origSpring.charAt(springPointer) == '#'
                    || origSpring.charAt(springPointer) == '?' ) {
                // Ver si tenemos criteria para esta averia
                if ( criteria.charAt( criteriaPointer ) != '|' ) {
                    h = new HotSpring(origSpring, springPointer + 1, criteria, criteriaPointer + 1);
                    result += h.arrangements();
                }
            }

            cache.put( this, result );
            return result;
        }

        @Override
        public boolean equals(Object o) {
            if ( this == o ) return true;
            if ( o instanceof HotSpring ) {
                HotSpring h = (HotSpring) o;

                if ( springPointer == h.springPointer
                    && criteriaPointer == h.criteriaPointer)
                    return true;
            }
            return false;
        }

        @Override
        public int hashCode() {
            return 1000 * springPointer + criteriaPointer;
        }

        @Override
        public String toString() {
            return origSpring + " - " + criteria;
        }
    }

}
