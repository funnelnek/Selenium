package unit;

import org.testng.Assert;
import org.testng.annotations.Test;

public class NumberGeneratorTest {
    @Test
    public void shouldReturnAnArrayTwentyOddNumbers() {
        int[] numbers = NumberGenerator.getTwentyOdds();
        Assert.assertEquals(numbers.length, 20);

        for (int number : numbers) {
            Assert.assertTrue(number % 2 != 0);
        }
    }
}
