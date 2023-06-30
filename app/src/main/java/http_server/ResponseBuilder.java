package http_server;
public class ResponseBuilder {

  private final ResponseHandler handler;
  private final RouteValidator routeValidator;

  public ResponseBuilder(ResponseHandler responseHandler, RouteValidator routeValidator) {
    this.handler = responseHandler;
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
          return handleSimpleGetWithBodyResponse(method);
        }
        case "/simple_get" -> {
          return handleSimpleGetResponse(method);
        }
        case "/echo_body" -> {
          return handleEchoBodyResponse(method, request.getBody());
        }
        case "/head_request" -> {
          return handleHeadRequestResponse(method);
        }
        case "/method_options" -> {
          return handleMethodOptionsResponse(method, "GET, HEAD, OPTIONS");
        }
        case "/method_options2" -> {
          return handleMethodOptionsResponse(method, "GET, HEAD, OPTIONS, PUT, POST");
        }
        case "/redirect" -> {
          return handleRedirectResponse(method);
        }
        default -> {
        }
      }
    }

    return handler.buildUnexpectedResponse("405");
  }

  private Response handleSimpleGetWithBodyResponse(String method) {
    if (method.equals("HEAD")) {
      return handler.buildHeadResponse("200");
    } else {
      return handler.buildResponse("200", "Hello world");
    }
  }

  private Response handleSimpleGetResponse(String method) {
    if (method.equals("HEAD")) {
      return handler.buildHeadResponse("200");
    } else {
      return handler.buildResponse("200", "");
    }
  }

  private Response handleEchoBodyResponse(String method, String body) {
    if (method.equals("POST") && body != null) {
      return handler.buildResponse("200", body);
    }
    return handler.buildUnexpectedResponse("405");
  }

  private Response handleHeadRequestResponse(String method) {
    if (method.equals("HEAD")) {
      return handler.buildHeadResponse("200");
    } else {
      return handler.buildNonHeadResponse("200");
    }
  }

  private Response handleMethodOptionsResponse(String method, String allowedMethods) {
    if (method.equals("OPTIONS")) {
      return handler.buildMethodOptionsResponse("allow", allowedMethods, "200");
    }
    return handler.buildUnexpectedResponse("405");
  }

  private Response handleRedirectResponse(String method) {
    if (method.equals("GET")) {
      return handler.buildRedirectResponse("location", "http://127.0.0.1:8080/simple_get", "301");
    }
    return handler.buildUnexpectedResponse("405");
  }
}


