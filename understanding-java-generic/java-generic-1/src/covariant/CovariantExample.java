package covariant;

public class CovariantExample {

    public static class Covariant<T>{
        T data;

        public Covariant(T data) {
            this.data = data;
        }
    }

    //read only
    public static void process(Covariant<? extends Object> data){
        System.out.println(data.data);
        //        data.data = "error";
    }

    public static void main(String[] args) {
        Covariant<String> data = new Covariant<>("data");
        //Invariant<Object> data2 = data;
        process(data);
    }
}
