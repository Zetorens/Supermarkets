import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class DatabaseInserter {

    public static void insertProducts(ArrayList<Product> products) {
        String sql = "INSERT INTO products (product_id, name, category, price, stock) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            for (Product p : products) {
                stmt.setInt(1, p.getProductId());
                stmt.setString(2, p.getName());
                stmt.setString(3, p.getCategory());
                stmt.setDouble(4, p.getPrice());
                stmt.setInt(5, p.getStock());
                stmt.executeUpdate();
            }

            System.out.println("Produits insérés en base de données.");

        } catch (SQLException e) {
            Logger.log("Erreur insertion produits (insertProducts) : " + e.getMessage());
            System.out.println("Erreur insertion produits : " + e.getMessage());
        }
    }

    public static void insertOrders(ArrayList<Order> orders) {
        String sql = "INSERT INTO orders (order_id, order_date, product_id, quantity) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            for (Order o : orders) {
                stmt.setInt(1, o.getOrderId());
                stmt.setDate(2, java.sql.Date.valueOf(o.getOrderDate()));
                stmt.setInt(3, o.getProductId());
                stmt.setInt(4, o.getQuantity());
                stmt.executeUpdate();
            }

            System.out.println("Commandes récentes insérées en base de données.");

        } catch (SQLException e) {
            Logger.log("Erreur insertion commandes (insertOrders) : " + e.getMessage());
            System.out.println("Erreur insertion commandes : " + e.getMessage());
        }
    }

    // Q4) Nouvelle méthode : ajouter un seul produit
    public static void insertProduct(Product p) {
        String sql = "INSERT INTO products (product_id, name, category, price, stock) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, p.getProductId());
            stmt.setString(2, p.getName());
            stmt.setString(3, p.getCategory());
            stmt.setDouble(4, p.getPrice());
            stmt.setInt(5, p.getStock());

            stmt.executeUpdate();
            System.out.println("Produit ajouté avec succès.");
        } catch (SQLException e) {
            Logger.log("Erreur lors de l'ajout du produit (insertProduct) : " + e.getMessage());
            System.out.println("Erreur lors de l'ajout du produit : " + e.getMessage());
        }
    }
}
