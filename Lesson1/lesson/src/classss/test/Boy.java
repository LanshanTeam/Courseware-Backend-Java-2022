package classss.test;

import classss.Person;

public class Boy extends Person {

    public static void main(String[] args) {
        Boy boy = new Boy();
        boy.say();
        boy.eat();
        Person person = new Person();
        person.eat();
        //报错，没有访问权限
        //person.say();

       // int number1 = Person.number;
    }
}
