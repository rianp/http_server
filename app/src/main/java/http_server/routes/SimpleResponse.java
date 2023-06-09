package http_server.routes;

public class SimpleResponse {
  private String response;

  public SimpleResponse (String response) {
    this.response = response;
  }
  public String getResponse() {
    return response;
  }
}
