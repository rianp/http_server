package http_server;

public class Router {

  private final RequestHandler handler;

  public Router(RequestHandler requestHandler) {
    this.handler = requestHandler;
  }

  public String routeRequest(RequestReader request) {
    String path = request.getPath();
    String method = request.getMethod();

    switch (path) {
      case "/simple_get_with_body":
        return handler.handleSimpleGetWithBody();

      case "/simple_get":
        return handler.handleSimpleGet();

      case "/echo_body":
        if ("POST".equals(method)) {
          return handler.handleEchoBody(request.getBody());
        }
        break;

      case "/head_request":
        if ("HEAD".equals(method)) {
          return handler.handleHeadRequest();
        }
        return handler.handleNonHeadRequest();

      default:
        break;
    }

    return handler.handleUnexpectedRequest();
  }
}
