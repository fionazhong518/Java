package ui;

import model.Event;
import model.EventLog;
import model.ShoppingCart;
import model.food.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// Represents the ui version of the main console class
public class GUI extends McDonaldApp implements ActionListener {
    private JFrame frame;
    private JPanel contentPane;
    private CardLayout cardLayout;

    private JPanel mainPage;
    private List<JButton> mainButtons;
    private JPanel burgerPage;
    private JPanel sidePage;
    private JPanel drinkPage;
    private JPanel dessertPage;
    private JPanel cartPage;
    private JPanel historyPage;
    // save and load button should only show content
    private List<JButton> orderButtons;
    private List<JButton> backButtons;
    private JButton checkoutButton;

    private List<Burgers> burgersList;
    private List<SnacksAndSides> sidesList;
    private List<DessertsAndShakes> dessertsList;
    private List<Beverages> drinksList;
    private List<String> orderHistory;
    private List<JTextArea> texts;
    private List<JButton> cartItem;
    private ShoppingCart cart;

    // for validating each sub-pages button
    private JPanel currentPage;
    private String currentListType;

    // Image
    private JLabel duckIcon;

    // JSON
    private static final String JSON_STORE = "./data/cart.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;


    public GUI() {
        // initialize
        initializeFrame();
        initializeFoods();
        cart = new ShoppingCart();
        orderHistory = new ArrayList<>();
        texts = new ArrayList<>();
        //create page and add to contentPlane
        createAndSetAllPages();
        // IMAGE and title
        addImageToLabel(duckIcon, 6, mainPage);
        setTitle();
        addImageToLabel(duckIcon, 6, mainPage);

        // Page and buttons
        currentPage = new JPanel(); // for future use
        mainButtons = setupButtons(mainPage, Buttons.setupMainButtons());

        // setup special buttons and slider
        orderButtons = new ArrayList<>();
        cartItem = new ArrayList<>();
        setupBackButton();
        setupCheckoutButton();
        decoratePages(); // add decoration
        addToContentPlane();
        // Show the first page
        cardLayout.show(contentPane, "MAIN MENU");

        // JSON
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    // EFFECTS: add some super cute duck pics to some pages (decoration)
    private void decoratePages() {
        addImageToLabel(duckIcon, 10, burgerPage);
        addImageToLabel(duckIcon, 10, sidePage);
        addImageToLabel(duckIcon, 10, drinkPage);
        addImageToLabel(duckIcon, 10, dessertPage);
    }

    // MODIFIES: food lists
    // EFFECTS: initialize all food info and add them to food lists
    public void initializeFoods() {
        burgersList = getBurgers();
        sidesList = getSides();
        dessertsList = getDesserts();
        drinksList = getDrinks();
    }

    // MODIFIES: frame
    // EFFECTS: initialize the frame for GUI
    public void initializeFrame() {
        frame = new JFrame("Ordering Pad");
        frame.setSize(600, 800);
        frame.setResizable(false);
        // Add the content pane to the frame
        contentPane = new JPanel();
        cardLayout = new CardLayout();
        contentPane.setLayout(cardLayout);
        //contentPane.setSize(300, 500);
        frame.setContentPane(contentPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.addWindowListener(new MyWindowListener());
    }

    // MODIFIES: mainPage
    // EFFECTS: setup texts appear on gui
    public void setTitle() {
        JLabel title = new JLabel("Mcdonald Ordering Pad");
        title.setBounds(100, 100, 600, 30);
        mainPage.add(title);
    }

    //MODIFIES: label, page
    //EFFECTS: constructs a scaled version of the img
    private void addImageToLabel(JLabel label, int count, JPanel page) {
        for (int i = 0; i < count; i++) {
            try {
                BufferedImage buffer = ImageIO.read(new File("./src/main/ui/duck.png"));
                label = new JLabel(new ImageIcon(buffer));
                ImageIcon imageIcon = new ImageIcon(buffer);
                Image image = imageIcon.getImage();
                Image newImage = image.getScaledInstance(25, 20, Image.SCALE_SMOOTH);
                label.setIcon(new ImageIcon(newImage));
                //label.setBounds(0,0,380,380);
                page.add(label);
                page.validate();
                page.repaint();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


    }

    // MODIFIES: all pages
    // EFFECTS: create and setup all pages
    public void createAndSetAllPages() {
        mainPage = new JPanel();
        mainPage.setBackground(Color.white);
        mainPage.setLayout(new FlowLayout(FlowLayout.CENTER));

        burgerPage = new JPanel();
        burgerPage.setBackground(Color.white);
        burgerPage.setLayout(new FlowLayout(FlowLayout.CENTER));
        //burgerPage.setPreferredSize(new Dimension(20, 520));

        sidePage = new JPanel();
        drinkPage = new JPanel();
        dessertPage = new JPanel();
        cartPage = new JPanel();
        historyPage = new JPanel();
    }

    // MODIFIES: contentPlane
    // EFFECTS: add the pages to the content plane
    public void addToContentPlane() {
        // Add the pages to the content pane
        contentPane.add(mainPage, "MAIN MENU");
        contentPane.add(burgerPage, "Burgers");
        contentPane.add(sidePage, "Snacks And Sides");
        contentPane.add(drinkPage, "Beverages");
        contentPane.add(dessertPage, "Desserts And Shakes");
        contentPane.add(cartPage, "Shopping Cart");
        contentPane.add(historyPage, "Order History");
    }

    // MODIFIES: page
    // EFFECTS: adds action listener, and adds the order button to the page
    public List setupButtons(JPanel page, List<JButton> b) {
        JPanel p = page;
        List<JButton> buttons = b;
        for (JButton button : buttons) {
            button.addActionListener(this);
            p.add(button);
        }


        return buttons;
    }

    // MODIFIES: all pages except the MAIN MENU, backButtons
    // EFFECTS: add back to main buttons to all food page
    public void setupBackButton() {
        backButtons = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            backButtons.add(new JButton("Back to Main"));
            backButtons.get(i).addActionListener(this);

        }
        burgerPage.add(backButtons.get(0));
        sidePage.add(backButtons.get(1));
        drinkPage.add(backButtons.get(2));
        dessertPage.add(backButtons.get(3));
        cartPage.add(backButtons.get(4));
        historyPage.add(backButtons.get(5));
    }

    // MODIFIES: checkButton, cartPage
    // EFFECTS: add checkout button to the shopping cart page
    public void setupCheckoutButton() {
        checkoutButton = new JButton("Checkout");
        checkoutButton.setIconTextGap(-10);
        checkoutButton.addActionListener(this);
        cartPage.add(checkoutButton);
    }

    // EFFECTS: perform button behaviors on main page
    //          mainButtons: [0]burger, [1]sides, [2]beverage, [3]dessert, [4]cart, [5]save, [6]load
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < backButtons.size(); i++) {
            if (e.getSource() == backButtons.get(i)) {
                cardLayout.show(contentPane, "MAIN MENU");
            }
        }
        if (e.getSource() == mainButtons.get(0)) {
            cardLayout.show(contentPane, "Burgers");
            displayItem(burgerPage, burgersList);
            currentPage = burgerPage;
            currentListType = "burger";
        } else if (e.getSource() == mainButtons.get(1)) {
            cardLayout.show(contentPane, "Snacks And Sides");
            displayItem(sidePage, sidesList);
            currentPage = sidePage;
            currentListType = "side";
        } else if (e.getSource() == mainButtons.get(2)) {
            cardLayout.show(contentPane, "Beverages");
            displayItem(drinkPage, drinksList);
            currentPage = drinkPage;
            currentListType = "drink";
        } else if (e.getSource() == mainButtons.get(3)) {
            cardLayout.show(contentPane, "Desserts And Shakes");
            displayItem(dessertPage, dessertsList);
            currentPage = dessertPage;
            currentListType = "dessert";
        } else if (e.getSource() == mainButtons.get(4)) {
            cardLayout.show(contentPane, "Shopping Cart");
            viewCart(cartPage);
        } else if (e.getSource() == mainButtons.get(5)) {
            // save button
            saveCart();
        } else if (e.getSource() == mainButtons.get(6)) {
            // load button
            loadCart();
        } else if (e.getSource() == mainButtons.get(7)) {
            cardLayout.show(contentPane, "Order History");
            viewHistory();

        } else if (e.getSource() == checkoutButton) {
            placeTheOrder();
        }
        for (int i = 0; i < orderButtons.size(); i++) {
            if (e.getSource() == orderButtons.get(i)) {
                addToCart(currentPage, currentListType, i);
            }
        }

        for (int i = 0; i < cartItem.size(); i++) {
            if (e.getSource() == cartItem.get(i)) {
                deleteItem(i);
            }
        }
    }

    // MODIFIES: orderButtons
    // EFFECTS: create and display JLabel for specific item info
    public void displayItem(JPanel page, List list) {
        orderButtons = new ArrayList<>();
        List<Food> foods = list;
        List<String> texts = new ArrayList<>();
        for (int i = 0; i < foods.size(); i++) {
            String str = showItemInfo(foods.get(i));
            texts.add(str);
        }

        for (String text : texts) {
            JButton item = new JButton("<html>" + text.replaceAll("\\n", "<br>") + "</html>");
            item.setIconTextGap(-10);
            item.addActionListener(this);
            item.setBackground(Color.lightGray);
            page.add(item);
            orderButtons.add(item);
        }
    }

    // EFFECTS: return a foodList according to the list type
    public List getList(String listType) {
        if (listType == "burger") {
            return burgersList;
        } else if (listType == "side") {
            return sidesList;
        } else if (listType == "drink") {
            return drinksList;
        } else if (listType == "dessert") {
            return dessertsList;
        }
        return null;
    }

    // MODIFIES: cart
    // EFFECTS: add the chosen food to the cart
    public void addToCart(JPanel page, String listType, int index) {
        List<Food> foodList = getList(listType);
        Food food = foodList.get(index);
        cart.addItems(food, food.getPrice());
        System.out.println("you just added " + food.getName());

    }

    // MODIFIES: cart
    // REQUIRES:
    // EFFECTS: remove the item from the shopping cart, if the selection is not valid, re-run this method
    private void deleteItem(int index) {
        int result = JOptionPane.showConfirmDialog(null, "Delete This Item?",
                "Delete Item", JOptionPane.YES_NO_CANCEL_OPTION);
        //System.out.println(result);
        if (result == 0) {

            cart.deleteItems(index + 1);
        }
        cardLayout.show(contentPane, "MAIN MENU");
    }

    //MODIFIES: CartPage
    //EFFECTS: clear all the components on the page, and reset some fundamental button back
    private void resetCartPage() {
        cartPage.removeAll();
        cartPage.add(backButtons.get(4));
        cartPage.add(checkoutButton);
    }

    // MODIFIES: page
    // EFFECTS: display all items in cart and the total/empty-cart message on to the screen
    private void displayCart(JPanel page) {
        for (JButton b : cartItem) {
            page.add(b);
        }
        for (JTextArea text : texts) {
            page.add(text);
        }
    }

    // MODIFIES: cartItem, texts
    // EFFECTS: display all the items in the cart
    public void viewCart(JPanel page) {
        List<Food> allItem = cart.getOrderList();
        List<JButton> buttons = new ArrayList<>();
        List<JTextArea> jta = new ArrayList<>();
        if (!allItem.isEmpty()) {
            int index = 0;
            if (index <= allItem.size() - 1) {
                for (Food food : allItem) {
                    JButton order = new JButton((index + 1) + ". " + cart.getItemAndPrice(food));
                    order.addActionListener(this);
                    buttons.add(order);
                    cartItem = buttons;
                    index++;
                }
            }
            JTextArea total = new JTextArea("Your total comes to: $" + cart.getTotalPrice());
            jta.add(total);
        } else {
            JTextArea emptyCart = new JTextArea("Your shopping cart is empty!");
            jta.add(emptyCart);
        }
        texts = jta;
        resetCartPage();
        displayCart(page);
    }

    // MODIFIES: cartItem, texts
    // EFFECTS: reset the item list and message list appear on cart page
    private void clearCartScreen() {
        cartItem.clear();
        texts.clear();
    }

    // MODIFIES: cart
    // EFFECTS: clear the order list and price list of the cart
    private void clearCartInfo() {
        cart.getOrderList().clear();
        cart.getPriceList().clear();
        double totalPrice = cart.getTotalPrice();
        totalPrice = 0;
    }

    // MODIFIES: orderHistory
    // EFFECTS: create JOptionPlane that asks user to confirm if they want to place order
    public void placeTheOrder() {
        int max = 99999;
        int min = 10000;
        int orderNum = (int) (Math.random() * (max - min + 1) + min);
        int result = -1;
        String orderString = "Order #: " + orderNum;
        List<Food> foodList = cart.getOrderList();
        if (!foodList.isEmpty()) {
            JOptionPane.showMessageDialog(null, orderString,
                    "Your order Has been placed", JOptionPane.INFORMATION_MESSAGE);
            System.out.println(result);
            orderHistory = saveOrder(orderNum);
            clearCartScreen();
            clearCartInfo();
        } else {
            JOptionPane.showMessageDialog(null, "Your cart is empty",
                    "Your cart is empty", JOptionPane.INFORMATION_MESSAGE);
        }
        cardLayout.show(contentPane, "MAIN MENU");
    }

    // MODIFIES: orderHistory
    // REQUIRES:
    // EFFECTS: save the order number and its order number into history
    public List saveOrder(int orderNum) {
        List<Food> allItem = cart.getOrderList();
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
        return orderHistory;
    }

    // MODIFIES: historyPage
    // EFFECTS: view the order history
    public void viewHistory() {
        historyPage.removeAll();
        historyPage.add(backButtons.get(5));

        if (!orderHistory.isEmpty()) {
            for (String order : orderHistory) {
                //System.out.println(order);
                JTextArea orderInfo = new JTextArea(order);
                historyPage.add(orderInfo);
            }
        } else {
            JLabel emptyHistory = new JLabel("Your order history is empty");
            historyPage.add(emptyHistory);
        }
    }


    // EFFECTS: save the current cart to file
    public void saveCart() {
        String msg = "";
        try {
            jsonWriter.open();
            jsonWriter.write(cart);
            jsonWriter.close();
            msg = "Shopping cart saved to " + JSON_STORE;
        } catch (FileNotFoundException e) {
            msg = "Unable to write to file: " + JSON_STORE;

        }
        JOptionPane.showMessageDialog(null, msg,
                "Save to File", JOptionPane.INFORMATION_MESSAGE);
    }

    // MODIFIES: cart
    // EFFECTS: load the cart from file
    public void loadCart() {
        String msg = "";
        try {
            cart = jsonReader.read();
            msg = "Loaded shopping cart from " + JSON_STORE;
            System.out.println(cart.getOrderList());

        } catch (IOException e) {
            msg = "Unable to read from file: " + JSON_STORE;
        }
        JOptionPane.showMessageDialog(null, msg,
                "Load from File", JOptionPane.INFORMATION_MESSAGE);
    }




}