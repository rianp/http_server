package http_server;

import jdk.jfr.Description;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class RequestReaderTest {
  @Mock
  private SocketIO socketIO;

  private RequestReader requestReader;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.openMocks(this);
    requestReader = new RequestReader(socketIO);
  }

  @Test
  @Description("Should read nothing when empty request is made")
  public void should_readNothing_when_emptyRequestIsMade() throws IOException {
    when(socketIO.readLine()).thenReturn("").thenReturn(null);

    requestReader.readRequest();

    assertThat(requestReader.getPath()).isNull();
    assertThat(requestReader.getBody()).isNull();
  }


  @Test
  @Description("Should read headers and path but no body when client makes a request with no body")
  public void should_readRequestWithNoBody_when_requestWithRequestWithNoBody() throws IOException {
    String rawRequest = "GET /path HTTP/1.1\r\n"
        + "Host: localhost\r\n"
        + "Content-Length: 10\r\n"
        + "\r\n";

    when(socketIO.readLine()).thenReturn(rawRequest).thenReturn(null);

    requestReader.readRequest();

    assertThat(requestReader.getPath()).isEqualTo("/path");
    assertThat(requestReader.getBody()).isNull();
    assertThat(requestReader.getHeaders()).containsEntry("Host", "localhost");
    assertThat(requestReader.getHeaders()).containsEntry("Content-Length", "10");
  }

  @Test
  @Description("Should read headers, path, and body when client makes a request with body")
  public void should_readRequest_when_requestWithBodyIsGiven() throws IOException {
    String rawRequest = "POST /path HTTP/1.1\r\n"
        + "Host: localhost\r\n"
        + "Content-Length: 10\r\n"
        + "\r\n"
        + "Hello World";

    when(socketIO.readLine()).thenReturn(rawRequest).thenReturn(null);
    when(socketIO.readBytes(10)).thenReturn("Hello World");

    requestReader.readRequest();

    assertThat(requestReader.getPath()).isEqualTo("/path");
    assertThat(requestReader.getBody()).isEqualTo("Hello World");
  }
}



