import java.util.ArrayList;
import java.util.List;

/**
 * @program: Courseware-Backend-Java-2022
 * @description: 常见异常类
 * @author: YxYL
 * @create: 2022-11-22 19:20
 **/

public class CommonException {
    public static void main(String[] args) {
        //NullPointerException
        Integer integer = null;
        System.out.println("integer.toString() = " + integer.toString());
        
        //ClassCastException
        Object x = new Integer(0);
        System.out.println((String)x);

        //IndexOutOfBoundsException
        List list = new ArrayList<>();
        System.out.println(list.get(0));
        
    }
}
