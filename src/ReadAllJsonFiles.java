import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ReadAllJsonFiles {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter username: ");
        String usernameInput = scanner.nextLine().trim().toLowerCase();
        scanner.close();

        File folder = new File("Message JSON Files");

        if (!folder.exists() || !folder.isDirectory()) {
            System.out.println("Folder not found: " + folder.getAbsolutePath());
            return;
        }

        File[] jsonFiles = folder.listFiles((dir, name) ->
            name.toLowerCase().contains(usernameInput) && name.toLowerCase().endsWith(".json")
        );

        if (jsonFiles == null || jsonFiles.length == 0) {
            System.out.println("No matching file found for username: " + usernameInput);
            return;
        }

        File matchingFile = jsonFiles[0]; // First match
        System.out.println("Found matching file: " + matchingFile.getName());

        try {
            ObjectMapper mapper = new ObjectMapper();
            DmData data = mapper.readValue(matchingFile, DmData.class);
            List<Message> messages = data.getMessages();

            System.out.println("Number of messages: " + messages.size());
            System.out.println("First message: " + messages.get(0)); // or analyze more deeply

        } catch (IOException e) {
            System.out.println("Failed to parse JSON file.");
            e.printStackTrace();
        }
    }
}
