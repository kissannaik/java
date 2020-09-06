package com.kissan.lambdas;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.function.Predicate;

public class FunctionalTest {

    public static void main(String[] args) {
        testInt();
     }

    private static void testInt(){
        List<Integer> numbers = createInt();

        testFunction1(numbers);

        testFunction2(numbers);
    }
    private static List<Integer> createInt(){
        return Arrays.asList(1, 2, 4, 3, 5, 6, 7, 8);
    }

    private static void testFunction1(List<Integer> numbers){
        // Find numbers greater than 3, filter even numbers, find first and multiply by 2
        System.out.println("Output = " +
                numbers.stream()
                        .filter(FunctionUtil.isGreaterThan.apply(3))
                        .filter(FunctionalTest::isEven)
                        .findFirst()
                        .map(FunctionUtil.mulByPivot.apply(2))
                        .get()
        );
    }

    private static void testFunction2(List<Integer> numbers) {
        int sum = 0;

        // Traditional Way
        long start = System.currentTimeMillis();
        for (int num : numbers){
            sum += doubleInt(num);
        }
        System.out.println("Traditional Way: sum = " + sum + " calculated in "+(System.currentTimeMillis() - start));

        // Sequential Stream
        start = System.currentTimeMillis();
        sum = numbers.stream()
                    .mapToInt(FunctionalTest::doubleInt)
                    .sum();
        System.out.println("Sequential Stream: sum = " + sum + " calculated in "+(System.currentTimeMillis() - start));

        // Sequential Stream
        start = System.currentTimeMillis();
        sum = numbers.parallelStream()
                .mapToInt(FunctionalTest::doubleInt)
                .sum();
        System.out.println("Parallel Stream: sum = " + sum + " calculated in "+(System.currentTimeMillis() - start));
    }

    private static boolean isEven(int number){
        return number % 2 == 0;
    }

    private static int doubleInt(int num) {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return num * 2;
    }
}
