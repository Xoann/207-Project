package com.gptchathelper.controller;

import com.gptchathelper.model.ChatMessage;
import com.gptchathelper.service.ChathistoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import com.theokanning.openai.service.OpenAiService;
import com.theokanning.openai.completion.CompletionRequest;

import static com.gptchathelper.model.ChatMessage.MessageType.CHAT;


@Controller
public class ChatController {

    @Autowired
    ChathistoryImpl chathistoryService;

    @Value("${spring.openai.apikey}")
    private String openai_apikey;

    @MessageMapping("/chat.register")
    @SendTo("/topic/public")
    public ChatMessage register(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }

    @MessageMapping("/chat.send")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        chathistoryService.insertChathistory(chatMessage.generateId(), chatMessage.getCurrentTime()
                , chatMessage.getSender(), chatMessage.getContent());
        return chatMessage;
    }

    @MessageMapping("/chat.generateResponse")
    @SendTo("/topic/public")
    public ChatMessage generateResponse(@Payload ChatMessage chatMessage) {

        String latestMessage = chathistoryService.getLatestMessageContent(); // Use only the latest message for the prompt

        OpenAiService service = new OpenAiService(openai_apikey);

        // Craft a more engaging and contextual prompt
        String helperPrompt = "As a chat helper, provide a supportive and concise one-sentence response to the following message: \"" + latestMessage + "\"";

        CompletionRequest completionRequest = CompletionRequest.builder()
                .prompt(helperPrompt)
                .model("text-davinci-003") // Replace with your desired model
                .build();

        try {
            String response = service.createCompletion(completionRequest).getChoices().get(0).getText();
            chatMessage.setContent(response);
            chatMessage.setType(CHAT); // Set the type of the message, if needed
            chatMessage.setSender(chatMessage.getSender()); // Set the sender as ChatGPT or similar
            // Set other necessary fields of chatMessage here, if there are any
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception, possibly by setting an error message in chatMessage
            chatMessage.setContent("An error occurred while generating a response.");
            chatMessage.setType(CHAT);
        }

        return chatMessage;
    }

}
