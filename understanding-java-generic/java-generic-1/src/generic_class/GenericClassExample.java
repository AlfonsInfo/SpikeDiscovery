package generic_class;

public class GenericClassExample {

    public static class GenericClass<E,K,N,T,V,S,U>{
        E element;
        K key;
        N number;
        T type;
        V value;
        S type2;
        U type3;
        V type4;
    }

    // example implementation Pair Class di Kotlin
    public static class Pair<T,U>{
        private T first;
        private U second;
    }
}
