/**
 * @program: Courseware-Backend-Java-2022
 * @description:
 * @author: YxYL
 * @create: 2022-11-27 19:38
 **/

public class Student {
    private String name ;
    private int id ;

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

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
