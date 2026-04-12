package task05.processing;

import task05.container.Box;
import task05.container.Pair;

public class InspectionUtils {

    public static <T> void logInspection(T item) {
        System.out.println("=== INSPECTION LOG ===");

        System.out.printf("  %-6s: %s\n", "Type", item.getClass().getSimpleName());
        System.out.printf("  %-6s: %s\n", "Value", item.toString());

        System.out.println("======================");
    }

    public static <T> Pair<String, T> label(String id, T item) {
        System.out.println(
                String.format("%s '%s' to: %s", "[LABELER] Assigning label", id, item.toString())
        );

        return new Pair<>(id, item);
    }

    public static <T> void transferBetweenBoxes(Box<T> source, Box<T> destination) {
        T item = source.getAndClear();

        destination.put(item);

        System.out.println("[TRANSFER] Moved: " + item.toString());
    }

}
