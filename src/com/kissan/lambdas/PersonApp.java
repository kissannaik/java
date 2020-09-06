package com.kissan.lambdas;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class PersonApp {

    public static void main(String[] args) {

        List<Person> personList = Arrays.asList(
                new Person("Charles", "Dickens", Gender.MALE, 56),
                new Person("Lewis", "Carolle", Gender.MALE, 46),
                new Person("Thomas", "Charley", Gender.MALE, 27),
                new Person("Charlotte", "Bronte",Gender.FEMALE,  30),
                new Person("Mattew", "Arnold",Gender.MALE,  39)
        );
        System.out.println("personList before = " + personList);

        System.out.println("     ");
        System.out.println(" -*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-* ");
        System.out.println("     ");

        // 1. Sort list by last name
        personList.sort(((p1, p2) -> p1.getLastName().compareToIgnoreCase(p2.getLastName())));
        System.out.println("personList after sorting = " + personList);
        System.out.println("     ");
        System.out.println(" -*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-* ");
        System.out.println("     ");

        // 2. Create a method that prints all elements in the list
        performConditionally(personList, person -> true, person -> System.out.println(person));
        //printAll(personList, person -> true);
        //personList.stream().forEach(System.out::println);

        System.out.println("personList with printing = " + personList);
        System.out.println("     ");
        System.out.println(" -*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-* ");
        System.out.println("     ");

        // 3. Create a method that prints all people that have the last name beginning with C
        performConditionally(personList, person -> person.getLastName().startsWith("C"),
                person -> System.out.println(person.getLastName()));
        //printAll(personList, person -> person.getLastName().startsWith("C"));
        //personList.stream().filter(person -> person.getLastName().startsWith("C")).forEach(System.out::println);

    }

    private static void performConditionally(List<Person> personList, Predicate<Person> condition,
                                          Consumer<Person> consumer) {
        for (Person person:personList ) {
            if (condition.test(person))
                consumer.accept(person);
        }
    }

    private static void printAll(List<Person> personList, Predicate<Person> condition) {
        for (Person person:personList ) {
            if (condition.test(person))
                System.out.println(person);
        }
    }
}
