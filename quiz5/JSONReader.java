package quiz5;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JSONReader {
    public static <JSONArray, JSONObject> void main(String[] args) {
        String filePath = "src/main/resources/chat_conversation.json";

        try (InputStream is = Files.newInputStream(Paths.get(filePath))) {
            byte[] bytes = is.readAllBytes();
            String content = new String(bytes);

            JSONArray jsonArray = new JSONArray(content);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject message = jsonArray.getJSONObject(i);
                System.out.println("User ID: " + message.getString("user_id"));
                System.out.println("Session ID: " + message.getString("session_id"));
                System.out.println("Timestamp: " + message.getString("timestamp"));
                System.out.println("Message Type: " + message.getString("message_type"));
                System.out.println("Message Content: " + message.getString("message_content"));
                System.out.println("Metadata: " + message.getJSONObject("metadata").toString());
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
