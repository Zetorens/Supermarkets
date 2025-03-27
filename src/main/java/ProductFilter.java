import java.util.ArrayList;
import java.util.stream.Collectors;

public class ProductFilter {

    public static ArrayList<Product> filterProductsByPriceRange(double minPrice, double maxPrice, ArrayList<Product> products) {
        return products.stream()
                .filter(p -> p.getPrice() >= minPrice && p.getPrice() <= maxPrice)
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
