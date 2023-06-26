package http_server;

public class ResponseBuilder {

  private final ResponseHandler handler;

  public ResponseBuilder(ResponseHandler requestHandler) {
    this.handler = requestHandler;
  }

  public Response buildResponse(RequestReader request) {
    String path = request.getPath();
    String method = request.getMethod();

    if (path != null && method != null) {
      switch (path) {
        case "/simple_get_with_body":
          return handler.handleSimpleGetWithBody();

        case "/simple_get":
          return handler.handleSimpleGet();

        case "/echo_body":
          if ("POST".equals(method)) {
            String body = request.getBody();
            if (body != null) {
              return handler.handleEchoBody(body);
            }
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
    }

    return handler.handleUnexpected();
  }
}
