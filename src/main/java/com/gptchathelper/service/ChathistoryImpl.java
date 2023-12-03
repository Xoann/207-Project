package com.gptchathelper.service;

import com.gptchathelper.data_access.ChathistoryDao;
import com.gptchathelper.pojo.ChathistoryPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;

@Service
public class ChathistoryImpl {

    @Autowired
    ChathistoryDao chathistoryDao;

    public void insertChathistory(String id, String time, String sender, String message) {
        chathistoryDao.insert(new ChathistoryPojo(id, time, sender, message));
    }

    public void deleteChathistoryByID(String id) {
        chathistoryDao.deleteById(id);
    }

    public void updateChathistory(String id, String time, String sender, String message) {
        chathistoryDao.updateById(new ChathistoryPojo(id, time, sender, message));
    }

    public void selectChathistory(String id) {
        chathistoryDao.selectById(id);
    }

    public void selectAllChathistory() {
        chathistoryDao.selectList(null);
    }

    public void deleteAllChathistory() {
        chathistoryDao.delete(null);
    }

    public void selectChathistoryByTime(String time) {
        chathistoryDao.selectByMap(new HashMap<String, Object>() {{
            put("time", time);
        }});
    }

    public Optional<ChathistoryPojo> findLatestMessage() {
        return chathistoryDao.findLatestMessage();
    }

    public String getLatestMessageContent() {
        Optional<ChathistoryPojo> latestMessageOpt = chathistoryDao.findLatestMessage();

        if (latestMessageOpt.isPresent()) {
            ChathistoryPojo latestMessage = latestMessageOpt.get();
            return latestMessage.getContent();
        } else {
            return "No messages available."; // or any default message or handling you prefer
        }
    }
}
