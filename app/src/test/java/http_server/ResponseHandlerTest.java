package http_server;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ResponseHandlerTest {

  @Test
  @DisplayName("should handle simple_get_with_body request")
  void shouldBuildSimpleGetWithBodyResponse() {
    ResponseHandler handler = new ResponseHandler();
    Response response = handler.buildSimpleGetWithBodyResponse();

    assertThat(response.getResponseBody()).isEqualTo("Hello world");
  }

  @Test
  @DisplayName("should handle simple_get request")
  void shouldBuildSimpleGet() {
    ResponseHandler handler = new ResponseHandler();
    Response response = handler.buildSimpleGetResponse();

    assertThat(response.getResponseBody()).isEqualTo("");
  }

  @Test
  @DisplayName("should handle echo_body request")
  void shouldBuildEchoBodyResponse() {
    ResponseHandler handler = new ResponseHandler();
    String requestBody = "Request Body";
    Response response = handler.buildEchoBodyResponse(requestBody);

    assertThat(response.getResponseBody()).isEqualTo(requestBody);
  }

  @Test
  @DisplayName("should handle head_request request")
  void shouldBuildHeadResponse() {
    ResponseHandler handler = new ResponseHandler();
    Response response = handler.buildHeadResponse();

    assertThat(response.getResponseBody()).isEqualTo("");
  }

  @Test
  @DisplayName("should handle non-head_request request")
  void shouldBuildNonHeadResponse() {
    ResponseHandler handler = new ResponseHandler();
    Response response = handler.buildNonHeadResponse();

    assertThat(response.getResponseBody()).isEqualTo("This body does not show up in a HEAD request");
  }

  @Test
  @DisplayName("should handle unexpected request")
  void shouldBuildUnexpectedResponse() {
    ResponseHandler handler = new ResponseHandler();
    Response response = handler.buildUnexpectedResponse();

    assertThat(response.getResponseBody()).isEqualTo("unexpected request");
  }
}


