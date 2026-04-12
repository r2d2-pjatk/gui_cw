package task05.transfer;

import task05.container.Box;

public class DockTransfer {

    public static <T> void transfer(Box<? extends T> source, Box<? super T> destination) {
        if (source.isEmpty()) {
            throw new IllegalStateException("brak elementu do przeniesienia");
        }
        
        T item = source.getAndClear();
        destination.put(item);
        
        System.out.println("[DOCK] Transferred: " + item);
    }

}
