package generic_vs_non_generic;

public class NonGenericExample {
    public static class NonGeneric{
        Object data;
    }

    public static void main(String[] args) {
        NonGeneric nonGeneric = new NonGeneric();
        nonGeneric.data = "test";
        // it should error
        System.out.println((int) nonGeneric.data + 3);
    }
}
