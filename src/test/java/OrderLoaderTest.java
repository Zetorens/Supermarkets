import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class OrderLoaderTest {

    @Test
    public void testFilterRecentOrders() {
        ArrayList<Order> orders = new ArrayList<>();

        // commande de plus d'un an -> doit être exclue
        orders.add(new Order(1, LocalDate.now().minusYears(2), 1, 5));

        // commande pile un an -> doit être incluse
        orders.add(new Order(2, LocalDate.now().minusYears(1), 2, 10));

        // commande récente -> doit être incluse
        orders.add(new Order(3, LocalDate.now().minusMonths(3), 3, 7));

        ArrayList<Order> filtered = OrderLoader.filterRecentOrders(orders);

        // On attend 2 commandes dans la liste filtrée
        assertEquals(2, filtered.size());

        // Vérifie qu'on a bien les commandes ID 2 et 3
        assertTrue(filtered.stream().anyMatch(o -> o.getOrderId() == 2));
        assertTrue(filtered.stream().anyMatch(o -> o.getOrderId() == 3));
    }
}
