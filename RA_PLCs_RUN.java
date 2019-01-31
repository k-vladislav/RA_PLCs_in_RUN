package RA_PLCs_RUN;

public class RA_PLCs_RUN {

    public static void main(String[] args) {
//        Line line = new Line();
//        long startTime = System.currentTimeMillis();
//        line.emplChangesPLCStateNEW();
//        System.out.println("Executed in "+(System.currentTimeMillis()-startTime));
//        line.countRUNQty();
//        System.out.println("In Run mode after process: " + line.inRUNQty);   
        Line.calcByOddOrEven(1100);
    }
}

class Line {

    private int PLCsQty = 1100;
    private int EMPLOYEES = PLCsQty;
    public int inRUNQty;
    public PLC[] PLCs = new PLC[PLCsQty];

    public Line() {
        for (int i = 0; i < PLCs.length; i++) {
            PLCs[i] = new PLC(i);
        }
    }

    /**
     * PLC is initiated with state=RUN
     * Starting with empl=2, change the state of each ..ths PLC
     */
    public void emplChangesPLCStateNEW() {
        for (int i = 2; i <= EMPLOYEES; i++) {
            for (int j = i - 1; j < PLCs.length; j += i) {
                if ((j + 1) % i == 0) {
                    // System.out.println("Employee(" + i + ") changed PLC(" + (j+1) + ") from " + PLCs[j] + " to " + PLCs[j].toStringOpposite());
                    PLCs[j].switchState();
                }
            }
        }
    }

    /**
     * A number has odd number of divisors if it's a perfect square. Excluding
     * 1, we get even number of switches
     *
     * @param N
     */
    public static void calcByOddOrEven(int N) {
        int countEvenNumberOfSwitches = 0;
        for (int n = 1; n <= N; n++) {
            int sqrt = (int) Math.sqrt(n);
            if (sqrt * sqrt == n) {
                countEvenNumberOfSwitches++;
            }
        }
        System.out.println("Result " + countEvenNumberOfSwitches);
    }

    public void countRUNQty() {
        inRUNQty = 0;
        for (int i = 0; i < PLCs.length; i++) {
            if (PLCs[i].state == PLC.Modes.RUN) {
                System.out.println("PLC(" + (i + 1) + ") is in RUN mode. State switched " + PLCs[i].numberOfSwitches + " times");
                inRUNQty++;
            }
        }
    }

}

class PLC {

    public enum Modes {
        PROGRAM, RUN;
    };
    public Modes state;
    public int numberOfSwitches;

    public PLC(int i) {
        numberOfSwitches = 0;
        state = Modes.RUN;
    }

    public void switchState() {
        if (state == Modes.RUN) {
            state = Modes.PROGRAM;
        } else {
            state = Modes.RUN;
        }
        numberOfSwitches++;
    }

    @Override
    public String toString() {
        return state == Modes.RUN ? "RUN" : "PROGRAM";
    }

    public String toStringOpposite() {
        return state == Modes.RUN ? "PROGRAM" : "RUN";
    }

}
