package model.foodTest;

import model.food.DessertsAndShakes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DessertsAndShakesTest {
    private DessertsAndShakes oreoMcflurry;

    @BeforeEach
    public void setup(){
        oreoMcflurry = new DessertsAndShakes("Oreo McFlurry", 3.89, 570);
    }

    @Test
    public void testDessertsAndShakes() {
        assertEquals("Oreo McFlurry", oreoMcflurry.getName());
        assertEquals(3.89, oreoMcflurry.getPrice());
        assertEquals(570, oreoMcflurry.getCalories());
        assertEquals("DessertsAndShakes", oreoMcflurry.getCategory());
    }
}
