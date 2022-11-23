import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @program: Courseware-Backend-Java-2022
 * @description: toArray()方法测试
 * @author: YxYL
 * @create: 2022-11-22 09:59
 **/

public class ToArrayDemo {
    public static void main(String[] args) {
        List<String> list=  new CopyOnWriteArrayList<>();
        list.add("Wo");
        list.add("shi");
        list.add("ni");
        list.add("ma");
        list.add("ya");
        System.out.println("list.toArray(new String[]{}) = " + list.toArray(new String[]{}));
    }
}
