<<<<<<< HEAD
# CS62-Final-Project

# Instagram DM Analyzer

This Java project analyzes your Instagram Direct Messages (DMs) downloaded from the Meta Accounts Center. There are multiple platforms and extentions that allow you to visualize and analyze data pertaining to instagram followers, but nothing that allows you to do the same for Instagram DMs. This written file will provide insights about messages, such as: total messages sent/received between you and a given username, most common one-word messages sent, most active conversation dates, and recent message history.

## Features

- 📊 **Message Statistics**  
  - View total messages sent to each user  
  - View total messages received from each user

- 🔠 **Common Words**  
  - Display the most commonly used single-word messages across all conversations

- 📅 **Longest Conversation Day**  
  - Get the date with the longest conversation (most messages exchanged) with a specific user

- 📥 **Recent Messages**  
  - Display the 20 most recent messages from a text thread with a specific user

## Interface

The core functionality is defined in the `InstagramDMAnalyzer` interface:

```java
public interface InstagramDMAnalyzer {
    void loadDataset(String filePath);
    Map<String, Integer> getTotalMessagesSentToEachPerson();
    Map<String, Integer> getTotalMessagesReceivedFromEachPerson();
    List<String> getMostCommonSingleWordMessages(int topN);
    String getDateOfLongestConversation(String username);
    List<String> getRecentMessages(String username);
}
=======
# CS62-Final-Project
>>>>>>> 292b78e46e5b365fc07a7e43469080eb857d99fc
