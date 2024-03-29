package com.example.eksamensprojektkeadatamatiker2semester.Service;

import com.example.eksamensprojektkeadatamatiker2semester.Model.User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
/* Lavet Af Malthe og Mohammed */
@Service
public class ControllerService {

  /*
          Denne process kører igennem hver metode som er i denne klasse
  // Denne metode checker på session om der er en userName gemt
  // hvis der er, så laver den en  user objekt
  // så tjekker den typen på useren, og hvis den har den rigitge type så ændrer den currentpage
  // hvis man ikke har den rigtige type bliver smidt tilbage til login og currentpage ændrer sig
  // hvis den ikke kunne finder userName fra session så bliver du også smidt til login
   */
  public String opretLejeAftaler(HttpSession httpSession) {
    String currentPage = null;
    if (httpSession.getAttribute("userName") != null) {
      User loggedUser = (User) httpSession.getAttribute("user");
      //System.out.println(loggedUser.getType());
      if (loggedUser.getType() == 1 || loggedUser.getType() == 4) {
        currentPage = "opretlejeaftale";
      } else if (loggedUser.getType() != 1 || loggedUser.getType() != 4) {
        currentPage = "login";
      }
    } else if (httpSession.getAttribute("userName") == null) {
      currentPage = "login";
    }
    return currentPage;
  }

  public String alleLejeAftaler(HttpSession httpSession) {
    String currentPage = null;
    if (httpSession.getAttribute("userName") != null) {
      User loggedUser = (User) httpSession.getAttribute("user");
      //System.out.println(loggedUser.getType());
      if (loggedUser.getType() == 1 || loggedUser.getType() == 4) {
        currentPage = "allelejeaftaler";
      } else if (loggedUser.getType() != 1 || loggedUser.getType() != 4) {
        currentPage = "login";
      }
    } else if (httpSession.getAttribute("userName") == null) {
      currentPage = "login";
    }
    return currentPage;
  }

  public String aftaler(HttpSession httpSession) {
    String currentPage = null;
    if (httpSession.getAttribute("userName") != null) {
      User loggedUser = (User) httpSession.getAttribute("user");
      //System.out.println(loggedUser.getType());
      if (loggedUser.getType() == 1 || loggedUser.getType() == 4) {
        currentPage = "aftale";
      } else if (loggedUser.getType() != 1 || loggedUser.getType() != 4) {
        currentPage = "login";
      }
    } else if (httpSession.getAttribute("userName") == null) {
      currentPage = "login";
    }
    return currentPage;
  }

  public String lejeAftaler(HttpSession httpSession) {
    String currentPage = null;
    if (httpSession.getAttribute("userName") != null) {
      User loggedUser = (User) httpSession.getAttribute("user");
      //System.out.println(loggedUser.getType());
      if (loggedUser.getType() == 1 || loggedUser.getType() == 4) {
        currentPage = "lejeaftale";
      } else if (loggedUser.getType() != 1 || loggedUser.getType() != 4) {
        currentPage = "login";
      }
    } else if (httpSession.getAttribute("userName") == null) {
      currentPage = "login";
    }
    return currentPage;
  }

  public String seAftale(HttpSession httpSession) {
    String currentPage = null;
    if (httpSession.getAttribute("userName") != null) {
      User loggedUser = (User) httpSession.getAttribute("user");
      //System.out.println(loggedUser.getType());
      if (loggedUser.getType() == 1 || loggedUser.getType() == 4) {
        currentPage = "seaftale";
      } else if (loggedUser.getType() != 1 || loggedUser.getType() != 4) {
        currentPage = "login";
      }
    } else if (httpSession.getAttribute("userName") == null) {
      currentPage = "login";
    }
    return currentPage;
  }

  //Type 2
  public String udbedring(HttpSession httpSession) {
    String currentPage = null;
    if (httpSession.getAttribute("userName") != null) {
      User loggedUser = (User) httpSession.getAttribute("user");
      //System.out.println(loggedUser.getType());
      if (loggedUser.getType() == 2 || loggedUser.getType() == 4) {
        currentPage = "udbedring";
      } else if (loggedUser.getType() != 2 || loggedUser.getType() != 4) {
        currentPage = "login";
      }
    } else if (httpSession.getAttribute("userName") == null) {
      currentPage = "login";
    }
    return currentPage;
  }

