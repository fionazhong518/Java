package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LoadInfoTest {

    @Test
    private void testLoadInfo() {
    }

    // EFFECTS: return a List that loaded all burgers and their info
    @Test
    public void testLoadBurgers() {
        assertEquals(9 , LoadInfo.loadBurgers().size());
    }

    // EFFECTS: return a List that loaded all sides and their info
    @Test
    public void testLoadSides() {
        assertEquals(9, LoadInfo.loadSides().size());
    }

    // EFFECTS: return a List that loaded all desserts and their info
    @Test
    public void testLoadDesserts() {
        assertEquals(9, LoadInfo.loadDesserts().size());
    }

    // EFFECTS: return a List that loaded all drinks and their info
    @Test
    public void testLoadDrinks() {
        assertEquals(9, LoadInfo.loadDrinks().size());
    }
}
