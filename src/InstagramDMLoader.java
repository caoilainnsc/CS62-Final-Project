import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InstagramDMLoader 
{

    /**
     * Loads all messages in the JSON files
     * @param folderPath A path to a folder with the JSON message files
     * @return A list of the messages in the files
     */
    public static List<Message> loadMessages(String folderPath) 
    {
        // Creates a list to hold the messages
        List<Message> allMessages = new ArrayList<>();
        // Creates a mapper to convert the JSON object to a Java object
        ObjectMapper mapper = new ObjectMapper();
        
        // Creates a folder from the folder path
        File folder = new File(folderPath);
        // If it is not in the directory or doesn't exist
        if (!folder.exists() || !folder.isDirectory()) 
        {
            // Print that it isn't a director or doesn't exist
            System.out.println("Folder does not exist or is not a directory: " + folder.getAbsolutePath());
            // Return what's currently in messages list
            return allMessages;
        }

        // Create an array of files that holds the JSON files
        File[] jsonFiles = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".json"));
        // If the files folder is not empty
        if (jsonFiles != null) 
        {
            // For the each file in the folder
            for (File file : jsonFiles) 
            {
                // Try to get the data from the file
                try 
                {
                    // Get the data from the value mapped from JSON to Java object
                    DmData data = mapper.readValue(file, DmData.class);
                    // If the messages are not empty
                    if (data.getMessages() != null) 
                    {
                        // Add all the messages in the file to the all message list
                        allMessages.addAll(data.getMessages());
                    }
                } 
                // If there is an IO exception and can't read the file
                catch (IOException e) 
                {
                    // Return that it couldn't read the file and which one
                    System.out.println("Failed to read JSON from: " + file.getName());
                    // And print what happened in stack
                    e.printStackTrace();
                }
            }
        }
        
        // REturn list with all messages
        return allMessages;
    }

    /**
     * Gets the messages from a specific participant
     * @param folderPath the folder with the JSON files of messages
     * @param participantName the participant that you want to get messages from
     * @return the list of messages from that participant
     */
    public static List<Message> loadMessagesFromParticipant(String folderPath, String participantName) 
    {
        // Creates a list to hold the user's messages
        List<Message> userMessages = new ArrayList<>();
        // Creates a mapper to convert JSON object to Java object
        ObjectMapper mapper = new ObjectMapper();

        // Create a folder to hold the files in the path
        File folder = new File(folderPath);
        // If the folder doesn't exist or is not in directory
        if (!folder.exists() || !folder.isDirectory()) 
        {
            // Return message to user and the path
            System.out.println("Folder does not exist or is not a directory: " + folder.getAbsolutePath());
            // Return what is currently in messages (should be empty)
            return userMessages;
        }

        // Creates array of files to hold all the JSON files in folder
        File[] jsonFiles = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".json"));
        // If the folder isn't empty
        if (jsonFiles != null) 
        {
            // For each file in the folder
            for (File file : jsonFiles) 
            {
                try 
                {
                    // Map the dm object from JSON to Java
                    DmData data = mapper.readValue(file, DmData.class);
                    // Get boolean if contains user
                    boolean containsUser = data.getParticipants().stream()
                        .anyMatch(p -> p.getName().equalsIgnoreCase(participantName));
                    // If the data contains the user and it has messsages
                    if (containsUser && data.getMessages() != null) 
                    {
                        // Add all the messages to the message list
                        userMessages.addAll(data.getMessages());
                    }
                }
                // If there is an issue reading the JSON file
                catch (IOException e) 
                {
                    // Print that the file wasn't able to be read
                    System.out.println("Failed to read JSON from: " + file.getName());
                    // And what happened in the stack
                    e.printStackTrace();
                }
            }
        }

        // Return the list of messages form requested user
        return userMessages;
    }
}
