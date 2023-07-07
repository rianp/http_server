package http_server;

import java.util.Map;

public class ResponseHandler {
  Response response = new Response();

  public Response buildResponse( String status, String responseBody) {
    response.setResponseStatus(status);
    response.setResponseBody(responseBody);
    return response;
  }

  public Response buildResourceResponse(String method, String options, String status, String responseBody) {
    response.setResponseStatus(status);
    response.setResponseHeaders(method, options);
    response.setResponseBody(responseBody);
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

  public Response buildRedirectResponse(String method, String options, String status) {
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
