package entity;

import java.util.ArrayList;

public class CommonUser implements User {

    private String name;

    private String password;

    private final ArrayList<User> friends = new ArrayList<>();

    CommonUser(String name, String password) {
        this.name = name;
        this.password = password;
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

}
