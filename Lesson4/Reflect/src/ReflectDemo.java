import entity.Student;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @program: Courseware-Backend-Java-2022
 * @description: 反射
 * @author: YxYL
 * @create: 2022-11-22 20:53
 **/

public class ReflectDemo {
    public static void main(String[] args) throws ClassNotFoundException,
            NoSuchMethodException,
            InvocationTargetException,
            InstantiationException,
            IllegalAccessException,
            NoSuchFieldException {
        //方法1
        Class class1 = Class.forName("entity.Student");
        //方法2
        Student student = new Student();
        Class class2 = student.getClass();
        //方法3
        Class class3 = Student.class;

        Constructor constructor = class1.getDeclaredConstructor(String.class, int.class);
        System.out.println(constructor.getName() +//提取构造器名称
                "===>" +
                constructor.getParameterCount());//提取构造器参数个数
        //获取对象
        Object o = constructor.newInstance("yxyl", 19);


        Field field = class1.getDeclaredField("name");
        field.setAccessible(true);

        Object value = field.get(o);
        System.out.println("value = " + value);

        field.set(o, "杨晨");
        Object overwriteValue = field.get(o);
        System.out.println("overwriteValue = " + overwriteValue);


        Method method = class1.getDeclaredMethod("eat", String.class);
        method.invoke(o, "学校的逆天饭菜");
    }
}
