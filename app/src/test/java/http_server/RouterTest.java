package http_server;

import http_server.routes.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;
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

    Response expectedResult = new Response();
    expectedResult.setResponseBody("Hello world");

    Response response = router.routeRequest(request);

    assertThat(response).isEqualToComparingFieldByFieldRecursively(expectedResult);
  }

  @Test
  @DisplayName("should return an expected response body when /head_request is made")
  void should_ReturnExpectedResponse_When_RequestingHeadRequestRoute() {
    RequestReader request = Mockito.mock(RequestReader.class);
    when(request.getPath()).thenReturn("/head_request");
    Response expectedResult = new Response();
    expectedResult.setResponseBody("This body does not show up in a HEAD request");

    Response response = router.routeRequest(request);

    assertThat(response).isEqualToComparingFieldByFieldRecursively(expectedResult);
  }

  @Test
  @DisplayName("should return an expected response body when /method_options is made")
  void should_ReturnExpectedResponse_When_RequestingOptionsRequestRoute() {
    RequestReader request = Mockito.mock(RequestReader.class);
    when(request.getPath()).thenReturn("/method_options");
    when(request.getMethod()).thenReturn("OPTIONS");
    Response expectedResult = new Response();
    expectedResult.setResponseHeaders("allow", "GET, HEAD, OPTIONS");

    Response response = router.routeRequest(request);

    assertThat(response).isEqualToComparingFieldByFieldRecursively(expectedResult);
  }

  @Test
  @DisplayName("should not route when unknown path is requested")
  void should_NotRoute_When_RequestingUnknownPath() {
    RequestReader request = Mockito.mock(RequestReader.class);
    when(request.getPath()).thenReturn("/unknown_path");

    Response expectedResult = new Response();
    expectedResult.setResponseBody("unexpected request");

    Response response = router.routeRequest(request);

    assertThat(response).isEqualToComparingFieldByFieldRecursively(expectedResult);
  }
}



