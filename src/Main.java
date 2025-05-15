import java.io.File;
import java.util.List;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter username to analyze: ");
        String name = scanner.nextLine().trim();

        List<Message> messages = InstagramDMLoader.loadMessagesFromParticipant("Message JSON Files", name);

        if (messages.isEmpty()) {
            System.out.println("No messages found.");
            return;
        }

        // Sort messages from newest to oldest
        Collections.sort(messages, new Comparator<Message>() {
            public int compare(Message m1, Message m2) {
                return Long.compare(m2.getTimestamp_ms(), m1.getTimestamp_ms());
            }
        });

        System.out.println("Recent messages from " + name + ":");
        int count = 0;
        for (Message msg : messages) {
            if (msg.getContent() != null) {
                System.out.println(msg.getContent());
                count++;
            }
            if (count == 20) break;
        }

        // Count messages per date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Map<String, Integer> messageCountByDate = new HashMap<>();

        for (Message msg : messages) {
            String date = sdf.format(new Date(msg.getTimestamp_ms()));
            Integer currentCount = messageCountByDate.get(date);
            if (currentCount == null) {
                messageCountByDate.put(date, 1);
            } else {
                messageCountByDate.put(date, currentCount + 1);
            }
        }

        // Find date with max messages
        String maxDate = null;
        int maxCount = 0;
        for (Map.Entry<String, Integer> entry : messageCountByDate.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                maxDate = entry.getKey();
            }
        }

        System.out.println("\nDate of longest conversation with " + name + ":");
        if (maxDate != null) {
            System.out.println(maxDate);
        } else {
            System.out.println("No data found.");
        }
    }
}
