package com.kissan.lambdas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class FunctionalTest2 {

    public static void main(String[] args) {
        testPerson();
    }

    private static void testPerson(){
        List<Person> people = createPeople();

        // Print all Male names in upper case
        people.stream()
                .filter(person -> person.getGender() == Gender.MALE)
                .map(person -> person.getFirstName().toUpperCase())
                .forEach(System.out::println);

        // Print all Persons who are Male and print name in upper case
        // Don't mutate the state in stream which will create problems in multithreaded environment
        people.stream()
                .filter(person -> person.getGender() == Gender.MALE)
                .map(person -> new Person(person.getFirstName().toUpperCase(),
                        person.getLastName().toUpperCase(),
                        person.getGender(),
                        person.getAge()))
                .forEach(System.out::println);

        // Sum of all the Persons
        System.out.println(
                people.stream()
                    .mapToInt(Person::getAge)
                    .sum()
        );

        // Get Max Age of the person in the list
        System.out.println(
                people.stream()
                        .max(((person1, person2) ->
                                person1.getAge() > person2.getAge() ? 1 : -1))
                        .get()
        );

        // Get Min Age of the person in the list
        System.out.println(
                people.stream()
                        .min(((person1, person2) ->
                                person1.getAge() > person2.getAge() ? 1 : -1))
                        .get()
        );

        // Get a new List of all the adults (persons with age greater than 17)
        List<Person> person17List =
                people.stream()
                    .filter(person -> person.getAge() > 17)
                    .collect(Collectors.toList());
        System.out.println(person17List);

        // Get a new List of the names of all the adults (persons with age greater than 17)
        // Used this approach if any special operation needs to be performed on the new list
        List<String> person17List2 =
                people.stream()
                        .filter(person -> person.getAge() > 17)
                        .map(person -> person.getFirstName().toUpperCase())
                        .collect(
                                () -> new ArrayList<>(),
                                (list, name) -> list.add(name),
                                (list1, list2) -> list1.addAll(list2)
                        );
        System.out.println(person17List2);

        // or
        // This approach will work if no special operation is needed
        person17List2 =
                people.stream()
                        .filter(person -> person.getAge() > 17)
                        .map(person -> person.getFirstName().toUpperCase())
                        .collect(Collectors.toList());
        System.out.println(person17List2);

        // Generate Map from the list
        Map<String, Person> personMap =
                people.stream()
                        .collect(Collectors.toMap(
                                person -> person.getFirstName() +":"+person.getAge(),
                                person -> person
                        ));
        System.out.println(personMap);

        // Generate Map grouping by First Name
        Map<String, List<Person>> personMap2 =
                people.stream()
                        .collect(Collectors.groupingBy(Person::getFirstName));
        System.out.println(personMap2);
    }

    private static List<Person> createPeople(){
        return Arrays.asList(
                new Person("Sara", "Dickens", Gender.FEMALE, 20),
                new Person("Sara", "Dickens", Gender.FEMALE, 22),
                new Person("Bob", "Sammy", Gender.MALE, 20),
                new Person("Paula", "Anderson", Gender.FEMALE, 32),
                new Person("Paul", "Roddy", Gender.MALE, 32),
                new Person("Jack", "Adams", Gender.MALE, 2),
                new Person("Jill", "Lara", Gender.FEMALE, 12),
                new Person("Jack", "Harty", Gender.MALE, 72)
        );
    }


}
