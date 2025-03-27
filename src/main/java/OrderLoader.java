import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class OrderLoader {

    public static ArrayList<Order> loadOrdersFromFile(String filePath) {
        ArrayList<Order> orders = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 4) {
                    int id = Integer.parseInt(parts[0]);
                    LocalDate date = LocalDate.parse(parts[1]);
                    int productId = Integer.parseInt(parts[2]);
                    int quantity = Integer.parseInt(parts[3]);

                    orders.add(new Order(id, date, productId, quantity));
                }
            }
        } catch (IOException e) {
            System.out.println("Erreur lecture commandes : " + e.getMessage());
        }
        return orders;
    }

    public static ArrayList<Order> filterRecentOrders(ArrayList<Order> orders) {
        LocalDate oneYearAgo = LocalDate.now().minusYears(1);

        return orders.stream()
                .filter(order -> order.getOrderDate().isAfter(oneYearAgo) || order.getOrderDate().isEqual(oneYearAgo))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
