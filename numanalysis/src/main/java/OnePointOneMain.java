import ru.spbsu.math.marnikitta.homeworks.numanalysis.matrix.Matrix;

/**
 * Created by marnikitta on 12/17/16.
 */
public class OnePointOneMain {
  public static void main(String[] args) throws InterruptedException {
    final Matrix A = new Matrix(new double[][]{{1.00, 0.99}, {0.99, 0.98}});
    System.out.println("Матрица A:");
    System.out.println(A.niceToString());
    System.out.println();

    final Matrix AInverse = new Matrix(new double[][]{{-9800, 9900}, {9900, -10000}});

    final Matrix b = new Matrix(new double[][]{{1.99}, {1.97}});
    System.out.println("Вектор b:");
    System.out.println(b.niceToString());
    System.out.println();

    final Matrix db = new Matrix(new double[][]{{-0.000097}, {0.000106}});
    System.out.println("Вектор db (возмущение):");
    System.out.println(db.niceToString());
    System.out.println();

    final Matrix x = new Matrix(new double[][]{{1}, {1}});
    System.out.println("Вектор неизвестных x:");
    System.out.println(x.niceToString());
    System.out.println();

    final Matrix xPrime = new Matrix(new double[][]{{3.0000}, {-1.0203}});
    System.out.println("Вектор неизвестных x':");
    System.out.println(xPrime.niceToString());
    System.out.println();

    final Matrix r = A.dot(x).subtract(b);
    System.out.println("Вектор невязки r:");
    System.out.println(r.niceToString());
    System.out.println();

    final Matrix rPrime = A.dot(xPrime).subtract(b.add(db));
    System.out.println("Вектор невязки r':");
    System.out.println(rPrime.niceToString());
    System.out.println();

    final double muA = A.matrixInfNorm() * AInverse.matrixInfNorm();
    System.out.println("Число обусловленности матрицы A:");
    System.out.println(muA);
    System.out.println();
  }
}
