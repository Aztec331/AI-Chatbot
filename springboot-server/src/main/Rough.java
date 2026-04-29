import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Rough {
    public static void main(String[] args) {
        List<Map <String, Object> > chats = new ArrayList<>();

        //Hashmap number 1 
        Map<String, Object> chat1 = new HashMap<>();
        chat1.put("id", 1);
        chat1.put("title", "New Chat");
        chat1.put("messages", List.of(
                createMessage("user", "Hello, how are you?"),
                createMessage("assistant", "I'm good, thank you! How can I assist you today?")
        ));
        chats.add(chat1);

        //Hashmap number 2
        Map<String, Object> chat2 = new HashMap<>();
        chat2.put("id", 2);
        chat2.put("title", "Project Discussion");
        chat2.put("messages", List.of(
                createMessage("user", "Can you help me build a chatbot UI?"),
                createMessage("assistant", "Yes, we can start with sidebar, chat area, and input box."),
                createMessage("user", "Nice, show me the folder structure too.")
        ));
        chats.add(chat2);

        //Hashmap number 3
        Map<String, Object> chat3 = new HashMap<>();
        chat3.put("id", 3);
        chat3.put("title", "Bug Fix Chat");
        chat3.put("messages", List.of(
                createMessage("user", "My messages are not rendering."),
                createMessage("assistant", "Check whether you are mapping over the correct messages array.")
        ));
        chats.add(chat3);

        //Hashmap number 4
        Map<String, Object> chat4 = new HashMap<>();
        chat4.put("id", 4);
        chat4.put("title", "Long Chat Example");
        chat4.put("messages", List.of(
                createMessage("user", "Message 1"),
                createMessage("assistant", "Message 2"),
                createMessage("user", "Message 3"),
                createMessage("assistant", "Message 4"),
                createMessage("user", "Message 5"),
                createMessage("assistant", "Message 6"),
                createMessage("user", "Message 7"),
                createMessage("assistant", "Message 8"),
                createMessage("user", "Message 9"),
                createMessage("assistant", "Message 10"),
                createMessage("user", "Message 11"),
                createMessage("assistant", "Message 12"),
                createMessage("user", "Message 13"),
                createMessage("assistant", "Message 14"),
                createMessage("user", "Message 15")
        ));
        chats.add(chat4);

        System.out.println(chats);
    }

    private static Map<String, String> createMessage(String role, String content) {
        Map<String, String> message = new HashMap<>();
        message.put("role", role);
        message.put("content", content);
        return message;
    }

    
}
