package http_server;

import http_server.routes.Response;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.mock;

public class ResponseHandlerTest {

  @Test
  public void testBuildResponse() {
    RequestReader requestReader = mock(RequestReader.class);
    Router router = mock(Router.class);

    Response response = mock(Response.class);

    Map<String, String> responseHeaders = new HashMap<>();
    responseHeaders.put("Content-Type", "text/plain");
    responseHeaders.put("Server", "Test Server");

    when(router.routeRequest(requestReader)).thenReturn(response);
    when(response.getResponseHeaders()).thenReturn(responseHeaders);
    when(response.getResponseBody()).thenReturn("Hello, world!");

    ResponseHandler responseHandler = new ResponseHandler();

    String actualResponse = responseHandler.buildResponse(requestReader, router);

    assertThat(actualResponse).startsWith("HTTP/1.1 200 OK\r\n");
    assertThat(actualResponse).contains("Content-Type: text/plain\r\n");
    assertThat(actualResponse).contains("Server: Test Server\r\n");
    assertThat(actualResponse).contains("\r\n");
    assertThat(actualResponse).contains("Hello, world!");
  }
}


