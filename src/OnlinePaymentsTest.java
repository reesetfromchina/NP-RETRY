import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Array;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class OnlinePaymentsTest {
    public static void main(String[] args) {
        OnlinePayments onlinePayments = new OnlinePayments();

        onlinePayments.readItems(System.in);

        IntStream.range(151020, 151025).mapToObj(String::valueOf).forEach(id -> onlinePayments.printStudentReport(id, System.out));
    }
}

class OnlinePayments{
    Map<String, Payment> paymentMap;
    public OnlinePayments() {
        paymentMap = new HashMap<>();
    }
    void readItems (InputStream is){
        Scanner sc = new Scanner(is);

        while(sc.hasNextLine()){
            String line = sc.nextLine();
            String [] parts = line.split(";");
            Payment payment = new Payment(parts[0]);
            paymentMap.putIfAbsent(parts[0], payment);
            paymentMap.get(parts[0]).items.add(new Items(parts[1], Integer.parseInt(parts[2])));
        }
    }

    void printStudentReport (String index, OutputStream os){
        PrintWriter pw = new PrintWriter(os);
        if(paymentMap.get(index) != null){
//            System.out.printf("Student: %s Net: %d Fee: %d Total: %d\n",index, paymentMap.get(index).calculateNet(),paymentMap.get(index).calculateFee(), paymentMap.get(index).calculateNet() + paymentMap.get(index).calculateFee() );
            pw.printf("Student: %s Net: %d Fee: %d Total: %d\n",index, paymentMap.get(index).calculateNet(),paymentMap.get(index).calculateFee(), paymentMap.get(index).calculateNet() + paymentMap.get(index).calculateFee() );

            List<Items> collect = paymentMap.get(index).items.stream().sorted(Comparator.comparing(Items::getPrice).reversed()).collect(Collectors.toList());
            int i = 1;
//            System.out.println("Items:");
            pw.println("Items:");
            for (Items item : collect) {
//                System.out.printf("%d. %s %d\n", i, item.itemName, item.price);
                pw.printf("%d. %s %d\n", i, item.itemName, item.price);
                i++;
            }
        }
        else{
//            System.out.printf("Student %s not found!\n", index);
            pw.printf("Student %s not found!\n", index);
        }

        pw.flush();
    }
}

class Payment{
    String index;
    List<Items> items;

    public Payment(String index) {
        this.index = index;
        items = new ArrayList<>();
    }



    public int calculateNet(){
        int net = 0;
        for (Items item : items) {
            net += item.price;
        }
        return net;
    }

    public int calculateFee(){
        float fee;
        fee = (float) ((calculateNet() * 1.14) / 100);
        if (fee <3){
            fee = 3;
        }else if(fee>300){
            fee = 300;
        }
        return Math.round(fee);
    }

    public void printItems(){
        int i = 1;
        System.out.println("Items:");
        for (Items item : items) {
            System.out.printf("%d. %s %d\n", i, item.itemName, item.price);
            i++;
        }
    }
}

class Items{
    String itemName;
    int price;

    public Items(String itemName, int price) {
        this.itemName = itemName;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Items{" +
                "itemName='" + itemName + '\'' +
                ", price=" + price +
                '}';
    }

    public int getPrice() {
        return price;
    }
}