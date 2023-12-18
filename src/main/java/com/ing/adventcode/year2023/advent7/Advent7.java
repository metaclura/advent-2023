package com.ing.adventcode.year2023.advent7;

import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Advent7 {
    List<String> content;
    List<Hand> hands;
    List<HHand> hhands;
    public Advent7() {
        try {
            File file = ResourceUtils.getFile("classpath:year2023/advent7/orig.txt");
            content = Files.readAllLines(file.toPath());

            // String[] aux = content.get(0).split(":")[1].trim().split("\\s+");
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String solvePartOne() {
        long result = 0;
        hands = new ArrayList<>();
        for ( String s :  content ) {
            String[] s1 = s.split(" ");
            hands.add( new Hand( s1[0], Integer.parseInt(s1[1])) );
        }

        hands.sort(new Comparator<Hand>() {
            @Override
            public int compare(Hand o1, Hand o2) {
                return o1.compareTo(o2);
            }
        });

        int i = 1;
        for ( Hand h: hands ) {
            System.out.println( h.cards + " " + h.type + " " + h.bid );
            result += h.bid * i++;
        }

        return "" + result;
    }

    public String solvePartTwo() {
        long result = 0;
        hhands = new ArrayList<>();
        for ( String s :  content ) {
            String[] s1 = s.split(" ");
            hhands.add( new HHand( s1[0], Integer.parseInt(s1[1])) );
        }

        hhands.sort(new Comparator<HHand>() {
            @Override
            public int compare(HHand o1, HHand o2) {
                return o1.compareTo(o2);
            }
        });

        int i = 1;
        for ( HHand h: hhands ) {
            System.out.println( h.cards + " " + h.type + " " + h.bid );
            result += h.bid * i++;
        }
        return "" + result;
    }

}

class Hand {
    String cards;
    Integer bid;
    Integer type;

    List<Integer> vals = new ArrayList<Integer>();
    public Hand(String s, int i) {
        cards = s;
        bid = i;
        type = calcType();

        char[] chars = s.toCharArray();
        vals = new ArrayList<>();
        for ( Character c : chars ) {
            if ( Character.isDigit(c) ) vals.add( Integer.parseInt( "" + c) );
            if ( "T".equals( "" + c ) ) vals.add( 10 );
            if ( "J".equals( "" + c ) ) vals.add( 11 );
            if ( "Q".equals( "" + c ) ) vals.add( 12 );
            if ( "K".equals( "" + c ) ) vals.add( 13 );
            if ( "A".equals( "" + c ) ) vals.add( 14 );
        }

    }

    private Integer calcType() {
        char[] chars = cards.toCharArray();

        Arrays.sort(chars);
        String sorted = new String(chars);

        Character prev = sorted.charAt(0);
        int countKind1 = 1;
        int countKind2 = 1;
        boolean close1 = false;
        for ( int i = 1; i < sorted.length(); i++) {
            if ( !close1 && sorted.toCharArray()[i] == prev ) {
                countKind1++;
            }
            else if ( countKind1 > 1 ) {
                close1 = true;
                if ( countKind1 > 1 && sorted.toCharArray()[i] == prev ){
                    countKind2++;
                }
            }

            prev = sorted.toCharArray()[i];
        }

        if ( countKind1 == 5 ) return 6;
        if ( countKind1 == 4 ) return 5;
        if ( countKind1 == 3 && countKind2 == 2 ) return 4;
        if ( countKind1 == 2 && countKind2 == 3 ) return 4;
        if ( countKind1 == 3 && countKind2 == 1 ) return 3;
        if ( countKind1 == 2 && countKind2 == 2 ) return 2;
        if ( countKind1 == 2 ) return 1;
        return 0;
    }

    public int compareTo( Hand h ) {
        if ( type > h.type ) return 1;
        else if ( type < h.type ) return -1;
        for ( int i = 0; i < vals.size(); i++ ) {
            if ( vals.get(i) > h.vals.get(i) ) return 1;
            if ( vals.get(i) < h.vals.get(i) ) return -1;
        }
        return 0;
    }
}

class HHand {
    String cards;
    Integer bid;
    Integer type;

    List<Integer> vals = new ArrayList<Integer>();
    public HHand(String s, int i) {
        cards = s;
        bid = i;
        type = calcType();

        char[] chars = s.toCharArray();
        vals = new ArrayList<>();
        for ( Character c : chars ) {
            if ( Character.isDigit(c) ) vals.add( Integer.parseInt( "" + c) );
            if ( "T".equals( "" + c ) ) vals.add( 10 );
            if ( "J".equals( "" + c ) ) vals.add( 1 );
            if ( "Q".equals( "" + c ) ) vals.add( 12 );
            if ( "K".equals( "" + c ) ) vals.add( 13 );
            if ( "A".equals( "" + c ) ) vals.add( 14 );
        }

    }
    private Integer calcType() {
        int normalType = calcNormalType();
        int js = occurrences();
        if ( js <= 0 || normalType == 6 )  return normalType;

        if ( normalType == 5 ) return 6;


        if ( js == 3 ) {
            if ( normalType == 4 ) return 6; // full -> repoker
            if ( normalType == 3 ) return 5; // trio -> poker
        }
        else if ( js == 2 ) {
            if ( normalType == 4 ) return 6; // full -> repoker
            if ( normalType == 2 ) return 5; // doubles -> poker
            if ( normalType == 1 ) return 3; // pair -> trio
        }
        else { // js = 1
            // if ( normalType == 4 ) return 6; // no full
            if ( normalType == 3 ) return 5; // trio -> poker
            if ( normalType == 2 ) return 4; // doubles -> full
            if ( normalType == 1 ) return 3; // pair -> trio
            if ( normalType == 0 ) return 1; // no -> doubles
        }

        return normalType;
    }

    private int occurrences() {
        int count = 0;
        for (int i = 0; i < cards.length(); i++) {
            if (cards.charAt(i) == 'J') {
                count++;
            }
        }
        return count;
    }

    private Integer calcNormalType() {
        char[] chars = cards.toCharArray();

        Arrays.sort(chars);
        String sorted = new String(chars);

        Character prev = sorted.charAt(0);
        int countKind1 = 1;
        int countKind2 = 1;
        boolean close1 = false;
        for ( int i = 1; i < sorted.length(); i++) {
            if ( !close1 && sorted.toCharArray()[i] == prev ) {
                countKind1++;
            }
            else if ( countKind1 > 1 ) {
                close1 = true;
                if ( countKind1 > 1 && sorted.toCharArray()[i] == prev ){
                    countKind2++;
                }
            }

            prev = sorted.toCharArray()[i];
        }

        if ( countKind1 == 5 ) return 6; // repoker
        if ( countKind1 == 4 ) return 5; // poker4
        if ( countKind1 == 3 && countKind2 == 2 ) return 4; // full
        if ( countKind1 == 2 && countKind2 == 3 ) return 4; // full
        if ( countKind1 == 3 && countKind2 == 1 ) return 3; // trio
        if ( countKind1 == 2 && countKind2 == 2 ) return 2; // dobles
        if ( countKind1 == 2 ) return 1; // pair
        return 0;
    }

    public int compareTo( HHand h ) {
        if ( type > h.type ) return 1;
        else if ( type < h.type ) return -1;
        for ( int i = 0; i < vals.size(); i++ ) {
            if ( vals.get(i) > h.vals.get(i) ) return 1;
            if ( vals.get(i) < h.vals.get(i) ) return -1;
        }
        return 0;
    }
}
