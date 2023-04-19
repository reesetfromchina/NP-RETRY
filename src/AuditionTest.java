import java.util.*;

public class AuditionTest {
    public static void main(String[] args) {
        Audition audition = new Audition();
        List<String> cities = new ArrayList<String>();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(";");
            if (parts.length > 1) {
                audition.addParticipant(parts[0], parts[1], parts[2],
                        Integer.parseInt(parts[3]));
            } else {
                cities.add(line);
            }
        }
        for (String city : cities) {
            System.out.printf("+++++ %s +++++\n", city);
            audition.listByCity(city);
        }
        scanner.close();
    }
}

class Audition{
    Map<String, Map<String, Candidate>> cityMapListCandidatesMap;
    public Audition() {
        cityMapListCandidatesMap = new HashMap<>();
    }
    void addParticipant(String city, String code, String name, int age){
        cityMapListCandidatesMap.putIfAbsent(city, new HashMap<>());
        cityMapListCandidatesMap.get(city).putIfAbsent(code, new Candidate(city, code, name, age));


    }

    void listByCity(String city){
        cityMapListCandidatesMap
                .entrySet()
                .stream()
                .filter(e -> e.getKey().equals(city))
                        .forEach(e-> e.getValue()
                                .entrySet()
                                .stream()
                                .sorted(Map.Entry.comparingByValue(Comparator.comparing(Candidate::getName)
                                        .thenComparing(Candidate::getAge)))
                                .forEach(asim -> System.out.println(asim.getValue())));
    }
}

class Candidate{
    String city, code, name;
    int age;

    public Candidate(String city, String code, String name, int age) {
        this.city = city;
        this.code = code;
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return String.format("%s %s %d", code, name, age);
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}