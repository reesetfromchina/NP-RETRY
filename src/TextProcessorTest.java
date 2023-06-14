import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Scanner;

class TextProcessor{
    public TextProcessor(){

    }

    public String readText(InputStream is){
        StringBuilder sb = new StringBuilder();
        Scanner sc = new Scanner(is);
        while(sc.hasNextLine()){
            String line = sc.nextLine();
            String cleanLine = line.replaceAll("[^a-zA-Z\\s]", ""); // заменување на сите карактери кои не се букви или празни места со празен string
            sb.append(cleanLine).append("\n");

        }
        System.out.println(sb.toString().trim());
        return sb.toString().trim(); // trim() за тстранување на било какви празнини пред и по текстот
    }
    public void printTextsVectors(OutputStream os){

    }

}

class CosineSimilarityCalculator {
    public static double cosineSimilarity (Collection<Integer> c1, Collection<Integer> c2) {
        int [] array1;
        int [] array2;
        array1 = c1.stream().mapToInt(i -> i).toArray();
        array2 = c2.stream().mapToInt(i -> i).toArray();
        double up = 0.0;
        double down1=0, down2=0;

        for (int i=0;i<c1.size();i++) {
            up+=(array1[i] * array2[i]);
        }

        for (int i=0;i<c1.size();i++) {
            down1+=(array1[i]*array1[i]);
        }

        for (int i=0;i<c1.size();i++) {
            down2+=(array2[i]*array2[i]);
        }

        return up/(Math.sqrt(down1)*Math.sqrt(down2));
    }
}

public class TextProcessorTest {

    public static void main(String[] args) {
        TextProcessor textProcessor = new TextProcessor();

        textProcessor.readText(System.in);

//        System.out.println("===PRINT VECTORS===");
//        textProcessor.printTextsVectors(System.out);
//
//        System.out.println("PRINT FIRST 20 WORDS SORTED ASCENDING BY FREQUENCY ");
//        textProcessor.printCorpus(System.out,  20, true);
//
//        System.out.println("PRINT FIRST 20 WORDS SORTED DESCENDING BY FREQUENCY");
//        textProcessor.printCorpus(System.out, 20, false);
//
//        System.out.println("===MOST SIMILAR TEXTS===");
//        textProcessor.mostSimilarTexts(System.out);
    }
}