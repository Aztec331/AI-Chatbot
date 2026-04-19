public class Student {

    String name;

    Student(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        
        Student s = new Student("Aditya");
        Student s1 = new Student("Babar");
        System.out.println("s object: "+s.name);
        System.out.println("s1 object: "+s1.name);

    }


}