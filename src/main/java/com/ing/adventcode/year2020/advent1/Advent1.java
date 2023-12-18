package com.ing.adventcode.year2020.advent1;

import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Advent1 {
    int[] nums = null;
    public Advent1() {
        try {
            File file = ResourceUtils.getFile("classpath:year2020/advent1/orig.txt");
            Scanner scanner = new Scanner(file);

            //instructions = scanner.nextLine().split("\\s+");

            List<String> content = new ArrayList<>();

            while (scanner.hasNextLine()) {
                String aux = scanner.nextLine();
                if (aux.length() > 0) {
                    content.add( aux );
                }
            }

            nums = content.stream().mapToInt(Integer::parseInt).toArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String solvePartOne() {
        for ( int i = 0; i < nums.length; i++ ) {
            for ( int j = i + 1; j < nums.length; j++ ) {
                if ( nums[i] + nums[j] == 2020 ) return "" + nums[i] * nums[j];
            }
        }
        return "";
    }

    public String solvePartTwo() {
        for ( int i = 0; i < nums.length; i++ ) {
            for ( int j = i + 1; j < nums.length; j++ ) {
                for ( int k = j + 1; k < nums.length; k++ ) {
                    if (nums[i] + nums[j] + nums[k] == 2020) return "" + nums[i] * nums[j] * nums[k];
                }
            }
        }
        return "";
    }

}
