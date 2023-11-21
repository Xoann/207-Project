package entity;

import java.util.ArrayList;

public class CommonUser implements User {

    private String name;
    private String password;
    private String apiKey;
    private final ArrayList<User> friends = new ArrayList<>();

    // TODO can remove public later on. This was added to add dummy data in the DAOs
    public CommonUser(String name, String password, String apiKey) {
        this.name = name;
        this.password = password;
        this.apiKey = apiKey;
    }

    @Override
    public String getUsername() {
        return this.name;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public ArrayList<User> getFriends() {
        return this.friends;
    }

    @Override
    public String getApiKey() { return this.apiKey; }

    @Override
    public void setUsername(String name) {
        this.name=name;
    }

    @Override
    public void setPassword(String password){
        this.password = password;
    }

    // Adding/removing friends is not mutual.
    @Override
    public void addFriend(User friend){
        this.friends.add(friend);
    }

    @Override
    public void removeFriend(User ex_friend){
        this.friends.remove(ex_friend);
    }

    @Override
    public void setApiKey(String apiKey) { this.apiKey = apiKey; }

}
