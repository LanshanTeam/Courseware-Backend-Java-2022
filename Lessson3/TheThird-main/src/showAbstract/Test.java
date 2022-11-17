package showAbstract;

public class Test {
    public static void main(String[] args) {
        //老虎
        Animal tiger = new Tiger();
        tiger.eat();
        //羊
        Animal sheep = new Sheep();
        sheep.eat();
        //上面是使用Animal来接收了子类对象，是使用了向上转型（面向对象的多态）
    }
}

//定义的一个抽象类
abstract class Animal {
    String name;

    public Animal(String name) {
        this.name = name;
    }

    abstract void eat();
}

//定义的实现上方抽象类的一个子类
class Tiger extends Animal {
    public Tiger() {
        super("老虎");
    }

    @Override
    void eat() {
        System.out.println("我是" + super.name + "我要吃肉！");
    }
}

//定义的实现上方抽象类的一个子类
class Sheep extends Animal {
    public Sheep() {
        super("羊");
    }

    @Override
    void eat() {
        System.out.println("我是" + super.name + "我要吃草！");
    }
}
