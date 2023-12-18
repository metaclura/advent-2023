package com.ing.adventcode.year2020.advent3;

import org.springframework.util.ResourceUtils;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Advent3 {
    List<String> content = new ArrayList<>();
    int height = 0;
    public Advent3() {
        try {
            File file = ResourceUtils.getFile("classpath:year2020/advent3/orig.txt");
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String aux = scanner.nextLine();
                if (aux.length() > 0) {
                    content.add( aux );
                }
            }
            height = content.size();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String solvePartOne() {
        int x = 3;
        int y = 1;
        int result = 0;
        for ( int i = 0; i < height - 1; i++ ) {
            result += isTree( x + 3 * i, y + i );
        }
        return "" + result;
    }

    private int isTree(int x, int y) {
        String s = content.get(y);
        String aux = s;
        for ( int i = 0; i < 10; i++) {
             aux += aux.concat(s);
        }
        if ( aux.charAt( x ) == '#' ) {
            //System.out.println( "# -> " +  aux );
            return 1;
        }
        //System.out.println( ". -> " +  aux );
        return 0;
    }

    public String solvePartTwo() {
        Dimension[] slopes = new Dimension[] {
                new Dimension(1,1),
                new Dimension(3,1),
                new Dimension(5,1),
                new Dimension(7,1),
                new Dimension(1,2),
        };

        long result = 1;
        for ( Dimension d : slopes ) {
            int x = 0;
            int y = 0;
            System.out.println( "Dim x " + x + ", " + y);
            long results = 0;

            int i = 0;
            while ( i * d.height < height ) {
                results += isTree(d.width * i, d.height * i );
                i++;
            }
            result *= results;
        }
        return "" + result;
    }

}
