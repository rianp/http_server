package http_server;

public class ResponseHandler {
  Response response = new Response();

  public Response handleSimpleGetWithBody() {
    response.setResponseBody("Hello world");
    return response;
  }

  public Response handleSimpleGet() {
    return response;
  }

  public Response handleEchoBody(String requestBody) {
    response.setResponseBody(requestBody);
    return response;
  }

  public Response handleHead() {
    return response;
  }

  public Response handleNonHead() {
    response.setResponseBody("This body does not show up in a HEAD request");
    return response;
  }

  public Response handleMethodOptions(String method, String options) {
    response.setResponseHeaders(method, options);
    return response;
  }

  public Response handleUnexpected() {
    response.setResponseBody("unexpected request");
    return response;
  }
}
