package http_server;

import java.io.FileWriter;
import java.io.IOException;

public class FileCreator {

  public void createFile(String body) throws IOException {
    String fileName = generateUniqueFileName();
    String filePath = getResourcesDirectoryPath() + fileName;

    FileWriter fileWriter = new FileWriter(filePath);
    fileWriter.write(body);
    fileWriter.close();
  }

  private String generateUniqueFileName() {
    long timestamp = System.currentTimeMillis();
    return "todo_" + timestamp + ".json";
  }

  private String getResourcesDirectoryPath() {
    return "app/src/main/Resources/";
  }

}

