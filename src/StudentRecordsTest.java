import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * January 2016 Exam problem 1
 */
public class StudentRecordsTest {
    public static void main(String[] args) {
        System.out.println("=== READING RECORDS ===");
        StudentRecords studentRecords = new StudentRecords();
        int total = studentRecords.readRecords(System.in);
        System.out.printf("Total records: %d\n", total);
        System.out.println("=== WRITING TABLE ===");
        studentRecords.writeTable(System.out);
        System.out.println("=== WRITING DISTRIBUTION ===");
        studentRecords.writeDistribution(System.out);
    }
}

// your code here

class StudentRecords {
    Map<String, List<Student>> directonByStudent;

    public StudentRecords() {
        directonByStudent = new HashMap<>();
    }

    int readRecords(InputStream inputStream) {
        Scanner sc = new Scanner(inputStream);
        int count = 0;
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] parts = line.split("\\s+");

            String code = parts[0];
            String direction = parts[1];
            directonByStudent.putIfAbsent(direction, new ArrayList<>());
            List<Integer> grades = new ArrayList<>();

            for (int i = 2; i < parts.length; i++) {
                grades.add(Integer.valueOf(parts[i]));

            }

//            students.add(new Student(code, direction, grades));
            count++;
            directonByStudent.get(direction).add(new Student(code, direction, grades));
        }

        return count;
    }

    void writeTable(OutputStream outputStream) {
        PrintWriter pw = new PrintWriter(outputStream);

        Map<String, List<Student>> sortedStudentsByDirection = directonByStudent.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> e.getValue().stream()
                                .sorted(Comparator.comparing(Student::getDirection).reversed().thenComparing(Student::avarage).reversed())
                                .collect(Collectors.toList()),
                        (v1, v2) -> v1,
                        LinkedHashMap::new

                ));

//        System.out.println(sortedStudentsByDirection);
        for (Map.Entry<String, List<Student>> entry : sortedStudentsByDirection.entrySet()) {
            String key = entry.getKey();
            System.out.println(key);
            entry.getValue().stream().forEach(i -> System.out.println(i));

        }

//        listMap.entrySet().stream().sorted(Comparator.comparing(Student::getDirection).reversed().thenComparing(Student::avarage).reversed());
//        students.stream()
//                .sorted(Comparator.comparing(Student::getDirection).reversed().thenComparing(Student::avarage).reversed())
//                .forEach(student -> {
//                    System.out.printf("%s %.2f\n",student.code, student.avarage());
//                });

//        List<Student> collect = students.stream().distinct().collect(Collectors.toList());
//        System.out.println(collect);
    }

    void writeDistribution(OutputStream outputStream) {
//        // Создади мапа за чување на бројот на оценки по насока
////        Map<String, int[]> distribution = new HashMap<>();
//
//        Map<String, List<Student>> sortedStudentsBy10s = directonByStudent.entrySet()
//                .stream()
//                .sorted(Map.Entry.comparingByKey())
//                .collect(Collectors.toMap(
//                        Map.Entry::getKey,
//                        e -> e.getValue().forEach(student -> {
//                            for (int grade : student.getGrades()) {
//                                if (grade == 10) {
//                                    student.numberOf10++;
//                                }
//                            }
//                        });
//                        (v1, v2) -> v1,
//                        LinkedHashMap::new
//
//                ));
//
//
//        // Итерирај низ сите студенти
//        for (Map.Entry<String, List<Student>> entry : sortedStudentsBy10ns.entrySet()) {
//            String direction = entry.getKey();
//            List<Student> students = entry.getValue();
//            System.out.println(direction);
//            // Иницијализирај низ со бројот на оценки за секоја насока
//            int[] counts = new int[11];
////            List<Integer> counts1 = new ArrayList<>(11);
//            List<Integer> counts1 = new ArrayList<>(Collections.nCopies(11, 0));
//            // Итерирај низ сите студенти за тековната насока и зголеми го бројот на оценки
//            for (Student student : students) {
//                for (int grade : student.grades) {
////                    counts[grade]++;
//                    counts1.set(grade, counts1.get(grade) + 1);
//                }
//            }
//
//            // Додади го низот со бројот на оценки за тековната насока во мапата
////            distribution.put(direction, counts);
//            System.out.println(counts1);
//
//        }
//        System.out.println(sortedStudentsBy10ns);
//
//        for (Map.Entry<String, List<Student>> entry : sortedStudentsBy10ns.entrySet()) {
//            String key = entry.getKey();
//            System.out.println(key);
//            AtomicInteger sum = new AtomicInteger();
//
//            entry.getValue().stream().sorted(Comparator.comparing(Student::numberOf10s)).forEach(i ->
//                    {
//                        sum.addAndGet(i.numberOf10s());
////                        System.out.println(sum.get());
//                    });
//            System.out.println(sum.get());
        }

        // Сортирај насоките според бројот на десетки во растечки редослед
//        List<String> directions = new ArrayList<>(distribution.keySet());
//        Collections.sort(directions, (a, b) -> {
//            int aTens = distribution.get(a)[10];
//            int bTens = distribution.get(b)[10];
//            return Integer.compare(aTens, bTens);
//        });
//
//        // Запиши ја дистрибуцијата на оценките во излезниот поток
//        PrintWriter writer = new PrintWriter(outputStream);
//        for (String direction : directions) {
//            int[] counts = distribution.get(direction);
//            writer.printf("%2d | %s (%d)%n", 10, repeat('*', counts[10] / 10), counts[10]);
//            for (int i = 9; i >= 0; i--) {
//                writer.printf("%2d | %s (%d)%n", i, repeat('*', counts[i] / 10), counts[i]);
//            }
//            writer.println();
//        }
//        writer.flush();
//    }
//
//    // Помошна метода за повторување на знакот *
//    private String repeat(char c, int count) {
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < count; i++) {
//            sb.append(c);
//        }
//        return sb.toString();
//    }
    }

    class Student {
        String code;
        String direction;
        List<Integer> grades;
        int numberOf10;
        public Student(String code, String direction, List<Integer> grades) {
            this.code = code;
            this.direction = direction;
            this.grades = grades;
            numberOf10 = 0;
        }

        @Override
        public String toString() {
            return String.format("%s %.2f", code, avarage());
        }

        public String getCode() {
            return code;
        }

        public String getDirection() {
            return direction;
        }

        public double avarage() {
            int sum = 0;
            for (int grade : grades) {
                sum += grade;
            }

            return (sum + 0.0) / grades.size();
        }

        public List<Integer> getGrades() {
            return grades;
        }

        public int getNumberOf10() {
            return numberOf10;
        }

        public int numberOf10s(){
            int count = 0;
            for(int grade : grades){
                if (grade == 10) {
                    count++;
                }
            }
            return count;
        }

        public static int countOccurrences(int[] arr, int target) {
            int count = 0;
            for (int num : arr) {
                if (num == target) {
                    count++;
                }
            }
            return count;
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Student student = (Student) o;
            return Objects.equals(direction, student.direction);
        }

        @Override
        public int hashCode() {
            return Objects.hash(direction);
        }
    }
