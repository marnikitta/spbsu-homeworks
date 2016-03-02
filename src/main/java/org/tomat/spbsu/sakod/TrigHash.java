package org.tomat.spbsu.sakod;

public class TrigHash {
    public static class Trig {
        private int x1, y1, x2, y2, x3, y3;

        public Trig(int x1, int y1, int x2, int y2, int x3, int y3) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
            this.x3 = x3;
            this.y3 = y3;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Trig trig = (Trig) o;

            if (x1 != trig.x1) return false;
            if (y1 != trig.y1) return false;
            if (x2 != trig.x2) return false;
            if (y2 != trig.y2) return false;
            if (x3 != trig.x3) return false;
            return y3 == trig.y3;
        }

        @Override
        public int hashCode() {
            int result = x1;
            result = 31 * result + y1;
            result = 31 * result + x2;
            result = 31 * result + y2;
            result = 31 * result + x3;
            result = 31 * result + y3;
            return result;
        }
    }
}
