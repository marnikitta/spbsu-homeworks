package org.marnikitta.spbsu.sakod;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class PlusCount {
    public static void main(String[] args) {
        new PlusCount().run();
    }

    public void run() {
        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream("plus"))) {
            int singleBalance = 0;
            int doubleBalance = 0;
            int counter = 0;
            while (bufferedInputStream.available() > 0) {
                char c = (char) bufferedInputStream.read();
                switch (c) {
                    case '\'':
                        singleBalance ^= 1;
                        break;
                    case '\"':
                        doubleBalance ^= 1;
                        break;
                    default:
                        if (singleBalance == 0 && doubleBalance == 0 && c == '+') {
                            counter++;
                        }
                }
            }
            System.out.println(counter);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
