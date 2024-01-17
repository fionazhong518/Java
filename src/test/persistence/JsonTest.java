package persistence;

import model.food.Food;

import static org.junit.jupiter.api.Assertions.*;

public class JsonTest {
    protected void checkItem(String name, double price, int calories, String category, Food food) {
        assertEquals(name, food.getName());
        assertEquals(price, food.getPrice());
        assertEquals(calories, food.getCalories());
        assertEquals(category, food.getCategory());
    }
}
