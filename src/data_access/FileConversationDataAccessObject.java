package data_access;

import entity.*;
import use_case.login.LoginConversationDataAccessInterface;
import use_case.recommendation.RecommendationConversationDataAccessInterface;
import use_case.send_message.SendMessageConversationDataAccessInterface;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;

public class FileConversationDataAccessObject implements SendMessageConversationDataAccessInterface, RecommendationConversationDataAccessInterface, LoginConversationDataAccessInterface {

    FileUserDataAccessObject userDataAccessObject;
    public FileConversationDataAccessObject(long id) {
        try {
            this.userDataAccessObject = new FileUserDataAccessObject("./users.csv", new CommonUserFactory());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Conversation get(long id) {
        String conversationPath = "conversations/" + id + ".txt";
        Conversation conversation = new Conversation(id);
        try {
            FileReader fileReader = new FileReader(conversationPath);
            BufferedReader reader = new BufferedReader(fileReader);
            String line;
            while ((line = reader.readLine()) != null) {
                String[] content = line.split(",", 3);
                String timeSent = content[0];
                String username = content[1];
                String messageText = content[2];
                User sender = userDataAccessObject.get(username);
                Message message = new CommonMessageFactory().create(messageText, sender, timeSent);
                conversation.sendMessage(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return conversation;
    }

    @Override
    public boolean existsById(long id) {
        Path conversationFilePath = Paths.get("conversations/", id + ".txt");
        return Files.exists(conversationFilePath);
    }

    @Override
    public void save(long id, Message message) {
        String conversationPath = "conversations/" + id + ".txt";
        if (this.existsById(id)) {
            try {
                writeMessage(message, conversationPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Path path = Paths.get(conversationPath);
            try {
                Files.createFile(path);
                writeMessage(message, conversationPath);
            } catch (IOException e) {
                System.err.println("Error creating the file: " + e.getMessage());
            }
        }
    }

    private void writeMessage(Message message, String conversationPath) throws IOException {
        try (FileWriter fileWriter = new FileWriter(conversationPath, true);
             BufferedWriter writer = new BufferedWriter(fileWriter)) {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            String formattedDateTime = message.getTimeSent().format(formatter);
            String logContent = formattedDateTime + "," + message.getSender().getUsername() + "," + message.getMessage() + "\n";

            writer.write(logContent);
        }
    }
}
