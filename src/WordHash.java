import java.text.SimpleDateFormat;
import java.util.*;

public class WordHash 
{
    private Map<String, ArrayList<String>> messagesByWord;

    public WordHash(ArrayList<Message> messages)
    {
        for (Message msg : messages)
        {

            String[] words = msg.getContent().split(" ");
            for (String word : words)
            {
                // If that date doesn't have anything in it yet
                if (!messagesByWord.containsKey(word.toLowerCase())) 
                {
                    // Add word to hash table and set num messages to 1
                    ArrayList<String> msgWithWord= new ArrayList<String>();
                    msgWithWord.add(msg.getContent());
                    messagesByWord.put(word.toLowerCase(), msgWithWord);
                } 
                // If already in hash table
                else 
                {
                    // Increment message count
                    messagesByWord.get(word.toLowerCase()).add(msg.getContent());
                }
            }
            
        }
    }

    public ArrayList<String> getMessageAtDate(String word)
    {
        ArrayList<String> longestConvo = new ArrayList<String>();
        if (!messagesByWord.containsKey(word.toLowerCase()) || word.equals(""))
        {
            longestConvo.add("No messages");
            return longestConvo;
        }
        return messagesByWord.get(word.toLowerCase());
    }
}
