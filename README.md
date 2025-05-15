# CS62-Final-Project - Instagram DM Data Analyzer

Description
-----------

A Java application that processes the JSON files of your Instagram direct messages (DMs), available to download on your Instagram account from the Meta data center. This program analyzes the message to show metadata about the messages (pulling from information about the user, message content, and timestamp). This provides interesting and insightful statistics regarding your message history with specific individuals that you can use and compare to messages in other chats. The program offers analysis using data from length of message, date of message, and frequency of word use.

The program reads from a dataset of DMs exported from the Meta Accounts center. It parses and organizes the messages, and then sorts and analyzes them in regards to the various features allowed by the program. These include the messages counts per contact, most frequently used one-word messages, most date with the most messages sent and recieved, and the most recent messages from the conversation. 

This project emphasizes core concepts in Java, such as file I/O, object-oriented design, sorting and filtering with collections, and effective use of data structures, more specifically, a custom hash table.

Features
--------

The application supports the following operations:

1.  **Display Total Messages Per User**Calculate and display the number of messages sent and recieved from each user in the dataset.
    
2.  **Most Common One-Word Messages**Analyze all messages across a conversation and return the most frequently used one-word messages (e.g., “lol”, “okay”, “hey”).
    
3.  **Date of Longest Conversation**Given a username, determine the date with the largest number of exchanged messages between you and that person.
    
4.  **Recent Messages**Given a username, return the 20 most recent messages from your conversation with that user, sorted by timestamp in descending order.
    

Classes to Store and Quickly Analyze Data
-----------------

### Message

Represents a single message and contains:

*   String sender
    
*   String receiver
    
*   String content
    
*   LocalDateTime timestamp

### DMAnalyzer

This is the class that processes and stores message data. It contains the following methods:

*   loadMessages reads from a file and populates a List.
    
*   getMessageCountsByUser returns a mapping of usernames to the number of messages they received.
    
*   getMessageCountsFromUser returns a mapping of usernames to the number of messages they sent.
    
*   getMostCommonOneWordMessages(int limit) returns a list of the most common one-word messages used across all conversations.
    
*   getDateOfLongestConversation(String username) finds the day with the most messages exchanged with a particular user.
    
*   getRecentMessages(String username, int count) returns the most recent count messages exchanged with the specified user.
    

Data Structures - TO BE EDITED
---------------

The data structure implemented to efficently sort and understand the message data is a custom hash table. The 

Instructions to Analyze Your Data
---------------

### 1\. Download Your Instagram DM Information from Meta

*   On your computer, open the Instagram website and go to your profile

*   Click on the gear icon next to "Edit Profle" and "View Archive"

*   Go to "Settings and Privacy" (or alternatively start by clicking "More" on the bottom right of the screen, then "Settings"
    
*   Go to the "Meta Accounts Center" where all your data from any Meta accounts that you may have connected is stored

*   On the right, go to the "Your Information and Permissions" tab

*   Go to "Download your information"

*   Chose "Download or Transfer Information" and chose "Some of Your Information" - this allows you to only download your message data rather than every piece of data Meta has from your profile

*   Under "Your Instagram Activity" check the box at the bottom of the group that says "Messages" and click the blue "Next" button

*   Chose "Download Information to Device"

*   In the next screen chose the date range you want your files to contain messages from, then change "Format" from HTML to JSON (important or the data will not be analyzable with this program) and click save, and chose low media quality (this program does not analyze photos, videos, or audios, so it is better to have them be as small as possible)

*   After you select the blue "Create Files" button, it will start the process of retrieving your information. Depending on how much data you have, it will send you an email with a link to the Meta Accounts Center to download your information after a period of time (up to four days). Download this information to your computer.

*   Unzip the file downloaded. There will be a folder called "your-instagram-message" that containx the folder "inbox". This is where each of your conversations are held.

*   To store the information you want analyzed, create a folder to hold all of the JSON files of your conversations. For any file that you want to have analyzed, go to the folder of that user (the name of the file is the user's name and a number), select the file (or files) that says "messages_" + number + ".json" - if there are multiple files because the conversation is large, you can name them with the user and the number on the json file (ex. "kate1.json" and "kate2.json"), but you will have to analyze these conversations separately (the file numbers go from 1 being most recent to the biggest number being the oldest part of the chat).
    
*   This folder will be what you provide when asked that will be analyzed
    

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
