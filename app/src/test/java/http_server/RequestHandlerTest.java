package http_server;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class RequestHandlerTest {

  @Test
  @DisplayName("should handle simple_get_with_body request")
  void shouldHandleSimpleGetWithBody() {
    RequestHandler handler = new RequestHandler();
    String response = handler.handleSimpleGetWithBody();

    assertThat(response, is(equalTo("Hello world")));
  }

  @Test
  @DisplayName("should handle simple_get request")
  void shouldHandleSimpleGet() {
    RequestHandler handler = new RequestHandler();
    String response = handler.handleSimpleGet();

    assertThat(response, is(equalTo("")));
  }

  @Test
  @DisplayName("should handle echo_body request")
  void shouldHandleEchoBody() {
    RequestHandler handler = new RequestHandler();
    String requestBody = "Request Body";
    String response = handler.handleEchoBody(requestBody);

    assertThat(response, is(equalTo(requestBody)));
  }

  @Test
  @DisplayName("should handle head_request request")
  void shouldHandleHeadRequest() {
    RequestHandler handler = new RequestHandler();
    String response = handler.handleHeadRequest();

    assertThat(response, is(equalTo("")));
  }

  @Test
  @DisplayName("should handle non-head_request request")
  void shouldHandleNonHeadRequest() {
    RequestHandler handler = new RequestHandler();
    String response = handler.handleNonHeadRequest();

    assertThat(response, is(equalTo("This body does not show up in a HEAD request")));
  }

  @Test
  @DisplayName("should handle unexpected request")
  void shouldHandleUnexpectedRequest() {
    RequestHandler handler = new RequestHandler();
    String response = handler.handleUnexpectedRequest();

    assertThat(response, is(equalTo("unexpected request")));
  }
}

