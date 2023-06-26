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
          return handler.buildSimpleGetWithBodyResponse();

        case "/simple_get":
          if (method.equals("HEAD")) {
            return handler.buildHeadResponse();
          }
          return handler.buildSimpleGetResponse();

        case "/echo_body":
          if ("POST".equals(method)) {
            String body = request.getBody();
            if (body != null) {
              return handler.buildEchoBodyResponse(body);
            }
          }
          break;

        case "/head_request":
          if (method.equals("HEAD")) {
            return handler.buildHeadResponse();
          }
          return handler.buildNonHeadResponse();

        case "/method_options":
          if (method.equals("OPTIONS")) {
            return handler.buildMethodOptionsResponse("allow", "GET, HEAD, OPTIONS");
          }

        case "/method_options2":
          if (method.equals("OPTIONS")) {
            return handler.buildMethodOptionsResponse("allow", "GET, HEAD, OPTIONS, PUT, POST");
          }

        default:
          break;
      }
    }

    return handler.buildUnexpectedResponse();
  }
}
