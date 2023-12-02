package data_access;

import entity.User;
import entity.UserFactory;
import use_case.recommendation.RecommendationUserDataAccessInterface;
import use_case.send_message.SendMessageUserDataAccessInterface;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class FileUserDataAccessObject implements RecommendationUserDataAccessInterface, SendMessageUserDataAccessInterface {

    private final File csvFile;

    private final Map<String, Integer> headers = new LinkedHashMap<>();

    private final Map<String, User> accounts = new HashMap<>();

    private UserFactory userFactory;

    public FileUserDataAccessObject(String csvPath, UserFactory userFactory) throws IOException {
        this.userFactory = userFactory;

        csvFile = new File(csvPath);
        headers.put("username", 0);
        headers.put("password", 1);
        headers.put("api_key", 2);

        if (!csvFile.exists() || csvFile.length() == 0) {
            initializeDefaultData();
        } else {
            loadUsersFromFile();
        }
    }

    private void initializeDefaultData() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFile))) {
            writer.write("username,password,api_key");
            writer.newLine();
            writer.write("test,test,put_key_here");
            writer.newLine();
            writer.write("test1,test1,put_key_here");
            writer.newLine();
            writer.write("test2,test2,put_key_here");
            writer.newLine();
        }
    }

    private void loadUsersFromFile() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
            String header = reader.readLine();

            assert header.equals("username,password,api_key");

            String row;
            while ((row = reader.readLine()) != null) {
                String[] col = row.split(",");
                String username = String.valueOf(col[headers.get("username")]);
                String password = String.valueOf(col[headers.get("password")]);
                String key = String.valueOf(col[headers.get("api_key")]);
                User user = userFactory.create(username, password, key);
                accounts.put(username, user);
            }
        }
    }


    public void save(User user) {
        accounts.put(user.getUsername(), user);
        this.save();
    }

    public void delete(User user) {
        accounts.remove(user.getUsername(), user);
        this.save();
    }

    @Override
    public User get(String username) {
        return accounts.get(username);
    }

    /**
     * Return whether a user exists with username identifier.
     * @param identifier the username to check.
     * @return whether a user exists with username identifier
     */
    @Override
    public boolean existsByUsername(String identifier) {
        return accounts.containsKey(identifier);
    }

    private void save() {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(csvFile));
            writer.write(String.join(",", headers.keySet()));
            writer.newLine();

            for (User user : accounts.values()) {
                String line = String.format("%s,%s,%s",
                        user.getUsername(), user.getPassword(), user.getApiKey());
                writer.write(line);
                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