  public String fundetretur(HttpSession httpSession) {
    String currentPage = null;
    if (httpSession.getAttribute("userName") != null) {
      User loggedUser = (User) httpSession.getAttribute("user");
      //System.out.println(loggedUser.getType());
      if (loggedUser.getType() == 2 || loggedUser.getType() == 4) {
        currentPage = "fundetretur";
      } else if (loggedUser.getType() != 2 || loggedUser.getType() != 4) {
        currentPage = "login";
      }
    } else if (httpSession.getAttribute("userName") == null) {
      currentPage = "login";
    }
    return currentPage;
  }

  public String findLease(HttpSession httpSession) {
    String currentPage = null;
    if (httpSession.getAttribute("userName") != null) {
      User loggedUser = (User) httpSession.getAttribute("user");
      //System.out.println(loggedUser.getType());
      if (loggedUser.getType() == 2 || loggedUser.getType() == 4) {
        currentPage = "findlease";
      } else if (loggedUser.getType() != 2 || loggedUser.getType() != 4) {
        currentPage = "login";
      }
    } else if (httpSession.getAttribute("userName") == null) {
      currentPage = "login";
    }
    return currentPage;
  }

  public String findRetur(HttpSession httpSession) {
    String currentPage = null;
    if (httpSession.getAttribute("userName") != null) {
      User loggedUser = (User) httpSession.getAttribute("user");
      //System.out.println(loggedUser.getType());
      if (loggedUser.getType() == 2 || loggedUser.getType() == 4) {
        currentPage = "findretur";
      } else if (loggedUser.getType() != 2 || loggedUser.getType() != 4) {
        currentPage = "login";
      }
    } else if (httpSession.getAttribute("userName") == null) {
      currentPage = "login";
    }
    return currentPage;
  }

  public String skader(HttpSession httpSession) {
    String currentPage = null;
    if (httpSession.getAttribute("userName") != null) {
      User loggedUser = (User) httpSession.getAttribute("user");
      //System.out.println(loggedUser.getType());
      if (loggedUser.getType() == 2 || loggedUser.getType() == 4) {
        currentPage = "skader";
      } else if (loggedUser.getType() != 2 || loggedUser.getType() != 4) {
        currentPage = "login";
      }
    } else if (httpSession.getAttribute("userName") == null) {
      currentPage = "login";
    }
    return currentPage;
  }

  public String registrerFejlOgMangler(HttpSession httpSession) {
    String currentPage = null;
    if (httpSession.getAttribute("userName") != null) {
      User loggedUser = (User) httpSession.getAttribute("user");
      //System.out.println(loggedUser.getType());
      if (loggedUser.getType() == 2 || loggedUser.getType() == 4) {
        currentPage = "skader";
      } else if (loggedUser.getType() != 2 || loggedUser.getType() != 4) {
        currentPage = "login";
      }
    } else if (httpSession.getAttribute("userName") == null) {
      currentPage = "login";
    }
    return currentPage;
  }

  public String skadeRapport(HttpSession httpSession) {
    String currentPage = null;
    if (httpSession.getAttribute("userName") != null) {
      User loggedUser = (User) httpSession.getAttribute("user");
      //System.out.println(loggedUser.getType());
      if (loggedUser.getType() == 2 || loggedUser.getType() == 4) {
        currentPage = "skaderapport";
      } else if (loggedUser.getType() != 2 || loggedUser.getType() != 4) {
        currentPage = "login";
      }
    } else if (httpSession.getAttribute("userName") == null) {
      currentPage = "login";
    }
    return currentPage;
  }

  //Type 4
  public String opretBruger(HttpSession httpSession) {
    String currentPage = null;
    if (httpSession.getAttribute("userName") != null) {
      User loggedUser = (User) httpSession.getAttribute("user");
      //System.out.println(loggedUser.getType());
      if (loggedUser.getType() == 4) {
        currentPage = "opretbruger";
      } else if (loggedUser.getType() != 4) {
        currentPage = "login";
      }
    } else if (httpSession.getAttribute("userName") == null) {
      currentPage = "login";
    }
    return currentPage;
  }

  public String skiftKode(HttpSession httpSession) {
    String currentPage = null;
    if (httpSession.getAttribute("userName") != null) {
      User loggedUser = (User) httpSession.getAttribute("user");
      //System.out.println(loggedUser.getType());
      if (loggedUser.getType() == 4) {
        currentPage = "skiftkode";
      } else if (loggedUser.getType() != 4) {
        currentPage = "login";
      }
    } else if (httpSession.getAttribute("userName") == null) {
      currentPage = "login";
    }
    return currentPage;
  }

