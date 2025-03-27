import java.io.*;
import java.util.ArrayList;

public class ProductLoader {

    public static ArrayList<Product> loadProductsFromFile(String filePath) {
        ArrayList<Product> products = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 5) {
                    int id = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    String category = parts[2];
                    double price = Double.parseDouble(parts[3]);
                    int stock = Integer.parseInt(parts[4]);
                    products.add(new Product(id, name, category, price, stock));
                }
            }
        } catch (IOException e) {
            System.out.println("Erreur lecture fichier produits : " + e.getMessage());
        }

        return products;
    }
}
