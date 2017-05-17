package classobject;

//静态域
//类只有一个静态域，所有对象共享该静态域
public class StaticField {

    public static void main(String[] args) {
        Employee e1 = new Employee();
        Employee e2 = new Employee();
        e1.setId();
        e2.setId();
        System.out.println(e1.getId()); //1
        System.out.println(e2.getId()); //2
    }

}

class Employee {
    private int id;

    private static int nextId = 1;

    public int getId() {
        return id;
    }

    public void setId() {
        this.id = nextId++;
    }

    public static int getNextId() {
        return nextId;
    }

    public static void setNextId(int nextId) {
        Employee.nextId = nextId;
    }
}