package http_server;

import java.util.HashMap;
import java.util.Map;

public class Response {
  private String responseStatus;
  private final Map<String, String> responseHeaders;
  private String responseBody;

  public Response () {
    this.responseStatus = "";
    this.responseHeaders = new HashMap<>();
    this.responseBody = "";
  }

  public String getResponseStatus() { return responseStatus; }

  public Map<String, String> getResponseHeaders() {
    return responseHeaders;
  }

  public String getResponseBody() {
    return responseBody;
  }

  public void setResponseStatus(String status) {
    responseStatus = status;
  }

  public void setResponseHeaders(String headerName, String headerValue) {
    responseHeaders.put(headerName, headerValue);
  }

  public void setResponseBody(String body) {
    responseBody = body;
  }

}