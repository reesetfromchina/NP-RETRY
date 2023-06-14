import java.util.Scanner;
import java.util.*;
public class PhoneBookTest1 {

    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < n; ++i) {
            String line = scanner.nextLine();
            String[] parts = line.split(":");
            try {
                phoneBook.addContact(parts[0], parts[1]);
            } catch (DuplicateNumberException e) {
                System.out.println(e.getMessage());
            }
        }
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            System.out.println(line);
            String[] parts = line.split(":");
            if (parts[0].equals("NUM")) {
                phoneBook.contactsByNumber(parts[1]);
            } else {
                phoneBook.contactsByName(parts[1]);
            }
        }
    }

}


class PhoneBook {
    private Map<String, TreeSet<Contact>> nameMap;         // maps names to contacts
    private Map<String, TreeSet<Contact>> numberPrefixMap; // maps number prefixes to contacts

    public PhoneBook() {
        nameMap = new HashMap<>();
        numberPrefixMap = new HashMap<>();
    }

    public void addContact(String name, String number) throws DuplicateNumberException {
        Contact contact = new Contact(name, number);

        // Check if number already exists
        if (numberPrefixMap.containsKey(number.substring(0, 3))) {
            for (Contact existingContact : numberPrefixMap.get(number.substring(0, 3))) {
                if (existingContact.getNumber().equals(number)) {
                    throw new DuplicateNumberException("Duplicate number: " + number);
                }
            }
        }

        // Add contact to name map
        if (!nameMap.containsKey(name)) {
            nameMap.put(name, new TreeSet<>());
        }
        nameMap.get(name).add(contact);

        // Add contact to number prefix map
        String numberPrefix = number.substring(0, 3);
        if (!numberPrefixMap.containsKey(numberPrefix)) {
            numberPrefixMap.put(numberPrefix, new TreeSet<>());
        }
        numberPrefixMap.get(numberPrefix).add(contact);
    }

    public void contactsByNumber(String number) {
        if (number.length() < 3) {
            System.out.println("Minimum length of number is 3");
            return;
        }

        // Find contacts with matching number prefix
        Set<Contact> contacts = new TreeSet<>();
        String numberPrefix = number.substring(0, 3);
        if (numberPrefixMap.containsKey(numberPrefix)) {
            for (Contact contact : numberPrefixMap.get(numberPrefix)) {
                if (contact.getNumber().contains(number)) {
                    contacts.add(contact);
                }
            }
        }

        // Print contacts
        for (Contact contact : contacts) {
            System.out.println(contact.getName() + ": " + contact.getNumber());
        }
    }

    public void contactsByName(String name) {
        if (!nameMap.containsKey(name)) {
            System.out.println("No contacts found with name: " + name);
            return;
        }

        // Print contacts
        for (Contact contact : nameMap.get(name)) {
            System.out.println(contact.getName() + ": " + contact.getNumber());
        }
    }
}

class Contact implements Comparable<Contact> {
    private String name;
    private String number;

    public Contact(String name, String number) {
        this.name = name;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    @Override
    public int compareTo(Contact other) {
        if (name.compareTo(other.name) == 0) {
            return number.compareTo(other.number);
        } else {
            return name.compareTo(other.name);
        }
    }
}

class DuplicateNumberException extends Exception {
    public DuplicateNumberException(String message) {
        super(message);
    }
}

// Вашиот код овде

