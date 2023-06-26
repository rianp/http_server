package http_server;

public class ResponseHandler {
  Response response = new Response();

  public Response buildSimpleGetWithBodyResponse() {
    response.setResponseBody("Hello world");
    return response;
  }

  public Response buildSimpleGetResponse() {
    return response;
  }

  public Response buildEchoBodyResponse(String requestBody) {
    response.setResponseBody(requestBody);
    return response;
  }

  public Response buildHeadResponse() {
    response.setResponseBody("");
    return response;
  }

  public Response buildNonHeadResponse() {
    response.setResponseBody("This body does not show up in a HEAD request");
    return response;
  }

  public Response buildMethodOptionsResponse(String method, String options) {
    response.setResponseHeaders(method, options);
    response.setResponseBody("");
    return response;
  }

  public Response buildUnexpectedResponse() {
    response.setResponseBody("unexpected request");
    return response;
  }
}
