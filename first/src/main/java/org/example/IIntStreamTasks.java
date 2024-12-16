package org.example;

import java.util.List;

public interface IIntStreamTasks {
    int sumInts(List<Integer> list);
    double findAverage(List<Integer> list);
    double findStandardDeviation(List<Integer> list);
    List<Integer> multiplyBy2(List<Integer> list);
    List<Integer> filterDivisibleBy3(List<Integer> list);
}
