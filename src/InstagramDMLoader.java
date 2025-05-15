import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InstagramDMLoader {

    // Load all messages from all JSON files
    public static List<Message> loadMessages(String folderPath) {
        List<Message> allMessages = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();

        File folder = new File(folderPath);
        if (!folder.exists() || !folder.isDirectory()) {
            System.out.println("Folder does not exist or is not a directory: " + folder.getAbsolutePath());
            return allMessages;
        }

        File[] jsonFiles = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".json"));
        if (jsonFiles != null) {
            for (File file : jsonFiles) {
                try {
                    DmData data = mapper.readValue(file, DmData.class);
                    if (data.getMessages() != null) {
                        allMessages.addAll(data.getMessages());
                    }
                } catch (IOException e) {
                    System.out.println("Failed to read JSON from: " + file.getName());
                    e.printStackTrace();
                }
            }
        }

        return allMessages;
    }

    // 🔍 Load messages only from files that include the specified username
    public static List<Message> loadMessagesFromParticipant(String folderPath, String participantName) {
        List<Message> userMessages = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();

        File folder = new File(folderPath);
        if (!folder.exists() || !folder.isDirectory()) {
            System.out.println("Folder does not exist or is not a directory: " + folder.getAbsolutePath());
            return userMessages;
        }

        File[] jsonFiles = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".json"));
        if (jsonFiles != null) {
            for (File file : jsonFiles) {
                try {
                    DmData data = mapper.readValue(file, DmData.class);
                    // Check if this thread includes the participant
                    boolean containsUser = data.getParticipants().stream()
                        .anyMatch(p -> p.getName().equalsIgnoreCase(participantName));
                    if (containsUser && data.getMessages() != null) {
                        userMessages.addAll(data.getMessages());
                    }
                } catch (IOException e) {
                    System.out.println("Failed to read JSON from: " + file.getName());
                    e.printStackTrace();
                }
            }
        }

        return userMessages;
    }
}
