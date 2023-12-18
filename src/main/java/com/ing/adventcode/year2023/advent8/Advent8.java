package com.ing.adventcode.year2023.advent8;

import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Advent8 {
    List<String> content;
    String[] instructions;
    Map<String, SDim> theMap = new HashMap<>();

    List<String> initNodes = new ArrayList<>();

    Map<Integer, Long> sols = new HashMap<>();
    public Advent8() {
        try {
            File file = ResourceUtils.getFile("classpath:year2023/advent8/orig.txt");
            //content = Files.readAllLines(file.toPath());


            Scanner scanner = new Scanner(file);

            instructions = scanner.nextLine().split("");

            while (scanner.hasNextLine()) {
                String aux = scanner.nextLine();
                if ( aux.length() > 0 ) {
                    String[] split = aux.split("=");
                    String node = split[0].trim();
                    theMap.put( node, new SDim( split[1].trim() ) );
                    if ( node.endsWith( "A" ) ) {
                        initNodes.add( node );
                    }
                }
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String solvePartOne() {
        int result = 0;

        String destination = "AAA";
        int pointer = 0;
        String instruction = null;
        while ( !destination.equals("ZZZ") ) {
            SDim sDim = theMap.get(destination);
            if ( pointer >= instructions.length ) {
                pointer = 0;
            }
            instruction = instructions[ pointer++ ];

            if ( instruction.equals( "L" ) ) destination = sDim.left;
            if ( instruction.equals( "R" ) ) destination = sDim.right;

            result++;
        }

        return "" + result;
    }


    public String solvePartTwo() {
        long result = 0;

        List<String> destinations = initNodes;
        int pointer = 0;
        String instruction = null;
        /*
        if ( result % 1 == 0 ) System.out.print(result + " " );
        for ( String s : destinations ) {
            System.out.print(s + " " );
        }
        System.out.println();
        */
        while ( continuing( destinations, result ) ) {
            if (pointer >= instructions.length) {
                pointer = 0;
            }
            instruction = instructions[pointer++];

            List<String> aux = new ArrayList<>();
            for ( String dest : destinations ) {
                SDim sDim = theMap.get(dest);

                if (instruction.equals("L")) aux.add( sDim.left );
                if (instruction.equals("R")) aux.add( sDim.right );
            }
            destinations = aux;

            result++;
            /*
            if ( result % 10000000 == 0 ){
                System.out.print(result + " " );
                for ( String s : destinations ) {
                    System.out.print(s + " " );
                }
                System.out.println();
            }
            */
        }

        Long[] array = sols.values().toArray(new Long[sols.values().size()]);

        return "" +  calcLcm( array );
    }

    private boolean continuing(List<String> destinations, long cont ) {
        int i = 0;
        boolean result = false;
        for ( String s : destinations ) {
            if ( !s.endsWith( "Z" ) ) {
                result = true;
            }
            else {
                Long sol = sols.get(i);
                if ( sol == null ) {
                    sols.put( i, cont );
                }
                if ( sols.keySet().size() >= destinations.size() ) {
                    return false;
                }
            }
            i++;
        }
        return result;
    }

    public long calcLcm(Long array[]){
        long lcm = array[0];

        for ( int i = 1; i < array.length; i++ )
            lcm = lcm( lcm, array[i] );

        System.out.println("LCM of the given array of numbers : " + lcm);

        return lcm;
    }

    public long gcd( long a, long b ) {
        if (a == 0) return b;
        return gcd(b % a, a );
    }

    public long lcm( long a, long b ) {
        return ( a / gcd(a, b) ) * b;
    }
}

class SDim {
    String left;
    String right;

    public SDim(String s) {
        String[] split = s.substring(1, s.length() - 1).split(",");
        left = split[0].trim();
        right = split[1].trim();
    }
}