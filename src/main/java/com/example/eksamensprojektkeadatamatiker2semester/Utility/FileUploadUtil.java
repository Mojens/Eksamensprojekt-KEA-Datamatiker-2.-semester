package com.example.eksamensprojektkeadatamatiker2semester.Utility;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
/* Lavet Af Malthe */
public class FileUploadUtil {

  // Metoden har 3 parameter, en uploadDir, et fileName og en multipartFile
  // uploadDir er selve mappen hvor filen bliver gemt
  // fileName er navnet på filen som bliver gemt i databasen som en String.
  // MultipartFile er en midlertidigt representation af en uploadet fil som er blevet modtaget i en multipart request.
  // Selve filnavnet bliver midlertidigt gemt i filsystemet via MultipartFile interfacen
  // og derefter permanent gemt i databasen i metoden addSpecificDamage i SpecificDamageController klassen.

  // Selve metoden er kun ansvarlig for at oprette mappen, hvis den ikke eksisterer,
  // og gemme den uploadede fil fra MultipartFile-objektet til en fil i filsystemet.
  public static void saveFile(String uploadDir, String fileName,
                              MultipartFile multipartFile) throws IOException {
    Path uploadPath = Paths.get(uploadDir);

    if (!Files.exists(uploadPath)) {
      Files.createDirectories(uploadPath);
    }

    try (InputStream inputStream = multipartFile.getInputStream()) {
      Path filePath = uploadPath.resolve(fileName);
      Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
    } catch (IOException ioe) {
      throw new IOException("Could not save image file: " + fileName, ioe);
    }
  }
}
