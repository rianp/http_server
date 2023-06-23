package http_server;

public class Router {

  private final ResponseHandler handler;

  public Router(ResponseHandler requestHandler) {
    this.handler = requestHandler;
  }

  public Response routeRequest(RequestReader request) {
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
          return handler.handleHead();
        }
        return handler.handleNonHead();

      case "/method_options":
        if ("OPTIONS".equals(method)) {
          return handler.handleMethodOptions("allow", "GET, HEAD, OPTIONS");
        }

      case "/method_options2":
        if ("OPTIONS".equals(method)) {
          return handler.handleMethodOptions("allow", "GET, HEAD, OPTIONS, PUT, POST");
        }

      default:
        break;
    }

    return handler.handleUnexpected();
  }
}
