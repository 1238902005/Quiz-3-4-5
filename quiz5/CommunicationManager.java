package quiz5;

import org.json.JSONObject;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public abstract class CommunicationManager {
    private final String baseUrl;

    public CommunicationManager(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String send(JSONObject message) {
        try {
            URL url = new URL(baseUrl + "/send");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json; utf-8");
            conn.setDoOutput(true);

            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = message.toString().getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            try (Scanner scanner = new Scanner(conn.getInputStream(), StandardCharsets.UTF_8)) {
                scanner.useDelimiter("\\A");
                return scanner.hasNext() ? scanner.next() : "";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Error";
        }
    }

    public abstract String send(Object message);

    public String receive() {
        try {
            URL url = new URL(baseUrl + "/receive");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            try (Scanner scanner = new Scanner(conn.getInputStream(), StandardCharsets.UTF_8)) {
                scanner.useDelimiter("\\A");
                return scanner.hasNext() ? scanner.next() : "";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Error";
        }
    }
}
