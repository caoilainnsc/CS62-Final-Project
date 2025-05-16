import java.text.SimpleDateFormat;
import java.util.*;
public class DateHash 
{
    private Map<String, ArrayList<String>> messagesByDate;

    public DateHash(ArrayList<Message> messages)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (Message msg : messages)
        {
            // Create the date from the timestamp to the format of month day year
            String date = sdf.format(new Date(msg.getTimestamp_ms()));
            // Count the number of messages by date
            ArrayList<String> message = messagesByDate.get(date);
            // If that date doesn't have anything in it yet
            if (message == null) 
            {
                // Add date to hash table and set num messages to 1
                ArrayList<String> msgAtDate= new ArrayList<String>();
                msgAtDate.add(msg.getContent());
                messagesByDate.put(date, msgAtDate);
            } 
            // If date's already in hash table
            else 
            {
                // Increment message count
                messagesByDate.get(date).add(msg.getContent());
            }
        }
    }

    public ArrayList<String> getMessageAtDate(String date)
    {
        ArrayList<String> longestConvo = new ArrayList<String>();
        if (!messagesByDate.containsKey(date))
        {
            longestConvo.add("No messages");
            return longestConvo;
        }
        return messagesByDate.get(date);
    }

}
