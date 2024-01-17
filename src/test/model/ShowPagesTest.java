package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Represents all the menu pages that would show when program runs
public class ShowPagesTest {

    @BeforeEach
    public void setup(){}

    @Test
    public void testShowPages() {}

    // EFFECTS: display the main menu page
    @Test
    public void testShowMain() {
        assertEquals(("\nSelect an food category to order:\n"
                + "\t1 --> Burgers\n"
                + "\t2 --> SnacksAndSides\n"
                + "\t3 --> Beverages\n"
                + "\t4 --> DessertsAndShakes\n"
                + "\t5 --> View Shopping Cart\n"
                + "\t6 --> View Order History\n"
                + "\ts --> Save your shopping cart to file\n"
                + "\tl --> Load your shopping cart from file\n"
                + "\tq --> Quit\n"
        ), ShowPages.showMain());
    }

    // EFFECTS: display the burger menu
    @Test
    public void testShowBurgerOption() {
        assertEquals(("\nSelect one burger to view its information.\n"
                + "\t1 --> McChicken\n"
                + "\t2 --> BigMac\n"
                + "\t3 --> Filet-O-Fish\n"
                + "\t4 --> Double Quarter Ponder BLT\n"
                + "\t5 --> Roasted Soy Sauce Bacon Tomato Thick Beef from JAPAN\n"
                + "\t6 --> Yurinchi Fried Chicken from JAPAN\n"
                + "\t7 --> BBQ Chicken Thigh Burger from CHINA\n"
                + "\t8 --> Bacon Angus MAX Beef Burger from CHINA\n"
                + "\t9 --> Sour Bamboo Burger from CHINA\n"
        ), ShowPages.showBurgerOption());
    }

    // EFFECTS: display the sides menu
    @Test
    public void testShowSideOption() {
        assertEquals(("\nSelect one item to view its information.\n"
                + "\t1 --> 6-piece McNuggets\n"
                + "\t2 --> World Famous Fries\n"
                + "\t3 --> Shaka-Chicki Red Pepper from JAPAN\n"
                + "\t4 --> Chocolate Bomb Triangle Pie from CHINA\n"
                + "\t5 --> Taro Pie from CHINA\n"
                + "\t6 --> Potato Scallops with Chicken Salt from AUSTRALIA\n"
                + "\t7 --> Pain au chocolat from PARIS\n"
                + "\t8 --> Loquat Bartlett Pear Pie from CHINA\n"
                + "\t9 --> Bubble Tea Flavour Pie from CHINA\n"
        ), ShowPages.showSideOption());
    }

    //EFFECTS: display the beverage menu
    @Test
    public void testShowDrinkOption() {
        assertEquals(ShowPages.showDrinkOption(),("\nSelect one item to view its information.\n"
                + "\t1 --> Coca-Cola Coke\n"
                + "\t2 --> Diet Coke\n"
                + "\t3 --> Sprite\n"
                + "\t4 --> Chocolate Triple Thick Milkshake\n"
                + "\t5 --> Strawberry Banana Smmothie\n"
                + "\t6 --> Coffee Afogado from CHINA\n"
                + "\t7 --> Matcha Afogado from CHINA\n"
                + "\t8 --> Suntory Kuro Oolong Tea#Strong from JAPAN\n"
                + "\t9 --> Jeju Hallabong Chiller from KOREA\n")
        );
    }

    // EFFECTS: display the dessert menu
    @Test
    public void testShowDessertOption() {
        assertEquals(ShowPages.showDessertOption(),("\nSelect one burger to view its information.\n"
                + "\t1 --> Vanila Cone\n"
                + "\t2 --> Oreo McFlurry\n"
                + "\t3 --> McFlurry® KOEDA from JAPAN\n"
                + "\t4 --> Waffle Cone Chocolate & Almond from JAPAN\n"
                + "\t5 --> Uji Matcha Sundae with OREO Cookie from HONG KONG\n"
                + "\t6 --> Purple Sweet Potato Sundae Waffle Cone from CHINA\n"
                + "\t7 --> 6-piece Macaroon Set\n"
                + "\t8 --> Cilantro Sundae Cone from CHINA\n"
                + "\t9 --> Sea Salt Coconut McFlurry from CHINA\n")
        );
    }
}
