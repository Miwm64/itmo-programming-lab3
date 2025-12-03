package objects;

import java.time.LocalDateTime;

public class BackpackTest {
    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();

        Backpack backpack = new Backpack("Adventure Pack", now, "For exploring");
        Stone stone = new Stone("Granite", now, "Heavy rock");
        Tree tree = new Tree("Oak", now, "Tall tree", 15.0);

        System.out.println("=== Testing Backpack ===");

        // Test add items
        backpack.addItem(stone);
        backpack.addItem(tree);
        System.out.println("Added 2 items. Count: " + backpack.getItems().size());

        // Test get item
        WorldObject firstItem = backpack.getItem(0);
        System.out.println("First item: " + firstItem.getTitle());

        // Test get items copy
        var itemsCopy = backpack.getItems();
        itemsCopy.clear();
        System.out.println("After clearing copy, backpack still has: " +
                backpack.getItems().size() + " items");

        // Test take item
        WorldObject taken = backpack.takeItem(0);
        System.out.println("Took item: " + taken.getTitle());
        System.out.println("Now has: " + backpack.getItems().size() + " items");

        // Test remove by object
        backpack.removeItem(tree);
        System.out.println("After removing tree, has: " +
                backpack.getItems().size() + " items");

        // Test clone
        backpack.addItem(stone);
        backpack.addItem(tree);
        Backpack cloned = backpack.clone();
        System.out.println("Clone equals original: " + backpack.equals(cloned));

        // Test set items
        var newItems = new java.util.ArrayList<WorldObject>();
        newItems.add(new Stone("Ruby", now, "Red gem"));
        backpack.setItems(newItems);
        System.out.println("After setItems, has: " + backpack.getItems().size() +
                " items, first: " + backpack.getItem(0).getTitle());

        System.out.println("=== All Backpack tests completed ===");
    }
}