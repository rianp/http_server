package http_server;

import java.io.IOException;
import java.util.HashMap;

public class Request {
  private final SocketIO socketIO;
  private Path path;
  private Method method;
  private HashMap<String, String> headers;
  private String body;

  public Request(SocketIO socketIO) {
    this.socketIO = socketIO;
  }

  public Path getPath() {
    return path;
  }

  public String getBody() {
    return body;
  }

  public void readRequest() throws IOException {
    String rawRequest = getRequestHead();

    if (!rawRequest.isBlank()) {
      RequestParser parser = new RequestParser(rawRequest);
      method = parser.method();
      path = parser.path();
      headers = parser.headers();

      body = null;
      if (headers.containsKey("Content-Length")) {
        int bytes = Integer.parseInt(headers.get("Content-Length"));
        body = getRequestBody(bytes);
      }
    }
  }

  private String getRequestHead() throws IOException {
    StringBuilder request = new StringBuilder();
    String line;

    while ((line = socketIO.readLine()) != null) {
      request.append(line).append("\r\n");

      if (line.isBlank()) {
        break;
      }
    }

    return request.toString();
  }

  private String getRequestBody(int length) throws IOException {
    return socketIO.readBytes(length);
  }
}