package com.ing.adventcode.year2023.advent4;

import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Advent4 {
    List<String> content;
    Map<Integer, Integer> cards = new HashMap<>();
    public Advent4() {
        try {
            // Path path = Path.of("/Users/tg01ej/Developer/middle/Advent/src/main/resources/year2023/advent4/input0.txt");

            File file = ResourceUtils.getFile("classpath:year2023/advent4/input0.txt");
            content = Files.readAllLines(file.toPath());
            for ( int i = 0; i < content.size(); i++ ) {
                cards.put( i + 1, 1 );
            }
            //String[] content = content.toArray(new String[0]);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String solvePartOne() {
        int result = 0;
        for ( String aux: content ) {
            String s = aux.split(":")[1].trim();
            result += Math.pow( 2, winnerNumbers( s.split("\\|")[0].trim(), s.split("\\|")[1] ) - 1 );
        }
        return "" + result;
    }

    private int winnerNumbers(String win, String cand ) {
        int result = 0;
        String[] c = cand.split(" ");
        List<Integer> ic = new ArrayList<>();
        for ( String aux : c ) { if (aux.length() > 0) ic.add( Integer.parseInt( aux ) ); }

        for ( String w: win.split(" " ) ) {
            if ( w.length() > 0 ) {
                int iw = Integer.parseInt(w);
                if (ic.contains(iw)) {
                    result++;
                }
            }
        }
        return result;
    }

    public String solvePartTwo() {
        int result = 0;
        int i = 1;
        for ( String aux: content ) {
            Integer multiplier = cards.get(i) - 1;

            String s = aux.split(":")[1].trim();

            List<Integer> wins = processList( s.split("\\|")[0].trim() );
            List<Integer> cands = processList( s.split("\\|")[1].trim() );

            int matches = contMatches( wins, cands );

            if ( matches > 0 ) increaseCards( i, matches, multiplier );


            i++;
        }

        for ( Integer val : cards.values() ) {
            result += val;
        }

        return "" + result;
    }

    private void increaseCards(Integer win, int matches, int multiplier) {
        for ( int i = 0; i < matches; i++ ) {
            Integer aux = cards.get(win + i + 1) + multiplier;
            cards.put( win + i + 1, ++aux );
        }
    }

    private int contMatches(List<Integer> wins, List<Integer> cands) {
        int result = 0;

        for ( Integer win: wins ) {
            if ( cands.contains( win ) ) {
                result++;
            }
        }

        return result;
    }

    private List<Integer> processList(String s) {
        String[] c = s.split(" ");
        List<Integer> ic = new ArrayList<>();
        for ( String aux : c ) {
            if (aux.length() > 0) ic.add( Integer.parseInt( aux ) );
        }
        return ic;
    }



}
