package http_server;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ResponseHandlerTest {

  @Test
  @DisplayName("should build response")
  void should_BuildResponse() {
    ResponseHandler handler = new ResponseHandler();
    String someResponseBody = "banana";
    String someResponseStatus = "999 OK";

    Response response = handler.buildResponse(someResponseStatus, someResponseBody);

    assertThat(response.getResponseBody()).isEqualTo(someResponseBody);
    assertThat(response.getResponseStatus()).isEqualTo(someResponseStatus);
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

