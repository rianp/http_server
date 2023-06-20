package http_server;

import java.util.Arrays;
import java.util.HashMap;

public class RequestParser {
  private final String rawRequest;

  public RequestParser(String rawRequest) {
    this.rawRequest = rawRequest;
  }

  public String method() {
    return startLine().split(" ")[0];
  }

  public String path() {
    return startLine().split(" ")[1];
  }

  public HashMap<String, String> headers() {
    String requestHead = rawRequest.split("\r\n\r\n")[0];
    String[] headLines = requestHead.split("\r\n");

    HashMap<String, String> mappedHeaders = new HashMap<>();
    String[] headers = Arrays.copyOfRange(headLines, 1, headLines.length);

    for (String line : headers) {
      if (!line.isBlank()) {
        String[] lineData = line.split(": ");
        mappedHeaders.put(lineData[0], lineData[1]);
      }
    }

    return mappedHeaders;
  }

  private String startLine() {
    return rawRequest.split("\r\n")[0];
  }
}
