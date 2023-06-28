package http_server;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ResponseTest {

  private Response response;

  @BeforeEach
  public void setup() {
    response = new Response();
  }

  @Test
  public void defaultConstructor_ShouldInitializeEmptyResponse() {
    assertThat(response.getResponseHeaders()).isEmpty();
    assertThat(response.getResponseBody()).isEmpty();
  }

  @Test
  public void setResponseHeaders_ShouldAddHeadersToResponse() {
    response.setResponseHeaders("Content-Type", "application/json");
    response.setResponseHeaders("Cache-Control", "no-cache");

    Map<String, String> headers = response.getResponseHeaders();

    assertThat(headers).containsEntry("Content-Type", "application/json");
    assertThat(headers).containsEntry("Cache-Control", "no-cache");
  }

  @Test
  public void setResponseBody_ShouldSetBodyInResponse() {
    String body = "This is the response body";
    response.setResponseBody(body);

    assertThat(response.getResponseBody()).isEqualTo(body);
  }

  @Test
  public void setResponseBody_ShouldOverridePreviousBody() {
    response.setResponseBody("Initial body");
    response.setResponseBody("Updated body");

    assertThat(response.getResponseBody()).isEqualTo("Updated body");
  }
}

