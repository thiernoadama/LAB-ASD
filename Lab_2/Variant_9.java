import java.util.Scanner;

/**
 * Лабораторная работа №2
 * Thierno Adama Diongue
 */
public class Variant_9 {

    static class BandMatrix {
        int n;
        int kl;
        int ku;
        int[][] band;

        BandMatrix(int n, int kl, int ku) {
            this.n = n;
            this.kl = kl;
            this.ku = ku;
            this.band = new int[n][kl + ku + 1];
        }

        int get(int i, int j) {
            if (j < i - kl || j > i + ku) {
                return 0;
            }
            return band[i][j - i + kl];
        }
    }

    static class CCSMatrix {
        int n;
        int[] an;
        int[] ia;
        int[] ja;

        CCSMatrix(int n, int[] an, int[] ia, int[] ja) {
            this.n = n;
            this.an = an;
            this.ia = ia;
            this.ja = ja;
        }
    }

    static int max(int a, int b) {
        if (a > b) {
            return a;
        }
        return b;
    }

    static int min(int a, int b) {
        if (a < b) {
            return a;
        }
        return b;
    }

    static BandMatrix readBandMatrix(Scanner scanner, int n, String name) {
        System.out.print("Матрица " + name + ": нижняя ширина ленты kl и верхняя ku: ");
        int kl = scanner.nextInt();
        int ku = scanner.nextInt();
        BandMatrix m = new BandMatrix(n, kl, ku);

        for (int i = 0; i < n; i++) {
            int from = max(0, i - kl);
            int to = min(n - 1, i + ku);
            System.out.print("Строка " + i + " (столбцы " + from + ".." + to + "): ");
            for (int j = from; j <= to; j++) {
                m.band[i][j - i + kl] = scanner.nextInt();
            }
        }
        return m;
    }

    static CCSMatrix addToCCS(BandMatrix a, BandMatrix b) {
        int n = a.n;
        int kl = max(a.kl, b.kl);
        int ku = max(a.ku, b.ku);

        int capacity = n * (kl + ku + 1);
        int[] an = new int[capacity];
        int[] ia = new int[capacity];
        int[] ja = new int[n + 1];
        int count = 0;

        for (int j = 0; j < n; j++) {
            ja[j] = count;
            int from = max(0, j - ku);
            int to = min(n - 1, j + kl);
            for (int i = from; i <= to; i++) {
                int sum = a.get(i, j) + b.get(i, j);
                if (sum != 0) {
                    an[count] = sum;
                    ia[count] = i;
                    count++;
                }
            }
        }
        ja[n] = count;

        int[] anTrim = new int[count];
        int[] iaTrim = new int[count];
        for (int k = 0; k < count; k++) {
            anTrim[k] = an[k];
            iaTrim[k] = ia[k];
        }
        return new CCSMatrix(n, anTrim, iaTrim, ja);
    }

    static void printArray(String name, int[] arr) {
        System.out.print(name + " = [");
        for (int k = 0; k < arr.length; k++) {
            if (k > 0) {
                System.out.print(", ");
            }
            System.out.print(arr[k]);
        }
        System.out.println("]");
    }

    static void printMatrix(CCSMatrix m) {
        int[][] dense = new int[m.n][m.n];
        for (int j = 0; j < m.n; j++) {
            for (int k = m.ja[j]; k < m.ja[j + 1]; k++) {
                dense[m.ia[k]][j] = m.an[k];
            }
        }
        for (int i = 0; i < m.n; i++) {
            for (int j = 0; j < m.n; j++) {
                System.out.print(dense[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Размер матриц n: ");
        int n = scanner.nextInt();

        BandMatrix a = readBandMatrix(scanner, n, "A");
        BandMatrix b = readBandMatrix(scanner, n, "B");

        CCSMatrix c = addToCCS(a, b);

        System.out.println("Результат A + B в формате CCS:");
        printArray("AN", c.an);
        printArray("IA", c.ia);
        printArray("JA", c.ja);
        System.out.println("Матрица:");
        printMatrix(c);
    }
}