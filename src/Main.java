import java.io.File;
import java.util.List;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.ArrayList;

public class Main 
{

    public void printMostRecents(int num, ArrayList<Message> givenMessages)
    {
        // Return what recent messages are
        System.out.println("Recent messages:");
        BinaryHeapDate messageTree = new BinaryHeapDate();
        // For all messages in chat
        for (Message msg : givenMessages) 
        {
            messageTree.insert(msg);
        }

        for (Message recent : messageTree.getMostRecent(num))
        {
            System.out.println(recent.getContent());
        }
    }

    public void printLongest(int num, ArrayList<Message> givenMessages)
    {
        System.out.println("Longest messages: ");
        BinaryHeapLength messageTree = new BinaryHeapLength();
        for (Message msg : givenMessages) 
        {
            messageTree.insert(msg);
        }

        for (Message recent : messageTree.getMostRecent(num))
        {
            System.out.println(recent.getContent());
        }
    }

    public static void main(String[] args) 
    {
        // Create scanner for user input
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the file path of folder containing JSON files:\n");
        String filePath = scanner.nextLine().trim();
        InstagramDMLoader userChats = new InstagramDMLoader(filePath);
        // Ask user for username to get conversation data from
        System.out.print("Enter username to analyze:\n");
        // Get user input of username
        String name = scanner.nextLine().trim();

        // Load messages from json file
        ArrayList<Message> messages = userChats.loadMessagesFromParticipant(name);

        // If there are no messages
        if (messages.isEmpty()) 
        {
            // Return that there were no messages found
            System.out.println("No messages found.");
            return;
            
        }
        
        // Count messages per date
        // Format data to year month day
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        // Create a hash table of message counts by date
        Map<String, Integer> messageCountByDate = new HashMap<>();

        // For the messages in chat
        for (Message msg : messages) 
        {
            // Create the date from the timestamp to the format of month day year
            String date = sdf.format(new Date(msg.getTimestamp_ms()));
            // Count the number of messages by date
            Integer currentCount = messageCountByDate.get(date);
            // If that date doesn't have anything in it yet
            if (currentCount == null) 
            {
                // Add date to hash table and set num messages to 1
                messageCountByDate.put(date, 1);
            } 
            // If date's already in hash table
            else 
            {
                // Increment message count
                messageCountByDate.put(date, currentCount + 1);
            }
        }

        // Find date with max messages
        // Set a string to find the date with most messages
        String maxDate = null;
        // Get a count of messages to find date with most
        int maxCount = 0;
        // For the entries in the hash map with the date and num messages
        for (Map.Entry<String, Integer> entry : messageCountByDate.entrySet()) 
        {
            // If the number is more than the previously biggest number
            if (entry.getValue() > maxCount) 
            {
                // Get the number of messages
                maxCount = entry.getValue();
                // Get the date
                maxDate = entry.getKey();
            }
        }

        // Tell the user the date of the longest conversation with the requested username
        System.out.println("\nDate of longest conversation with " + name + ":");
        // If there was data found 
        if (maxDate != null) 
        {
            // Print the longest date
            System.out.println(maxDate);
        }
        // If there were no messages 
        else 
        {
            // Return no data was found
            System.out.println("No data found.");
        }
        // Close scanner
        scanner.close();
    }
}
