import entity.Result;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: Courseware-Backend-Java-2022
 * @description:
 * @author: YxYL
 * @create: 2022-11-22 09:23
 **/

public class Demo1 {
    public static void main(String[] args) {
        
        List list = new ArrayList();
        list.add("abc");
        list.add(100);
        for (int i = 0; i < list.size(); i++) {
            System.out.println("执行到第 " + (i + 1) + " 次循环。");
            String str = (String) list.get(i);
            System.out.println(str);
        }


        Result<String> r1 = new Result<>();
        r1.setData("111");
        
        Result<Integer> r2 = new Result<>();
        r2.setData(111);

        Result<Boolean> r3 = new Result<>();
        r3.setData(true);
    }
}
