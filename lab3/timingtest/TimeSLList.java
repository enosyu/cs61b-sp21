package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeSLList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeGetLast();
    }

    public static void timeGetLast() {
        // TODO: YOUR CODE HERE
        AList<Integer> Ns = new AList<>();
        AList<Double> times = new AList<>();
        AList<Integer> opCounts = new AList<>();
        for(int i = 1000; i < 128000; i *= 2){
            Ns.addLast(i);
        }

        for(int j = 0; j < Ns.size() ; j += 1){
            int n = Ns.get(j);
            int op = 0;
            SLList<String> a = new SLList<>();

            for(int k = 0; k < n; k += 1){
                a.addLast("item");
            }
            Stopwatch sw = new Stopwatch();
            for(int m = 0; m < 10000; m += 1){
                a.getLast();
                op += 1;
            }
            double time = sw.elapsedTime();
            opCounts.addLast(op);
            times.addLast(time);
        }

        printTimingTable(Ns,times,opCounts);
    }

}
