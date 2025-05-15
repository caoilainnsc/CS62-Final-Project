import java.util.List;
import java.io.File;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DmData 
{
    // List of participants in the chat
    private List<Participant> participants;
    // List of the messages in the chat
    private List<Message> messages;

     /**
     * Returns a list of participants in a chat
     * @return list of participants
     */
    public List<Participant> getParticipants() 
    {
        return participants;
    }

    /**
     * Updates the list of participants to a different list
     */
    public void setParticipants(List<Participant> participants) 
    {
        this.participants = participants;
    }

    /**
     * Returns a messages in a chat
     * @return list of messages
     */
    public List<Message> getMessages() 
    {
        return messages;
    }

    /**
     * Updates the list of messages to a different list
     */
    public void setMessages(List<Message> messages) 
    {
        this.messages = messages;
    }
}
