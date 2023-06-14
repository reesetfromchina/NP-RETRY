package ThreadsANDDesignPatterns;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class HAsim {
    // Create 100 threads that will print the first consecutive 100 numbers.
    public static void main(String[] args) {

        List<NumberedThread> threads = IntStream.range(1, 101).mapToObj(NumberedThread::new).toList();

        threads.forEach(Thread::start);

        threads.forEach(t-> {
            try {
                t.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });


    }
}


class NumberedThread extends Thread{
    int number;

    public NumberedThread(int number) {
        this.number = number;
    }

    @Override
    public void run(){
        System.out.println(number);
    }
}
