package classss;

public class Person {

     public static  int number=100;
    String name;
    int age;
    boolean sex;


    public void eat(){
        System.out.println("人会吃东西");
    }
    public  void eat(int L){
        System.out.println("吃了"+L+"碗饭");
    }

    void run(){
        System.out.println("人会跑步");
    }

    protected void say(){
        System.out.println("人会说话");
    }

}
