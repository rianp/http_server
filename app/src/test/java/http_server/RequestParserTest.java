package http_server;

import jdk.jfr.Description;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

public class RequestParserTest {

  @Test
  @Description("Should return the path when a request with a path is given")
  public void should_returnThePath_when_aRequestWithPathIsGiven() {
    String rawRequest = "POST /path HTTP/1.1\r\nHost: balloons.com\r\nContent-Type: cookies/pizza\r\n\r\n";
    RequestParser requestParser = new RequestParser(rawRequest);

    String path = requestParser.path();

    assertThat(path, is(equalTo("/path")));
  }

  @Test
  @Description("Should return the header when a single header is given")
  public void should_returnHeaders_when_aRequestWithSingleHeaderIsGiven() {
    String rawRequest = "POST /path HTTP/1.1\r\nHost: balloons.com\r\nContent-Type: cookies/pizza\r\n\r\n";
    RequestParser requestParser = new RequestParser(rawRequest);

    HashMap<String, String> headers = requestParser.headers();

    assertThat(headers, is(notNullValue()));
    assertThat(headers, hasEntry("Host", "balloons.com"));
    assertThat(headers, hasEntry("Content-Type", "cookies/pizza"));
  }

  @Test
  @Description("Should return the headers when multiple headers are given")
  public void should_returnHeaders_when_RequestWithMultipleHeadersAreGiven() {
    String rawRequest = "GET /path HTTP/1.1\r\nHost: balloons.com\r\nContent-Type: cookies/pizza\r\nUser-Agent: cows\r\n\r\n";
    RequestParser requestParser = new RequestParser(rawRequest);

    HashMap<String, String> headers = requestParser.headers();

    assertThat(headers, is(notNullValue()));
    assertThat(headers, hasEntry("Host", "balloons.com"));
    assertThat(headers, hasEntry("Content-Type", "cookies/pizza"));
    assertThat(headers, hasEntry("User-Agent", "cows"));
  }
}
