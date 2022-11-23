package entity;

/**
 * @program: Courseware-Backend-Java-2022
 * @description: 学生类
 * @author: YxYL
 * @create: 2022-11-22 20:52
 **/

public class Student {
    private String name;
    private int id;

    public Student(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public Student() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public void eat(String food){
        System.out.println(this.name+"吃了"+food);
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
