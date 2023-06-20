package http_server;
import http_server.routes.Response;

public class RequestHandler {

  public String handleSimpleGetWithBody() {
    Response body = new Response("Hello world");
    return body.getResponse();
  }

  public String handleSimpleGet() {
    Response body = new Response("");
    return body.getResponse();
  }

  public String handleEchoBody(String requestBody) {
    Response body = new Response(requestBody);
    return body.getResponse();
  }

  public String handleHeadRequest() {
    Response body = new Response("");
    return body.getResponse();
  }

  public String handleNonHeadRequest() {
    Response body = new Response("This body does not show up in a HEAD request");
    return body.getResponse();
  }

  public String handleUnexpectedRequest() {
    Response body = new Response("unexpected request");
    return body.getResponse();
  }
}
