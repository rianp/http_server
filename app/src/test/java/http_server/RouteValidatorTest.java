package http_server;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class RouteValidatorTest {

  @Test
  @DisplayName("should return true when a method exists for a given path")
  void should_ReturnTrue_When_AMethodExistsForAGivenPath() {
    RouteValidator routeValidator = new RouteValidator();
    String key = "/simple_get_with_body";
    String value = "GET";

    boolean result = routeValidator.hasMethod(key, value);

    assertThat(result).isTrue();
  }

  @Test
  @DisplayName("should return false when a method doesnt exist for a given path")
  void should_ReturnFalse_When_AMethodDoesntExistForAGivenPath() {
    RouteValidator routeValidator = new RouteValidator();
    String key = "/simple_get_with_body";
    String value = "POST";

    boolean result = routeValidator.hasMethod(key, value);

    assertThat(result).isFalse();
  }

  @Test
  @DisplayName("should return false when a given path doesn't exist")
  void should_ReturnFalse_WhenAGivenPathDoesNotExist() {
    RouteValidator routeValidator = new RouteValidator();
    String key = "/non_existing_key";
    String value = "GET";

    boolean result = routeValidator.hasMethod(key, value);

    assertThat(result).isFalse();
  }
}

