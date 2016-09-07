package org.marnikitta.spbsu.sakod;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/***
 * Описать переменную типа List<List<Integer>> (список списков чисел) и заполнить ее как-нибудь
 * (можно прямо задать начальные значения в программе) .
 * Проверить, верно ли, что в этом списке списков во всех подсписках есть и четные и нечетные числа.
 * Напечатать "да" или "нет".
 */
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

        boolean result = list.stream().
                allMatch(li ->
                                (li.stream().anyMatch(i -> (i & 1) == 0)
                                        && li.stream().anyMatch(i -> (i & 1) == 1))
                );

        if (result) {
            System.out.println("yes");
        } else {
            System.out.println("no");
        }
    }
}
