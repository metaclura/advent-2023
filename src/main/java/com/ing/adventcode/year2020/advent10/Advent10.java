package com.ing.adventcode.year2020.advent10;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class Advent10 {
    List<Integer> jolts;
    public static HashMap<Integer, Node> pool = new HashMap<>() {
        @Override
        public String toString() {
            StringBuilder stb = new StringBuilder();
            for ( Entry<Integer, Node> e : this.entrySet() ) {
                stb.append( e.getValue().plug + "(" + e.getValue().accumCount + ")\n");
                stb.append( "\t" );
                for (  Node n : e.getValue().branches ) {
                    stb.append( n.plug + "(" + n.accumCount + ") | ");
                }
                stb.append( "\n" );
            }

            return stb.toString();
        }
    };

    public Advent10() {
        try {
            Path filePath = Path.of("/Users/tg01ej/Developer/middle/Advent/src/main/resources/year2020/advent10/orig.txt");
            String[] command = Files.readString(filePath).trim().split("\n");
            jolts = Arrays.stream(command).map( Integer :: parseInt ).collect(Collectors.toList());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String solvePartOne() {
        Collections.sort( jolts );
        Integer prev = 0;
        int count1 = 0;
        int count3 = 1;
        for ( Integer i : jolts ) {
            if ( i - prev == 1 ) count1++;
            else if ( i - prev == 3 ) count3++;
            prev = i;
        }
        return "jolts1: " + count1 + " - jolts3: " + count3;
    }

    public String solvePartTwo() {
        Collections.sort( jolts );
        Node root = new Node( 0 );
        pool.put( 0, root );

        System.out.print( ">>" + root.plug );
        for ( Integer candidate : jolts ) {
            pool.put( candidate, new Node( candidate ) );

            System.out.print( "," + candidate );
            Node aux = pool.get( candidate - 1 );
            if ( aux != null ) aux.insertPlug( candidate );
            aux = pool.get( candidate - 2 );
            if ( aux != null ) aux.insertPlug( candidate );
            aux = pool.get( candidate - 3 );
            if ( aux != null ) aux.insertPlug( candidate );
        }
        long result = inorderIterative(root);

        System.out.println();
        System.out.println( pool.toString() );
        return "" + result;
    }
    public long inorderIterative(Node root) {
        long leavesCount = 0;

        Stack<Node> stack = new Stack<>();
        stack.push( root );
        Node curr = root;
        while ( !stack.empty() || curr != null ) {
            if ( curr.calcBranchesAccum() > 0 ) {
                curr.accumCount = curr.calcBranchesAccum();
                if ( curr == root ) curr = null;
                else curr = stack.pop();
            }
            else if (curr.branches.size() > 0) {
                for ( Node n : curr.branches ) {
                    stack.push(n);
                }
                curr = stack.peek();
            }
        }
        return root.accumCount;
    }

    class Node {
        long accumCount = 0;
        public int plug;
        List<Node> branches = new ArrayList<>();
        public Node( int i ) { plug = i; }

        public void insertPlug(int candidate) {
            Node n = Advent10.pool.get( candidate );
            this.branches.add( n );
        }
        @Override
        public String toString() {
            return "" + plug;
        }

        public long calcBranchesAccum() {
            if (branches.size() <= 0 ) return 1;
            long result = 0;
            for ( Node n : branches ) {
                if ( n.accumCount <= 0 ) return 0;
                result += n.accumCount;
            }
            return result;
        }
    }
}