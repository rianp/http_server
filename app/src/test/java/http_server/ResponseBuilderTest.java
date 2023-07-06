package http_server;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.File;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class ResponseBuilderTest {
  private ResponseBuilder responseBuilder;

  @Mock
  private FileCreator fileCreatorMock;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
    ResponseHandler requestHandler = new ResponseHandler();
    RouteValidator routeValidator = new RouteValidator();
    responseBuilder = new ResponseBuilder(requestHandler, routeValidator, fileCreatorMock);
  }

  @Test
  @DisplayName("should return an expected response body when simple_get_with_body request is made")
  void should_RouteToSimpleBodyFile_When_RequestingSimpleGetWithBody() throws IOException {
    RequestReader request = mock(RequestReader.class);
    when(request.getPath()).thenReturn("/simple_get_with_body");
    when(request.getMethod()).thenReturn("GET");

    Response expectedResult = new Response();
    expectedResult.setResponseBody("Hello world");

    Response response = responseBuilder.buildResponse(request);

    assertThat(response.getResponseBody()).isEqualTo(expectedResult.getResponseBody());
    assertThat(response.getResponseHeaders()).isEqualTo(expectedResult.getResponseHeaders());
  }

  @Test
  @DisplayName("should return an expected response body when /head_request is made")
  void should_ReturnExpectedResponse_When_RequestingHeadRequestRoute() throws IOException {
    RequestReader request = mock(RequestReader.class);
    when(request.getPath()).thenReturn("/head_request");
    when(request.getMethod()).thenReturn("GET");

    Response expectedResult = new Response();
    expectedResult.setResponseBody("This body does not show up in a HEAD request");

    Response response = responseBuilder.buildResponse(request);

    assertThat(response.getResponseBody()).isEqualTo(expectedResult.getResponseBody());
    assertThat(response.getResponseHeaders()).isEqualTo(expectedResult.getResponseHeaders());
  }

  @Test
  @DisplayName("should return an expected response body when /method_options is made")
  void should_ReturnExpectedResponse_When_RequestingOptionsRequestRoute() throws IOException {
    RequestReader request = mock(RequestReader.class);
    when(request.getPath()).thenReturn("/method_options");
    when(request.getMethod()).thenReturn("OPTIONS");

    Response expectedResult = new Response();
    expectedResult.setResponseHeaders("allow", "GET, HEAD, OPTIONS");

    Response response = responseBuilder.buildResponse(request);

    assertThat(response.getResponseBody()).isEqualTo(expectedResult.getResponseBody());
    assertThat(response.getResponseHeaders()).isEqualTo(expectedResult.getResponseHeaders());
  }

  @Test
  @DisplayName("should return an expected response body when /method_options2 is made")
  void should_ReturnExpectedResponse_When_RequestingOptions2RequestRoute() throws IOException {
    RequestReader request = mock(RequestReader.class);
    when(request.getPath()).thenReturn("/method_options2");
    when(request.getMethod()).thenReturn("OPTIONS");

    Response expectedResult = new Response();
    expectedResult.setResponseHeaders("allow", "GET, HEAD, OPTIONS, PUT, POST");

    Response response = responseBuilder.buildResponse(request);

    assertThat(response.getResponseBody()).isEqualTo(expectedResult.getResponseBody());
    assertThat(response.getResponseHeaders()).isEqualTo(expectedResult.getResponseHeaders());
  }

  @Test
  @DisplayName("should return an unexpected request message when an unknown path is requested")
  void should_NotRoute_When_RequestingUnknownPath() throws IOException {
    RequestReader request = mock(RequestReader.class);
    when(request.getPath()).thenReturn("/unknown_path");

    Response expectedResult = new Response();
    expectedResult.setResponseBody("unexpected request");

    Response response = responseBuilder.buildResponse(request);

    assertThat(response.getResponseBody()).isEqualTo(expectedResult.getResponseBody());
    assertThat(response.getResponseHeaders()).isEqualTo(expectedResult.getResponseHeaders());
  }

  @Test
  @DisplayName("should return a successful response with status code 201 when a POST request is made to /todo with a non-null body")
  public void should_ReturnResponseWithStatusCode201_For_TodoPathWithNonNullBody() throws IOException {
    String path = "/todo";
    String method = "POST";
    String requestBody = "{\"task\":\"a new task\"}";

    RequestReader request = mock(RequestReader.class);
    when(request.getPath()).thenReturn(path);
    when(request.getMethod()).thenReturn(method);
    when(request.getBody()).thenReturn(requestBody);

    doNothing().when(fileCreatorMock).createFile(anyString());

    Response expectedResponse = new Response();
    expectedResponse.setResponseStatus("201 Created");
    expectedResponse.setResponseHeaders("Content-Type", "application/json;charset=utf-8");
    expectedResponse.setResponseBody(requestBody);

    Response response = responseBuilder.buildResponse(request);

    assertThat(response.getResponseStatus()).isEqualTo(expectedResponse.getResponseStatus());
    assertThat(response.getResponseBody()).isEqualTo(expectedResponse.getResponseBody());
    assertThat(response.getResponseHeaders()).containsEntry("Content-Type", "application/json;charset=utf-8");
  }
}






