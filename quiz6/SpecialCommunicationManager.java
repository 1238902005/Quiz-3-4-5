package quiz6;

public class SpecialCommunicationManager {

    private final String commonServiceUrl;
    private final String specialServiceUrl;

    public SpecialCommunicationManager(String commonServiceUrl, String specialServiceUrl) {
        this.commonServiceUrl = commonServiceUrl;
        this.specialServiceUrl = specialServiceUrl;
    }


    public void sendMessage(String message, boolean isHelpRequested) {
        String endpointUrl = isHelpRequested ? specialServiceUrl : commonServiceUrl;
        // Simulate sending message to endpointUrl
        System.out.println("Sending message to " + endpointUrl + ": " + message);
    }


    public static void main(String[] args) {

        SpecialCommunicationManager manager = new SpecialCommunicationManager("http://common-service-url", "http://special-service-url");


        String userMessage = "Can you help me?";
        boolean helpRequested = userMessage.toLowerCase().contains("help");


        manager.sendMessage(userMessage, helpRequested);
    }
}
