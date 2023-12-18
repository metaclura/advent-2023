package com.ing.adventcode.year2023.advent9;

import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Advent9 {
    List<Sequencia> content = new ArrayList<>();
    public Advent9() {
        try {
            File file = ResourceUtils.getFile("classpath:year2023/advent9/orig.txt");
            Scanner scanner = new Scanner(file);

            //instructions = scanner.nextLine().split("\\s+");

            while (scanner.hasNextLine()) {
                String aux = scanner.nextLine();
                if (aux.length() > 0) {
                    long[] l = Arrays.stream(aux.split(" ")).mapToLong(Long::parseLong).toArray();
                    content.add( new Sequencia( l )  );
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String solvePartOne() {
        int result = 0;
        for ( Sequencia s: content ) {
            List<Long> ls = s.calcNext();
            result += ls.get( ls.size() - 1 );
        }
        return "" + result;
    }

    public String solvePartTwo() {
        int result = 0;
        for ( Sequencia s: content ) {
            List<Long> ls = s.calcPrev();
            result += ls.get( ls.size() - 1 );
        }
        return "" + result;
    }

}

class Sequencia {
    List<long[]> seq = new ArrayList<>();

    public Sequencia(long[] l) {
        seq.add( l );

        subSeq( l );
    }

    private void subSeq(long[] list) {
        long[] result = new long[ list.length - 1 ];
        Long diff = list[0];
        boolean iterate = false;
        for ( int i = 1; i  < list.length; i++ ) {
            diff = list[i] - list[i-1];
            result[i-1] = diff;
            if ( diff != 0 ) iterate = true;
        }

        seq.add( result );
        if ( iterate ) subSeq( result );
    }

    public List<Long> calcNext() {
        List<Long> result = new ArrayList<>();
        Long val = 0l;
        for ( int i = seq.size() - 1; i >= 1; i-- ) {
            long[] prev = seq.get(i-1);
            result.add( val + prev[ prev.length - 1 ] );
            val += prev[ prev.length - 1 ];
        }
        return result;
    }

    public List<Long> calcPrev() {
        List<Long> result = new ArrayList<>();
        Long val = 0l;
        for ( int i = seq.size() - 1; i >= 1; i-- ) {
            long[] prev = seq.get(i-1);
            result.add( prev[ 0 ] - val );
            val = prev[ 0 ] - val;
        }
        return result;
    }
}