package model.foodTest;

import model.food.Burgers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BurgersTest {
    private Burgers mcChicken;
    private Burgers bigmac;

    @BeforeEach
    public void setup() {
        mcChicken = new Burgers("McChicken", 3.5, 480);
        bigmac = new Burgers("Big Mac", 6.39, 560);
    }

    @Test
    public void testBurgers() {
        assertEquals("McChicken", mcChicken.getName());
        assertEquals(480, mcChicken.getCalories());
        assertEquals(3.5, mcChicken.getPrice());
        assertEquals("Burgers", mcChicken.getCategory());

        assertEquals("Big Mac", bigmac.getName());
        assertEquals(6.39, bigmac.getPrice());
        assertEquals(560, bigmac.getCalories());
        assertEquals("Burgers", bigmac.getCategory());

    }

}
