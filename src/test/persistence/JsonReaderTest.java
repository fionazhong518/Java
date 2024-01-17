package persistence;

import model.ShoppingCart;
import model.food.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest{

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            ShoppingCart cart = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        //JsonReader reader = new JsonReader("./data/testReaderEmptyCart.json");
        try {
            ShoppingCart cart = new ShoppingCart();
            JsonWriter writer = new JsonWriter("./data/testReaderEmptyCart.json");
            writer.open();
            writer.write(cart);
            writer.close();

            JsonReader reader = new JsonReader("./data/testReaderEmptyCart.json");
            cart = reader.read();

            assertEquals(0, cart.getOrderList().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        //JsonReader reader = new JsonReader("./data/testReaderGeneralCart.json");
        try {
            ShoppingCart cart = new ShoppingCart();
            cart.addItems(new Burgers("McChicken", 6.09, 480), 6.09);
            cart.addItems(new SnacksAndSides("6-piece McNuggets", 6.29, 360), 6.29);
            cart.addItems(new DessertsAndShakes("Oreo McFlurry", 3.89, 570), 3.89);
            cart.addItems(new Beverages("Diet Coke", 2.35, 1), 2.35);
            cart.addItems(new SnacksAndSides("World Famous Fries", 3.69, 350), 3.69);

            JsonWriter writer = new JsonWriter("./data/testReaderGeneralCart.json");
            writer.open();
            writer.write(cart);
            writer.close();

            JsonReader reader = new JsonReader("./data/testReaderGeneralCart.json");
            cart = reader.read();

            List<Food> orderList = cart.getOrderList();
            assertEquals(5, orderList.size());
            checkItem("McChicken", 6.09, 480, "Burgers", orderList.get(0));
            checkItem("6-piece McNuggets", 6.29, 360, "SnacksAndSides", orderList.get(1));
            checkItem("Oreo McFlurry", 3.89, 570, "DessertsAndShakes", orderList.get(2));
            checkItem("Diet Coke", 2.35, 1, "Beverages", orderList.get(3));
            checkItem("World Famous Fries", 3.69, 350, "SnacksAndSides", orderList.get(4));

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
