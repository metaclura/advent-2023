package com.ing.adventcode.year2023.advent2;

import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

public class Advent2 {

    HashMap<Integer, List<Mano>> games = new HashMap<>();
    public Advent2() {
        try {
            File file = ResourceUtils.getFile("classpath:year2023/advent2/input0.txt");
            List<String> content = Files.readAllLines(file.toPath());
            preprocess( content );
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void preprocess(List<String> content) {
        int i = 1;
        for ( String c: content ) {
            String aux = c.split(":")[1].trim();
            String[] split = aux.split(";");
            List<Mano> gameList = new ArrayList<>();
            for( String m: split ) {
                gameList.add( Mano.build(m.split(",")));
            }
            games.put( i++, gameList );
        }
    }

    public String solvePartOne() {
        int result = 0;
        Iterator<Map.Entry<Integer, List<Mano>>> it = games.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Integer, List<Mano>> next = it.next();
            if ( checkPossibleMano( next.getValue() ) ) {
                result += next.getKey();
            }
        }

        return "" + result;
    }

    private boolean checkPossibleMano(List<Mano> value) {
        for ( Mano m : value ) {
            if ( !m.check( 12, 13, 14 ) ) return false;
        }
        return true;
    }

    private Mano checkMinimunMano(List<Mano> value) {
        Mano min = new Mano();
        for ( Mano m : value ) {
            if ( m.red > min.red ) min.red = m.red;
            if ( m.green > min.green ) min.green = m.green;
            if ( m.blue > min.blue ) min.blue = m.blue;
        }
        return min;
    }

    public String solvePartTwo() {
        int result = 0;
        Iterator<Map.Entry<Integer, List<Mano>>> it = games.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Integer, List<Mano>> next = it.next();
            Mano m = checkMinimunMano( next.getValue() );
            result += m.red * m.green * m.blue;
        }

        return "" + result;
    }



    private int process(String aux) {
        return 0;
    }


}

class Mano {
    int blue = 0;
    int red = 0;
    int green = 0;

    public static Mano build(String[] split) {
        Mano result = new Mano();
        for ( String c: split ) {
            if ( c.endsWith( "blue" ) ) result.blue = Integer.parseInt( c.substring(0, c.length()-4).trim() );
            if ( c.endsWith( "red" ) ) result.red = Integer.parseInt( c.substring(0, c.length()-3).trim() );
            if ( c.endsWith( "green" ) ) result.green = Integer.parseInt( c.substring(0, c.length()-5).trim() );
        }
        return result;
    }

    public boolean check(int r, int g, int b) {
        if ( r >= red && g >= green && b >= blue ) return true;
        return false;
    }
}