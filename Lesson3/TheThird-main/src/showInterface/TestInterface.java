package showInterface;

public class TestInterface {
    public static void main(String[] args) {
        Plane plane = new Plane();
        plane.ToFly();
        //plane.WhatCanIDo();
        Bird bird = new Bird();
        bird.ToFly();
    }
}

//定义了一个接口
interface Fly {
    void ToFly();//抽象方法去飞行
    //接口的默认方法

    default void WhatCanIDo() {
        System.out.println("实现这个接口，就能飞行啦！");
    }

}
//定义另一个接口
interface Power {
    void GetPower();//抽象方法获取能源
    //接口的默认方法

    default void WhatCanIDo() {
        System.out.println("实现这个接口，就能飞行啦！");
    }
}

//定义了实体类飞机并实现了飞行接口
class Plane implements Fly ,Power{
    @Override
    public void ToFly() {
        System.out.println("我是飞机，我是靠喷气飞行！");
    }
    @Override
    public void GetPower() {
        System.out.println("请给我燃油！");
    }

    //实现了两个接口，并且两个接口都拥有相同的默认方法，所以该子类必须重写这个默认方法（不然会报错）
    @Override
    public void WhatCanIDo() {
        System.out.println("我已经实现了能源和飞行接口");
    }
}


//定义了实体类飞机并实现了飞行接口
class Bird implements Fly {
    @Override
    public void ToFly() {
        System.out.println("我是小鸟，我是靠振动翅膀飞行！");
    }
}


