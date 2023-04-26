import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

public class RiskTester1 {
    public static void main(String[] args) {
        Risk1 risk = new Risk1();
        risk.processAttacksData(System.in);
    }
}

class Risk1{
    List<Integer> attackerList;
    List<Integer> defenderList;

    public Risk1() {
    }

    void processAttacksData (InputStream is){
        int wins = 0;

        Scanner sc = new Scanner(is);
        while (sc.hasNextLine()){
            String line = sc.nextLine();
            String [] parts = line.split(";");
            attackerList = new ArrayList<>();
            defenderList = new ArrayList<>();

            String [] partsAttacker = parts[0].split("\\s+");
            String [] partsDefender = parts[1].split("\\s+");

            attackerList.add(Integer.parseInt(partsAttacker[0]));
            attackerList.add(Integer.parseInt(partsAttacker[1]));
            attackerList.add(Integer.parseInt(partsAttacker[2]));
            defenderList.add(Integer.parseInt(partsDefender[0]));
            defenderList.add(Integer.parseInt(partsDefender[1]));
            defenderList.add(Integer.parseInt(partsDefender[2]));


            Collections.sort(attackerList);
            Collections.sort(defenderList);

            if(attackerList.get(0)> defenderList.get(0) && attackerList.get(1)> defenderList.get(1) && attackerList.get(2)> defenderList.get(2) )
                wins++;
        }
        System.out.println(wins);

    }
}


