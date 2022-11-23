package countNum;

public class Count {
    //静态的全局int类型用于统计该类被实例化的次数
    public static int count = 0;

    public static int a=10;
    int b=20;
    public Count() {
        count++;
        System.out.println("第" + count + "次创建对象！！！");
    }
}
