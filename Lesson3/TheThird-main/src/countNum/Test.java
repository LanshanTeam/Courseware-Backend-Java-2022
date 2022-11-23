package countNum;

public class Test {
    public static void main(String[] args) {
        //对象计数器
        Count count1 = new Count();
        Count count2 = new Count();

        //System.out.println(Count.a);//直接用类名进行访问
        //System.out.println(new Count().b);//b没有static修饰，只能通过对象访问
        //System.out.println(new Count().a);//虽然通过实体对象也可以访问到该类的静态变量，但是不推荐使用这种方式
        //System.out.println(Count.a);//直接用类名进行访问

    }
}
