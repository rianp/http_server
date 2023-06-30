package http_server;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ResponseFormatterTest {

  @Test
  public void testBuildResponse() {

    Response response = mock(Response.class);

    Map<String, String> responseHeaders = new HashMap<>();
    responseHeaders.put("Content-Type", "text/plain");
    responseHeaders.put("Server", "Test Server");

    when(response.getResponseHeaders()).thenReturn(responseHeaders);
    when(response.getResponseStatus()).thenReturn("200 OK");
    when(response.getResponseBody()).thenReturn("Hello, world!");

    ResponseFormatter responseFormatter = new ResponseFormatter();

    String actualResponse = responseFormatter.format(response);

    assertThat(actualResponse).startsWith("HTTP/1.1 200 OK\r\n");
    assertThat(actualResponse).contains("Content-Type: text/plain\r\n");
    assertThat(actualResponse).contains("Server: Test Server\r\n");
    assertThat(actualResponse).contains("\r\n");
    assertThat(actualResponse).contains("Hello, world!");
  }
}


