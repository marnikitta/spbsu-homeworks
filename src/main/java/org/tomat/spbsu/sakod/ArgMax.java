package org.tomat.spbsu.sakod;

import java.util.function.DoubleFunction;

/***
 * ������� ������� MaxArg(a, b, f), ������� ����������� ��������� ��������,
 * ��� ������� ������ ������� ��������� ���������� �������� �� ��������� �� a �� b.
 *
 * ��� ���� ����� �������� ��� ������� ������� ������ ����������,
 * � ����� ������ ������� (��� ��������� ����� �������� ����� ����������)
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
