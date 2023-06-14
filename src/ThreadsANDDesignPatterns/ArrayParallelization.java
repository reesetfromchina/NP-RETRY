package ThreadsANDDesignPatterns;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ArrayParallelization {
    public static int [] ARRAY = new int[10000000];
    public static Random RANDOM = new Random();
    public static int ARRAY_SIZE = 10000000;
    public static int THREAD_COUNT = 100;


    public static void main(String[] args) {
        for(int i =0; i < ARRAY_SIZE; i++){
            ARRAY[i] = RANDOM.nextInt(101); // [0,100]

        }
        List<Searcher> searchers = new ArrayList<>();
        for(int i = 0; i < THREAD_COUNT; i++){
            int start = i * ARRAY_SIZE / THREAD_COUNT;
            int end = start + ARRAY_SIZE / THREAD_COUNT;
            searchers.add(new Searcher(start, end));
        }

        searchers.forEach(Thread::start);
        searchers.forEach(t-> {
            try {
                t.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        searchers.forEach(searcher -> System.out.println(searcher));
    }
}



class Searcher extends Thread{
    int start;
    int end;

    int min, max;

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public Searcher(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public void run() {
        min = max = ArrayParallelization.ARRAY[start];
        for(int i = start+1; i<end; i++){
            if(ArrayParallelization.ARRAY[i] < min){
                min = ArrayParallelization.ARRAY[i];
            }
            if(ArrayParallelization.ARRAY[i]>max){
                max = ArrayParallelization.ARRAY[i];
            }

        }
    }

    @Override
    public String toString() {
        return "Searcher{" +
                "start=" + start +
                ", end=" + end +
                ", min=" + min +
                ", max=" + max +
                '}';
    }
}
