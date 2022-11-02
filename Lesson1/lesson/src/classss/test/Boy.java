package classss.test;

import classss.Person;

public class Boy extends Person {

    public static void main(String[] args) {
        //Boy没有声明任何属性，但是可以实现父类方法
        Boy boy = new Boy();
        boy.say();
        boy.eat();
        Person person = new Person();
        person.eat();
        //报错，没有访问权限,say被protected修饰
        //person.say();
        //静态
         // int number1 = Person.number;
    }
}
