package unit;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class HumanTest {
    private static Human human;

    @BeforeMethod
    @Parameters({ "firstname", "middlename", "lastname"})
    public void createHuman(String firstname, String middleware, String lastname) {
        human = new Human(firstname, middleware, lastname);
    }

    @Test
    @Parameters({ "firstname", "middlename", "lastname"})
    public void shouldHaveAnInitialFirstNameMiddleNameAndLastName(String firstname, String middleware, String lastname) {
        Assert.assertEquals(human.getFirstName(), firstname);
        Assert.assertEquals(human.getMiddleName(), middleware);
        Assert.assertEquals(human.getLastName(), lastname);
    }

    @Test
    public void shouldHaveNoInitialHeightOrWeight() {
        Assert.assertEquals(human.getHeight(), 0.0);
        Assert.assertEquals(human.getWeight(), 0.0);
    }

    @Test
    public void shouldSetHeight() {
        human.setHeight(5.8);
        Assert.assertEquals(human.getHeight(), 5.8);
    }

    @Test
    public void shouldSetWeight() {
        human.setWeight(185);
        Assert.assertEquals(human.getWeight(), 185);
    }

    @Test
    @Parameters({ "firstname", "middlename", "lastname"})
    public void shouldReturnFullName(String firstname, String middleware, String lastname) {
        Assert.assertEquals(human.fullName(), firstname + " " + middleware + " " + lastname);
    }

    @Test
    public void shouldCompareHumanBySortableName() {

    }
}
