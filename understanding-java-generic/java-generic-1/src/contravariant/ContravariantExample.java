package contravariant;

public class ContravariantExample {

    public static class Contravariant<T>{
        T data;

        public Contravariant(T data) {
            this.data = data;
        }
    }

    //becareful when read, can write
    public static void process(Contravariant<? super String> data){
        // tidak pasti data.data adalah sebuah string
        //String data1 = data.data;
        System.out.println(data.data);
        data.data = "error";
    }

    public static void main(String[] args) {
        Contravariant<String> data = new Contravariant<>("data");
        //Invariant<Object> data2 = data;
        process(data);
    }
}
