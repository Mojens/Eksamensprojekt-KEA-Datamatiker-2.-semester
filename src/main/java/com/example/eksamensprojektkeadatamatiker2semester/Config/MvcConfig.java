package com.example.eksamensprojektkeadatamatiker2semester.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;
/* Lavet Af Malthe */
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    // Metoden gemmer registreringer af resourceHandlers til at betjene statiske ressourcer såsom billeder
    // gennem Spring MVC, samt indstilling af cache-headers som er optimeret til effektiv indlæsning i en webbrowser.
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        exposeDirectory("user-photos", registry);
        exposeDirectory("profile-photos",registry);
    }



    // Vi er nødt til at expose den mappe, der indeholder de uploadede filer, så klienterne (webbrowsere) kan få adgang til denne mappe.
    // Her konfigurerer vi Spring MVC til at give adgang til mappen /user-photos i filsystemet,
    // med tilknytning til applikationens context path som er /user-photos.
    // context path er et navn, som en webapplikation tilgås med. Det er roden til applikationen.
    private void exposeDirectory(String dirName, ResourceHandlerRegistry registry) {
        Path uploadDir = Paths.get(dirName);
        String uploadPath = uploadDir.toFile().getAbsolutePath();

        if (dirName.startsWith("../")) dirName = dirName.replace("../", "");

        registry.addResourceHandler("/" + dirName + "/**").addResourceLocations("file://"+ uploadPath + "/");
    }
}