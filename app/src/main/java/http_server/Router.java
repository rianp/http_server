package http_server;
import http_server.routes.SimpleResponse;

public class Router {

  public String routeRequest(Request request) {
    if ("/simple_get_with_body".equals(request.getPath())) {
      SimpleResponse body = new SimpleResponse("Hello World");
      return body.getResponse();
    }

    return "";
  }
}
