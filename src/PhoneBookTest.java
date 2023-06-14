//import java.util.HashMap;
//import java.util.Map;
//import java.util.Scanner;
//
//public class PhoneBookTest {
//
//    public static void main(String[] args) {
//        PhoneBook phoneBook = new PhoneBook();
//        Scanner scanner = new Scanner(System.in);
//        int n = scanner.nextInt();
//        scanner.nextLine();
//        for (int i = 0; i < n; ++i) {
//            String line = scanner.nextLine();
//            String[] parts = line.split(":");
//            try {
//                phoneBook.addContact(parts[0], parts[1]);
//            } catch (DuplicateNumberException e) {
//                System.out.println(e.getMessage());
//            }
//        }
//        while (scanner.hasNextLine()) {
//            String line = scanner.nextLine();
//            System.out.println(line);
//            String[] parts = line.split(":");
//            if (parts[0].equals("NUM")) {
//                phoneBook.contactsByNumber(parts[1]);
//            } else {
//                phoneBook.contactsByName(parts[1]);
//            }
//        }
//    }
//
//}
//
//// Вашиот код овде
//
//class PhoneBook{
//    Map<String, Contact> contactMap;
//    public PhoneBook(){
//        contactMap = new HashMap<>();
//    }
//    void addContact(String name, String number) throws DuplicateNumberException {
//        Contact contact = new Contact(name, number);
//        contactMap.putIfAbsent(number, contact);
//
//        if(contactMap.get(name).number.equals(number)){
//            throw new DuplicateNumberException(number);
//        }
//    }
//
//    void contactsByNumber(String number) {
//        if()
//    }
//}
//
//class Contact{
//    String name;
//    String number;
//
//    public Contact(String name, String number) {
//        this.name = name;
//        this.number = number;
//    }
//}
//
//class DuplicateNumberException extends Exception{
//    public DuplicateNumberException(String number) {
//        super(String.format("Duplicate number: [%s]", number));
//    }
//}