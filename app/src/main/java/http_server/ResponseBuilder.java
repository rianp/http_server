package http_server;

public class ResponseBuilder {

  private final ResponseHandler handler;
  private final RouteValidator routeValidator;

  public ResponseBuilder(ResponseHandler requestHandler, RouteValidator routeValidator) {
    this.handler = requestHandler;
    this.routeValidator = routeValidator;
  }

  public Response buildResponse(RequestReader request) {
    String path = request.getPath();
    String method = request.getMethod();

    if (path != null && method != null) {
      switch (path) {
        case "/simple_get_with_body":
          if (routeValidator.hasMethod(path, method)) {
            return handler.buildResponse("200", "Hello world!");
          }
          return handler.buildMethodOptionsResponse("allow", "GET, HEAD, OPTIONS", "405");

        case "/simple_get":
          if (routeValidator.hasMethod(path, method)) {
            if (method.equals("HEAD")) {
              return handler.buildHeadResponse("200");
            }
            return handler.buildResponse("200", "");
          }
          return handler.buildMethodOptionsResponse("allow", "GET, HEAD, OPTIONS", "405");

        case "/echo_body":
          if (method.equals("POST")) {
            String body = request.getBody();
            if (body != null) {
              return handler.buildResponse("200", body);
            }
          }
          break;

        case "/head_request":
          if (method.equals("HEAD")) {
            return handler.buildHeadResponse("200");
          }
          return handler.buildNonHeadResponse("200");

        case "/method_options":
          if (method.equals("OPTIONS")) {
            return handler.buildMethodOptionsResponse("allow", "GET, HEAD, OPTIONS", "200");
          }

        case "/method_options2":
          if (method.equals("OPTIONS")) {
            return handler.buildMethodOptionsResponse("allow", "GET, HEAD, OPTIONS, PUT, POST", "200");
          }

        case "/redirect":
          if (method.equals("GET")) {
            return handler.buildRedirectResponse("location", "http://127.0.0.1:8080/simple_get", "301");
          }

        default:
          break;
      }
    }

    return handler.buildUnexpectedResponse("404");
  }
}
