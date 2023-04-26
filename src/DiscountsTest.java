import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


public class DiscountsTest {
    public static void main(String[] args) throws IOException {
        Discounts discounts = new Discounts();
        int stores = discounts.readStores(System.in);
        System.out.println("Stores read: " + stores);
        System.out.println("=== By average discount ===");
              discounts.byAverageDiscount().forEach(System.out::println);
        System.out.println("=== By total discount ===");
//        discounts.byTotalDiscount().forEach(System.out::println);

    }
}

// Vashiot kod ovde

class Discounts{

    List<Store> storeList;
    public Discounts() {
        storeList = new ArrayList<>();
    }

    public int readStores(InputStream inputStream) throws IOException {
        Scanner sc = new Scanner(inputStream);
        while(sc.hasNextLine()){
            String line = sc.nextLine();
            String storeName;
            String[] parts = line.split("\\s+");
            storeName = parts[0];
            List<Discount> d = new ArrayList<>();;

            for(int i = 1; i<parts.length; i++){
                String[] partsDiscount = parts[i].split(":");
                for(int j = 0; j<partsDiscount.length; j+=2){
                    d.add(new Discount(Integer.parseInt(partsDiscount[j]), Integer.parseInt(partsDiscount[j+1])));
                }
            }
            storeList.add(new Store(storeName, d));
        }
        return storeList.size();
    }

    //    - метод кој враќа листа од 3-те продавници со најголем просечен попуст (просечна вредност на попустот за секој производ од таа продавница). Попустот (намалувањето на цената) е изразен во цел број (проценти) и треба да се пресмета од намалената цена и оригиналната цена. Ако две продавници имаат ист попуст, се подредуваат според името лексикографски.
    public List<Store> byAverageDiscount(){
        return storeList.stream().sorted(Comparator.comparing(Store::averageSale)).collect(Collectors.toList());

    }

    @Override
    public String toString() {
        return "Discounts{";
    }
}

class Store{
    String name;
    List<Discount> discountsList;

    public Store(String name, List<Discount> discountsList) {
        this.name = name;
        this.discountsList = discountsList;
    }

    public int averageSale(){
        int average = 0;

        for (Discount discounts : discountsList) {
//            average += ((discounts.sale_price - discounts.price) / discounts.price);
            average += discounts.sale_price;
//            System.out.println();
//            System.out.println("average: "+ average);
       }
        int result = average / discountsList.size();
//        System.out.println(result);
       return result;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
       return String.format("%s %d",name, averageSale());
    }
}

class Discount{
    int sale_price, price;

    public Discount(int sales_price, int price){
        this.sale_price = sales_price;
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("%s:%s", sale_price, price);
    }
}