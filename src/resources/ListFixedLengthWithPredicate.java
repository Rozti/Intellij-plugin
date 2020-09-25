package resources;

import java.util.function.Predicate;

public class ListFixedLengthWithPredicate<T> extends ListFixedLength {
    public Predicate<String> predicate;
    public String name;

    public ListFixedLengthWithPredicate(int length, Predicate<String> p, String s) {
        super(length);
        predicate = p;
        name = s;
    }
}





