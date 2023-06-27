package http_server;

import java.io.IOException;
import java.util.HashMap;

public class RequestReader {
  private final SocketIO socketIO;
  private String method;
  private String path;
  private HashMap<String, String> headers;
  private String body;

  public RequestReader(SocketIO socketIO) {
    this.socketIO = socketIO;
  }

  public String getPath() {
    return path;
  }

  public String getBody() {
    return body;
  }

  public String getMethod() {
    return method;
  }

  public void readRequest() throws IOException {
    String rawRequest = getStartOfRequest();

    if (!rawRequest.isBlank()) {
      RequestParser parser = new RequestParser(rawRequest);
      method = parser.method();
      path = parser.path();
      headers = parser.headers();

      body = null;
      if (headers != null && headers.containsKey("Content-Length")) {
        int bytes = Integer.parseInt(headers.get("Content-Length"));
        body = getRequestBody(bytes);
      }
    }
  }

  private String getStartOfRequest() throws IOException {
    StringBuilder request = new StringBuilder();
    String line;

    while ((line = socketIO.readLine()) != null) {
      request.append(line).append("\r\n");

      if (line.isEmpty()) {
        break;
      }
    }

    return request.toString();
  }

  private String getRequestBody(int length) throws IOException {
    return socketIO.readBytes(length);
  }

  public HashMap<String, String> getHeaders() {
    return headers;
  }
}
