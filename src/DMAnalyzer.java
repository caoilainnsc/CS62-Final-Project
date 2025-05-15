import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class DMAnalyzer {

    private List<Message> messages;

    public DMAnalyzer(List<Message> messages) {
        this.messages = messages;
    }

    // All your methods that use the messages list:
    
    /**
     * Returns the date of the longest conversation (most messages exchanged) with the given user.
     * @param username Username of the person.
     * @return Date string (e.g., "2023-04-15") of the most active conversation day.
     */
    public String getDateOfLongestConversation(String username) {
        Map<String, Integer> dateMessageCount = new HashMap<>();

        for (Message message : messages) {
            String sender = message.getSender_name();
            String date = message.toString().substring(0, 10); // Format: yyyy-MM-dd

            // Check if the message is between the user and the given username
            if (sender.equals(username) || message.isFromOrTo(username)) {
                dateMessageCount.put(date, dateMessageCount.getOrDefault(date, 0) + 1);
            }
        }

        String mostActiveDate = null;
        int maxCount = 0;

        for (Map.Entry<String, Integer> entry : dateMessageCount.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                mostActiveDate = entry.getKey();
            }
        }

        return mostActiveDate;
    }


    public List<String> getRecentMessages(String username) {
        // Step 1: Create a list for relevant messages
        List<Message> relevantMessages = new ArrayList<>();

        // Step 2: Filter messages involving the user
        for (Message m : messages) {
            if (m.isFromOrTo(username)) {
                relevantMessages.add(m);
            }
        }

        // Step 3: Sort messages by timestamp (most recent first)
        Collections.sort(relevantMessages, new Comparator<Message>() {
            public int compare(Message m1, Message m2) {
                return Long.compare(m2.getTimestamp_ms(), m1.getTimestamp_ms());

            }
        });

        // Step 4: Create the result list with readable strings (up to 10 messages)
        List<String> result = new ArrayList<>();
        int count = Math.min(10, relevantMessages.size());
        for (int i = 0; i < count; i++) {
            Message msg = relevantMessages.get(i);
            result.add(msg.toString()); // Customize this if needed
        }

        return result;
    }

}
