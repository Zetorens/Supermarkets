import java.sql.*;
import java.util.ArrayList;

public class DatabaseReader {

    public static ArrayList<Product> getAllProducts() {
        ArrayList<Product> products = new ArrayList<>();

        String sql = "SELECT * FROM products";

        try (Connection conn = DatabaseConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("product_id");
                String name = rs.getString("name");
                String category = rs.getString("category");
                double price = rs.getDouble("price");
                int stock = rs.getInt("stock");

                products.add(new Product(id, name, category, price, stock));
            }

        } catch (SQLException e) {
            Logger.log("Erreur lors de la lecture des produits (getAllProducts) : " + e.getMessage());
            System.out.println("Erreur lecture produits depuis la base : " + e.getMessage());
        }

        return products;
    }

    public static ArrayList<Product> getProductsByCategory(String category) {
        ArrayList<Product> products = new ArrayList<>();
    
        String sql = "SELECT * FROM products WHERE category = ?";
    
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
    
            stmt.setString(1, category);
            ResultSet rs = stmt.executeQuery();
    
            while (rs.next()) {
                int id = rs.getInt("product_id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                int stock = rs.getInt("stock");
    
                products.add(new Product(id, name, category, price, stock));
            }
    
        } catch (SQLException e) {
            Logger.log("Erreur lecture produits par catégorie (getProductsByCategory) : " + e.getMessage());
            System.out.println("Erreur lecture produits par catégorie : " + e.getMessage());
        }
    
        return products;
    }

    public static ArrayList<Product> getProductsLowStock(int threshold) {
        ArrayList<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products WHERE stock < ?";
    
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
    
            stmt.setInt(1, threshold);
            ResultSet rs = stmt.executeQuery();
    
            while (rs.next()) {
                int id = rs.getInt("product_id");
                String name = rs.getString("name");
                String category = rs.getString("category");
                double price = rs.getDouble("price");
                int stock = rs.getInt("stock");
    
                products.add(new Product(id, name, category, price, stock));
            }
    
        } catch (SQLException e) {
            Logger.log("Erreur lecture produits à faible stock (getProductsLowStock) : " + e.getMessage());
            System.out.println("Erreur lecture produits à faible stock : " + e.getMessage());
        }
    
        return products;
    }    
}
