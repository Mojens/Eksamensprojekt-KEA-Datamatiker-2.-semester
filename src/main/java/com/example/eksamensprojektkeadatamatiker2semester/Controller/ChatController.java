package com.example.eksamensprojektkeadatamatiker2semester.Controller;


import com.example.eksamensprojektkeadatamatiker2semester.Model.ChatMessage;
import com.example.eksamensprojektkeadatamatiker2semester.Model.User;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class ChatController {

  @GetMapping("/chat")
  public String index(){
    return "index";
  }


  @MessageMapping("/chat.register")
  @SendTo("/topic/public")
  public ChatMessage register(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor, HttpSession httpSession) {
    User loggedUser = (User) httpSession.getAttribute("user");
    headerAccessor.getSessionAttributes().put(loggedUser.getUsername(), chatMessage.getSender());
    return chatMessage;
  }

  @MessageMapping("/chat.send")
  @SendTo("/topic/public")
  public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
    return chatMessage;
  }

}