  public String sletBruger(HttpSession httpSession) {
    String currentPage = null;
    if (httpSession.getAttribute("userName") != null) {
      User loggedUser = (User) httpSession.getAttribute("user");
      //System.out.println(loggedUser.getType());
      if (loggedUser.getType() == 4) {
        currentPage = "sletbruger";
      } else if (loggedUser.getType() != 4) {
        currentPage = "login";
      }
    } else if (httpSession.getAttribute("userName") == null) {
      currentPage = "login";
    }
    return currentPage;
  }

  //Type 3
  public String dashboard(HttpSession httpSession) {
    String currentPage = null;
    if (httpSession.getAttribute("userName") != null) {
      User loggedUser = (User) httpSession.getAttribute("user");
      //System.out.println(loggedUser.getType());
      if (loggedUser.getType() == 3 || loggedUser.getType() == 4) {
        currentPage = "dashboard";
      } else if (loggedUser.getType() != 3 || loggedUser.getType() != 4) {
        currentPage = "login";
      }
    } else if (httpSession.getAttribute("userName") == null) {
      currentPage = "login";
    }
    return currentPage;
  }


  public String createCar(HttpSession httpSession) {
    String currentPage = null;
    if (httpSession.getAttribute("userName") != null) {
      User loggedUser = (User) httpSession.getAttribute("user");
      //System.out.println(loggedUser.getType());
      if (loggedUser.getType() == 3 || loggedUser.getType() == 4) {
        currentPage = "createcar";
      } else if (loggedUser.getType() != 3 || loggedUser.getType() != 4) {
        currentPage = "login";
      }
    } else if (httpSession.getAttribute("userName") == null) {
      currentPage = "login";
    }
    return currentPage;
  }

  public String ifLoggedReturn(HttpSession httpSession) {
    User loggedUser = (User) httpSession.getAttribute("user");
    if (loggedUser != null) {
      if (loggedUser.getType() == 1) {
        return "redirect:opretlejeaftale";
      } else if (loggedUser.getType() == 2) {
        return "redirect:findlease";
      } else if (loggedUser.getType() == 3) {
        return "redirect:dashboard";
      } else if (loggedUser.getType() == 4) {
        return "redirect:opretbruger";
      }
      return "redirect:login";
    }
    return "login";
  }
  public String lagerOverblik(HttpSession httpSession) {
    String currentPage = null;
    if (httpSession.getAttribute("userName") != null) {
      User loggedUser = (User) httpSession.getAttribute("user");
      //System.out.println(loggedUser.getType());
      if (loggedUser.getType() == 4 || loggedUser.getType() == 1) {
        currentPage = "lageroverblik";
      }
    }else if (httpSession.getAttribute("userName") == null) {
        currentPage = "login";
      }
      return currentPage;

  }
  public String profile(HttpSession httpSession) {
    String currentPage = null;
    if (httpSession.getAttribute("userName") != null) {
      User loggedUser = (User) httpSession.getAttribute("user");
      //System.out.println(loggedUser.getType());
      if (loggedUser.getType() == 1 || loggedUser.getType() == 2 || loggedUser.getType() == 3 || loggedUser.getType() == 4) {
        currentPage = "profile";
      } else if (loggedUser.getType() != 1 || loggedUser.getType() != 2 || loggedUser.getType() != 3 || loggedUser.getType() != 4) {
        currentPage = "login";
      }
    } else if (httpSession.getAttribute("userName") == null) {
      currentPage = "login";
    }
    return currentPage;
  }
  public String profileEdit(HttpSession httpSession) {
    String currentPage = null;
    if (httpSession.getAttribute("userName") != null) {
      User loggedUser = (User) httpSession.getAttribute("user");
      //System.out.println(loggedUser.getType());
      if (loggedUser.getType() == 1 || loggedUser.getType() == 2 || loggedUser.getType() == 3 || loggedUser.getType() == 4) {
        currentPage = "profileEdit";
      } else if (loggedUser.getType() != 1 || loggedUser.getType() != 2 || loggedUser.getType() != 3 || loggedUser.getType() != 4) {
        currentPage = "login";
      }
    } else if (httpSession.getAttribute("userName") == null) {
      currentPage = "login";
    }
    return currentPage;
  }
  public String alleMedarbejdere(HttpSession httpSession) {
    String currentPage = null;
    if (httpSession.getAttribute("userName") != null) {
      User loggedUser = (User) httpSession.getAttribute("user");
      //System.out.println(loggedUser.getType());
      if (loggedUser.getType() == 4) {
        currentPage = "allemedarbejdere";
      } else if (loggedUser.getType() != 4) {
        currentPage = "login";
      }
    } else if (httpSession.getAttribute("userName") == null) {
      currentPage = "login";
    }
    return currentPage;
  }

}
