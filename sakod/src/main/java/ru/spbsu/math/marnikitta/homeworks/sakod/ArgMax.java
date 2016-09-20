package ru.spbsu.math.marnikitta.homeworks.sakod;

import java.util.function.DoubleFunction;

/***
 * Описать функцию MaxArg(a, b, f), которая приближенно вычисляет аргумент,
 * при котором данная функция принимает наибольшее значение на интервале от a до b.
 *
 * При этом точно известно что функция сначала строго возрастает,
 * а потом строго убывает (это позволяет найти максимум очень эффективно)
 */

public class ArgMax {
    public static void main(String[] args) {
        new ArgMax().run();
    }

    public void run() {
        System.out.println(argMax(-1, 1, x -> -(x - 1) * (x - 1)));
    }

    public double argMax(double a, double b, DoubleFunction<Double> function) {
        double l = a;
        double r = b;
        double eps = 1e-5;

        while (r - l > eps) {
            double m1 = l + (r - l) / 3,
                    m2 = r - (r - l) / 3;
            if (function.apply(m1) < function.apply(m2)) {
                l = m1;
            } else {
                r = m2;
            }
        }

        return (r + l) / 2;
    }
}
