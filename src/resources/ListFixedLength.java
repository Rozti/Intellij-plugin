package resources;

import java.util.*;

public class ListFixedLength<T> extends ArrayList implements List{
    private int fixedLength;
    public ListFixedLength(int length){
        super();
        fixedLength = length;
    }
    @Override
    public boolean add(Object o) {
        if (this.size()>= fixedLength) remove(0);

        return super.add(o);
    }
}

