import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // === Questions 1, 2, 3 ===
        // Charger les produits
        String filePath = "products.txt";
        ArrayList<Product> products = ProductLoader.loadProductsFromFile(filePath);

        System.out.println("=== Liste des produits ===");
        for (Product p : products) {
            System.out.println(p);
        }

        // Charger toutes les commandes
        System.out.println("\n=== Commandes ===");
        ArrayList<Order> orders = OrderLoader.loadOrdersFromFile("orders.txt");
        for (Order o : orders) {
            System.out.println(o);
        }

        // Filtrer les commandes récentes
        System.out.println("\n=== Commandes récentes (moins d'un an) ===");
        ArrayList<Order> recentOrders = OrderLoader.filterRecentOrders(orders);
        for (Order o : recentOrders) {
            System.out.println(o);
        }

        // Partie base de données
        System.out.println("\n=== Insertion en base de données ===");
        DatabaseInserter.insertProducts(products);
        DatabaseInserter.insertOrders(recentOrders);

        // === Question 4 : Menu interactif ===
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n=== MENU PRINCIPAL [Q4] ===");
            System.out.println("1. Afficher les produits depuis la base de données");
            System.out.println("2. Ajouter un produit");
            System.out.println("3. Supprimer une commande");
            System.out.println("4. Afficher les produits d'une catégorie");
            System.out.println("5. Afficher les produits avec un stock < 20");
            System.out.println("6. Filtrer les produits par plage de prix");
            System.out.println("7. Quitter");
            System.out.print("Votre choix : ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.println("\n=== [Q4] Produits depuis la base de données ===");
                    ArrayList<Product> productsFromDB = DatabaseReader.getAllProducts();
                    for (Product p : productsFromDB) {
                        System.out.println(p);
                    }
                }
                case 2 -> {
                    System.out.println("\n=== Ajouter un nouveau produit ===");
            
                    System.out.print("ID produit : ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
            
                    System.out.print("Nom : ");
                    String name = scanner.nextLine();
            
                    System.out.print("Catégorie : ");
                    String category = scanner.nextLine();
            
                    System.out.print("Prix : ");
                    double price = scanner.nextDouble();
            
                    System.out.print("Stock : ");
                    int stock = scanner.nextInt();
                    scanner.nextLine();
            
                    Product newProduct = new Product(id, name, category, price, stock);
                    DatabaseInserter.insertProduct(newProduct);
                }

                case 3 -> {
                    System.out.print("\nID de la commande à supprimer : ");
                    int orderIdToDelete = scanner.nextInt();
                    scanner.nextLine();
                    DatabaseDeleter.deleteOrderById(orderIdToDelete);
                }

                case 4 -> {
                    System.out.print("Entrez le nom de la catégorie : ");
                    String cat = scanner.nextLine();
                    ArrayList<Product> productsInCategory = DatabaseReader.getProductsByCategory(cat);
            
                    if (productsInCategory.isEmpty()) {
                        System.out.println("Aucun produit trouvé pour cette catégorie.");
                    } else {
                        System.out.println("\nProduits de la catégorie \"" + cat + "\" :");
                        for (Product p : productsInCategory) {
                            System.out.println(p);
                        }
                    }
                }

                case 5 -> {
                    System.out.println("\n=== Produits avec stock inférieur à 20 ===");
                    ArrayList<Product> lowStockProducts = DatabaseReader.getProductsLowStock(20);
                
                    if (lowStockProducts.isEmpty()) {
                        System.out.println("Aucun produit avec un stock inférieur à 20.");
                    } else {
                        for (Product p : lowStockProducts) {
                            System.out.println(p);
                        }
                    }
                }
                
                case 6 -> {
                    System.out.print("Prix minimum : ");
                    double minPrice = Double.parseDouble(scanner.nextLine().replace(",", "."));
                    System.out.print("Prix maximum : ");
                    double maxPrice = Double.parseDouble(scanner.nextLine().replace(",", "."));
                
                    ArrayList<Product> allProducts = DatabaseReader.getAllProducts();
                
                    ArrayList<Product> filtered = ProductFilter.filterProductsByPriceRange(minPrice, maxPrice, allProducts);
                
                    if (filtered.isEmpty()) {
                        System.out.println("Aucun produit trouvé dans cette plage de prix.");
                    } else {
                        System.out.println("\nProduits entre " + minPrice + " EUR et " + maxPrice + " EUR :");
                        for (Product p : filtered) {
                            System.out.println(p);
                        }
                    }
                }
                
                case 7 -> System.out.println("Fin du programme. Merci !");                

                default -> System.out.println("Choix invalide. Réessayez.");
            }
        } while (choice != 7);

        scanner.close();
    }
}
