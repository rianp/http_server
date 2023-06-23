package http_server;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ResponseHandlerTest {

  @Test
  @DisplayName("should handle simple_get_with_body request")
  void shouldHandleSimpleGetWithBody() {
    ResponseHandler handler = new ResponseHandler();
    Response response = handler.handleSimpleGetWithBody();

    assertThat(response.getResponseBody(), equalTo("Hello world"));
  }

  @Test
  @DisplayName("should handle simple_get request")
  void shouldHandleSimpleGet() {
    ResponseHandler handler = new ResponseHandler();
    Response response = handler.handleSimpleGet();

    assertThat(response.getResponseBody(), equalTo(""));
  }

  @Test
  @DisplayName("should handle echo_body request")
  void shouldHandleEchoBody() {
    ResponseHandler handler = new ResponseHandler();
    String requestBody = "Request Body";
    Response response = handler.handleEchoBody(requestBody);

    assertThat(response.getResponseBody(), equalTo(requestBody));
  }

  @Test
  @DisplayName("should handle head_request request")
  void shouldhandleHead() {
    ResponseHandler handler = new ResponseHandler();
    Response response = handler.handleHead();

    assertThat(response.getResponseBody(), equalTo(""));
  }

  @Test
  @DisplayName("should handle non-head_request request")
  void shouldhandleNonHead() {
    ResponseHandler handler = new ResponseHandler();
    Response response = handler.handleNonHead();

    assertThat(response.getResponseBody(), equalTo("This body does not show up in a HEAD request"));
  }

  @Test
  @DisplayName("should handle unexpected request")
  void shouldhandleUnexpected() {
    ResponseHandler handler = new ResponseHandler();
    Response response = handler.handleUnexpected();

    assertThat(response.getResponseBody(), equalTo("unexpected request"));
  }
}

