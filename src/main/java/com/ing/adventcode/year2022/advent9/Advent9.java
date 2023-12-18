package com.ing.adventcode.year2022.advent9;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

public class Advent9 {


    String[] command, command2;
    public Advent9() {
        try {
            Path filePath = Path.of("/Users/tg01ej/Developer/middle/Advent/src/main/resources/year2022/advent9/input0.txt");
            command = Files.readString(filePath).trim().split("\n");
            filePath = Path.of("/Users/tg01ej/Developer/middle/Advent/src/main/resources/year2022/advent9/input2.txt");
            command2 = Files.readString(filePath).trim().split("\n");
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String solvePartOne() {
        Knot aux = new Knot(0, 0 );
        aux = new Knot(0, 0, aux );

        for ( String s : command ) {
            processCommand( aux, s.split( " " )[0], Integer.parseInt( s.split( " " )[1] ));
        }

        return "" + Knot.field.size();
    }

    private void processCommand(Knot head, String command, int amount ) {
        for ( int i = 0; i < amount; i++ ) {
            switch (command) {
                case "U":
                    head.coord.y++;
                    if ( head.isTail() ) Knot.field.add( head.coord.clone() );
                    else head.next.follow( head  );
                    break;
                case "D":
                    head.coord.y--;
                    if ( head.isTail() ) Knot.field.add( head.coord.clone() );
                    else head.next.follow( head  );
                    break;
                case "R":
                    head.coord.x++;
                    if ( head.isTail() ) Knot.field.add( head.coord.clone() );
                    else head.next.follow( head  );
                    break;
                case "L":
                    head.coord.x--;
                    if ( head.isTail() ) Knot.field.add( head.coord.clone() );
                    else head.next.follow( head  );
                    break;
            }
        }
    }

    public String solvePartTwo() {
        Knot aux = new Knot(0, 0 );
        for ( int i = 1; i < 10; i++ ) {
            aux = new Knot(0, 0, aux);
        }
        for ( String s : command2 ) {
            processCommand( aux, s.split( " " )[0], Integer.parseInt( s.split( " " )[1] ));
        }

        return "" + Knot.field.size();
    }



}

class IntDim {
    public int x;
    public int y;

    public IntDim( int x, int y ) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        IntDim i = (IntDim)obj;
        if ( obj instanceof IntDim) return ( this.x == i.x && this.y == i.y );
        return false;
    }

    @Override
    public int hashCode() {
        return x * y;
    }

    public IntDim clone() {
        return new IntDim( x, y );
    }

}
class Knot {
    public static Set<IntDim> field = new HashSet<>();
    public Knot next;
    public IntDim coord;

    public Knot(int x, int y ) {
        this.coord = new IntDim( x, y );
        Knot.field.clear();
    }

    public Knot(int x, int y, Knot next ) {
        this.coord = new IntDim( x, y );
        this.next = next;
        Knot.field.add( this.coord.clone() );
    }

    public boolean isTail() {
        return next == null;
    }

    public static boolean isFar(Knot a, Knot b) {
        return (b.coord.x - a.coord.x) * (b.coord.x - a.coord.x) + (b.coord.y - a.coord.y) * (b.coord.y - a.coord.y) > 3;
    }

    public void follow( Knot lead ) {
        if ( isFar( this, lead ) ) {
            if ( lead.coord.x > this.coord.x ) {
                this.coord.x++;
            }
            else if ( lead.coord.x < this.coord.x ) {
                this.coord.x--;
            }
            if ( lead.coord.y > this.coord.y ) {
                this.coord.y++;
            }
            else if ( lead.coord.y < this.coord.y ) {
                this.coord.y--;
            }
        }

        if ( isTail() ) Knot.field.add( this.coord.clone() );
        else this.next.follow(this);
    }

}