package org.example;

import java.util.List;
import java.util.random.RandomGenerator;

class App {
    public static void main(String[] args) {
        RandomGenerator rng = RandomGenerator.getDefault();
        List<Integer> intList = rng.ints(15, 0, 100).boxed().toList();
        System.out.print("Integers list: ");
        printList(intList);
        System.out.println("Tasks using Stream API:");
        tasks(intList, new StreamAPITasks());
        System.out.println("Tasks using Parallel Stream");
        tasks(intList, new ParallelStreamAPITasks());
    }

    static void tasks(List<Integer> list, IIntStreamTasks operator) {
        int sum = operator.sumInts(list);
        System.out.printf("Ints sum: %d\n", sum);

        double avg = operator.findAverage(list);
        System.out.printf("Average: %.02f\n", avg);

        double dev = operator.findStandardDeviation(list);
        System.out.printf("Standard deviation: %.02f\n", dev);

        List<Integer> multipliedBy2List = operator.multiplyBy2(list);
        System.out.print("Multiplied by 2: ");
        printList(multipliedBy2List);

        List<Integer> filterDivisibleBy3List = operator.filterDivisibleBy3(list);
        System.out.print("Divisible by 3: ");
        printList(filterDivisibleBy3List);
    }

    static void printList(List<Integer> list) {
        list.forEach(v -> System.out.printf("%d ", v));
        System.out.println();
    }

}
