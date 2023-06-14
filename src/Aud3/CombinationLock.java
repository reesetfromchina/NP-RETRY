package Aud3;

public class CombinationLock {
    private static final int DEFAULT_COMBINATION = 100;
    private int combination;
    private boolean isOpen;


    public CombinationLock(int combination) {
        if (isCombinationValid(combination)) this.combination = combination;
        else this.combination = DEFAULT_COMBINATION;


        this.isOpen = false;
    }

    private boolean isCombinationValid(int combination) {
        return combination <= 999 && combination >= 100;
    }

    public boolean open(int combination) {
        this.isOpen = (this.combination == combination);
        return this.isOpen;
    }


    public boolean changeCombination(int old_combination, int new_combination) {
        if(open(old_combination) && isCombinationValid(new_combination)){
            this.combination = new_combination;
            return true;
        }
        return false;
    }

    public void lock(){
        this.isOpen = false;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public static void main(String[] args) {
        CombinationLock validLock = new CombinationLock(123);
        System.out.println(validLock.isOpen());

        validLock.open(123);
        CombinationLock invalidLock = new CombinationLock(12334);
    }
}

