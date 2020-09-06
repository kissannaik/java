package com.kissan.lambdas;

import java.util.function.Function;
import java.util.function.Predicate;

public class FunctionUtil {
    // Find the numbers greater than given pivot
    static Function<Integer, Predicate<Integer>> isGreaterThan = (pivot -> number -> number > pivot);

    // Multiply the numbers by given pivot
    static Function<Integer, Function<Integer, Integer>> mulByPivot = (pivot -> number -> number * pivot);

}
