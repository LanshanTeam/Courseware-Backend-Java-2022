package InnerClass;

//用于测试局部内部类
public class TestInner2 {
    public static void main(String[] args) {
        int num = 1;
        //定义在方法中的局部内部类
        class Inner {
            public void toPrint() {
                //访问了外部方法的局部变量
                System.out.println(num);
            }
        }
        Inner inner =new Inner();
        inner.toPrint();

/*
        //局部匿名内部类，直接在此处实现了接口的抽象方法
        Fight fighter = new Fight() {
            @Override
            public void beAttacked() {
                System.out.println("完成一次战斗！");
            }
        };
        fighter.beAttacked();*/
    }
}
