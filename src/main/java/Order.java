import java.time.LocalDate;

public class Order {
    private int orderId;
    private LocalDate orderDate;
    private int productId;
    private int quantity;

    public Order(int orderId, LocalDate orderDate, int productId, int quantity) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.productId = productId;
        this.quantity = quantity;
    }

    public int getOrderId() { return orderId; }
    public LocalDate getOrderDate() { return orderDate; }
    public int getProductId() { return productId; }
    public int getQuantity() { return quantity; }

    @Override
    public String toString() {
        return String.format("Commande #%d | Date: %s | Produit ID: %d | Quantité: %d",
                orderId, orderDate, productId, quantity);
    }
}
