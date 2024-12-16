package org.example;

import java.util.List;

public class StreamAPITasks implements IIntStreamTasks {
    public StreamAPITasks() {}

    @Override
    public int sumInts(List<Integer> list) {
        return list.stream().mapToInt(Integer::intValue).reduce(0, (subtotal, el) -> subtotal + el);
    }
    @Override
    public double findAverage(List<Integer> list) {
        return list.stream().mapToInt(Integer::intValue).average().orElse(0.0);
    }
    @Override
    public double findStandardDeviation(List<Integer> list) {
        double mean = this.findAverage(list);
        double variance = list.stream()
                .mapToDouble(num -> Math.pow(num - mean, 2))
                .average()
                .orElse(0.0);
        return Math.sqrt(variance);
    }

    @Override
    public List<Integer> multiplyBy2(List<Integer> list) {
        return list.stream().map(v -> v * 2).toList();
    }

    @Override
    public List<Integer> filterDivisibleBy3(List<Integer> list) {
        return list.stream().filter(v -> v % 3 == 0).toList();
    }
}
