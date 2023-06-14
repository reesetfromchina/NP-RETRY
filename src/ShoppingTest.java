import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

abstract class Product {
    private  String id;
    private String name;
    private double price;
    private double quantity;

    public Product(String id, String name, double price, double quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
    public abstract double calculatePrice();


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }


    public String toString() {
        return String.format("%s - %.2f", getId(), calculatePrice());
    }
}

class WholeProduct extends  Product{


    public WholeProduct(String id, String name, double price, double quantity) {
        super(id, name, price, quantity);
    }

    public double calculatePrice(){
        return getPrice() * getQuantity();
    }

    @Override
    public String toString() {
        return String.format("%s - %.2f", getId(), calculatePrice());
    }
}

class GramProduct extends Product{


    public GramProduct(String id, String name, double price, double quantity) {
        super(id, name, price, quantity);
    }

    @Override
    public String toString() {
        return String.format("%s - %.2f", getId(), calculatePrice());
    }


    public double calculatePrice(){
        return getPrice() * (getQuantity() / 1000);
    }

}

class ShoppingCart{
    List<WholeProduct> wholeProducts;
    List<GramProduct> gramProducts;
    List<Product> products;
    public ShoppingCart() {
        wholeProducts = new ArrayList<>();
        gramProducts = new ArrayList<>();
        products = new ArrayList<>();
    }


    public void addItem(String itemData) throws InvalidOperationException {
        String [] parts = itemData.split(";");
        String id = parts[1];
        String name = parts[2];
        double price = Double.parseDouble(parts[3]);
        double quantity = Double.parseDouble(parts[4]);

        if(quantity == 0){
            throw new InvalidOperationException(String.format("The quantity of the product with id %s can not be 0.", id));
        }
        if(parts[0].equals("WS")){
            products.add(new WholeProduct(id, name, price, quantity));
        }else{
            products.add(new GramProduct(id, name, price, quantity));
        }
    }

    public void printShoppingCart(OutputStream outputStream){
        PrintWriter pw = new PrintWriter(outputStream);

        List<Product> collectedProducts = products.stream().sorted(Comparator.comparing(Product::calculatePrice).reversed()).collect(Collectors.toList());
        collectedProducts.forEach(pw::println);

        pw.flush();

    }


    public void blackFridayOffer(List<Integer> discountItems, OutputStream os) throws InvalidOperationException {
        if(discountItems.size() == 0){
            throw new InvalidOperationException("There are no products with discount.");
        }

        PrintWriter pw = new PrintWriter(os);

        for(int i = 0; i < products.size(); i++){
            for(int j =0; j<discountItems.size(); j++){
                if(products.get(i).getId().equals(discountItems.get(j).toString())){
                    products.get(i).setPrice(products.get(i).getPrice() * 0.1);
                    pw.println(products.get(i));
                }

            }
        }

//        products.stream()
//                .filter(p -> discountItems.contains(p.getId()))
//                .forEach(p -> {
//                    p.setPrice(p.getPrice() * 0.1);
//                    pw.println("ALO BEVERLI");
//                });

        pw.flush();
    }
}


public class ShoppingTest {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ShoppingCart cart = new ShoppingCart();

        int items = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < items; i++) {
            try {
                cart.addItem(sc.nextLine());
            } catch (InvalidOperationException e) {
                System.out.println(e.getMessage());
            }
        }

        List<Integer> discountItems = new ArrayList<>();
        int discountItemsCount = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < discountItemsCount; i++) {
            discountItems.add(Integer.parseInt(sc.nextLine()));
        }

        int testCase = Integer.parseInt(sc.nextLine());
        if (testCase == 1) {
            cart.printShoppingCart(System.out);
        } else if (testCase == 2) {
            try {
                cart.blackFridayOffer(discountItems, System.out);
            } catch (InvalidOperationException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Invalid test case");
        }
    }
}


    class InvalidOperationException extends Exception {
    public InvalidOperationException(String message) {
        super(String.format(message));
    }
}