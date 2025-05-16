import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class InstagramDMLoader 
{
    private static String folderPath;
    private static File folder;
    private static ArrayList<File> conversations;
    private ArrayList<Message> chat;
    private static ObjectMapper mapper;
    
    public InstagramDMLoader(String path)
    {
        folderPath = path;
        folder = new File(folderPath);
        chat = new ArrayList<Message>();
        mapper = new ObjectMapper();
        File[] convoArray = folder.listFiles();
        conversations = new ArrayList<File>(convoArray.length);
        try
        {
            if (conversations.equals(null))
            {
                throw new IOException("Folder is empty");
            }
            for (File chat : convoArray)
            {
                conversations.add(chat);
            }
        }
        catch(IOException e)
        {
            System.out.println("Issue with folder: " + e);
        }
    }

    /**
     * Loads all messages in the JSON files
     * @return A list of the messages in the files
     */
    public static ArrayList<Message> loadAllMessages() 
    {
        ArrayList<Message> allMessages = new ArrayList<Message>();
        
        for(File convo : conversations)
        {
            try
            {
                DmData data = mapper.readValue(convo, DmData.class );
                for (Message message : data.getMessages())
                {
                    allMessages.add(0, message);
                }
            }
            catch(IOException e)
            {
                System.out.println("Issue parsing JSON file " + convo.getName() + ": " + e);
            }
            
        }
        return allMessages;
    }

    /**
     * Gets the messages from a specific participant
     * @param folderPath the folder with the JSON files of messages
     * @param participantName the participant that you want to get messages from
     * @return the list of messages from that participant
     */
    public ArrayList<Message> loadMessagesFromParticipant(String participantName) 
    {
        File chatFile = new File(folderPath + "/" + participantName + ".json");
        try
        {
            if (!conversations.contains(chatFile))
            {
                throw new IOException("No chat exists with that name");
            }
            try
            {
                DmData data = mapper.readValue(chatFile, DmData.class );
                for (Message message : data.getMessages())
                {
                    chat.add(0, message);
                }
            }
            catch(IOException e)
            {
                System.out.println("Issue parsing JSON file for user: " + e);
            }
            
        }
        catch (IOException e)
        {
            System.out.println("Issue finding chat: " + e);
        }

        
        // Return the list of messages form requested user
        return chat;
    }
}
