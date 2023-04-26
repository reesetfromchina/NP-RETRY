import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

public class RiskTester {
    public static void main(String[] args) {
        Risk risk = new Risk();
        risk.processAttacksData(System.in);
    }
}

class Risk{
    List<Integer> attackerList;
    List<Integer> defenderList;

    public Risk() {

    }

    void processAttacksData (InputStream is){

       Scanner sc = new Scanner(is);
       while (sc.hasNextLine()){
           String line = sc.nextLine();
           String [] parts = line.split(";");
           attackerList = new ArrayList<>();
           defenderList = new ArrayList<>();

           String [] partsAttacker = parts[0].split("\\s+");
           String [] partsDefender = parts[1].split("\\s+");

//           Fight attacker = new Fight(Integer.parseInt(partsAttacker[0]), Integer.parseInt(partsAttacker[1]), Integer.parseInt(partsAttacker[2]));
           attackerList.add(Integer.parseInt(partsAttacker[0]));
           attackerList.add(Integer.parseInt(partsAttacker[1]));
           attackerList.add(Integer.parseInt(partsAttacker[2]));
           defenderList.add(Integer.parseInt(partsDefender[0]));
           defenderList.add(Integer.parseInt(partsDefender[1]));
           defenderList.add(Integer.parseInt(partsDefender[2]));


//           Fight defender = new Fight(Integer.parseInt(partsDefender[0]), Integer.parseInt(partsDefender[1]), Integer.parseInt(partsDefender[2]));
           Collections.sort(attackerList);
           Collections.sort(defenderList);
           int attackingScore = 0;
           int defendingScore = 0;

           if(attackerList.get(0) > defenderList.get(0)){
               attackingScore++;
           }else{
               defendingScore++;
           }
           if(attackerList.get(1) > defenderList.get(1)){
               attackingScore++;
           }else{
               defendingScore++;
           }

           if(attackerList.get(2) > defenderList.get(2)){
               attackingScore++;
           }else{
               defendingScore++;
           }

           System.out.println(attackingScore+" "+defendingScore);
       }
    }
}

class Fight{
    int x1, x2, x3;

    public Fight(int x1, int x2, int x3) {
        this.x1 = x1;
        this.x2 = x2;
        this.x3 = x3;
    }
}