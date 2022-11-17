package InnerClass;

public class TestInner {
    public static void main(String[] args) {
        TestInnerClass testInnerClass = new TestInnerClass();
        //通过外部类的test方法间接使用内部类
        testInnerClass.test();


        //直接创建内部类对象
        TestInnerClass.InnerClass inner = new TestInnerClass().new InnerClass();
        inner.toPrint();
    }
}

