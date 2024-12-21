package bounded_type_parameter;

import java.util.ArrayList;
import java.util.List;

public class BoundedTypeParamsExample {


    public static void main(String[] args) {
        List<String> data = new ArrayList<>();
        data.add("data");
        //tidak bisa, karena sudah dibatasin sebagai string
//        data.add(1);
        System.out.println(data);
    }
}
