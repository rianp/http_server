package http_server;

import java.util.Map;

public class ResponseFormatter {

  public String format(Response response) {
    StringBuilder responseBuilder = new StringBuilder();
    responseBuilder.append("HTTP/1.1 " + response.getResponseStatus() + "\r\n");

    for (Map.Entry<String, String> entry : response.getResponseHeaders().entrySet()) {
      responseBuilder
          .append(entry.getKey())
          .append(": ")
          .append(entry.getValue()).append("\r\n");
    }

    responseBuilder.append("\r\n");
    responseBuilder.append(response.getResponseBody());
    return responseBuilder.toString();
  }
}
