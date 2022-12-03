import java.lang.reflect.Field;
import java.util.*;

/**
 * @program: Courseware-Backend-Java-2022
 * @description: Demo
 * @author: YxYL
 * @create: 2022-11-27 19:31
 **/

public class Demo {

    public static void main(String[] args) {
        Class<Student> studentClass = Student.class;
        //NoSuchFieldException
        try {
            Field id = studentClass.getDeclaredField("id");
        } catch (NoSuchFieldException e) {
            //..处理异常
            throw new RuntimeException(e);
        }

    }
}
