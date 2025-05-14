# CS62-Final-Project

Instagram DM Analyzer
=====================

Description
-----------

In this project, you will create a Java application that processes your downloaded Instagram direct messages (DMs) and extracts insightful statistics and summaries. By analyzing message metadata and text content, your program will offer multiple features that allow you to explore trends and patterns in your conversations.

You will write a program that reads in a dataset of DMs (exported from the Meta Accounts Center), parses and organizes the messages, and implements various functionalities. These include computing message counts per contact, identifying the most frequently used one-word messages, retrieving the most active conversation date with a specific user, and displaying recent conversations.

The project emphasizes core concepts in Java, such as file I/O, object-oriented design, sorting and filtering with collections, and effective use of data structures like HashMap, List, and Set.

Features
--------

Your application will support the following operations:

1.  **Display Total Messages Per User**Calculate and display the number of messages sent _to_ and _from_ each user in the dataset.
    
2.  **Most Common One-Word Messages**Analyze all messages across all conversations and return the most frequently used one-word messages (e.g., “lol”, “okay”, “hey”).
    
3.  **Date of Longest Conversation**Given a username, determine the date with the largest number of exchanged messages between you and that person.
    
4.  **Recent Messages Viewer**Given a username, return the 20 most recent messages from your conversation with that user, sorted by timestamp in descending order.
    

Suggested Classes
-----------------

### Message

Represents a single message and contains:

*   String sender
    
*   String receiver
    
*   String content
    
*   LocalDateTime timestamp
    

You may optionally override toString() for clean printing of messages.

### DMAnalyzer

This is the core class that processes and stores message data. It contains the following methods:

Plain 
<pre> ```java public interface DMAnalyzer { void loadMessages(String filePath); Map getMessageCountsByUser(); Map getMessageCountsFromUser(); List getMostCommonOneWordMessages(int limit); LocalDate getDateOfLongestConversation(String username); List getRecentMessages(String username, int count); } ``` </pre>

**Notes:**

*   loadMessages reads from a file and populates a List.
    
*   getMessageCountsByUser returns a mapping of usernames to the number of messages they received.
    
*   getMessageCountsFromUser returns a mapping of usernames to the number of messages they sent.
    
*   getMostCommonOneWordMessages(int limit) returns a list of the most common one-word messages used across all conversations.
    
*   getDateOfLongestConversation(String username) finds the day with the most messages exchanged with a particular user.
    
*   getRecentMessages(String username, int count) returns the most recent count messages exchanged with the specified user.
    

Data Structures
---------------

Each feature is backed by thoughtfully chosen data structures:

FeatureData StructureReasonMessage count per userMapEfficient mapping of usernames to message countCommon one-word messagesMap + PriorityQueue or StreamFrequency counting with fast top-k retrievalLongest conversation dateMapAggregate messages per date for a given userRecent messagesList (filtered + sorted)Flexible sorting and sublisting by timestamp

Getting Started
---------------

### 1\. Prepare Your Dataset

*   Go to [Meta Accounts Center](https://accountscenter.meta.com/) > “Your Information” > “Download Your Information”.
    
*   Export your Instagram messages as JSON and unzip the archive.
    
*   Find the relevant message data file (e.g., message\_1.json).
    

### 2\. Load and Parse Messages

Start by implementing the loadMessages method to read the JSON file and convert each entry into a Message object.

### 3\. Develop Features One-by-One

Focus on one method at a time. For example:

*   First, implement getMessageCountsFromUser.
    
*   Verify it with print statements or JUnit tests.
    
*   Once stable, move to the next method like getRecentMessages.
    

### 4\. Testing & Output

Use main methods or unit tests to verify each feature. Print outputs in readable formats to validate correctness.

Example Output
--------------

**Top 5 One-Word Messages:**

Plain  
1. lol   — 92 times  
2. okay  — 87 times  
3. hey   — 74 times  
4. omg   — 53 times  
5. sure  — 49 times


**Recent Messages with @alexdoe:**

Plain 
[2023-12-15 18:42] me: Did you finish the playlist?  
[2023-12-15 18:43] alexdoe: Almost lol  
[2023-12-15 18:45] me: Ok cool  
...  (20 messages total)

**Most Active Day with @alexdoe:**

Plain 
2023-12-15 — 47 messages exchanged
