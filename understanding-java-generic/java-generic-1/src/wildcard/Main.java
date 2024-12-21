package wildcard;

public class Main {

    public static class Data<T>{
        T data;

    }
    public static void main(String[] args) {
        printLength(new Data<>());
    }

    public static void printLength(Data<?> data){
        System.out.println(data);
    }
}
