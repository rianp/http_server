package http_server.routes;

public class Response {
  private String response;

  public Response (String response) {
    this.response = response;
  }
  public String getResponse() {
    return response;
  }
}