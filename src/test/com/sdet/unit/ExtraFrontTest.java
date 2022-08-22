package unit;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ExtraFrontTest {
    @Test
    public void shouldRepeatTheFirstTwoCharactersOfString() {
        ExtraFront extraFront = new ExtraFront();

        Assert.assertEquals(extraFront.extraFront("Hello"), "HeHeHe");
        Assert.assertEquals(extraFront.extraFront("ab"), "ababab");
        Assert.assertEquals(extraFront.extraFront("H"), "HHH");
        Assert.assertEquals(extraFront.extraFront(""), "");
    }
}
