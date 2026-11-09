public class MathUtils {

    static int max(int a, int b) {
        if (a > b) {
            return a;
        }
        return b;
    }

    static int abs(int a) {
        if (a < 0) {
            return -a;
        }
        return a;
    }
}