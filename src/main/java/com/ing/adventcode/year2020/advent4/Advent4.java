package com.ing.adventcode.year2020.advent4;

import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Advent4 {
    List<String> content = new ArrayList<>();
    List<Dato> datos = new ArrayList<>();
    int height = 0;
    public Advent4() {
        try {
            File file = ResourceUtils.getFile("classpath:year2020/advent4/orig.txt");
            Scanner scanner = new Scanner(file);

            String buf = "";
            while (scanner.hasNextLine()) {
                String aux = scanner.nextLine();
                if ( "".equals( aux ) ) {
                    content.add( buf );
                    buf = "";
                }
                else if (aux.length() > 0) {
                    buf += " " + aux;
                }
            }

            for ( String s : content ) {
                String[] s1 = s.trim().split("\\s+");
                datos.add( new Dato( s1 ) );
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String solvePartOne() {
        long result = 0;
        for ( Dato d : datos ) {
            boolean valid = d.isValid();
            if ( valid ) {
                result++;
            }
            if (valid)
                System.out.println( "true " + d );
            else
                System.out.println( "fals " + d );
        }
        return "" + result;
    }


    public String solvePartTwo() {

        return "";
    }


}

class Dato {
    private String byr;
    private String iyr;
    private String eyr;
    private String hgt;
    private String hcl;
    private String ecl;
    private String pid;
    private String cid;

    public Dato(String[] dato ) {
        for ( String d: dato ) {
            String[] d1 = d.split( ":" );
            switch (d1[0]) {
                case "eyr":
                    eyr = d1[1];
                    break;
                case "pid":
                    pid = d1[1];
                    break;
                case "byr":
                    byr = d1[1];
                    break;
                case "ecl":
                    ecl = d1[1];
                    break;
                case "cid":
                    cid = d1[1];
                    break;
                case "hgt":
                    hgt = d1[1];
                    break;
                case "iyr":
                    iyr = d1[1];
                    break;
                case "hcl":
                    hcl = d1[1];
                    break;

            }

        }
    }
    public boolean isValid() {
        try {
            if ("".equals(eyr) || "".equals(pid) || "".equals(byr) || "".equals(ecl) || "".equals(hgt) || "".equals(iyr) || "".equals(hcl))
                throw new RuntimeException("WTF");
            Integer i0 = Integer.parseInt( byr );
            Integer i1 = Integer.parseInt( iyr );
            Integer i2 = Integer.parseInt( eyr );

            Long l = Long.parseLong(pid);
            if ( ecl.length() != 3 ) throw new RuntimeException( "ECL!! ");
            if ( !hgt.endsWith("cm") || !hgt.endsWith("in") ) throw new RuntimeException( "ECL!! ");
            if ( hcl.length() != 7 ) throw new RuntimeException( "ECL!! ");


            if (eyr == null || pid == null && byr == null || ecl == null || hgt == null || iyr == null || hcl == null) return false;
        }
        catch ( Exception ex ) {

        }
        return true;
    }
    @Override
    public String toString() {
        return "[eyr:" + eyr + ", pid:" + pid + ", byr: " + byr + ", ecl: " + ecl + ", cid: " + 0 + ", hgt: " + hgt + ", iyr: " + iyr + ", hcl: " + hcl + "]";
    }
}