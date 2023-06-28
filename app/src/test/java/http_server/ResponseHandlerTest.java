package http_server;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ResponseHandlerTest {

  @Test
  @DisplayName("should handle simple_get_with_body request")
  void should_BuildSimpleGetWithBodyResponse() {
    ResponseHandler handler = new ResponseHandler();
    Response response = handler.buildSimpleGetWithBodyResponse("200 OK");

    assertThat(response.getResponseBody()).isEqualTo("Hello world");
  }

  @Test
  @DisplayName("should handle simple_get request")
  void should_BuildSimpleGet() {
    ResponseHandler handler = new ResponseHandler();
    Response response = handler.buildSimpleGetResponse("200 OK");

    assertThat(response.getResponseBody()).isEqualTo("");
  }

  @Test
  @DisplayName("should handle echo_body request")
  void should_BuildEchoBodyResponse() {
    ResponseHandler handler = new ResponseHandler();
    String requestBody = "Request Body";
    Response response = handler.buildEchoBodyResponse(requestBody, "200 OK");

    assertThat(response.getResponseBody()).isEqualTo(requestBody);
  }

  @Test
  @DisplayName("should handle head_request request")
  void should_BuildHeadResponse() {
    ResponseHandler handler = new ResponseHandler();
    Response response = handler.buildHeadResponse("200 OK");

    assertThat(response.getResponseBody()).isEqualTo("");
  }

  @Test
  @DisplayName("should handle non-head_request request")
  void should_BuildNonHeadResponse() {
    ResponseHandler handler = new ResponseHandler();
    Response response = handler.buildNonHeadResponse("200 OK");

    assertThat(response.getResponseBody()).isEqualTo("This body does not show up in a HEAD request");
  }

  @Test
  @DisplayName("should handle unexpected request")
  void should_BuildUnexpectedResponse() {
    ResponseHandler handler = new ResponseHandler();
    Response response = handler.buildUnexpectedResponse("200 OK");

    assertThat(response.getResponseBody()).isEqualTo("unexpected request");
  }
}

