package invariant;

public class InvariantExample {
    public static class Invariant<T>{
        T data;

        public Invariant(T data) {
            this.data = data;
        }
    }
    public static void main(String[] args) {
        Invariant<String> data = new Invariant<>("data");
        //Invariant<Object> data2 = data;

        //
    }
}
