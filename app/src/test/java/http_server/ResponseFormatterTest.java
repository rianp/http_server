package http_server;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.startsWith;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

public class ResponseFormatterTest {

  @Test
  public void testBuildResponse() {

    Response response = mock(Response.class);

    Map<String, String> responseHeaders = new HashMap<>();
    responseHeaders.put("Content-Type", "text/plain");
    responseHeaders.put("Server", "Test Server");

    when(response.getResponseHeaders()).thenReturn(responseHeaders);
    when(response.getResponseBody()).thenReturn("Hello, world!");

    ResponseFormatter formattedResponse = new ResponseFormatter();

    String actualResponse = formattedResponse.formatResponse(response);

    assertThat(actualResponse, startsWith("HTTP/1.1 200 OK\r\n"));
    assertThat(actualResponse, containsString("Content-Type: text/plain\r\n"));
    assertThat(actualResponse, containsString("Server: Test Server\r\n"));
    assertThat(actualResponse, containsString("\r\n"));
    assertThat(actualResponse, containsString("Hello, world!"));
  }
}

