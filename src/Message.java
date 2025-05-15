import java.util.List;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Message 
{
    // Who the sender is
    private String sender_name;
    // What the timestamp is
    private long timestamp_ms;
    // Content of message
    private String content;

    // Getter methods

    /**
     * Get the sender's name
     * @return String: sender's name
     */
    public String getSender_name() 
    {
        return sender_name;
    }

    /**
     * Get the timestamp
     * @return Long: the timestamp to be converted to month day year fomat
     */
    public long getTimestamp_ms() 
    {
        return timestamp_ms;
    }

    /**
     * Gets the content of the message
     * @return String: content of the message
     */
    public String getContent() 
    {
        return content;
    }

    // Setter methods
    
    /**
     * Set the sender name
     * @param sender_name - String of what the new name is
     */
    public void setSender_name(String sender_name) 
    {
        this.sender_name = sender_name;
    }

    /**
     * Changes the time stamp
     * @param timestamp_ms - long (to be converted to month day year fomat) of timestamp
     */
    public void setTimestamp_ms(long timestamp_ms) 
    {
        this.timestamp_ms = timestamp_ms;
    }

    /**
     * Sets the message content
     * @param content - String of what the message says
     */
    public void setContent(String content) 
    {
        this.content = content;
    }

    // Other methods

    /**
     * Returns if the message is either from or two a given username
     * @param username - String of the username to check
     * @return Boolean: if the message is to or from username
     */
    public boolean isFromOrTo(String username) 
    {
        return sender_name.equals(username);
    }

    /**
     * Reformats the timestamp from Epoch to month day year format
     * @return
     */
    public String formatTimestamp() 
    {
        // Creates object holding timestamp in Epcoch format
        Instant instant = Instant.ofEpochMilli(timestamp_ms);
        // Gets the time zone
        ZoneId zone = ZoneId.systemDefault();
        // Finds the local time of the timestamp
        LocalDateTime dateTime = LocalDateTime.ofInstant(instant, zone);
        // Creates a formatter to change it to year, month day, hours, minutes, seconds
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        // Makes a string that has the formatted date
        String formattedDate = dateTime.format(formatter);
        // Returs the formatted date
        return formattedDate;
    }

    public static void main(String args[]) 
    {
    }

}
