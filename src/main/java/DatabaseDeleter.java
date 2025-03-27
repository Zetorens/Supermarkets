import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseDeleter {

    public static void deleteOrderById(int orderId) {
        String sql = "DELETE FROM orders WHERE order_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, orderId);
            int rows = stmt.executeUpdate();

            if (rows > 0) {
                System.out.println("Commande supprimée avec succès.");
            } else {
                System.out.println("Aucune commande trouvée avec cet ID.");
            }

        } catch (SQLException e) {
            Logger.log("Erreur lors de la suppression de la commande (deleteOrderById) : " + e.getMessage());
            System.out.println("Erreur lors de la suppression de la commande : " + e.getMessage());
        }
    }
}
