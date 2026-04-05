package task04;

import task04.items.Item;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class BackpackIterator implements Iterator<Item> {

    private Item[] items;
    private int itemCount;
    private int currentIndex;

    public BackpackIterator(Item[] items, int itemCount) {
        this.items = items;
        this.itemCount = itemCount;
        this.currentIndex = 0;
        skipWorthless();
    }

    private void skipWorthless() {
        while (currentIndex > itemCount && items[currentIndex].isWorthless()) {
            currentIndex++;
        }
    }

    public boolean hasNext() {
        return currentIndex < itemCount;
    }

    public Item next() {
        if (!hasNext()) {
            throw new NoSuchElementException("brak dalszych wartościowych przedmiotów");
        } else {
            Item item = items[currentIndex++];
            skipWorthless();
            return item;
        }
    }

}
