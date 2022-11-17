package InnerClass;

public class TestInnerClass {
    private String str = "这是一个私有属性";
    private int same=100;

    //该方法是测试创建内部类对象，并调用内部类方法
    public void test() {
        InnerClass inner = new InnerClass();
        inner.toPrint();
        System.out.println(inner.a);
    }


    private void test1() {
        System.out.println("内部类方法调用了外部类的私有方法");
    }

    //定义的一个内部类
    class InnerClass {
        private int a = 0;
        public int b = 1;
        int same =200;

        public void toPrint() {
            int same=300;
            System.out.println(str);
            test1();
/*            System.out.println("外部类的属性"+TestInnerClass.this.same);
            System.out.println("内部类的属性"+this.same);
            System.out.println("内部类的方法内的局部变量"+same);*/
        }
    }
}
