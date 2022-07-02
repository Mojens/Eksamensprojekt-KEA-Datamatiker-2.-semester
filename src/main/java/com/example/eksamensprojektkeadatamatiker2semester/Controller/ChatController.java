package com.example.eksamensprojektkeadatamatiker2semester.Controller;


import com.example.eksamensprojektkeadatamatiker2semester.Model.ChatMessage;
import com.example.eksamensprojektkeadatamatiker2semester.Model.Employee;
import com.example.eksamensprojektkeadatamatiker2semester.Model.User;
import com.example.eksamensprojektkeadatamatiker2semester.Repository.EmployeeRepository;
import com.example.eksamensprojektkeadatamatiker2semester.Service.ControllerService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class ChatController {

  ControllerService controllerService;
  EmployeeRepository employeeRepository;

  public ChatController(ControllerService controllerService,
                        EmployeeRepository employeeRepository){
    this.controllerService = controllerService;
    this.employeeRepository = employeeRepository;
  }



  @GetMapping("/chat")
  public String index(HttpSession httpSession,
                      Model model){
    User user = (User) httpSession.getAttribute("user");
    model.addAttribute("user",user);
    Employee employee = employeeRepository.findEmployeeByUserID(user.getUserID());
    model.addAttribute("profile",employee);
    model.addAttribute("pagetitle","Bilabonnement - Portalen Chat");
    return controllerService.chat(httpSession);
  }


  @MessageMapping("/chat.register")
  @SendTo("/topic/public")
  public ChatMessage register(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
    headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
    return chatMessage;
  }

  @MessageMapping("/chat.send")
  @SendTo("/topic/public")
  public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
    return chatMessage;
  }

}

