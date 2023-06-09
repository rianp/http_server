package http_server;

public class Request {
  private String request;
  private String path;

  public Request(String request) {
    this.request = request;
    this.path = extractPath(request);
  }

  public String getPath() {
    return path;
  }

  String extractPath(String request) {
    String[] lines = request.split("\\r?\\n");
    if (lines.length > 0) {
      String[] firstLineParts = lines[0].split(" ");
      if (firstLineParts.length >= 2) {
        return firstLineParts[1];
      }
    }
    return "";
  }
}