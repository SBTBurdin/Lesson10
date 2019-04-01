package ru.sbt.lesson10;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

public class Streams<T> {

    private final List<T> list;

    private Streams(List<? extends T> list) {
        this.list = new ArrayList<>(list);
    }

    // Cтатический метод, который принимает коллекцию и создает новый объект Streams
    public static <T> Streams<T> of(List<T> collection) {
        return new Streams<T>(collection);
    }

    // оставляет в коллекции только те элементы, которые удовлетворяют условию в лямбде.
    public Streams<T> filter(Predicate<? super T> filter) {
        for (Iterator<T> i = list.iterator(); i.hasNext(); ) {
            T item = i.next();
            if (!filter.test(item)) {
                i.remove();
            }
        }
//        list.removeIf(item -> !filter.test(item));
        return this;
    }

    // преобразует элемент в другой.
    public Streams<T> transform(Function<? super T, ? extends T> funct) {
        for (ListIterator<T> i = list.listIterator(); i.hasNext(); ) {
            T editItem = funct.apply(i.next());
            i.set(editItem);
        }
        return this;
    }

    // Принимает 2 лямбды для создания мапы, в одной указывается,
    // что использовать в качестве ключа, в другой, что в качестве значения.
    public <K, V> Map<K, V> toMap(Function<? super T, ? extends K> funcKey,
                                  Function<? super T, ? extends V> funkValue) {
        Map<K, V> map = new HashMap();
        for (T item : list) {
            K key = funcKey.apply(item);
            V value = funkValue.apply(item);
            map.put(key, value);
        }
        return map;
    }
}
