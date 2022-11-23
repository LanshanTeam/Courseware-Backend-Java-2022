import java.util.ArrayList;
import java.util.List;

/**
 * @program: Courseware-Backend-Java-2022
 * @description: 类的静态属性不能使用泛型类型测试
 * @author: YxYL
 * @create: 2022-11-22 19:03
 **/

public class GenericsExample<T> {
    private static T member;

    public static void main(String[] args) {
        List<int> list1 = new ArrayList<>();    // 不允许
        List<Integer> list2 = new ArrayList<>();
    }
}
