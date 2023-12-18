package com.ing.adventcode.year2022.advent7;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

//
public class Advent7 {
    Tree tree;
    public Advent7() {
        Path filePath = Path.of("/Users/tg01ej/Developer/middle/Advent/src/main/resources/year2022/advent7/input0.txt");

        try {
            String[] split = Files.readString(filePath).trim().split("\n");
            tree = processInput( Arrays.asList(split) );
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Tree processInput(List<String> list) {
        tree = new Tree();

        for ( String s: list ) {
            if (s.startsWith( "$ cd") ) {
                tree.navigate( s.substring( "$ cd".length(), s.length() ).trim() );
            }
            else if (s.startsWith( "$ ls" )) {
                continue;
            }
            else if (s.startsWith( "dir" )) {
                tree.insert( s.substring( "dir".length(), s.length() ).trim() );
            }
            else {
                tree.insert( Integer.parseInt( s.split(" ")[0].trim() ), s.split(" ")[1].trim() );
            }

        }
        return tree;
    }

    public class Tree {
        private Node root;
        private Node current;
        public Tree() {
            root = new Node( "/");
            current = root;
        }
        public Node insert(String name) {
            Node aux = new Node(name );
            current.children.add( aux );
            aux.parent = current;

            return current;
        }
        public Node insert(Integer size, String name) {
            Node aux = new Node( size, name );
            current.children.add( aux );
            aux.parent = current;

            return current;
        }

        public Node navigate( String command ) {
            if ( command.equals("/") ) {
                current = root;
            }
            else if ( command.equals("..") && (current.parent != null ) ) {
                current = current.parent;
            }
            else {
                for ( Node n: current.children ) {
                    if ( n.isDir && n.name.equals( command ) ) {
                        current = n;
                        break;
                    }
                }
            }
            return current;
        }

        public int sumSize( Node auxCurrent ) {
            int result = 0;
            if ( auxCurrent.isDir ) {
                for ( Node n : auxCurrent.children ) {
                    result += sumSize( n );
                }
            }
            else {
                result = auxCurrent.size;
            }
            return result;
        }

        public class Node {
            public boolean isDir = false;
            public Integer size;
            public String name;
            public Node parent;
            public List<Node> children;

            public Node( String name ) {
                isDir = true;
                this.name = name;
                children = new ArrayList<>();
            }

            public Node( Integer size, String name ) {
                this.size = size;
                this.name = name;
            }

        }
    }

    public String solvePartOne() {
        List<Tree.Node> list = new ArrayList<>();
        recoverDirsBelowSize( list, 100000, tree.root );
        int result = 0;
        for ( Tree.Node n: list ) {
            result += tree.sumSize( n );
        }

        return "" + result;
    }

    private int recoverDirsBelowSize( List<Tree.Node> list, int max, Tree.Node auxCurrent ) {
        int result = 0;

        if ( auxCurrent.isDir ) {
            for ( Tree.Node n : auxCurrent.children ) {
                result += tree.sumSize( n );
                if ( n.isDir ) recoverDirsBelowSize( list, max, n );
            }
            if ( result < max ) list.add( auxCurrent );
        }
        else {
            result = auxCurrent.size;
        }
        return result;
    }

    private int recoverDirsAbobeEqualsSize( List<Tree.Node> list, int max, Tree.Node auxCurrent ) {
        int result = 0;

        if ( auxCurrent.isDir ) {
            for ( Tree.Node n : auxCurrent.children ) {
                result += tree.sumSize( n );
                if ( n.isDir ) recoverDirsAbobeEqualsSize( list, max, n );
            }
            if ( result >= max ) list.add( auxCurrent );
        }
        else {
            result = auxCurrent.size;
        }
        return result;
    }

    public String solvePartTwo() {
        int emptySpace = 70000000 - tree.sumSize( tree.root );
        int targetCleanSpace = 30000000 - emptySpace;

        List<Tree.Node> list = new ArrayList<>();
        recoverDirsAbobeEqualsSize( list, targetCleanSpace, tree.root );

        int result = 70000000;
        for ( Tree.Node n: list ) {
            if ( result > tree.sumSize(n) ) {
                result = tree.sumSize(n);
            }
        }

        return "" + result;
    }

}
