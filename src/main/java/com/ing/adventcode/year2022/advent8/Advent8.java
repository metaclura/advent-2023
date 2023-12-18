package com.ing.adventcode.year2022.advent8;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

//
public class Advent8 {
    Integer[][] trees;
    public Advent8() {
        Path filePath = Path.of("/Users/tg01ej/Developer/middle/Advent/src/main/resources/year2022/advent8/input0.txt");

        try {
            String[] split = Files.readString(filePath).trim().split("\n");
            trees = processInput(Arrays.asList(split));
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Integer[][] processInput(List<String> list) {
        Integer[][] result = new Integer[list.get(0).length()][list.size()];
        for ( int y = 0; y < list.size(); y++ ) {
            String[] split = list.get(y).split("");
            for ( int x = 0; x < split.length; x++ ) {
                result[y][x] = Integer.parseInt( split[ x ] );
            }
        }
        return result;
    }

    public boolean isVissibleRight( int x, int y ) {
        boolean result = true;
        for ( int xAux = 0; xAux < x; xAux++ ) {
            if (trees[y][xAux] >= trees[y][x]) {
                return false;
            }
        }
        return result;
    }

    public boolean isVissibleLeft( int x, int y ) {
        boolean result = true;
        for ( int xAux = trees[0].length-1; xAux > x; xAux-- ) {
            if (trees[y][xAux] >= trees[y][x]) {
                return false;
            }
        }
        return result;
    }

    public boolean isVissibleTop( int x, int y ) {
        boolean result = true;
        for ( int yAux = 0; yAux < y; yAux++ ) {
            if (trees[yAux][x] >= trees[y][x]) {
                return false;
            }
        }
        return result;
    }

    public boolean isVissibleBottom( int x, int y ) {
        boolean result = true;
        for ( int yAux = trees.length-1; yAux > y; yAux-- ) {
            if (trees[yAux][x] >= trees[y][x]) {
                return false;
            }
        }
        return result;
    }

    public String solvePartOne() {
        int result = 0;

        for ( int y = 0; y < trees.length; y++ ) {
            for ( int x = 0; x < trees[0].length; x++ ) {
                if ( isVissibleTop( x, y) || isVissibleBottom( x, y) || isVissibleLeft( x, y) || isVissibleRight( x, y) ) {
                    result++;
                }
            }
        }

        return "" + result;
    }

    public int scenicDistanceLeft( int x, int y ) {
        int result = 0;
        for ( int xAux = x-1; xAux >= 0; xAux-- ) {
            result++;
            if (trees[y][xAux] >= trees[y][x]) break;
        }
        return result;
    }

    public int scenicDistanceRight( int x, int y ) {
        int result = 0;
        for ( int xAux = x+1; xAux < trees[0].length; xAux++ ) {
            result++;
            if (trees[y][xAux] >= trees[y][x]) break;
        }
        return result;
    }

    public int scenicDistanceTop( int x, int y ) {
        int result = 0;
        for ( int yAux = y-1; yAux >= 0; yAux-- ) {
            result++;
            if (trees[yAux][x] >= trees[y][x]) break;
        }
        return result;
    }

    public int scenicDistanceBottom( int x, int y ) {
        int result = 0;
        for ( int yAux = y+1; yAux < trees.length; yAux++ ) {
            result++;
            if (trees[yAux][x] >= trees[y][x]) break;
        }
        return result;
    }

    public int scenicDistance( int x, int y ) {
        return scenicDistanceLeft( x, y ) * scenicDistanceRight( x, y) * scenicDistanceTop( x, y) * scenicDistanceBottom( x, y);
    }

    public String solvePartTwo() {
        int result = 0;
        int aux = 0;

        for ( int y = 0; y < trees.length; y++ ) {
            for ( int x = 0; x < trees[0].length; x++ ) {
                aux = scenicDistance( x, y );
                if ( aux > result ) result = aux;
            }
        }

        return "" + result;
    }

}
