import java.util.List;
import java.util.Map;

public interface InstagramDMAnalyzer {

    /**
     * Loads the dataset from a file path (e.g., JSON or CSV format).
     * @param filePath Path to the dataset file.
     */
    void loadDataset(String filePath);

    /**
     * Returns a map of usernames to the total number of messages sent to each.
     * @return Map where keys are usernames and values are counts of messages sent to them.
     */
    Map<String, Integer> getTotalMessagesSentToEachPerson();

    /**
     * Returns a map of usernames to the total number of messages received from each.
     * @return Map where keys are usernames and values are counts of messages received from them.
     */
    Map<String, Integer> getTotalMessagesReceivedFromEachPerson();

    /**
     * Returns a list of the most common one-word messages across all text threads.
     * @param topN Number of top words to return.
     * @return List of most commonly used single-word messages.
     */
    List<String> getMostCommonSingleWordMessages(int topN);

    /**
     * Returns the date of the longest conversation (most messages exchanged) with the given user.
     * @param username Username of the person.
     * @return Date string (e.g., "2023-04-15") of the most active conversation day.
     */
    String getDateOfLongestConversation(String username);

    /**
     * Returns the 20 most recent messages exchanged with the given user.
     * @param username Username of the person.
     * @return List of message strings, ordered from most recent to least recent.
     */
    List<String> getRecentMessages(String username);
}
