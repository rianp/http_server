package http_server;

import http_server.routes.SimpleResponse;

public class Router {

  public String routeRequest(RequestReader request) {
    if ("/simple_get_with_body".equals(request.getPath())) {
      SimpleResponse body = new SimpleResponse("Hello world");
      return body.getResponse();
    } else if ("/echo_body".equals(request.getPath())) {
      SimpleResponse body = new SimpleResponse(request.getBody());
      return body.getResponse();
    } else if ("/head_request".equals(request.getPath())) {
      SimpleResponse body = new SimpleResponse("This body does not show up in a HEAD request");
      return body.getResponse();
    }

    return "";
  }
}
