package http_server;

public class ResponseBuilder {

  private final ResponseHandler handler;
  private final Router router;

  public ResponseBuilder(ResponseHandler requestHandler, Router router) {
    this.handler = requestHandler;
    this.router = router;
  }

  public Response buildResponse(RequestReader request) {
    String path = request.getPath();
    String method = request.getMethod();

    if (path != null && method != null) {
      switch (path) {
        case "/simple_get_with_body":
          if (router.isValuePresent(path, method)) {
            return handler.buildSimpleGetWithBodyResponse("200");
          }
          return handler.buildMethodOptionsResponse("allow", "GET, HEAD, OPTIONS", "405");

        case "/simple_get":
          if (router.isValuePresent(path, method)) {
            if (method.equals("HEAD")) {
              return handler.buildHeadResponse("200");
            }
            return handler.buildSimpleGetResponse("200");
          }
          return handler.buildMethodOptionsResponse("allow", "GET, HEAD, OPTIONS", "405");

        case "/echo_body":
          if (method.equals("POST")) {
            String body = request.getBody();
            if (body != null) {
              return handler.buildEchoBodyResponse(body, "200");
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
            return handler.buildMethodOptionsResponse("location", "http://0.0.0.0:8080/simple_get", "301");
          }

        default:
          break;
      }
    }

    return handler.buildUnexpectedResponse("404");
  }
}
