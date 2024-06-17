package quiz5;

import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

public class DummyCommunicationManager<JSONObject> extends CommunicationManager {
    private final Map<String, String> responses;

    public DummyCommunicationManager() {
        super("");
        responses = new HashMap<>();
        responses.put("Hello!", "Hi there! How can I assist you today?");
        responses.put("Can you tell me the weather forecast for today?", "Sure! Where are you located?");
        responses.put("I'm in New York.", "The weather in New York is sunny with a high of 75°F.");
    }

    @Override
    public String send(Object message) {
        String content = message.getString("message_content");
        return responses.getOrDefault(content, "Sorry, I didn't understand that.");
    }

    @Override
    public String receive() {
        return "This method is not implemented in DummyCommunicationManager.";
    }
}

