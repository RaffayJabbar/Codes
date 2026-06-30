import java.util.*;

public class BoyerMoore {
    private static final int NO_OF_CHARS = 256;


    private static void badCharHeuristic(String str, int size, int[] badChar) {
        Arrays.fill(badChar, -1);
        for (int i = 0; i < size; i++) {
            badChar[(int) str.charAt(i)] = i;
        }
    }

    public static void search(String txt, String pat) {
        int m = pat.length();
        int n = txt.length();
        int[] badChar = new int[NO_OF_CHARS];

        badCharHeuristic(pat, m, badChar);

        int s = 0;
        while (s <= (n - m)) {
            int j = m - 1;


            while (j >= 0 && pat.charAt(j) == txt.charAt(s + j)) {
                j--;
            }

            if (j < 0) {
                System.out.println("Pattern found at index " + s);
                s += (s + m < n) ? m - badChar[txt.charAt(s + m)] : 1;
            } else {
                s += Math.max(1, j - badChar[txt.charAt(s + j)]);
            }
        }
    }

    public static void main(String[] args) {
        String text = "Insertion sort typically has a smaller constant factor than merge sort";
        String pattern = "sort";

        System.out.println("Program Output:");
        search(text, pattern);
    }
}