package http_server;

import http_server.routes.Response;
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

    Response expectedBody = new Response();
    expectedBody.setResponseBody("Hello world");
    Response expectedResponse = expectedBody;

    Response response = router.routeRequest(request);

    assertThat(response, samePropertyValuesAs(expectedResponse));
  }

  @Test
  @DisplayName("should return an expected response body when /head_request is made")
  void should_ReturnExpectedResponse_When_RequestingHeadRequestRoute() {
    RequestReader request = Mockito.mock(RequestReader.class);
    when(request.getPath()).thenReturn("/head_request");
    Response expectedBody = new Response();
    expectedBody.setResponseBody("This body does not show up in a HEAD request");
    Response expectedResponse = expectedBody;

    Response response = router.routeRequest(request);

    assertThat(response, samePropertyValuesAs(expectedResponse));
  }

  @Test
  @DisplayName("should not route when unknown path is requested")
  void should_NotRoute_When_RequestingUnknownPath() {
    RequestReader request = Mockito.mock(RequestReader.class);
    when(request.getPath()).thenReturn("/unknown_path");

    Response expectedBody = new Response();
    expectedBody.setResponseBody("unexpected request");
    Response expectedResponse = expectedBody;

    Response response = router.routeRequest(request);

    assertThat(response, samePropertyValuesAs(expectedResponse));
  }
}


