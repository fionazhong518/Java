# McDonald's Ordering Application

## Description
### *What will it do & Who will use it*
This application allows users to order McDonald's online,
while being ***so convenient*** for "lazy" users who don't want to stand in line at the store,
or those in rush and trying to save time in line.
After ordering, they are given an order number, so they can pick up their order in-store.

In addition, the app will ***not only include items from the Canadian menu***,
it will also include new items from McDonald's in other countries.
For example, in China it has *Taro Pie* on the menu. And in Japan, it has *Japanese BBQ Egg Thick Burger*.
In this way, the app might interest people to find out what McDonald's sells in different countries.

### *Why is this project of interest to you*
As a fan of McDonald's, its mobile app is very convenient,
I can order food online in advance and don't have to wait in line at the store or wait so long to get my food.
Moreover, McDonald's in different countries have their own unique menu.
I have tried the Taro Pie in China, which is one of my favorite McDonald's food.
And on the internet, I saw a lot of people from all over the world sharing McDonald's products from their own countries,
which sparked my interest to try McDonald's from more countries.
Therefore, I want to design a McDonald's app which not only has the functions of the current app,
but also can add items from the menu of more countries so that more people can learn about them,
and maybe they will also be interested in trying new products.

### *User Stories*
- As a user, I want to add any items to my shopping cart.
- As a user, I want to remove any items from my shopping cart.
- As a user, I want to view the list of items under different food categories.
- As a user, I want to be able to see the nutrition information of the food.
- As a user, I want to be able to get an order number after placing the order.
- As a user, I want to be able to view the order history.
- As a user, when I select the quit option from the application menu, I want to be reminded to save my shopping cart to file (If I so choose).
- As a user, I want to be able to load my shopping cart from file (if I so choose).

# Instructions for Grader

- You can generate the first required action related to adding Xs to a Y by pressing any food button on the ui.
- You can generate the second required action related to removing Xs, from Y by pressing the food item in the 
shopping cart, and it will confirm if you want to delete this item or not.
- You can locate my visual component by looking at my main page and any food item pages.
- You can save the state of my application by pressing "Save to file" button on the ui.
- You can reload the state of my application by pressing "Load to file" button on the ui.

# Phase 4: Task 2 Sample Code
- Tue Apr 11 15:28:52 PDT 2023 Successfully added Double Quarter Ponder BLT
- Tue Apr 11 15:29:00 PDT 2023 Successfully remove Big Mac

# Phase 4: Task 3
- If I had more time to work on the project, I will upload some images of each item for user to have a visual image
of the item. Also, I can upload more items into my ordering system, also, 
I can build sliders on each page, so that when there are more items uploaded into the system, 
the user can slide up and down to view the menu and shopping cart when there
are more items in it. Also, I will sort my JButtons and JLabel on my pages, and decorate my ordering system to look nicer,
like the real Mcdonald ordering system. 
- Moreover, I will refactor my main class, such as the McDonaldApp class and GUI class, because there are lots of
method in it that makes the class structure looks complicated. If I have more time, I will grab some method into a
new class, in order to let my class's structure and methods be more clear and understandable. Also, I can find
some methods that have similar functions and structures, extract a new method to save spaces and reduce duplication,
for example, I have a lot of for-loop, and in my actionPerformed method in the GUI class, the way to check each JButton
is very similar. Therefor if I have more time, I will try to refactor this to reduce some lines, let my codes look
neater and simplified, by that, I don't need to exclude the checkstyle on this method.