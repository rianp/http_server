package http_server;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class RequestTest {
  private Request request;

  @BeforeEach
  public void setUp() {
    String sampleRequest = "GET /example/path HTTP/1.1\r\n" +
        "Host: www.example.com\r\n" +
        "Connection: keep-alive\r\n\r\n";
    request = new Request(sampleRequest);
  }

  @Test
  @DisplayName("should return the path asked for when a user inputs a valid path")
  public void should_GetValidPath_When_ValidPathIsRequested() {
    String expectedPath = "/example/path";
    String actualPath = request.getPath();

    assertThat(actualPath, equalTo(expectedPath));
  }

}
