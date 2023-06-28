package http_server;

public class ResponseHandler {
  Response response = new Response();

  public Response buildSimpleGetWithBodyResponse(String status) {
    response.setResponseStatus(status);
    response.setResponseBody("Hello world");
    return response;
  }

  public Response buildSimpleGetResponse(String status) {
    response.setResponseStatus(status);
    response.setResponseBody("");
    return response;
  }

  public Response buildEchoBodyResponse(String requestBody, String status) {
    response.setResponseStatus(status);
    response.setResponseBody(requestBody);
    return response;
  }

  public Response buildHeadResponse(String status) {
    response.setResponseStatus(status);
    response.setResponseBody("");
    return response;
  }

  public Response buildNonHeadResponse(String status) {
    response.setResponseStatus(status);
    response.setResponseBody("This body does not show up in a HEAD request");
    return response;
  }

  public Response buildMethodOptionsResponse(String method, String options, String status) {
    response.setResponseStatus(status);
    response.setResponseHeaders(method, options);
    response.setResponseBody("");
    return response;
  }

  public Response buildUnexpectedResponse(String status) {
    response.setResponseStatus(status);
    response.setResponseBody("unexpected request");
    return response;
  }
}
