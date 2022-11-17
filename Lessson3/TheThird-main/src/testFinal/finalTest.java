package testFinal;

public class finalTest {
    public static void main(String[] args) {
        final Student stu =new Student("赵丽颖");
        //stu=new Student("赵丽颖");//错误写法，地址值不可改变
        stu.setName("高圆圆");//正确写法
        System.out.println(stu.getName());
    }
}
