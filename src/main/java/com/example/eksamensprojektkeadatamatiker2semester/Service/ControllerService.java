package com.example.eksamensprojektkeadatamatiker2semester.Service;

import com.example.eksamensprojektkeadatamatiker2semester.Model.User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class ControllerService {

  public String registrerLejeAftaler(HttpSession httpSession) {
    String currentPage = null;
    if (httpSession.getAttribute("userName") != null) {
      User loggedUser = (User) httpSession.getAttribute("user");
      System.out.println(loggedUser.getType());
      if (loggedUser.getType() == 1 || loggedUser.getType() == 4) {
        currentPage = "/registrerLejeAftaler";
      } else if (loggedUser.getType() != 1 || loggedUser.getType() != 4) {
        currentPage = "/login";
      }
    } else if (httpSession.getAttribute("userName") == null) {
      currentPage = "/login";
    }
    return currentPage;
  }

  public String registrerFejlOgMangel(HttpSession httpSession) {
    String currentPage = null;
    if (httpSession.getAttribute("userName") != null) {
      User loggedUser = (User) httpSession.getAttribute("user");
      System.out.println(loggedUser.getType());
      if (loggedUser.getType() == 2 || loggedUser.getType() == 4) {
        currentPage = "/registrerFejlOgMangel";
      } else if (loggedUser.getType() != 2 || loggedUser.getType() != 4) {
        currentPage = "/login";
      }
    } else if (httpSession.getAttribute("userName") == null) {
      currentPage = "/login";
    }
    return currentPage;
  }

  public String skader(HttpSession httpSession) {
    String currentPage = null;
    if (httpSession.getAttribute("userName") != null) {
      User loggedUser = (User) httpSession.getAttribute("user");
      System.out.println(loggedUser.getType());
      if (loggedUser.getType() == 2 || loggedUser.getType() == 4) {
        currentPage = "/skader";
      } else if (loggedUser.getType() != 2 || loggedUser.getType() != 4) {
        currentPage = "/login";
      }
    } else if (httpSession.getAttribute("userName") == null) {
      currentPage = "/login";
    }
    return currentPage;
  }

  public String skadeRapport(HttpSession httpSession) {
    String currentPage = null;
    if (httpSession.getAttribute("userName") != null) {
      User loggedUser = (User) httpSession.getAttribute("user");
      System.out.println(loggedUser.getType());
      if (loggedUser.getType() == 2 || loggedUser.getType() == 4) {
        currentPage = "/skaderapport";
      } else if (loggedUser.getType() != 2 || loggedUser.getType() != 4) {
        currentPage = "/login";
      }
    } else if (httpSession.getAttribute("userName") == null) {
      currentPage = "/login";
    }
    return currentPage;
  }

  public String admin(HttpSession httpSession) {
    String currentPage = null;
    if (httpSession.getAttribute("userName") != null) {
      User loggedUser = (User) httpSession.getAttribute("user");
      System.out.println(loggedUser.getType());
      if (loggedUser.getType() == 4) {
        currentPage = "/admin";
      } else if (loggedUser.getType() != 4) {
        currentPage = "/login";
      }
    } else if (httpSession.getAttribute("userName") == null) {
      currentPage = "/login";
    }
    return currentPage;
  }

  public String dashboard(HttpSession httpSession) {
    String currentPage = null;
    if (httpSession.getAttribute("userName") != null) {
      User loggedUser = (User) httpSession.getAttribute("user");
      System.out.println(loggedUser.getType());
      if (loggedUser.getType() == 3 || loggedUser.getType() == 4) {
        currentPage = "/dashboard";
      } else if (loggedUser.getType() != 3 || loggedUser.getType() != 4) {
        currentPage = "/login";
      }
    } else if (httpSession.getAttribute("userName") == null) {
      currentPage = "/login";
    }
    return currentPage;
  }


}
