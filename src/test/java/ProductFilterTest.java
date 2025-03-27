import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ProductFilterTest {

    @Test
    public void testFilterProductsByPriceRange() {
        ArrayList<Product> products = new ArrayList<>();
        products.add(new Product(1, "Produit A", "CatA", 5.0, 10));
        products.add(new Product(2, "Produit B", "CatA", 10.0, 10));
        products.add(new Product(3, "Produit C", "CatB", 15.0, 10));
        products.add(new Product(4, "Produit D", "CatB", 25.0, 10));

        ArrayList<Product> filtered = ProductFilter.filterProductsByPriceRange(5.0, 15.0, products);

        // On attend les produits 1, 2 et 3
        assertEquals(3, filtered.size());
        assertTrue(filtered.stream().anyMatch(p -> p.getProductId() == 1));
        assertTrue(filtered.stream().anyMatch(p -> p.getProductId() == 2));
        assertTrue(filtered.stream().anyMatch(p -> p.getProductId() == 3));
    }
}
