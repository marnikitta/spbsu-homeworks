import java.util.concurrent.Semaphore;

/**
 * Created by marnikitta on 12/17/16.
 */
public class Test {
  public static void main(String[] args) throws InterruptedException {
    Semaphore sem = new Semaphore(-1);
    sem.release(2);
    sem.acquire();
    System.out.println("Fuck");
  }
}
