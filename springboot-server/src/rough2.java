import java.util.HashMap;
import java.util.Map;

public class rough2 {

    public static void main(String[] args) {
        Map<String, String> studentMap = new HashMap<>();

        studentMap.put("id", "101");
        studentMap.put("name", "Alex");
        studentMap.put("age", "25");
        studentMap.put("city", "New York");
        studentMap.put("country", "USA");
        studentMap.put("job", "Engineer");
        studentMap.put("department", "IT");
        studentMap.put("status", "Active");
        studentMap.put("level", "Beginner");
        studentMap.put("type", "Full-Time");

        System.out.println(studentMap);

    }
}
