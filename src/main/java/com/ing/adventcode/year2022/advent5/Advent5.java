package com.ing.adventcode.year2022.advent5;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

//
public class Advent5 {
    List<String[]> input;
    Map<String, List<String>> crates = new HashMap<String, List<String>>();
    public Advent5() {
        Path filePath = Path.of("/Users/tg01ej/Developer/middle/Advent/src/main/resources/year2022/advent5/input0.txt");
        Path filePath2 = Path.of("/Users/tg01ej/Developer/middle/Advent/src/main/resources/year2022/advent5/crates.txt");

        try {
            String[] split = Files.readString(filePath).split("\n");
            input = Arrays.stream(split).map(elem -> elem.replaceFirst("move", "").trim().split("\\s*from\\s*|\\s*to\\s*") ).collect(Collectors.toList());

            split = Files.readString(filePath2).split("\n");
            Object[] array = Arrays.stream(split).map(s -> s.split("")).toArray();
            String[] aux = (String[])array[array.length - 1];
            for ( int i = 1; i <= Integer.parseInt( aux[aux.length-1] ); i++) crates.put( "" + i, new ArrayList<String>() );
            for ( int i = array.length-2; i >= 0; i-- ) {
                for ( int j = 0; j < ((String[])array[i]).length; j+=4) {
                    List<String> crate = crates.get("" + (j/4+1));
                    String s = ((String[]) array[i])[j + 1];
                    if ( s.trim().length() > 0 ) crate.add( s );
                }
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String solvePartOne() {
        input.stream().forEach( order -> processOrder( order[0], order[1], order[2] ) );
        return crates.entrySet().stream().map(v -> v.getValue().get(v.getValue().size() - 1)).collect(Collectors.joining());
    }

    private void processOrder(String s, String s1, String s2) {
        List<String> orig = crates.get( s1 );
        List<String> dest = crates.get( s2 );
        for ( int i = 0; i < Integer.parseInt( s ); i++ ) {
            String remove = orig.remove(orig.size() - 1);
            dest.add( remove );
        }

    }

    public String solvePartTwo() {
        input.stream().forEach( order -> processOrderV1( order[0], order[1], order[2] ) );
        return crates.entrySet().stream().map(v -> v.getValue().get(v.getValue().size() - 1)).collect(Collectors.joining());
    }

    private void processOrderV1(String s, String s1, String s2) {
        List<String> orig = crates.get( s1 );
        List<String> dest = crates.get( s2 );
        ArrayList<String> list = new ArrayList<String>();

        for ( int i = 0; i < Integer.parseInt( s ); i++ ) {
            String remove = orig.remove(orig.size() - 1);
            list.add( 0, remove );
        }
        dest.addAll( list );
    }
}
