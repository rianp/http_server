package http_server;

import http_server.routes.Response;

public class Router {

  private final Response response = new Response();

  public Response routeRequest(RequestReader request) {
    String path = request.getPath();
    String method = request.getMethod();

    switch (path) {
      case "/simple_get_with_body":
        response.setResponseBody("Hello world");
        return response;

      case "/simple_get":
        return response;

      case "/echo_body":
        if (method.equals("POST")) {
          response.setResponseBody(request.getBody());
          return response;
        }
        break;

      case "/head_request":
        if ("HEAD".equals(method)) {
          return response;
        }
        response.setResponseBody("This body does not show up in a HEAD request");
        return response;

      case "/method_options":
        if ("OPTIONS".equals(method)) {
          response.setResponseHeaders("allow", "GET, HEAD, OPTIONS");
          return response;
        }

      case "/method_options2":
        if ("OPTIONS".equals(method)) {
          response.setResponseHeaders("allow", "GET, HEAD, OPTIONS, PUT, POST");
          return response;
        }

      default:
        break;
    }

    response.setResponseBody("unexpected request");
    return response;
  }
}
