package http_server;

import http_server.routes.SimpleResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;

public class RouterTest {
  private Router router;

  @BeforeEach
  public void setUp() {
    router = new Router();
  }

  @Test
  @DisplayName("should route to simple body file when simple_get_with_body request is made")
  void should_RouteToSimpleBodyFile_When_RequestingSimpleGetWithBody() {
    RequestReader request = Mockito.mock(RequestReader.class);
    when(request.getPath()).thenReturn("/simple_get_with_body");
    SimpleResponse expectedBody = new SimpleResponse("Hello world");
    String expectedResponse = expectedBody.getResponse();

    String response = router.routeRequest(request);

    assertThat(response, is(equalTo(expectedResponse)));
  }

  @Test
  @DisplayName("should not route when unknown path is requested")
  void should_NotRoute_When_RequestingUnknownPath() {
    RequestReader request = Mockito.mock(RequestReader.class);
    when(request.getPath()).thenReturn("/unknown_path");
    String response = router.routeRequest(request);

    assertThat(response, emptyString());
  }
}


