package com.ing.adventcode.year2021.advent4;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

//
public class Advent4 {
    String[] content;
    String[] bingo;
    ArrayList<Carton> cartones;
    public Advent4() {
        Path filePath = Path.of("/Users/tg01ej/Developer/middle/Advent/src/main/resources/year2021/advent4/input0.txt");

        try {
            String aux = Files.readString(filePath);
            content = aux.split("\n\n");
            bingo = content[0].split(",");
            cartones = new ArrayList<Carton>();
            for (int i = 1; i < content.length; i++) {
                String s = content[i];
                Carton carton = new Carton(s);
                cartones.add( carton );
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    public String solvePartTwo() {
        List<String> l = new ArrayList<String>(Arrays.asList(bingo));
        ListIterator<String> s = l.listIterator();
        while( s.hasNext() ) {
            int i = Integer.parseInt(s.next());
            checkWiners(i, cartones);
            if ( returnLastWinner(cartones) != null ) {
                return "" + i * returnLastWinner(cartones).score();
            }

        }

        return "";
    }

    private Carton returnLastWinner(ArrayList<Carton> cartones) {
        Carton result = null;

        for (Carton c: cartones) {
            if ( !c.won && result == null )
                result = c;
            else if ( !c.won && result != null )
                return null;
        }

        return result;
    }

    public String solvePartOne() {
        List<String> l = new ArrayList<String>(Arrays.asList(bingo));
        ListIterator<String> s = l.listIterator();
        while( s.hasNext() ) {
            int i = Integer.parseInt(s.next());
            Carton c = checkWiner(i, cartones);
            if ( c != null ) {
                return "" + i * c.score();
            };
        }

        return "";
    }

    private Carton checkWiner(int i, ArrayList<Carton> cartones) {
        for ( Carton c: cartones ) {
            int i1 = c.checkCartonWinner(i);
            if ( i1 >= 0 ) {
                return c;
            }
        }

        return null;
    }
    private void checkWiners(int i, ArrayList<Carton> cartones) {
        for ( Carton c: cartones ) {
            c.checkCartonWinner(i);
        }
    }

    @Override
    public String toString() {
        return "";
    }

    private class Carton {
        Boolean won = false;
        ArrayList<String[][]> lines = new ArrayList<String[][]>();
        public Carton(String s) {
            String[] split = s.split("\n");

            for ( int i = 0; i < split.length; i++) {
                String[] split1 = split[i].trim().split("\\s+");
                String[][] result = new String[split1.length][ 2 ];
                for ( int j = 0; j < split.length; j++  ) {
                    result[j][0] = split1[ j ];
                }
                lines.add( result );
            }
        }

        public int checkCartonWinner(int i0) {
            int result = -1;

            for ( String[][] line : lines ) {
                for ( int i = 0; i < line.length; i++ ) {
                    int i1 = Integer.parseInt(line[i][0]);
                    if ( i1 == i0 ) {
                        line[ i ][ 1 ] = "1";
                        if ( line[ 0 ][1] == "1" && line[ 1 ][1] == "1" && line[ 2 ][1] == "1" && line[ 3 ][1] == "1" && line[ 4 ][ 1 ] == "1" ) {
                            won = true;
                            result = i0;
                        }
                    }

                }
            }

            return result;
        }

        public int score() {
            int bingo = 0;

            for ( String[][] line : lines ) {
                for ( int i = 0; i < line.length; i++ ) {
                    int i1 = Integer.parseInt(line[i][0]);
                    if ( line[i][1] == null )
                        bingo += i1;
                }
            }

            return bingo;
        }
    }
}
