package ui;

import model.*;
import model.food.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Main running program (console)
public class McDonaldApp {

    // Json
    private static final String JSON_STORE = "./data/cart.json";
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // in class variables
    private List<Burgers> burgersList;
    private List<SnacksAndSides> sidesList;
    private List<DessertsAndShakes> dessertsList;
    private List<Beverages> drinksList;
    private List<String> orderHistory;
    private ShoppingCart cart;

    private static final String CALORIES_UNIT  = "cals";
    private boolean running = true;

    public McDonaldApp() {
        //runApp();
        initialize();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runApp() {
        //boolean running = true;
        String command;

        initialize();

        while (running) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                running = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGoodbye!");
    }

    // REQUIRES:
    // EFFECTS: processes user command and lead them to the specific page
    private void processCommand(String command) {
        if (command.equals("1")) {
            System.out.println(ShowPages.showBurgerOption());
            displayItems(burgersList);
        } else if (command.equals("2")) {
            System.out.println(ShowPages.showSideOption());
            displayItems(sidesList);
        } else if (command.equals("3")) {
            System.out.println(ShowPages.showDrinkOption());
            displayItems(drinksList);
        } else if (command.equals("4")) {
            System.out.println(ShowPages.showDessertOption());
            displayItems(dessertsList);
        } else if (command.equals("5")) {
            viewCart();
        } else if (command.equals("6")) {
            viewHistory();
        } else if (command.equals("s")) {
            saveToFile();
        } else if (command.equals("l")) {
            loadFile();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // REQUIRES:
    // MODIFIES:
    // EFFECTS: initializes the app
    private void initialize() {
        // Initializing all variables
        cart = new ShoppingCart();
        orderHistory = new ArrayList<>();
        burgersList = new ArrayList<>();
        sidesList = new ArrayList<>();
        dessertsList = new ArrayList<>();
        drinksList = new ArrayList<>();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        // loading all lists of items
        burgersList = LoadInfo.loadBurgers();
        sidesList = LoadInfo.loadSides();
        drinksList = LoadInfo.loadDrinks();
        dessertsList = LoadInfo.loadDesserts();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    public List getBurgers() {
        return burgersList;
    }

    public List getSides() {
        return sidesList;
    }

    public List getDrinks() {
        return drinksList;
    }

    public List getDesserts() {
        return dessertsList;
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println(ShowPages.showMain());
    }

    // MODIFIES: the given foodList
    // EFFECTS: print out item info and get user's selection
    private void displayItems(List foodList) {
        List<Food> list = foodList;
        String c1 = input.next();
        int index = Integer.parseInt(c1);
        if (index <= 9) {
            showItemInfo(list.get(index - 1));
            addToCart(list.get(index - 1));
        } else {
            System.out.println("Selection is not valid... please choose again!");
            displayItems(list);
        }
    }

    // REQUIRES:
    // EFFECTS: return the food's name, price, and calories
    public String showItemInfo(Food food) {
        String text = "\nItem name: " + food.getName()
                + "\nPrice: $" + food.getPrice() //+ ","
                + "\nCalories: " + food.getCalories() + CALORIES_UNIT;
        System.out.println("\nItem information:");
        System.out.println("\tItem name: " + food.getName());
        System.out.println("\tPrice: $" + food.getPrice());
        System.out.println("\tCalories: " + food.getCalories() + CALORIES_UNIT);
        System.out.println("\tEnter b to go back to last page.");
        System.out.println("\tEnter a to add this item to your cart.");

        return text;
    }

    // MODIFIES: cart
    // REQUIRES:
    // EFFECTS: add a food item into the shopping cart, if the selection is not valid, re-run this method
    private void addToCart(Food food) {
        String addToCart = input.next();
        if (addToCart.equals("a")) {
            cart.addItems(food, food.getPrice());
            System.out.println("You just added " + food.getName() + " into your cart.");
        } else if (addToCart.equals("b")) {
            displayMenu();
        } else {
            System.out.println("Selection not valid...");
            addToCart(food);
        }
    }

    // MODIFIES:
    // REQUIRES:
    // EFFECTS: display all items and their prices in the shopping cart
    public void viewCart() {
        List<Food> allItem = cart.getOrderList();
        if (!allItem.isEmpty()) {
            System.out.println("Below is your shopping cart:");
            int index = 0;
            if (index <= allItem.size() - 1) {
                for (Food food : allItem) {
                    System.out.println((index + 1) + ". " + cart.getItemAndPrice(food));
                    index++;
                }
            }
            System.out.println("Your total comes to: $" + cart.getTotalPrice());
            actionWithCart();  //ask the user to continue ordering or delete any item or go to checkout
        } else {
            System.out.println("Your shopping cart is empty!");
        }
    }

    // MODIFIES: cart
    // REQUIRES:
    // EFFECTS: remove the item from the shopping cart, if the selection is not valid, re-run this method
    private void deleteItem() {
        System.out.println("Please enter the index of the item you want to remove:");
        String c1 = input.next();
        int index = Integer.parseInt(c1);
        boolean canDelete = cart.deleteItems(index);
        if (!canDelete) {
            System.out.println("Selection not valid...");
            deleteItem();
        }
    }

    // EFFECTS: ask the user if user want to continue shopping or checkout,
    //          a for continue, 2 for checkout
    private void actionWithCart() {
        System.out.println("\n1 --> Continue to order" + "\n2 --> Delete item" + "\n3 --> check out?");
        String c1 = input.next();
        if (c1.equals("1")) {
            displayMenu();
        } else if (c1.equals("2")) {
            deleteItem();
        } else if (c1.equals("3")) {
            placeOrder();
        } else {
            System.out.println("Please type a valid number!");
        }
    }

    // MODIFIES:
    // REQUIRES:
    // EFFECTS: display an order to user, ask if user want to leave, q = quit
    public int placeOrder() {
        int max = 99999;
        int min = 10000;
        int orderNum = (int) (Math.random() * (max - min + 1) + min);
        System.out.println("Here is your order number: " + orderNum);
        saveHistory(orderNum);
        continueOrQuit();
        return orderNum;
    }

    // MODIFIES: orderHistory
    // REQUIRES:
    // EFFECTS: save the order number and its order number into history
    public void saveHistory(int orderNum) {
        List<Food> allItem =  cart.getOrderList();
        String history = "Order Number: " + orderNum + "\n";
        int index = 0;
        if (index <= allItem.size() - 1) {
            for (Food food : allItem) {
                history += (index + 1) + ". " + cart.getItemAndPrice(food) + "\n";
                index++;
            }
            history += ("Total price:" + cart.getTotalPrice() + "\n");
            orderHistory.add(history);

        }

        cart.getOrderList().clear();
        cart.getPriceList().clear();
    }

    // EFFECTS: print out order history, if the order history is empty, print
    private void viewHistory() {
        if (!orderHistory.isEmpty()) {
            for (String order : orderHistory) {
                System.out.println(order);
            }
        } else {
            System.out.println("Your order history is empty");
        }

    }

    // EFFECTS: saves the cart to file
    public void saveToFile() {
        try {
            jsonWriter.open();
            jsonWriter.write(cart);
            jsonWriter.close();
            System.out.println("Shopping cart saved to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads cart from file
    public void loadFile() {
        try {
            cart = jsonReader.read();
            System.out.println("Loaded shopping cart from " + JSON_STORE);
            System.out.println(cart.getOrderList());
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // MODIFIES: running
    // EFFECTS: ask the user if user want to quit, q for quit, any other command for resume
    private void continueOrQuit() {
        System.out.println("\nq --> Quit" + "\nType any other command to resume");
        String c1 = input.next();
        if (c1.equals("q")) {
            running = false;
            System.out.println("Thank you for using.");
        }

    }
}
