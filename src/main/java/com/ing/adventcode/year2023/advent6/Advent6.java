package com.ing.adventcode.year2023.advent6;

import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

public class Advent6 {
    List<String> content;
    int[] times = null;
    int[] records = null;
    public Advent6() {
        try {
            File file = ResourceUtils.getFile("classpath:year2023/advent6/input0.txt");
            content = Files.readAllLines(file.toPath());

            String[] t = content.get(0).split(":")[1].trim().split("\\s+");
            times = Arrays.stream(t)
                    .mapToInt(Integer::parseInt)
                    .toArray();
            String[] r = content.get(1).split(":")[1].trim().split("\\s+");
            records = Arrays.stream(r)
                    .mapToInt(Integer::parseInt)
                    .toArray();

        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String solvePartOne() {
        long result = 1;
        for ( int i = 0; i < times.length; i++ ) {
            result *= processRaceWins( times[i], records[i] );
        }
        return "" + result;
    }

    private long processRaceWins(long time, long record) {
        long result = 0;
        for (int speed = 1; speed < time; speed++ ) {
            long lastTime = time - speed;
            long distance = speed * lastTime;
            if ( distance > record ) result++;
        }
        return result;
    }

    public String solvePartTwo() {
        long result = 0;
        String auxt = "";
        for ( int t : times ) {
            auxt += t;
        }
        String auxr = "";
        for ( int r : records ) {
            auxr += r;
        }

        result = processRaceWins( Long.parseLong(auxt), Long.parseLong(auxr) );

        return "" + result;
    }
}
