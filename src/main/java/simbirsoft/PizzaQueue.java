package simbirsoft;

import java.util.LinkedList;
import java.util.Queue;

public class PizzaQueue {
    private final Queue<Pizza> queueOfPreparation = new LinkedList<Pizza>();
    private final Queue<Pizza> queueOfIssue = new LinkedList<Pizza>();

    public void add(Pizza pizza) {
        final int sleepTime = 10000;
        queueOfPreparation.add(pizza);
        Runnable myRunnable = () -> {
            try {
                Thread.sleep(sleepTime);
            } catch (Exception e) {
                System.out.println(e.toString());
            }
            ;
            queueOfIssue.add(queueOfPreparation.poll());
        };
        Thread th = new Thread(myRunnable);
        th.start();
    }

    public void printAll() {
        System.out.println("In queue of preparation: " + queueOfPreparation.size());
        System.out.println("In queue of issue: " + queueOfIssue.size());
    }
}
