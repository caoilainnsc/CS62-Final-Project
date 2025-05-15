import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class DMAnalyzer 
{

    // List to hold all messages
    private List<Message> messages;

    // Constructor that takes a list of messages
    public DMAnalyzer(List<Message> messages) 
    {
        this.messages = messages;
    }

    // All your methods that use the messages list:
    
    /**
     * Returns the date of the longest conversation (most messages exchanged) with the given user.
     * @param username Username of the person.
     * @return Date string (e.g., "2023-04-15") of the most active conversation day.
     */
    public String getDateOfLongestConversation(String username) 
    {
        // Creates a hash map to get the dated messages
        Map<String, Integer> dateMessageCount = new HashMap<>();

        // For all the messages in the list
        for (Message message : messages) 
        {
            // The sender is the sender name
            String sender = message.getSender_name();
            // The date held is the date
            String date = message.toString().substring(0, 10); // Format: yyyy-MM-dd

            // If the message is between the user and the given username
            if (sender.equals(username) || message.isFromOrTo(username)) 
            {
                // Have entry created with 1 or incremented  
                dateMessageCount.put(date, dateMessageCount.getOrDefault(date, 0) + 1);
            }
        }

        // Create string to hold longest conversation date
        String mostActiveDate = null;
        // Number of messages
        int maxCount = 0;
        
        // For all the dates in the hash map
        for (Map.Entry<String, Integer> entry : dateMessageCount.entrySet()) 
        {
            // If the entry has more than the current biggest
            if (entry.getValue() > maxCount) 
            {
                // Update this count to what the bigger count is
                maxCount = entry.getValue();
                // Update most active date to this date
                mostActiveDate = entry.getKey();
            }
        }

        // Return date with most number of messages
        return mostActiveDate;
    }

    /**
     * Creates a list of the recent messages
     * @param username which use the conversation is with
     * @return Returns the list with the 10 most recent messages
     */
    public List<String> getRecentMessages(String username) 
    {
        // Creates list to hold relevant messages
        List<Message> relevantMessages = new ArrayList<>();

        // For the messages in the list
        for (Message m : messages) 
        {
            // If the message is from the requested username
            if (m.isFromOrTo(username)) 
            {
                // Add to list
                relevantMessages.add(m);
            }
        }

        // Creates a comparator for the messages to find the more recent date
        Collections.sort(relevantMessages, new Comparator<Message>()
        {
            // Find most recent message
            public int compare(Message m1, Message m2) 
            {
                return Long.compare(m2.getTimestamp_ms(), m1.getTimestamp_ms());
            }
        });

        // Create the result list with readable strings (up to 10 messages)
        // Creates a string of the list to return with most recent messages
        List<String> result = new ArrayList<>();
        // Creates a count to find the 10 most recent messages
        int count = Math.min(10, relevantMessages.size());
        // For the number or messages wanted
        for (int i = 0; i < count; i++) 
        {
            // Get the message from the relevent messages
            Message msg = relevantMessages.get(i);
            // Make result a string and add to list
            result.add(msg.toString()); // Customize this if needed
        }

        // Return list of recent messages
        return result;
    }

}
