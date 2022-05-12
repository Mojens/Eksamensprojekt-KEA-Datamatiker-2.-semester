package com.example.eksamensprojektkeadatamatiker2semester.Controller;


import com.example.eksamensprojektkeadatamatiker2semester.Service.ControllerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {
  ControllerService controllerService;

  public HomeController(ControllerService controllerService){
    this.controllerService = controllerService;
  }

  @GetMapping("/registrerLejeAftaler")
  public String registrerLejeAftaler(HttpSession httpSession){
    return controllerService.registrerLejeAftaler(httpSession);
  }

  @GetMapping("/registrerFejlOgMangel")
  public String registrerFejlOgMangel(HttpSession httpSession){
    return controllerService.registrerFejlOgMangel(httpSession);
  }

  @GetMapping("/showKPI")
  public String showKPI(HttpSession httpSession){
    return controllerService.showKPI(httpSession);
  }

  @GetMapping("/admin")
  public String admin(HttpSession httpSession){
    return controllerService.admin(httpSession);
  }

  @GetMapping("/logout")
  public String logOut(HttpSession httpSession){
    httpSession.removeAttribute("userName");
    return "/login";
  }

  @GetMapping("/login")
  public String login(){
    return "/login";
  }

  @GetMapping("/")
  public String index(){
    return "/login";
  }

}
