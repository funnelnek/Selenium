package unit;

public class NumberGenerator {
    public static void main() {
        for (int number : NumberGenerator.getTwentyOdds()) {
            System.out.println(number);
        }
    }

    public static int[] getTwentyOdds() {
        int[] odds = new int[20];

        for (int i = 0; i < odds.length; i++) {
            odds[i] = i * 2 + 1;
        }

        return odds;
    }
}
