package bounded_type_parameter;

public class MultiBoundedTypeParamsExample {
    //multiple bounded
    //multiple bounds pada parameter generik hanya memungkinkan satu kelas dan beberapa antarmuka
    static class DataProcessor<T extends Number & Comparable<T>> {
        private final T data;

        public DataProcessor(T data) {
            this.data = data;
        }

        public boolean isGreaterThan(T other) {
            return data.compareTo(other) > 0;
        }

        public double doubleValue() {
            return data.doubleValue();
        }
    }


    public static void main(String[] args) {
        DataProcessor<Integer> processor = new DataProcessor<>(42);
        System.out.println(processor.isGreaterThan(30)); // true
        System.out.println(processor.doubleValue());    // 42.0

    }
}
