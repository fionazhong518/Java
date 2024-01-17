package ui;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

// Represent a class that create JButtons for the gui class to reference
public class Buttons {

    // EFFECTS: return a list of MAIN MENU buttons
    public static List setupMainButtons() {
        List<JButton> buttons = new ArrayList<>();
        buttons.add(new JButton("Burgers Menu"));
        buttons.add(new JButton("Snacks and Sides Menu"));
        buttons.add(new JButton("Beverage Menu"));
        buttons.add(new JButton("Dessert and Shakes Menu"));
        buttons.add(new JButton("Your Shopping Cart"));
        buttons.add(new JButton("Save your shopping cart"));
        buttons.add(new JButton("Load your shopping cart"));
        buttons.add(new JButton("Order History"));

        return buttons;
    }




}
