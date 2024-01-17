package model.foodTest;

import model.food.SnacksAndSides;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Represents the SnacksAndSide type of food with name, price, calories, and its category
public class SnacksAndSidesTest {
    private SnacksAndSides fries;

    @BeforeEach
    public void setup(){
        fries = new SnacksAndSides("World Famous Fries", 3.5, 350);
    }

    @Test
    public void testSnacksAndSides() {
        assertEquals("World Famous Fries", fries.getName());
        assertEquals(3.5, fries.getPrice());
        assertEquals(350, fries.getCalories());
        assertEquals("SnacksAndSides", fries.getCategory());
    }
}
