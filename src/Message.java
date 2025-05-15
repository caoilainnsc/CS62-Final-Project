import java.util.List;
//import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Message {
    private String sender_name;
    private long timestamp_ms;
    private String content;

    // Getter methods
    public String getSender_name() {
        return sender_name;
    }

    public long getTimestamp_ms() {
        return timestamp_ms;
    }

    public String getContent() {
        return content;
    }

    // Setter methods
    public void setSender_name(String sender_name) {
        this.sender_name = sender_name;
    }

    public void setTimestamp_ms(long timestamp_ms) {
        this.timestamp_ms = timestamp_ms;
    }

    public void setContent(String content) {
        this.content = content;
    }





/*
 * 
 * 



public class Message {
    public String sender_name;
    public long timestamp_ms;
    public String content;

    // Optional: constructor
    public Message(String sender_name, long timestamp_ms, String content) {
        this.sender_name = sender_name;
        this.timestamp_ms = timestamp_ms;
        this.content = content;
    }


    public String getSender() {
        return sender_name;
    }

    public String getContent() {
        return content;
    }

    public String getTimestamp() {
       return formatTimestamp();
         
    }
 * 
 */




    /**
     * Returns true if the message is from or to the given username.
     */
    public boolean isFromOrTo(String username) {
        return sender_name.equals(username);
    }
    public String formatTimestamp() {
        Instant instant = Instant.ofEpochMilli(timestamp_ms);
        ZoneId zone = ZoneId.systemDefault(); // Still needed to convert Instant to LocalDateTime
        LocalDateTime dateTime = LocalDateTime.ofInstant(instant, zone);
    
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDate = dateTime.format(formatter);
        return formattedDate;
    }

   // @Override
   // public String toString() {
  //      return "[" + timestamp_ms + "] " + sender_name + ": " + content;
   // }

    public static void main(String args[]) {
       // private List<Message> messages = new ArrayList<>();
    }

}
