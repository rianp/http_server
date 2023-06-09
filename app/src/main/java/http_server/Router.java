package http_server;
import http_server.routes.HelloWorld;

public class Router {

  public String handleRequest(Request request) {
    if ("/simple_get_with_body".equals(request.getPath())) {
      HelloWorld body = new HelloWorld();
      return body.getHelloWorld();
    }

    return "";
  }
}
