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

    if (!routeValidator.hasPath(path)) {
      return handler.buildUnexpectedResponse("404");
    }

    if (!routeValidator.hasMethod(path, method)) {
      return handler.buildMethodOptionsResponse("allow", "GET, HEAD, OPTIONS", "405");
    }

    if (path != null && method != null) {
      switch (path) {
        case "/simple_get_with_body" -> {
          return buildSimpleGetWithBodyResponse(method);
        }
        case "/simple_get" -> {
          return buildSimpleGetResponse(method);
        }
        case "/echo_body" -> {
          return buildEchoBodyResponse(method, request.getBody());
        }
        case "/head_request" -> {
          return buildHeadRequestResponse(method);
        }
        case "/method_options" -> {
          return buildMethodOptionsResponse(method, "GET, HEAD, OPTIONS");
        }
        case "/method_options2" -> {
          return buildMethodOptionsResponse(method, "GET, HEAD, OPTIONS, PUT, POST");
        }
        case "/redirect" -> {
          return buildRedirectResponse(method);
        }
        default -> {
        }
      }
    }

    return handler.buildUnexpectedResponse("405");
  }

  private Response buildSimpleGetWithBodyResponse(String method) {
    if (method.equals("HEAD")) {
      return handler.buildHeadResponse("200");
    } else {
      return handler.buildResponse("200", "Hello world");
    }
  }

  private Response buildSimpleGetResponse(String method) {
    if (method.equals("HEAD")) {
      return handler.buildHeadResponse("200");
    } else {
      return handler.buildResponse("200", "");
    }
  }

  private Response buildEchoBodyResponse(String method, String body) {
    if (method.equals("POST") && body != null) {
      return handler.buildResponse("200", body);
    }
    return handler.buildUnexpectedResponse("405");
  }

  private Response buildHeadRequestResponse(String method) {
    if (method.equals("HEAD")) {
      return handler.buildHeadResponse("200");
    } else {
      return handler.buildNonHeadResponse("200");
    }
  }

  private Response buildMethodOptionsResponse(String method, String allowedMethods) {
    if (method.equals("OPTIONS")) {
      return handler.buildMethodOptionsResponse("allow", allowedMethods, "200");
    }
    return handler.buildUnexpectedResponse("405");
  }

  private Response buildRedirectResponse(String method) {
    if (method.equals("GET")) {
      return handler.buildRedirectResponse("location", "http://127.0.0.1:8080/simple_get", "301");
    }
    return handler.buildUnexpectedResponse("405");
  }
}


