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



Dataset Format 
--------

The dataset comes from exporting messages using the [Meta Accounts Center](https://accountscenter.meta.com/). JSON is used due to its structured and easy-to-parse format. Each file contains:

```json
{
  "participants": [ {"name": "User A"}, {"name": "User B"} ],
  "messages": [
    {
      "sender_name": "User A",
      "timestamp_ms": 1716673008104,
      "content": "Hey!",
      "reactions": [...],
      "is_geoblocked_for_viewer": false
    }
  ]
}
```

Adding Jackson API to Java Project
--------
   
For our java program to be able to read our various JSON files and convert them to java objects, we have to manually add three core jackson libraries to our code. First, create a java folder in your directory titled lib/. Then download these three Jackson JAR files to your computer:

*   https://repo1.maven.org/maven2/com/fasterxml/jackson/core/jackson-core/2.15.2/jackson-core-2.15.2.jar 
*   https://repo1.maven.org/maven2/com/fasterxml/jackson/core/jackson-databind/2.15.2/jackson-databind-2.15.2.jar
*   https://repo1.maven.org/maven2/com/fasterxml/jackson/core/jackson-annotations/2.15.2/jackson-annotations-2.15.2.jar

Next, you need to drag and drop these three downloaded files into your lib/ folder in your directory (This should be a folder outside of your src folder).

Then, in order to compile, you must create a new outside folder titled .vscode/ and create a settings.json file inside of it.

Inside of settings.json, copy and paste this snippet of code inside:

```json
{
  "java.project.sourcePaths": [
    "src"
  ],
  "java.project.referencedLibraries": [
    "lib/**/*.jar"
  ]
}
```
This tells vscode to include all of your .jar files in the lib/ folder in your classpath 



Classes to Store and Analyze Data
-----------------

### Message

Represents a single message and contains:

*   String sender
    
*   String receiver
    
*   String content (the actual message sent)
    
*   LocalDateTime timestamp

### DMAnalyzer

This class is responsible for analyzing the list of parsed messages. It includes logic for computing key features based on message data.

It's key methods include: 

```json
String getDateOfLongestConversation(String username)
```
This method tterates through all messages and counts the number of messages per date where the specified user was either the sender or recipient. Returns the date (as a yyyy-MM-dd string) on which the most messages were exchanged with that user.

```json
List<String> getRecentMessages(String username)
```

This method returns the 20 most recent messages involving the specified user, sorted by timestamp in descending order. It uses a comparator to sort the messages and formats each one into a string.


### DMData 

The DmData class is a data model used to represent the contents of a single Instagram message JSON file. It is used during the deserialization process to map the file's structure to Java objects.

This class is annotated with:

```json
@JsonIgnoreProperties(ignoreUnknown = true)
```

which tells Jackson to ignore any JSON fields that aren't explicitly defined in the class, such as reactions, media, or other metadata not needed for analysis. DmData acts as a wrapper for each conversation thread in the dataset. Each message JSON file typically includes both participant information and an array of messages. This class encapsulates those two fields, allowing them to be accessed and manipulated as needed.

Key methods to implement include: 
```json
List<Participant> participants
```
A list of all participants in the current conversation thread.

```json
List<Message> messages
```
A list of all direct messages exchanged in the thread.

A getter and setter method is also needed for both of these. This class should primarily be used by InstagramDMLoader.java to deserialize each individual JSON file into usable Java objects. Once loaded, the message lists from each DmData instance are combined to form a complete dataset, which is then passed to the DMAnalyzer.


### InstagramDMLoader

This class is responsible for reading and parsing Instagram DM .json files from a user-specified folder. It utilizes the Jackson ObjectMapper to deserialize each file into DmData objects, from which the messages are extracted and aggregated.

What it should do:
*   Traverse a folder of Instagram JSON DM Files
*   Parse each file into java objects (DmData, Message, Participant)
*   Filter messages by participant name

```jason 
public static List<Message> loadMessages(String folderPath)
```

This line of code loads all messages from every .json file in the provided folder. It's meant to return a combined ```json List<Message>``` containing messages from all files.
Usage: Ideal when analyzing the full dataset regardless of participant.

```json
public static List<Message> loadMessagesFromParticipant(String folderPath, String participantName)
```

This line of code filters and loads messages only from conversations that include a specific participant. It returns a ```json List<Message>``` containing only the messages from relevant threads involving the specified user.
    
```json
ObjectMapper mapper = new ObjectMapper();
```
This line of code is used to deserialize .json into Java objects.

Lastly, messages should be extracted through data.getMessages()

This class acts as the data ingestion layer. It isolates file reading and parsing logic from the rest of the application, enabling classes like DMAnalyzer to focus on analysis, not input handling. It's designed to be reusable and safe, handling missing files and format errors pretty gracefully.

### Participant

This is a fairly simple data class that represents an individual participant in an Instagram direct message thread. This class is used primarily when deserializing the participants array from the Instagram JSON structure. Its responsibilities include: holding the name of one participant in a conversation, and providing getter and setter methods to access or modify that name.

Fields: private String name;

The Participant class works alongside the DmData class to represent a conversation's metadata. While simple, it is essential for identifying which users are involved in a particular message thread, especially when filtering or analyzing messages by participant.

### Message Thread 

The MessageThread class represents a single Instagram DM conversation, encapsulating both the participants involved and the messages exchanged within that thread. Its purpose serves as a lightweight container for bundling messages and participants together. It's useful for grouping a single chat thread when organizing or analyzing multiple message files.

Its fields include: 
```json
public List<Participant> participants;
```

which is a list of users involved in this single specific chat. It should also include,

```json
public List<Message> messages;
```

which is a list of Message objects representing each text/message sent in this thread. At this point, it should be deserialized from the messages array in the original Instagram JSON structure. You might use MessageThread when grouping messages and participants from a single JSON file for easier display or export. It could also be used when passing thread-level data between components that expect both metadata and content.

### ReadAllJsonFiles

This class is a standalone program that allows users to input a username, search for Instagram DM JSON files matching that username, and display basic information about the messages contained in the first matching file. Wen properly implemented, this class should allow for manual verification or quick inspection of Instagram DM JSON exports. It should also filter message files by username, read the matching file, and print summary details such as message count and the first few

How it Should Work:

*   User Prompt: Asks the user to input a username via the terminal.

*   Folder Lookup: Searches inside the Message JSON Files/ directory for any .json files that include the given username in the filename.

*   File Parsing:

        Uses Jackson's ObjectMapper to deserialize the JSON into a DmData object.

        Extracts the list of messages.

*   Output: Prints the number of messages and the contents of the first message (using its toString() method)..


Dependencies: 

```json
Jackson Databind (com.fasterxml.jackson.databind.ObjectMapper)
```

This line is used for converting JSON files to Java objects (DmData, Message, etc.).

There are also some assumptions you must look out for with this file. It should assume JSON files are stored in a soecific folder, in our case something like Message JSON Files. That folder should be located in the root directory.

Filenames include the participant's name (e.g., caoilainn.json).

The message file format conforms to the Instagram data export format supported by the DmData class.


Things to Keep in Mind: 

This class only loads the first matching file. It also assumes case-insensitive matching based on filename (not message content). Lastly, there is no validation of message format beyond successful deserialization.


### Main 

The Main class serves as the entry point to the Instagram DM Analyzer. It allows the user to input a participant's name, loads their messages from JSON files, and performs various key analyses:

*   Displays the 20 most recent messages with the participant

*   Finds and prints the date of the most active conversation (by message count)


How it Should Work:

*   Prompts the user for a participant's name.

*   Loads all messages involving that participant from JSON files located in the "Message JSON Files" folder.

*   Sorts the messages by timestamp (newest first).

*   Prints the 20 most recent non-empty messages.

*   Counts the number of messages sent per date.

*   Identifies and prints the date with the highest message volume (i.e., longest conversation)

Reminder:
    Make sure JSON message files being inputted are located in a folder named Message JSON Files. (all files end with .json, so also consider implementing code so that searching caoilainn searches for caoilainn.json in the Message JSON Files folder)

    Each file should follow the Instagram archive structure (compatible with Jackson's deserialization into DmData).



### Interface 

Make sure to also implement the InstagramDMAnalyzer interface, which defines a contract for analyzing Instagram Direct Messages. It outlines the core functionality expected of any class that implements it, enabling flexible and testable message analysis.

Method void loadDataset(String filePath) 

Loads Instagram message data from a specified JSON file path.

```json
Map<String, Integer> getTotalMessagesSentToEachPerson()	
```
Returns a map of usernames to the total number of messages sent to each.

```json
Map<String, Integer> getTotalMessagesReceivedFromEachPerson()
```
Returns a map of usernames to the number of messages received from each.

```json
List<String> getMostCommonSingleWordMessages(int topN)
```
Returns a list of the top N most common single-word messages.

```json
String getDateOfLongestConversation(String username)
```
Finds the date of the longest (most active) conversation with a specified user.

```json
List<String> getRecentMessages(String username)
```
Retrieves the 20 most recent messages exchanged with a specified user.





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
    

### 2\. NEEDS TO BE EDITED - USER INSTRUCTIONS ON HOW TO GET FOLDER INTO PROGRAM

Start by implementing the loadMessages method to read the JSON file and convert each entry into a Message object.

### 3\. NEEDS TO BE EDITED - USER INSTRUCTIONS ON HOW TO USE FEATURES/MAIN

Focus on one method at a time. For example:

*   First, implement getMessageCountsFromUser.
    
*   Verify it with print statements or JUnit tests.
    
*   Once stable, move to the next method like getRecentMessages.
    

Example Output
--------------

**Recent Messages with Kate:**

Thank you!!!
Liked a message
Happy Birthday Kate :)
Ok perf, so excited
212 Sagehen Ave
That's so fun, will definitely be there. What's your address
Ok cool, well I'm throwing a party on Saturday from like 8-12 if you want to come
No, I should be free
That's so real totally get it haha. Are you doing anything this weekend
I'm good, super busy with work lol
Hey, how are you doing?
Wait yes let's cross our fingers lol
I hope we get to be in the wedding that would be so fun
Haha yeah. And yes they are like the perfect couple
Took long enough, lol, but Emily's great
I know I'm so glad
Wait aw that's so exciting for her
She just got engaged!
Omg no what's going on
Did you hear about Julia?


**Most Active Day with @alexdoe:**

Date of longest conversation with Kate:
2023-2-25
