package quiz5;

import java.time.Instant;
import org.json.JSONObject;

public class UserInteractionManager {
    private String userId;
    private String sessionId;
    private CommunicationManager commManager;

    public UserInteractionManager(String userId, String sessionId, CommunicationManager commManager) {
        this.userId = userId;
        this.sessionId = sessionId;
        this.commManager = commManager;
    }

    private JSONObject createMessage(String messageContent) {
        JSONObject message = new JSONObject();
        message.put("user_id", userId);
        message.put("session_id", sessionId);
        message.put("timestamp", Instant.now().toString());
        message.put("message_type", "text");
        message.put("message_content", messageContent);
        message.put("metadata", new JSONObject());
        return message;
    }

    public String sendMessage(String messageContent) {
        JSONObject message = createMessage(messageContent);
        return commManager.send(message);
    }

    public String receiveMessage() {
        return commManager.receive();
    }

    public static void main(String[] args) {
        CommunicationManager commManager = new DummyCommunicationManager();
        UserInteractionManager userInteractionManager = new UserInteractionManager("user123", "session456", commManager);

        System.out.println("User: Hello!");
        String response = userInteractionManager.sendMessage("Hello!");
        System.out.println("Bot: " + response);

        System.out.println("User: Can you tell me the weather forecast for today?");
        response = userInteractionManager.sendMessage("Can you tell me the weather forecast for today?");
        System.out.println("Bot: " + response);
    }
}

