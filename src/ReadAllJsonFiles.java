import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ReadAllJsonFiles 
{
    public static void main(String[] args) 
    {
        // Creates scanner to get user input
        Scanner scanner = new Scanner(System.in);
        // Tells user to put username
        System.out.print("Enter username: ");
        // Gets username from user (and puts in lowercase)
        String usernameInput = scanner.nextLine().trim().toLowerCase();
        // Closes scanner
        scanner.close();

        // Creates folder to hold JSON files of messages
        File folder = new File("Message JSON Files");

        // If the folder doesn't exist
        if (!folder.exists() || !folder.isDirectory()) 
        {
            // Tell the User it doesn't exist
            System.out.println("Folder not found: " + folder.getAbsolutePath());
            return;
        }

        // Creates an array to hold the files in the foler
        File[] jsonFiles = folder.listFiles((dir, name) ->
            name.toLowerCase().contains(usernameInput) && name.toLowerCase().endsWith(".json")
        );

        // If the file with name is empty (doesn't have any file with messages from username)
        if (jsonFiles == null || jsonFiles.length == 0) 
        {
            // Tell the user no file was found
            System.out.println("No matching file found for username: " + usernameInput);
            return;
        }

        // Find the first matching instance of file with name
        File matchingFile = jsonFiles[0];
        // Tell the user there was a matching file with that name
        System.out.println("Found matching file: " + matchingFile.getName());

        try 
        {
            // Create mapper to convert JSON object to Java
            ObjectMapper mapper = new ObjectMapper();
            // Get data from the file using the DmData class
            DmData data = mapper.readValue(matchingFile, DmData.class);
            // Convert the data from the file to a list of messages
            List<Message> messages = data.getMessages();

            // Return the number of messages in chat
            System.out.println("Number of messages: " + messages.size());
            // Return what the first message was
            System.out.println("First message: " + messages.get(0));

        }
        // If was unable to read file 
        catch (IOException e) 
        {
            // Tell user that the file could not be read
            System.out.println("Failed to parse JSON file.");
            // Print what happened in the stack
            e.printStackTrace();
        }
    }
}
