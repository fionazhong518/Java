package model.foodTest;

import model.food.Beverages;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BeveragesTest {
    private Beverages sprite;

    @BeforeEach
    public void setup() {
        sprite = new Beverages("Sprite", 2.35, 190);
    }

    @Test
    public void testBeverages() {
        assertEquals("Sprite", sprite.getName());
        assertEquals(2.35, sprite.getPrice());
        assertEquals(190, sprite.getCalories());
    }

    @Test
    public void testGetName() {
        assertEquals("Sprite", sprite.getName());
    }

    @Test
    public void testGetPrice() {
        assertEquals(2.35, sprite.getPrice());
    }

    @Test
    public void testGetCalories() {
        assertEquals(190, sprite.getCalories());
    }

    @Test
    public void testGetCategory() {
        assertEquals("Beverages", sprite.getCategory());
    }
}
