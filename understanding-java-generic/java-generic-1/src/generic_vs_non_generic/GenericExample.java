package generic_vs_non_generic;

public class GenericExample {
    public static class Generic<T>{
        T data;
    }

    public static void main(String[] args) {
        Generic<String> generic = new Generic<>();
        generic.data = "test";
        System.out.println(generic.data);
    }
}
