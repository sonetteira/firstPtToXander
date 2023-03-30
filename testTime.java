import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

class testTime {
    private static int numTests = 1000;
    private static double testMin = 0;
    private static double testMax = 15;
    

    static double[][] randomTests() {
        double[][] times = new double[numTests][2];
        double start, end;
        for(int i = 0; i < numTests; i++) {
            start = ThreadLocalRandom.current().nextDouble(testMin, testMax);
            end = ThreadLocalRandom.current().nextDouble(start, testMax);
            times[i][0] = start;
            times[i][1] = end;
        }
        return times;
    }

    static int noSortTimeTest(double[][] times, double searchTime) {
        int count = 0;
        for(int i = 0; i < times.length; i++) {
            if(times[i][0] <= searchTime && times[i][1] >= searchTime)
                count++;
        }
        return count;
    }

    static int sortTimeTest(double[][] times, double searchTime) {
        int count = 0;
        Arrays.sort(times, new Comparator<double[]>() {
            //sort 2d array by first double
            @Override
            public int compare(double[] a, double[] b) {
                return Double.compare(a[0], b[0]);
            }
        });
        for(int i = 0; i < times.length; i++) {
            if(times[i][0] <= searchTime) {
                if(times[i][1] >= searchTime)
                    count++;
            } else {
                break;
            }
        }
        return count;
    }

    public static void main (String[] args) {
        double[][] times = randomTests();

        // for(int i = 0; i < times.length; i++) {
        //     System.out.println(times[i][0] + " " + times[i][1]);
        // }

        int count1, count2;
        long execTimeStart, execTimeEnd, execTime1, execTime2;
        execTimeStart = System.nanoTime();
        count1 = noSortTimeTest(times, 5);
        execTimeEnd = System.nanoTime();
        execTime1 = execTimeEnd - execTimeStart;
        // System.out.println(execTime1);

        execTimeStart = System.nanoTime();
        count2 = sortTimeTest(times, 5);
        execTimeEnd = System.nanoTime();
        execTime2 = execTimeEnd - execTimeStart;
        // System.out.println(execTime2);
        

        if(count1 == count2) {
            if(execTime1 < execTime2) {
                System.out.println("Xander is right by a factor of " + (execTime2 / execTime1));
            } else {
                System.out.println("Leanne is right by a factor of " + (execTime1 / execTime2));
            }
        } else {
            System.out.println("Sorts returned different counts");
        }

    }
}