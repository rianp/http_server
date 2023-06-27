package http_server;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ResponseTest {

  private Response response;

  @BeforeEach
  public void setup() {
    response = new Response();
  }

  @Test
  public void defaultConstructor_ShouldInitializeEmptyResponse() {
    assertThat(response.getResponseHeaders(), anEmptyMap());
    assertThat(response.getResponseBody(), isEmptyString());
  }

  @Test
  public void setResponseHeaders_ShouldAddHeadersToResponse() {
    response.setResponseHeaders("Content-Type", "application/json");
    response.setResponseHeaders("Cache-Control", "no-cache");

    Map<String, String> headers = response.getResponseHeaders();

    assertThat(headers, hasEntry("Content-Type", "application/json"));
    assertThat(headers, hasEntry("Cache-Control", "no-cache"));
  }

  @Test
  public void setResponseBody_ShouldSetBodyInResponse() {
    String body = "This is the response body";
    response.setResponseBody(body);

    assertThat(response.getResponseBody(), equalTo(body));
  }

  @Test
  public void setResponseBody_ShouldOverridePreviousBody() {
    response.setResponseBody("Initial body");
    response.setResponseBody("Updated body");

    assertThat(response.getResponseBody(), equalTo("Updated body"));
  }
}

