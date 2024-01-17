package model;

import model.food.Burgers;
import model.food.SnacksAndSides;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShoppingCartTest {
    private ShoppingCart cart;
    private Burgers mcChicken;
    private SnacksAndSides fries;

    @BeforeEach
    public void setup() {
        cart = new ShoppingCart();
        mcChicken = new Burgers("McChicken", 3.5, 480);
        fries = new SnacksAndSides("World Famous Fries", 3.5, 350);


    }
    // REQUIRES:
    // MODIFIES: this
    // EFFECTS: create an empty shopping cart with an initial total amount 0 and nothing in the order list
    @Test
    public void testConstructor() {
        assertEquals(0.00, cart.getTotalPrice());
        //assertEquals(0, cart.getTotalPointsEarn());
        assertEquals(0, cart.getOrderList().size());


    }

    // MODIFIES: this
    // REQUIRES: ??
    // EFFECTS: add the item into the order list, add the item's price to the total price
    @Test
    public void testAddItems() {

        cart.addItems(mcChicken, mcChicken.getPrice());
        assertEquals(1, cart.getOrderList().size());
        assertEquals(cart.getOrderList().size(), cart.getPriceList().size());


        cart.addItems(fries, fries.getPrice());
        assertEquals(2, cart.getOrderList().size());
        assertEquals(cart.getOrderList().size(), cart.getPriceList().size());

    }

    public void loadList() {
        cart.addItems(mcChicken, mcChicken.getPrice());
        cart.addItems(fries, fries.getPrice());

    }

    // MODIFIES: this
    // REQUIRES: ??
    // EFFECTS: delete the item from the order list, add the item's price to the total price
    @Test
    public void testDeleteItems() {
        loadList();

        assertEquals(2, cart.getOrderList().size());
        assertEquals(cart.getOrderList().size(), cart.getPriceList().size());

        //cart.deleteItems(2);
        assertTrue(cart.deleteItems(2));
        assertEquals(1, cart.getOrderList().size());
        assertEquals(cart.getOrderList().size(), cart.getPriceList().size());


        //cart.deleteItems(1);
        assertTrue(cart.deleteItems(1));
        assertEquals(0, cart.getOrderList().size());
        assertEquals(cart.getOrderList().size(), cart.getPriceList().size());


        assertFalse(cart.deleteItems(10));

    }

    // MODIFIES:
    // REQUIRES: Food
    // EFFECTS: return the item name and its price in the order list
    @Test
    public void testGetItemAndPrice() {
        loadList();
        assertEquals(cart.getItemAndPrice(mcChicken), "McChicken --- $3.5");

        assertEquals(cart.getItemAndPrice(fries), "World Famous Fries --- $3.5");
    }

}