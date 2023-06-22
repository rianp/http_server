package http_server;

import http_server.routes.Response;

import java.util.Map;

public class ResponseHandler {

  public String buildResponse(RequestReader httpRequest, Router router) {
    Response response = router.routeRequest(httpRequest);

    StringBuilder responseBuilder = new StringBuilder();
    responseBuilder.append("HTTP/1.1 200 OK\r\n");

    for (Map.Entry<String, String> entry : response.getResponseHeaders().entrySet()) {
      responseBuilder.append(entry.getKey()).append(": ").append(entry.getValue()).append("\r\n");
    }

    responseBuilder.append("\r\n");
    responseBuilder.append(response.getResponseBody());

    return responseBuilder.toString();
  }
}


