import entity.Student;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @program: Courseware-Backend-Java-2022
 * @description:
 * @author: YxYL
 * @create: 2022-11-27 20:53
 **/

public class Test {
    public static void main(String[] args) throws Exception {
        
        Student student1= new Student("yxyl", 2021214391);
        Class<? extends Student> clazz = student1.getClass();
        Method method = clazz.getDeclaredMethod("eat", String.class);
        
        method.invoke(student1,"学校的逆天饭菜");

    }
}
