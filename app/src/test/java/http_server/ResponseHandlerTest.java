package http_server;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ResponseHandlerTest {

  @Test
  @DisplayName("should handle simple_get_with_body request")
  void shouldbuildSimpleGetWithBodyResponse() {
    ResponseHandler handler = new ResponseHandler();
    Response response = handler.buildSimpleGetWithBodyResponse();

    assertThat(response.getResponseBody(), equalTo("Hello world"));
  }

  @Test
  @DisplayName("should handle simple_get request")
  void shouldbuildSimpleGet() {
    ResponseHandler handler = new ResponseHandler();
    Response response = handler.buildSimpleGetResponse();

    assertThat(response.getResponseBody(), equalTo(""));
  }

  @Test
  @DisplayName("should handle echo_body request")
  void shouldbuildEchoBodyResponse() {
    ResponseHandler handler = new ResponseHandler();
    String requestBody = "Request Body";
    Response response = handler.buildEchoBodyResponse(requestBody);

    assertThat(response.getResponseBody(), equalTo(requestBody));
  }

  @Test
  @DisplayName("should handle head_request request")
  void shouldbuildHeadResponse() {
    ResponseHandler handler = new ResponseHandler();
    Response response = handler.buildHeadResponse();

    assertThat(response.getResponseBody(), equalTo(""));
  }

  @Test
  @DisplayName("should handle non-head_request request")
  void shouldbuildNonHeadResponse() {
    ResponseHandler handler = new ResponseHandler();
    Response response = handler.buildNonHeadResponse();

    assertThat(response.getResponseBody(), equalTo("This body does not show up in a HEAD request"));
  }

  @Test
  @DisplayName("should handle unexpected request")
  void shouldbuildUnexpectedResponse() {
    ResponseHandler handler = new ResponseHandler();
    Response response = handler.buildUnexpectedResponse();

    assertThat(response.getResponseBody(), equalTo("unexpected request"));
  }
}

