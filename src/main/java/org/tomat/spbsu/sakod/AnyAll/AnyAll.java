package org.tomat.spbsu.sakod.AnyAll;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class AnyAll {
    public static void main(String[] args) {
        List<List<Integer>> list = new ArrayList<>();
        Random rd = new Random();
        for (int i = 0; i < 100; ++i) {
            ArrayList<Integer> t = new ArrayList<>();
            for (int j = 0; j < 100; ++j) {
                t.add(rd.nextInt(100));
            }
            list.add(t);
        }

        if (list.stream().flatMap(List::stream).allMatch(x -> (x & 1) == 0)) {
            System.out.println("yes");
        } else {
            System.out.println("no");
        }
    }
}
