package ru.sbt.lesson10;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Application {
    public static void main(String[] args) {
        List<Person> piply = new ArrayList<>();
        piply.add(new Person("Alex", 15));
        piply.add(new Person("Max", 35));
        piply.add(new Person("Jon", 51));
        piply.add(new Person("Kate", 132));
        piply.add(new Person("Mary", 2));
        piply.add(new Person("WomanName", 62));

        Map resultMap = Streams.of(piply)
                .filter(p -> p.getAge() < 20)
                .transform(p -> new Person(p.getName(), p.getAge() + 20))
                .toMap(Person::getName, p -> p);

        resultMap.forEach((key, value) -> System.out.println(key + " - " + value));
    }
}
