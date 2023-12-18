package com.ing.adventcode.year2022.advent11;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Advent11 {


    String[] command;

    public Advent11() {
        try {
            Path filePath = Path.of("/Users/tg01ej/Developer/middle/Advent/src/main/resources/year2022/advent11/input0.txt");
            command = Files.readString(filePath).trim().split("\n");

        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String solvePartOne() {
        Map<Integer,Monkey> monkeys = parseMonkeys(command);
        for ( int round = 0; round < 20; round++ ) {
            Integer[] array = monkeys.keySet().toArray(new Integer[]{});
            for ( Integer i: array ) {
                processMonkey( monkeys, monkeys.get( i ) );
            }
        }

        return "" + scoreMonkeys( monkeys );
    }

    private int scoreMonkeys(Map<Integer, Monkey> monkeys) {
        Integer[] array = monkeys.keySet().toArray(new Integer[]{});
        List<Integer> score = new ArrayList<Integer>();
        for ( Integer i: array ) {
            score.add( monkeys.get( i ).inspected );
        }
        score.sort( Comparator.reverseOrder() );
        // System.out.println( score );
        return score.get( 0 ) * score.get( 1 );
    }


    private void processMonkey(Map<Integer, Monkey> monkeys, Monkey monkey) {
        monkey.inspected += monkey.items.size();
        ListIterator<BigInteger> it = monkey.items.listIterator();
        while( it.hasNext() ) {
            BigInteger next = it.next();
            it.previous();
            it.remove();

            BigInteger apply = monkey.operacion.apply(next);
            apply = apply.divide( new BigInteger( "3" ) );

            if ( apply.mod( monkey.divisible ).equals( new BigInteger( "0" ) ) ) {
                monkeys.get( monkey.divisibleTrue ).items.add( apply );
            }
            else {
                monkeys.get( monkey.divisibleFalse ).items.add( apply );
            }
        }
    }

    private Map<Integer,Monkey> parseMonkeys(String[] command) {
        Map<Integer,Monkey> result = new HashMap<>();
        Monkey monkey = null;
        for ( String s: command ) {
            if (s.startsWith( "Monkey ") ) {
                int i = Integer.parseInt(s.trim().substring("Monkey ".length(), s.length() - 1));
                monkey = new Monkey( i );
                result.put( i, monkey );
            }
            else if (s.startsWith( "  Starting items:") ) {
                String[] split = s.substring("  Starting items: ".length(), s.length()).split(", ");
                monkey.items = Arrays.stream(split).map(BigInteger::new).collect(Collectors.toList());
            }
            else if (s.startsWith( "  Operation: new = old ") ) {
                String[] split = s.substring("  Operation: new = old ".length(), s.length()).split(" ");
                if ( split[0].equals( "+") ) monkey.operacion = arg -> arg.add( new BigInteger( split[1].trim() ) );
                else if ( split[0].equals( "*") ) {
                    monkey.operacion =  split[1].trim().equals( "old" )?
                            (arg -> arg.multiply( arg )) :
                            (monkey.operacion = arg -> arg.multiply( new BigInteger( split[1].trim() ) ) );
                }
            }
            else if (s.startsWith( "  Test: divisible by ") ) {
                monkey.divisible = new BigInteger( s.substring("  Test: divisible by ".length(), s.length()) );
            }
            else if (s.startsWith( "    If true: throw to monkey ") ) {
                monkey.divisibleTrue = Integer.parseInt( s.substring("    If true: throw to monkey ".length(), s.length()) );
            }
            else if (s.startsWith( "    If false: throw to monkey ") ) {
                monkey.divisibleFalse = Integer.parseInt( s.substring("    If false: throw to monkey ".length(), s.length()) );
            }
        }

        return result;
    }


    public String solvePartTwo() {
        Map<Integer,Monkey> monkeys = parseMonkeys(command);
        for ( int round = 0; round < 10000; round++ ) {
            Integer[] array = monkeys.keySet().toArray(new Integer[]{});
            for ( Integer i: array ) {
                processMonkey2( monkeys, monkeys.get( i ) );
            }
            if ( round % 100 == 0 ) System.out.println( round+1 );
            scoreMonkeys( monkeys );
        }

        return "" + scoreMonkeys( monkeys );
    }
    private void processMonkey2(Map<Integer,Monkey> monkeys, Monkey monkey) {
        monkey.inspected += monkey.items.size();
        ListIterator<BigInteger> it = monkey.items.listIterator();
        while( it.hasNext() ) {
            BigInteger next = it.next();
            it.previous();
            it.remove();

            BigInteger apply = monkey.operacion.apply(next);

            if ( apply.mod( monkey.divisible ).equals( new BigInteger("0" ) ) ) {
                monkeys.get( monkey.divisibleTrue ).items.add( apply );
            }
            else {
                monkeys.get( monkey.divisibleFalse ).items.add( apply );
            }
        }
    }
    class Monkey {
        public int id = 0;
        public List<BigInteger> items;
        public Function<BigInteger, BigInteger> operacion;
        public BigInteger divisible;
        public int divisibleTrue;
        public int divisibleFalse;
        public int inspected;

        public Monkey( int i ) {
            id = i;
        }

    }
}
