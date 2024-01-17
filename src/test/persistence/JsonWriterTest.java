package persistence;

import model.ShoppingCart;
import model.food.Burgers;
import model.food.Food;
import model.food.SnacksAndSides;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest extends JsonTest{

    @BeforeEach
    public void setup() {
        ShoppingCart cart = new ShoppingCart();
    }

    @Test
    public void testWriterInvalidFile() {
        try {
            ShoppingCart cart = new ShoppingCart();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    public void testWriterEmptyWorkroom() {
        try {
            ShoppingCart cart = new ShoppingCart();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyCart.json");
            writer.open();
            writer.write(cart);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyCart.json");
            cart = reader.read();
            assertEquals(0, cart.getOrderList().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            ShoppingCart cart = new ShoppingCart();
            cart.addItems(new Burgers("McChicken", 6.09, 480), 6.09);
            cart.addItems(new Burgers("Big Mac", 6.39, 560), 6.39);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralCart.json");
            writer.open();
            writer.write(cart);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralCart.json");
            cart = reader.read();
            List<Food> orderList = cart.getOrderList();
            assertEquals(2, orderList.size());
            checkItem("McChicken", 6.09, 480, "Burgers", orderList.get(0));
            checkItem("Big Mac", 6.39, 560, "Burgers", orderList.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
